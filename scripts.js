

function getTokens() {
  const params= new URLSearchParams({
      response_type:"code",
      client_id :'ee0bdca0269146eaa8345787f079f9d2',
      scope :'user-follow-read',
      redirect_uri : 'http://localhost:5000/callback',
  });

  const url = "https://accounts.spotify.com/authorize?"+ params.toString();
  location.href = url;

}


