function getTokens() {
  const params= new URLSearchParams({
      response_type:"code",
      client_id :'ee0bdca0269146eaa8345787f079f9d2',
      scope :'user-follow-read',
      redirect_uri : 'http://localhost:5000/callback',
  });

  .ajax({
    method: "GET",
    url: "https:ccounts.spotify.com/authorize?"+ params.toString(),
    headers: {"Accept": "application/json"}
})
.done(function(result){
    console.log(JSON.stringify(result));
});
}




//const url = "https://accounts.spotify.com/authorize?"+ params.toString();
//location.href = url;

