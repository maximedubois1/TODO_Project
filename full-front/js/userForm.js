
function process(elt){
    
    let firstName = document.getElementsByName('first-name');
    let lastName = document.getElementsByName('last-name');
    let password = document.getElementsByName('password');
    let rePassword = document.getElementsByName('re-password');
    checkPassword(password[0].value, rePassword[0].value)

    let user = {
        "surname": firstName[0].value,
        // "lastName": lastName[0].value,
        "password": password[0].value,
    }
    console.log("Creating user : " + firstName[0].value + " " + lastName[0].value + " with password : " + password[0].value)
    createUser(user)

    // data = {"firstName":firstName[0].value, "lastName":lastName[0].value, "password":password[0].value}
    // console.log(data);
}

function checkPassword(password, rePassword){
    if(password !== rePassword){
        alert("Password do not match");
    }
}


