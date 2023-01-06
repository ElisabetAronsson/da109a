async function getToken(){
    // Funktionen hämtar spotifys token och sparar denna lokalt i webbläsaren.
    let params = (new URL(location.href.replace('#','?'))).searchParams;
    let token = params.get('access_token');
    window.localStorage.setItem('access_token',token)   
    location.href = "listArtists.html";
}
    
function login(){
    // Funktionen hanterar inloggningen på spotifys konto. 
    let scope = 'user-follow-read';
    let clientID = '7a6d44b7b4c7435f9ed4340b9e613395';
    let redirect_uir = 'http://localhost:8888/callback';
    let response_type = 'token';
    
    window.location.href = `https://accounts.spotify.com/authorize?client_id=${clientID}&redirect_uri=${redirect_uir}&response_type=${response_type}&scope=${scope}`;
}
