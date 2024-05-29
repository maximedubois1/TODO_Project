let guser;

function onProcess(id){
    let roomName = document.getElementsByName('room-name');
    let betName = document.getElementsByName('bet');
    

    data = {"name":roomName[0].value, "bet":betName[0].value, "ownerID":guser.id}
    console.log(data);
    createRoom(data);
}

function onProcessGame(obj){
    let room_id =obj.firstElementChild.innerHTML;
    room_id = room_id.replace(" ", "");
    console.log(room_id);
    joinRoom(room_id, guser.id).then(() => document.location.href="./cardList-select-card.html?room_id="+room_id);
    // document.location.href="./cardList-select-card.html?room_id="+room_id;
}


function setUserInfo(user){
    guser = user;
    document.getElementById("userNameId").innerHTML= user.surname;
    document.getElementById("walletId").innerHTML= user.wallet;
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const userCardId = urlParams.get('id');

fetchUserInfo().then(r => setUserInfo(r))
fetchRooms().then(r => setTemplateRoom("#roomlist","#roomContent",r))







