$(function () {

    /* =========   BEGIN ONLOAD  =========*/

    var activeCluster = {
            particles: [],
            lastSelectedGroup: 0
        },
        $results = $('#results');

    initialize();

    function initialize() {

        $.getJSON( "/Stream2Grid/kickoff", function( data ) {

            $results.css("text-align", "left");
            $results.css("margin-top", "10px");
            var response = "<p>All Done!</p><p>"
                + "In the 10 seconds of streaming, we collected " + data.statusCount + " status updates from "
                + data.userCount + " users. There were " + data.deleteCount + " delete requests that we had to make sure "
                + "weren't related to anything we were holding. We counted " + data.hashtagCount + " hashtags, with "
                + data.trendCount + " of them trending.</p><a class='btn' href='#!/about'>read about the stack</a>";

            $results.html(response);
        });
    }

});


