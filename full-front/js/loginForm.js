
function process(elt){
    
    let login = document.getElementsByName('login');
    let password = document.getElementsByName('password');

    let user = {
        "surname": login[0].value,
        "password": password[0].value,
    }
    console.log("authenticating user : " + user.surname + " with password : " + user.password)
    authUser(user)
    
    // data = {"login":login[0].value, "password":password[0].value}
    // console.log(data);
}
