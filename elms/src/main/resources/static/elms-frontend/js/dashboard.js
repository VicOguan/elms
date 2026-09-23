const token = localStorage.getItem("token");
const role = localStorage.getItem("role");
const username = localStorage.getItem("username");

if (!token) {
    window.location.href = "login.html";
}

document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("welcomeText").innerText =
        "Welcome, " + (username || "User");

    const roleBadge = document.getElementById("userRoleBadge");
    if (roleBadge) {
        roleBadge.innerText = role || "EMPLOYEE";
    }

    if (role === "ADMIN") {
        addEmployeeBtn.style.display = "block";
        manageAccountsBtn.style.display = "block"; // <-- Add this line
    }

    if (role === "MANAGER" || role === "ADMIN") {

        dashboardBtn.style.display = "block";
        employeeBtn.style.display = "block";
        leaveRequestBtn.style.display = "block";

        myLeavesBtn.style.display = "none";
        submitLeaveBtn.style.display = "none";

        if (role === "ADMIN") {
            addEmployeeBtn.style.display = "block";
        }

        showDashboard();

    } else {

        showMyLeaves();

    }
});

function goHome() {
    window.location.href = "index.html";
}

function logout() {
    localStorage.clear();
    window.location.href = "login.html";
}