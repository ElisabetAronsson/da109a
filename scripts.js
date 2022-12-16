function getTokens(){
    var client_id = 'ee0bdca0269146eaa8345787f079f9d2';
    var redirect_uri = 'http://localhost:8888/callback';
    
    var app = express();
    
    app.get('/login', function(req, res) {
    
      var state = generateRandomString(16);
      var scope = 'user-follow-read';
    
      res.redirect('https://accounts.spotify.com/authorize?' +
        querystring.stringify({
          response_type: 'code',
          client_id: client_id,
          scope: scope,
          redirect_uri: redirect_uri,
          state: state
        }));
    });
}



fetch('https://accounts.spotify.com/api/token', {
    method: 'POST',
    headers: {
        'Authorization': 'Basic credentials',
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    },
    body: 'grant_type=client_credentials'
})
.then(response => response.json())
.then(data => {
    console.log(data);
});