// admin.js

// 1. Load the User Management Table
async function loadUserManagement() {
    const container = document.getElementById("contentArea");
    container.innerHTML = "<p>Loading accounts...</p>";

    try {
        // Fetch both datasets simultaneously
        const [empRes, userRes] = await Promise.all([
            fetch(API_URL + "/employee", { headers: getHeaders() }),
            fetch(API_URL + "/users", { headers: getHeaders() })
        ]);

        const employees = await empRes.json();
        const users = await userRes.json();

        let rows = "";
        employees.forEach(emp => {
            // Check if this employee has an account
            const userAccount = users.find(u => u.employeeNumber === emp.employeeNumber);

            const statusBadge = userAccount
                ? `<span class="status-badge active">${userAccount.role}</span>`
                : `<span class="status-badge resigned">UNREGISTERED</span>`;

            const actionBtn = userAccount
                ? `<span style="color: #64748b; font-size: 13px; font-weight: bold;">@${userAccount.username}</span>`
                : `<button class="action-btn edit-btn" onclick="showRegisterAccountForm('${emp.employeeNumber}', '${emp.firstname} ${emp.lastName}')">Create Account</button>`;

            rows += `
                <tr>
                    <td><span class="emp-id-badge">${emp.employeeNumber}</span></td>
                    <td style="font-weight: bold;">${emp.firstname} ${emp.lastName}</td>
                    <td>${emp.department}</td>
                    <td>${statusBadge}</td>
                    <td>${actionBtn}</td>
                </tr>`;
        });

        container.innerHTML = `
            <div class="table-header-wrap">
                <h2>User Account Management</h2>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Employee No.</th>
                        <th>Name</th>
                        <th>Department</th>
                        <th>Account Status</th>
                        <th>Action / Username</th>
                    </tr>
                </thead>
                <tbody>
                    ${rows || "<tr><td colspan='5'>No employees found.</td></tr>"}
                </tbody>
            </table>`;
    } catch (e) {
        console.error(e);
        container.innerHTML = "<p>Failed to load user management.</p>";
    }
}

// 2. Show the Registration Form for a specific employee
function showRegisterAccountForm(empNumber, empName) {
    document.getElementById("contentArea").innerHTML = `
        <div class="form-card">
            <h2>Create Account for ${empName}</h2>
            <input id="regEmpNumber" value="${empNumber}" readonly style="background: #f1f5f9; cursor: not-allowed; color: #64748b;">
            <input id="regUsername" placeholder="Username" autocomplete="off">
            <input id="regPassword" type="password" placeholder="Password" autocomplete="new-password">
            <select id="regRole">
                <option value="EMPLOYEE">Employee</option>
                <option value="MANAGER">Manager</option>
                <option value="ADMIN">Admin</option>
            </select>
            <button onclick="submitNewAccount()">Create Account</button>
            <p id="regMessage" style="margin-top: 15px; font-weight: bold;"></p>
        </div>
    `;
}

// 3. Submit the new account
async function submitNewAccount() {
    const employeeNumber = document.getElementById("regEmpNumber").value;
    const username = document.getElementById("regUsername").value.trim();
    const password = document.getElementById("regPassword").value;
    const role = document.getElementById("regRole").value;
    const message = document.getElementById("regMessage");

    if (!username || !password) {
        message.style.color = "red";
        message.innerText = "Username and password are required.";
        return;
    }

    // Route to the correct endpoint based on the dropdown
    let endpoint = "/auth/register";
    if (role === "MANAGER") endpoint = "/auth/register/manager";
    if (role === "ADMIN") endpoint = "/auth/register/admin";

    try {
        const response = await fetch(API_URL + endpoint, {
            method: "POST",
            headers: getHeaders(), // Using getHeaders() because /auth/register/admin requires an auth token
            body: JSON.stringify({ employeeNumber, username, password })
        });

        if (response.ok) {
            message.style.color = "green";
            message.innerText = "Account created successfully!";
            setTimeout(loadUserManagement, 1200); // Return to table
        } else {
            message.style.color = "red";
            message.innerText = await response.text();
        }
    } catch (e) {
        message.style.color = "red";
        message.innerText = "Failed to connect to server.";
    }
}