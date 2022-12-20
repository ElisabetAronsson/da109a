function fetchInfo(details){
    return function(){
        location.href = "eventAndArtist.html";
        
        $.ajax({
            url:details,
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
};

$(document).ready(function(){
    $.ajax({
        url: 'http://localhost:8888/v1/api/artists',
        headers: {"Accept": "application/json"}
    })
    .done(function(data){
        listArtists=$("#artists");

        for(i=0; i<data.length; i++){
            html="<li id='artist_" + i + "'>" + data[i]["name"] + "</li>" // key:n kan behöva ändras
            listEventAndArtists.append(html);

            $("#artist_" + i).click(fetchInfo(data[i]["details"]));
        }
    });
});