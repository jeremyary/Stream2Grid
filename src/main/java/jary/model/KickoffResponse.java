package jary.model;

/**
 * @author jary
 * @since Nov/11/2014
 */
public class KickoffResponse {

    private Integer statusCount;

    private Integer deleteCount;

    private Integer hashtagCount;

    private Integer userCount;

    private Integer trendCount;

    public KickoffResponse() {
    }

    public Integer getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
    }

    public Integer getHashtagCount() {
        return hashtagCount;
    }

    public void setHashtagCount(Integer hashtagCount) {
        this.hashtagCount = hashtagCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getTrendCount() {
        return trendCount;
    }

    public void setTrendCount(Integer trendCount) {
        this.trendCount = trendCount;
    }

    public Integer getDeleteCount() {
        return deleteCount;
    }

    public void setDeleteCount(Integer deleteCount) {
        this.deleteCount = deleteCount;
    }
}
