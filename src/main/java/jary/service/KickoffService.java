package jary.service;

import jary.amqp.AmqpMediator;
import jary.annotation.Slf4j;
import jary.datagrid.DataGridMediator;
import jary.model.KickoffResponse;
import jary.twitter.listener.MessageType;
import jary.twitter.listener.StreamStatusListener;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import java.util.Map;

/**
 * @author jary
 * @since Nov/11/2014
 */
@Component
public class KickoffService {

    @Slf4j
    private Logger log;

    @Autowired
    protected DataGridMediator dataGridMediator;

    @Autowired
    protected AmqpMediator amqpMediator;

    @Autowired
    protected StreamStatusListener streamStatusListener;

    public static final Integer OPEN_INTERVAL = 10000;

    public static final Integer CLEANUP_INTERVAL = 25000;

    public KickoffResponse getResponse() {

        amqpMediator.flush();

        TwitterStream stream = new TwitterStreamFactory().getInstance();

        KickoffResponse response = new KickoffResponse();

        try {
            // put our listener on stream that will drive action
            stream.addListener(streamStatusListener);

            // start taking in data from stream
            log.info("starting sample stream");
            stream.sample();

            // allow some time for collection on stream thread
            Thread.sleep(OPEN_INTERVAL);

            log.info("closing sample stream. Processing queues...");
            stream.shutdown();

            // give the queues time to catch up, large amounts can cause some lag on the async
            // queue-receiving threads adding to our maps
            Thread.sleep(CLEANUP_INTERVAL);

            log.info("all done, gathering result stats...");
            // make sure we actually got data into our aggregator maps
            assert dataGridMediator.getHashtagMap().size() > 0;

            // user map, too
            assert dataGridMediator.getUserMap().size() > 0;

            for (Map.Entry<MessageType, Integer> entry : streamStatusListener.getMessageCounts().entrySet()) {
                if (entry.getKey().toString().equalsIgnoreCase("status")) {
                    response.setStatusCount(entry.getValue());

                } else if (entry.getKey().toString().equalsIgnoreCase("delete")) {
                    response.setDeleteCount(entry.getValue());
                }
            }

            response.setHashtagCount(dataGridMediator.getHashtagMap().size());
            response.setUserCount(dataGridMediator.getUserMap().size());
            response.setTrendCount(dataGridMediator.getTrendMap().size());

        } catch (Exception exception) {
            log.error(exception.getMessage());
        } finally {
            amqpMediator.flush();
            dataGridMediator.flush();
            streamStatusListener.getMessageCounts().clear();
        }
        return response;
    }
}
