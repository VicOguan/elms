const API_URL="http://localhost:8080";

async function login(){

    const username=document.getElementById("username").value;

    const password=document.getElementById("password").value;

    const response=await fetch(API_URL+"/auth/login",{

        method:"POST",

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify({

            username,
            password

        })
    });

    if(!response.ok){

        document.getElementById("message").innerText="Invalid credentials";

        return;
    }

    const data=await response.json();

    localStorage.setItem("token",data.token);

    //Decode JWT payload

    const payload=JSON.parse(atob(data.token.split(".")[1]));

    localStorage.setItem("role",payload.role||payload.roles);

    window.location="dashboard.html";
}