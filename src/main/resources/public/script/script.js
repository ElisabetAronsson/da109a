function fetchEvents(){
        let params = (new URL(location.href)).searchParams;
        let artistName = params.get('artist').replace("%20", "_")
        console.log(artistName)

        $.ajax({
            url:"http://localhost:8888/api/v1/artists/"+ artistName +"/concerts",
            headers:{"Accept": "application/json"}
        })
        .done(function(data){
            console.log("hej")
            listEvent=$("#eventsList");
            const events=JSON.parse(data).events.items

            $("#artistName").text(artistName);

            for(i=0; i<data.length; i++){
                html="<div id='event_" + i + "'> <a href='eventAndArtist.html?artist=" + artistName + "?event=" + "'> <h4>" + events[i]["name"] + "</h4> </a></div>" // key:n kan behöva ändras
                listEvent.append(html);
            }
        });
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
        url: 'http://localhost:8888/api/v1/artists',
        headers: {"Accept": "application/json", 'Authorization': 'Bearer '+ token}
    })
    .done(function(data){
        listArtists=$("#artistsList");

        const artists=JSON.parse(data).artists.items
        console.log(artists)
    
        for(i=0; i<artists.length; i++){
            html="<div id='artist_" + i + "'> <a href='listEvent.html?artist=" + artists[i]['name'] + "'> <img class='artistListImage' src='" + artists[i]["images"][0]["url"] + "'> <h4>" + artists[i]["name"] + "</h4> </a></div>" // key:n kan behöva ändras
            listArtists.append(html);
        }
    });
}



