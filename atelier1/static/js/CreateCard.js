

const GET_CHUCK_URL="http://tp.cpe.fr:8083/card"; 
var parsedURl = new URL(window.location.href);

var name = parsedURl.searchParams.get('name')
var description = parsedURl.searchParams.get('description')
var imgUrl = parsedURl.searchParams.get('imgUrl')
var family = parsedURl.searchParams.get('family')
var affinity = parsedURl.searchParams.get('affinity')
var hp = parsedURl.searchParams.get('hp')
var energy = parsedURl.searchParams.get('energy')
var attack = parsedURl.searchParams.get('attack')
var defence = parsedURl.searchParams.get('defence')

if (hp==null)document.location.href="./addCard.html"; 


let context =   {
    method: 'POST',
    headers: {
            'Accept': '*/*',
            'Content-Type': 'application/json'
    },
    body:JSON.stringify(
        {
            "name": name,
            "description": description,
            "family": family,
            "affinity": affinity,
            "imgUrl": imgUrl,
            "smallImgUrl": "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/DC_Comics_logo.png/280px-DC_Comics_logo.png",
            "energy": energy,
            "hp": hp,
            "defence": defence,
            "attack": attack,
            "price": 0,
            "userId": 0
        }
    )
};
fetch(GET_CHUCK_URL,context)
    .then(response => response.json())
        .then(response => callback(response))
        .catch(error => err_callback(error));


function callback(response){
    console.log(response);
    document.location.href=`./card.html?search=${name}`; 
    //document.getElementById("txtChuck").innerHTML = response.value;
}

function err_callback(error){
    console.log(error);
}






