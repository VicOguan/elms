const token=localStorage.getItem("token");

const role=localStorage.getItem("role");

if(role==="ADMIN"||role==="MANAGER"){

    document.getElementById("adminMenu").style.display="block";
}

function logout(){

    localStorage.clear();

    window.location="login.html";
}