const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const roomName = urlParams.get('room_id');
let userID;

async function onProcess(id) {
    console.log(id)
    setPlayerCard(roomName, userID, id).then(() => window.location.replace("./cardList-play.html?roomid=" + roomName));
    // window.location.replace("./cardList-play.html?roomid=" + roomName)
}

function setCardlist(cardList) {

    setTemplate("#cardlist", "#tableContent", cardList)
}

function setUserInfo(user) {
    userID = user.id;
    document.getElementById("userNameId").innerHTML = user.surname;
    document.getElementById("walletId").innerHTML = user.wallet;
}

fetchUserInfo().then(r => setUserInfo(r))
fetchUserCards().then(r => setCardlist(r))

