





function setCardlist(cardList) {
    setTemplate("#cardlist", "#tableContent", cardList)
}


function setUserInfo(user) {
    console.log(user)
    document.getElementById("userNameId").innerHTML = user.surname;
    document.getElementById("walletId").innerHTML = user.wallet;
}

fetchUserCards().then(r => setCardlist(r))
fetchUserInfo().then(r => setUserInfo(r))






