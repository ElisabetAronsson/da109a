function fetchEvent(artistName){
    return function(){
        location.href = "listEvent.html";

        $.ajax({
            url:"http://localhost:8888/v1/api/artists/"+ artistName +"/concerts",
            headers:{"Accept": "application/json"}
        })
        .done(function(data){
            listEvent=$("#eventsList");
            $("#artistName").text(data["artistName"]); // key:n kan behöva ändras

            for(i=0; i<data.length; i++){
                html="<li id='event_" + i + "'>" + data[i]["eventName"] + "</li>" // key:n kan behöva ändras
                listEvent.append(html);
    
                $("#event_" + i).click(fetchInfo(artistName, data[i]["eventName"]));
            }
        });
    }
}

function fetchInfo(artistName, eventName){
    return function(){
        location.href = "eventAndArtist.html";

        $.ajax({
            url: "http://localhost:8888/api/v1/artists/ "+ artistName + "/concerts/" + eventName,
            headers:{"Accept": "application/json"}
        })
        .done(function(data){

            $("#artistName").text(data["artistName"]); // key:n kan behöva ändras
            $("#artistImage").html("<img src='" + data["image"] + "'>");// key:n kan behöva ändras
            $("#artistInfo").text(data["artistInfo"]);// key:n kan behöva ändras

            $("#eventName").text(data["eventName"]);// key:n kan behöva ändras
            $("#eventWhare").text(data["eventWhare"]);// key:n kan behöva ändras
            $("#eventDateAndTime").text(data["eventDateAndTime"]);// key:n kan behöva ändras
            $("#eventScen").text(data["eventScen"]);// key:n kan behöva ändras
        });
    }
}

$(document).ready(function(){
    $.ajax({
        url: 'http://localhost:8888/v1/api/artists',
        headers: {"Accept": "application/json"}
    })
    .done(function(data){
        listArtists=$("#artistsList");

        for(i=0; i<data.length; i++){
            html="<li id='artist_" + i + "'> <div> <img src='" + data[i]["artistImage"] + "'> <p>" + data[i]["artistName"] + "</p> </div> </li>" // key:n kan behöva ändras
            listArtists.append(html);

            $("#artist_" + i).click(fetchEvent(data[i]["artistName"]));
        }
    });
});



