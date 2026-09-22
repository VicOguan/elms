const API_URL = "http://localhost:8080";

async function login() {

    console.log("Login clicked");

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value;

    try {

        const response = await fetch(API_URL + "/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username,
                password
            })
        });

        console.log("Status:", response.status);

        if (!response.ok) {
            document.getElementById("message").innerText = "Invalid credentials.";
            return;
        }

        const data = await response.json();
        console.log("Token received:", data);

        localStorage.setItem("token", data.token);

        const payload = JSON.parse(atob(data.token.split(".")[1]));

        localStorage.setItem("username", payload.sub);
        localStorage.setItem("role", payload.role || payload.roles || "");

        window.location.href = "dashboard.html";

    } catch (err) {
        console.error(err);
        document.getElementById("message").innerText = "Cannot connect to server.";
    }
}