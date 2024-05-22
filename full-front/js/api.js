const URL = 'http://localhost:8080/api/v1';

async function fetchUserCards() {
    return fetch('http://localhost:8080/api/v1/cards/mine', {
        credentials: 'include',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        })
}

async function fetchUserInfo() {
    return fetch('http://localhost:8080/api/v1/users/profile', {
        credentials: 'include',
    }) // fetch logged user info
        .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        });
}

async function createUser(user) {
    fetch('http://localhost:8080/api/v1/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify(user),
    })
        // .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            window.location.href = "login.html";
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

async function authUser(data) {
    fetch('http://localhost:8080/api/v1/auth/login', {
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
    return fetch(`${URL}/cards/${id}/sell`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include',
    }).then(response => {
        if (response.ok) {
            fetchUserCards().then(r => setCardlist(r))
            window.location.reload()
            return response.json()
        } else {
            alert("Error selling card")
        }
    })
}

async function buyCard(id) {
    console.log(id)
    return fetch(`${URL}/cards/${id}/buy`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    }).then(response => {
        if (response.ok) {
            fetchAvailableCards().then(r => setCardlist(r))
            window.location.reload()
            return response.json()
        } else {
            alert("Error selling card")
        }
    })
}

async function fetchAvailableCards() {
    return fetch(`${URL}/cards/market`, {
        credentials: 'include',
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
            return data
        })
}
