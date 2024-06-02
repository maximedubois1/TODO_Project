const Auth_PORT = 8080;
const Card_PORT = 8080;
const Room_PORT = 8080;
const URL_BASE = `http://localhost`;

let userid;

async function fetchCards() {
    await fetchUserInfo();
    return fetch(`${URL_BASE}:${Card_PORT}/api/v1/cards`, {
        credentials: 'include',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        })
}

async function fetchUserCards() {
    await fetchUserInfo();
    return fetch(`${URL_BASE}:${Card_PORT}/api/v1/cards/user/${userid}`, {
        credentials: 'include',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        })
}

async function fetchCardById(id) {
    return fetch(`${URL_BASE}:${Card_PORT}/api/v1/cards/${id}`, {
        credentials: 'include',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        })
}


async function generateUserCards(id) {
    return fetch(`${URL_BASE}:${Card_PORT}/api/v1/cards/generate-for/${id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    }).then(response => {
        if (response.ok) {
            // fetchUserCards().then(r => setCardlist(r))
            window.location.reload()
            return response.json()
        } else {
            alert("Error generating cards")
        }
    })

}

async function fetchUserInfo() {
    return fetch(`${URL_BASE}:${Auth_PORT}/api/v1/users/profile`, {
        credentials: 'include',
    }) // fetch logged user info
        .then(response => response.json())
        .then(data => {
            console.log(data)
            userid = data.id
            return data
        });
}

async function fetchUserInfoByID(id) {
    return fetch(`${URL_BASE}:${Auth_PORT}/api/v1/users/${id}`, {
        credentials: 'include',
    }) // fetch logged user info
        .then(response => response.json())
        .then(data => {
            console.log(data)
            userid = data.id
            return data
        });
}

async function createUser(user) {
    return fetch(`${URL_BASE}:${Auth_PORT}/api/v1/auth/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify(user),
    })
        .then(response => response.json())
        .then(async data => {
            console.log('Success:', data);
            userid = data;
            // await generateUserCards(data);
            window.location.href = "loginUser.html";
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

async function authUser(data) {
    deleteCookie('auth_jwt');
    return fetch(`${URL_BASE}:${Auth_PORT}/api/v1/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify(data),
    })
        // .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            window.location.href = "home.html";
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

async function sellCard(id) {
    console.log(id)
    return fetch(`${URL_BASE}:${Card_PORT}/api/v1/cards/sell/${id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include',
    }).then(response => {
        if (response.ok) {
            // fetchUserCards().then(r => setCardlist(r))
            window.location.reload()
            return response.json()
        } else {
            alert("Error selling card")
        }
    })
}

async function buyCard(id) {
    console.log(id)
    return fetch(`${URL_BASE}:${Card_PORT}/api/v1/cards/buy/${id}/to-user/${userid}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    }).then(response => {
        if (response.ok) {
            // fetchAvailableCards().then(r => setCardlist(r))
            window.location.reload()
            return response.json()
        } else {
            alert("Error buy card")
        }
    })
}

async function fetchAvailableCards() {
    return fetch(`${URL_BASE}:${Card_PORT}/api/v1/cards/market`, {
        credentials: 'include',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        })
}

async function logout() {
    return fetch(`${URL_BASE}:${Auth_PORT}/api/v1/auth/logout`, {
        method: 'GET',
        credentials: 'include',
    }).then(response => {
        if (response.ok) {
            window.location.href = "loginUser.html";
        } else {
            alert("Error logout")
        }
    })
}

async function fetchRooms(){
    return fetch(`${URL_BASE}:${Room_PORT}/api/v1/rooms`, {
        method: 'GET',
        credentials: 'include',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        })
}

async function createRoom(room){
    return fetch(`${URL_BASE}:${Room_PORT}/api/v1/rooms/new`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify(room),
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        })
}


async function setPlayerCard(roomName, userID, cardID){
    return fetch(`${URL_BASE}:${Room_PORT}/api/v1/rooms/setcard/${roomName}/${userID}/${cardID}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        // .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        })
}

async function joinRoom(roomName, userID){
    return fetch(`${URL_BASE}:${Room_PORT}/api/v1/rooms/join/${roomName}/${userID}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    })
        // .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        })
}

async function fetchRoomInfo(roomName){
    return fetch(`${URL_BASE}:${Room_PORT}/api/v1/rooms/${roomName}`, {
        method: 'GET',
        credentials: 'include',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        })
}

async function fight(fightDTO){
    return fetch(`${URL_BASE}:${Card_PORT}/api/v1/cards/fight`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify(fightDTO),
    })
        .then(response => response.text())
        .then(data => {
            console.log("fight"  + data);
            return data;
        })
}
