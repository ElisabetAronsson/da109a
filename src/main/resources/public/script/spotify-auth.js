function getToken(){
    let params = (new URL(location.href.replace('#','?'))).searchParams;
    let token = params.get('access_token');
    let hej = window.localStorage.setItem('access_token',token)

    console.log(params)
    console.log(token)
    console.log(hej)
    
    }

function login(){
    let scope = 'user-follow-read';
    let clientID = '7a6d44b7b4c7435f9ed4340b9e613395';
    let redirect_uir = 'http://localhost:8888/callback';
    let response_type = 'token';
    
    //window.open(`https://accounts.spotify.com/authorize?client_id=${clientID}&redirect_uri=${redirect_uir}&response_type=${response_type}&scope=${scope}`,'_self')
    window.location.href = `https://accounts.spotify.com/authorize?client_id=${clientID}&redirect_uri=${redirect_uir}&response_type=${response_type}&scope=${scope}`;
 
}




