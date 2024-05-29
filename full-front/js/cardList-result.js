let guser;
let groom;



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
const result = urlParams.get('result')
let bet = urlParams.get('bet')
fetchUserInfo().then(r => {
    setUserInfo(r);
})

if(result === 'win') {
    resultMessage = 'You won!'
    bet = "+" + bet
} else if(result === 'lost') {
    resultMessage = 'You lost!'
    bet = "-" + bet
}
document.getElementById("resultMessage").innerHTML = resultMessage;
document.getElementById("amount").innerHTML = bet;





