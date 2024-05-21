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

function authUser(data) {
    fetch('http://localhost:8080/api/v1/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify(data),
    })
    // .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        window.location.href = "home.html";
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}
