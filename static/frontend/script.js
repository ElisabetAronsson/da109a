let client = new JSO({
	response_type:"code",
    client_id :'ee0bdca0269146eaa8345787f079f9d2',
    scope :'user-follow-read',
    rederect_uri : 'http://localhost:5000/callback',
    scopes: { request: ["https://accounts.spotify.com/authorize?"]}
})

client.callback()

client.getToken(opts)
    .then((token) => {
    	console.log("I got the token: ", token)
    })

