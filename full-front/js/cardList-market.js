
function onProcess(id) {
    console.log(id)
}


function setCardlist(cardList) {
    setTemplate("#cardlist", "#tableContent", cardList)
}


function setUserInfo(user) {
    console.log(user)
    document.getElementById("userNameId").innerHTML = user.username;
    document.getElementById("walletId").innerHTML = user.wallet;
}

fetchUserCards().then(r => setCardlist(r))
fetchUserInfo().then(r => setUserInfo(r))






