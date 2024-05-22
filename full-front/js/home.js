
function setUserInfo(user) {
    document.getElementById("userNameId").innerHTML = user.surname;
    document.getElementById("walletId").innerHTML = user.wallet;
}

fetchUserInfo().then(r => setUserInfo(r))










