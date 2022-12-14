function getSerchObject(){
    return function(){
        let searchObject={};
        searchObject.spotifyUserName=$("#searchMyEvent input[name=spotifyUserName]").val();
        searchObject.location=$("#searchMyEvent input[name=location").val();
    }
};

function fetchInfo(){


};

$(document).ready(function(){
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