function fetchInfo(details){
    return function(){
        $.ajax({
            url:details,
            headers:{"Accept": "application/json"}
        })
        .done(function(data){

            $("#artistName").text(data["artistName"]);
            $("#artistImage").html("<img src='" + data["image"] + "'>");
            $("#artistInfo").text(data["artistInfo"]);

            $("#eventName").text(data["eventName"]);
            $("#eventWhare").text(data["eventWhare"]);
            $("#eventDate").text(data["eventDate"]);
            $("#eventTime").text(data["eventTime"]);
            $("#eventScen").text(data["eventScen"]);
            $("#eventOtherInfo").text(data["eventOtherInfo"]);
            $("#eventLink").html("<a href='" + data["eventLink"] + "'>" + "Länk" + "</a>")

        });
    }
};

$(document).ready(function(){
    $.ajax({
        url: 'http://localhost:8888/v1/api/artists',
        headers: {"Accept": "application/json"}
    })
    .done(function(data){
        listArtists=$("#artists");

        for(i=0; i<data.length; i++){
            html="<li id='artist_" + i + "'>" + data[i]["name"] + "</li>"
            listEventAndArtists.append(html);

            $("#artist_" + i).click(fetchInfo(data[i]["details"]));
        }
    });
});