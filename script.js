function getSerchObject(){
    return function(){
        let searchObject={};
        searchObject.spotifyUserName=$("#searchMyEvent input[name=spotifyUserName]").val();
        searchObject.location=$("#searchMyEvent input[name=location").val();
        
        $.ajax({
            method: "POST",
            url: 'http://localhost:5000',
            data: JSON.stringify(data),
            headers: {"Accept": "application/json"}
          })
          .done(function(result) {
          });
    }
};

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
    $("#searchButton").click(getSerchObject());

    $.ajax({
        url: 'http://localhost:5000',
        headers: {"Accept": "application/json"}
    })
    .done(function(data){
        list=$("$artists");

        for(i=0; i<data.length; i++){
            html="";
            html="<li id='artist_" + i + "'>" + data[i] ["name"] + "</li>"
            list.append(html);

            $("#artists_" + i).click(fetchInfo(data[i]["details"]));
        }
    });
});