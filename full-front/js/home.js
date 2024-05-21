// let user ={"username":"John","wallet":5000}
// let user;

function setUserInfo(user) {
    document.getElementById("userNameId").innerHTML = user.surname;
    document.getElementById("walletId").innerHTML = user.wallet;
}

async function fetchUserInfo() {
    return fetch('http://localhost:8080/api/v1/users/profile', {
        credentials: 'include',
    }) // fetch logged user info
        .then(response => response.json())
        .then(data => {
            user = data;
            console.log(user)
            return data
        });
}

// setUserInfo()
fetchUserInfo().then(r => setUserInfo(r))










