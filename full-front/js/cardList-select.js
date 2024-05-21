
function onProcess(id) {
    console.log(id)
    window.location.replace("/cardList-play.html?id=" + id)
}

function setCardlist(cardList) {

    setTemplate("#cardlist", "#tableContent", cardList)
}

function setUserInfo(user) {
    document.getElementById("userNameId").innerHTML = user.username;
    document.getElementById("walletId").innerHTML = user.wallet;
}

fetchUserInfo().then(r => setUserInfo(r))
fetchUserCards().then(r => setCardlist(r))
