let guser;
let groom;

function onProcess(id) {
    console.log(id);
    console.log("Play");
    let fightdto = {};
    fightdto.roomName = groom.roomName;
    fightdto.ownerID = groom.ownerID;
    fightdto.ownerCardID = groom.ownerCardID;
    fightdto.opponentID = groom.opponentID;
    fightdto.opponentCardID = groom.opponentCardID;
    fight(fightdto).then(r => alert(r.text()));
}


// private String roomName;
// private Long ownerID;
// private Long ownerCardID;
// private Long opponentID;
// private Long opponentCardID;



// function setGame(userCardId){
//     let cList=[]
//     cList.push(getCardById(userCardId,cardList))
//     setTemplate("#cardPlayer","#cardPlayerContent",cList)
//
//     let cList2=[]
//     let opponentId=getRandomInt(cardList.length)
//     cList2.push(getCardById(opponentId+1,cardList))
//
//     setTemplate("#opponentPlayer","#opponentContent",cList2)
//
// }

function setGame(room) {
    console.log(room);
    groom = room;
    let cardIDUser;
    let cardIDOpp;
    if (room.ownerID === guser.id) {
        cardIDUser = room.ownerCardID
        cardIDOpp = room.opponentCardID
    } else {
        cardIDUser = room.opponentCardID
        cardIDOpp = room.ownerCardID
    }

    let cList = []
    fetchCardById(cardIDUser).then(r => {
        cList.push(r)
        setTemplate("#cardPlayer", "#cardPlayerContent", cList)

    })

    let cList2 = []
    fetchCardById(cardIDOpp).then(r => {
        cList2.push(r)
        setTemplate("#opponentPlayer", "#opponentContent", cList2)
    })
}


function setUserInfo(user) {
    guser = user;
    document.getElementById("userNameId").innerHTML = user.surname;
    document.getElementById("walletId").innerHTML = user.wallet;
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const roomId = urlParams.get('roomid')

fetchUserInfo().then(r => {
    setUserInfo(r);
    fetchRoomInfo(roomId).then(r => setGame(r));
})
// let cardList = fetchCards().then(r => setGame(userCardId))
// setGame(userCardId)








