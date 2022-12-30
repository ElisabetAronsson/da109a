function fetchEvents(){
        let params = (new URL(location.href)).searchParams;
        let artistName = params.get('artist').replace("%20", "_")

        const events= JSON.parse(window.localStorage.getItem('eventAndArtists')).items
        
        $('#artistName').text(artistName);

        listEvent=$("#eventsList");

        if(events.name==artistName){
            for(i=0; i<events.length; i++){
                html="<div id='event_" + i + "'> <a href='eventAndArtist.html?artist=" + artistName + "?event=" + "'> <h4>" + events[i]["short_title"] + "</h4> <p>" +events[i]["city"] + "</p> </a></div>"
                listEvent.append(html);
        }};    
    }


function fetchInfo(){
    let params = (new URL(location.href)).searchParams;
    let artistName = params.get('artist').replace("%20", "_")
    let eventName = params.get("event").replace("%20", "_")

    location.href = "eventAndArtist.html";

    $.ajax({
        url: "http://localhost:8888/api/v1/artists/ "+ artistName + "/concerts/" + eventName,
        headers:{"Accept": "application/json"}
    })
    .done(function(data){

        $("#artistName").text(artistName);
        $("#artistImage").html("<img src='" + data["image"] + "'>");// key:n kan behöva ändras
        $("#artistInfo").text(data["artistInfo"]);// key:n kan behöva ändras

        $("#eventName").text(eventName);
        $("#eventWhare").text(data["eventWhare"]);// key:n kan behöva ändras
        $("#eventDateAndTime").text(data["eventDateAndTime"]);// key:n kan behöva ändras
        $("#eventScen").text(data["eventScen"]);// key:n kan behöva ändras
    });
    
}

function fetchArtists(){
    const token= window.localStorage.getItem('access_token')
    $.ajax({
        url: 'http://localhost:8888/api/v1/artists/concerts',
        headers: {"Accept": "application/json", 'Authorization': 'Bearer '+ token}
    })
    .done(function(data){
        listArtists=$("#artistsList");

        const artists=JSON.parse(data).items
        console.log(artists)
    
        for(i=0; i<artists.length; i++){
            html="<div id='artist_" + i + "'> <a href='listEvent.html?artist=" + artists[i]['name'] + "'> <img class='artistListImage' src='" + artists[i]["images"][0]["url"] + "'> <h4>" + artists[i]["name"] + "</h4> </a></div>"
            listArtists.append(html);
        }
        const artistsList = JSON.stringify(artists)
        window.localStorage.setItem('eventAndArtists',artistsList) 
    });
    
}
