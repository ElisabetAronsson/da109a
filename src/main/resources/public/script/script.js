function fetchEvents(){
// Funktionen hämtar en lista med event kopplat till en specifik artist från backend.

    let params = (new URL(location.href)).searchParams;
    let artistName = params.get('artist').replace("%20", " ");
    let artistID =params.get('artistID');

    $.ajax({
        url: 'http://localhost:8888/api/v1/artists/' + artistName + '/concerts',
        headers: {"Accept": "application/json"}
    })
    .done(function(events){
    
        $('#artistName').text(artistName);
        listEvent=$("#eventsList");

        for (i=0; i<events.length; i++){
            let date=events[i]["datetime_utc"].split("T")
            let concertID=events[i]["id"]
            html="<div id='event_" + i + "'> <a href='eventAndArtist.html?artist=" + artistName + "&artistID=" + artistID + "&event=" + concertID + "'> <h4>" + events[i]["short_title"] + "</h4> <p>" + events[i]["venue"]["city"] + "</p> <p>" + date[0] + "</p></a></div>"
            listEvent.append(html);
        }
    });
}

function fetchInfo(){
// Funktionen hämtar ett specifikt event från en specifik artist från backen.

    let params = (new URL(location.href)).searchParams;

    let artistName = params.get('artist').replace("%20", " ");
    let concertID = params.get('event');
    let artistID = params.get('artistID');

    const token= window.localStorage.getItem('access_token')

    $.ajax({
        url: 'http://localhost:8888/api/v1/artists/' + artistID + '/concerts/' + concertID,
        headers: {"Accept": "application/json", 'Authorization': 'Bearer '+ token}
    })
    .done(function(concertAndArtist){
       console.log(concertAndArtist)
       
       const date = concertAndArtist["events"][0]["datetime_utc"].split("T")

       $("#artistName").text(artistName);
       $("#artistImage").html("<img id='eventArtistImage' src='" + concertAndArtist["images"][0]["url"] + "'>");
       $("#artistInfo").html(concertAndArtist["extractWrapper"]["extract_html"])

       $("#eventName").text(concertAndArtist["events"][0]["short_title"]);
       $("#eventWhere").text(concertAndArtist["events"][0]["venue"]["city"]);
       $("#eventDate").text(date[0]);
       $("#eventTime").text(date[1])
       $("#eventLocation").text(concertAndArtist["events"][0]["venue"]["name"]);
       $("#eventLink").html("<a href=' " + concertAndArtist["events"][0]["url"] +"'>"+ concertAndArtist["events"][0]["short_title"] +"</a>") 
    }); 
}

function fetchArtists(){
// Funktionen hämtar en lista med alla följda artister från en användare

    const token= window.localStorage.getItem('access_token')

    $.ajax({
        url: 'http://localhost:8888/api/v1/artists',
        headers: {"Accept": "application/json", 'Authorization': 'Bearer '+ token}
    })
    .done(function(data){
        listArtists=$("#artistsList");

        const artists=JSON.parse(data).items

        for(i=0; i<artists.length; i++){
            html="<div id='artist_" + i + "'> <a href='listEvent.html?artist=" + artists[i]['name']+ "&artistID=" + artists[i]["id"]+"'> <img class='artistListImage' src='" + artists[i]["images"][0]["url"] + "'> <h4>" + artists[i]["name"] + "</h4> </a></div>"
            listArtists.append(html);
        }
        const artistsList = JSON.stringify(artists)
        window.localStorage.setItem('eventAndArtists',artistsList) 
    });
}
