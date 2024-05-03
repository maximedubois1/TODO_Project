const GET_CHUCK_URL="http://tp.cpe.fr:8083/cards"; 
let context =   {
                    method: 'GET'
                };
    
fetch(GET_CHUCK_URL,context)
    .then(response => response.json())
        .then(response => callback(response))
        .catch(error => err_callback(error));

function callback(response){
    var parsedURl = new URL(window.location.href);
    var name = parsedURl.searchParams.get('search')
    if(name == null) return;
    
    for(const card of response){
        if(card.name == name){
            let template = document.querySelector("#selectedCard");
            let clone = document.importNode(template.content, true);
            newContent= clone.firstElementChild.innerHTML
            .replace(/{{family_src}}/g, card.smallImgUrl)
            .replace(/{{family_name}}/g, card.family)
            .replace(/{{img_src}}/g, card.imgUrl)
            .replace(/{{name}}/g, card.name)
            .replace(/{{description}}/g, card.description)
            .replace(/{{hp}}/g, card.hp)
            .replace(/{{energy}}/g, card.energy)
            .replace(/{{attack}}/g, card.attack)
            .replace(/{{defense}}/g, card.defence);
            clone.firstElementChild.innerHTML= newContent;
            
            let cardContainer= document.querySelector("#cardContainer");
            cardContainer.appendChild(clone);
            return
        }
    }
    console.error("Card not found");
    
}

function err_callback(error){
    console.log(error);
}






