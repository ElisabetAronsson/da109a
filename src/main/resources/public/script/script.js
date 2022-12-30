function fetchEvents(){
        let params = (new URL(location.href)).searchParams;
        let artistName = params.get('artist').replace("%20", "_")

        const artists= JSON.parse(window.localStorage.getItem('eventAndArtists'))
        
        $('#artistName').text(artistName);

        listEvent=$("#eventsList");

        
            for(i=0; i<artists.length; i++){

                if(artists[i]["name"]== artistName){

                    for(y=0; y<artists[i]["events"].length; y++){
                        let date = artists[i]["events"][y]["datetime_utc"].split("T")

                        const eventName =  artists[i]["events"][y]["short_title"]
    
                        html="<div id='event_" + i + "'> <a href='eventAndArtist.html?artist=" + artistName + "?event=" + eventName + "'> <h4>" + artists[i]["events"][y]["short_title"] + "</h4> <p>" + artists[i]["events"][y]["venue"]["city"] + "</p> <p>" + date[0] + "</p></a></div>"
                        listEvent.append(html);
                    }
                }};    
    }


function fetchInfo(){
    let params = (new URL(location.href)).searchParams;
    let artistName = params.get('artist').replace("%20", "_")
    let eventName = params.get("event").replace("%20", "_")

    location.href = "eventAndArtist.html";


    const artists= JSON.parse(window.localStorage.getItem('eventAndArtists'))
        
    listEvent=$("#eventsList");

    
        for(i=0; i<artists.length; i++){

            if(artists[i]["name"]== artistName){

                for(y=0; y<artists[i]["events"].length; y++){

                    if(artists[i]["events"][y]["short_title"] == eventName){

                        let date = artists[i]["events"][y]["datetime_utc"].split("T")

                        $("#artistName").text(artistName);
                        $("#artistImage").html("<img src='" + artists["image"] + "'>");
                        $("#artistInfo") //wikipedia info
                
                        $("#eventName").text(eventName);
                        $("#eventWhare").text(artists[i]["events"][y]["venue"]["city"]);// key:n kan behöva ändras
                        $("#eventDate").text(date[0]);
                        $("#eventTime").text(date[1])
                        $("#eventLocation").text(artists[i]["events"][y]["venue"]["name"]);// key:n kan behöva ändras

                    }
                }
            }};   

       
    
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
