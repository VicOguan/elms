const API_URL = "http://localhost:8080";

function getHeaders() {
    return {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + localStorage.getItem("token")
    };
}

/* -----------------------------
LEAVES
----------------------------- */

async function deleteLeave(id){

    if(!confirm("Delete this leave request?")) return;

    const response=await fetch(API_URL+`/leave/${id}`,{

        method:"DELETE",

        headers:getHeaders()

    });

    if(response.ok){

        loadLeaves();

    }else{

        alert("Failed to delete leave.");

    }
}

async function updateLeaveStatus(id,status){

    const response=await fetch(API_URL+`/leave/${id}/status`,{

        method:"PUT",

        headers:getHeaders(),

        body:JSON.stringify({
            leaveStatus:status
        })

    });

    if(response.ok){

        loadLeaves();

    }else{

        alert("Failed to update leave.");

    }
}

async function loadLeaves(){

    const container=document.getElementById("contentArea");

    const response=await fetch(API_URL+"/leave",{
        headers:getHeaders()
    });

    const leaves=await response.json();

    let rows="";

    leaves.forEach(leave=>{

        const status=leave.status.toLowerCase();

        rows+=`
<tr>

<td>
    <div class="employee-cell">
        <div class="person-icon-wrapper small">
            <svg class="person-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                <circle cx="12" cy="7" r="4"></circle>
            </svg>
        </div>
        <span class="employee-name">${leave.employeeName}</span>
    </div>
</td>

<td>${leave.leaveType}</td>

<td>${leave.startDate}</td>

<td>${leave.endDate}</td>

<td>
<span class="status ${status}">
${leave.status}
</span>
</td>

<td>

<button class="action-btn approve"
onclick="updateLeaveStatus(${leave.id},'APPROVED')">
Approve
</button>

<button class="action-btn reject"
onclick="updateLeaveStatus(${leave.id},'REJECTED')">
Reject
</button>

<button class="action-btn delete"
onclick="deleteLeave(${leave.id})">
Delete
</button>

</td>

</tr>`;
    });

    container.innerHTML=`

<h2>Leave Requests</h2>

<table>

<thead>

<tr>

<th>Employee</th>

<th>Type</th>

<th>Start</th>

<th>End</th>

<th>Status</th>

<th>Action</th>

</tr>

</thead>

<tbody>

${rows}

</tbody>

</table>`;
}

async function showDashboard(){

    const container=document.getElementById("contentArea");

    try{

        const empRes=await fetch(API_URL+"/employee",{
            headers:getHeaders()
        });

        const leaveRes=await fetch(API_URL+"/leave",{
            headers:getHeaders()
        });

        const employees=await empRes.json();
        const leaves=await leaveRes.json();

        const pending=leaves.filter(l=>l.status==="PENDING").length;
        const approved=leaves.filter(l=>l.status==="APPROVED").length;
        const rejected=leaves.filter(l=>l.status==="REJECTED").length;

        container.innerHTML=`
<div class="cards">

<div class="card">
<h2>${employees.length}</h2>
<p>Employees</p>
</div>

<div class="card">
<h2>${pending}</h2>
<p>Pending</p>
</div>

<div class="card">
<h2>${approved}</h2>
<p>Approved</p>
</div>

<div class="card">
<h2>${rejected}</h2>
<p>Rejected</p>
</div>

</div>`;
    }
    catch{

        container.innerHTML="<p>Failed to load dashboard.</p>";

    }
}

async function showMyLeaves(){


    const container=document.getElementById("contentArea");
    container.innerHTML="<p>Loading...</p>";

    try{

        const response= await fetch(API_URL+"/leave/my",{
            method: "GET",
            headers: getHeaders()

        });

        const leaves=await response.json();

        let pending=0;
        let approved=0;
        let rejected=0;

        let rows="";

        leaves.forEach(leave=>{

            const status=leave.status.toLowerCase();

            if(status==="pending") pending++;
            if(status==="approved") approved++;
            if(status==="rejected") rejected++;

            rows+=`
<tr>

<td>${leave.leaveType}</td>

<td>${leave.startDate}</td>

<td>${leave.endDate}</td>

<td>${leave.reason}</td>

<td>

<span class="status ${status}">
${leave.status}
</span>

</td>

</tr>`;
        });

        container.innerHTML=`

<div class="cards">

<div class="card">

<h2>${pending}</h2>

<p>Pending</p>

</div>

<div class="card">

<h2>${approved}</h2>

<p>Approved</p>

</div>

<div class="card">

<h2>${rejected}</h2>

<p>Rejected</p>

</div>

</div>

<table>

<thead>

<tr>

<th>Leave Type</th>

<th>Start</th>

<th>End</th>

<th>Reason</th>

<th>Status</th>

</tr>

</thead>

<tbody>

${rows||"<tr><td colspan='5'>No leave requests found.</td></tr>"}

</tbody>

</table>
`;

    }
    catch(e){

        container.innerHTML="<p>Failed to load leaves.</p>";

    }

}

/* -----------------------------
EMPLOYEE
----------------------------- */

async function loadEmployees(){

    const container=document.getElementById("contentArea");

    const response=await fetch(API_URL+"/employee",{
        headers:getHeaders()
    });

    const employees=await response.json();

    let rows="";

    employees.forEach(emp=>{
        const status = emp.employeeStatus || "ACTIVE";
        const statusClass = status.toLowerCase();

        rows+=`
<tr>

<td><span class="emp-id-badge">${emp.employeeNumber}</span></td>

<td>
    <div class="employee-cell">
        <div class="person-icon-wrapper">
            <svg class="person-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                <circle cx="12" cy="7" r="4"></circle>
            </svg>
        </div>
        <div class="employee-details">
            <div class="employee-name">${emp.firstname} ${emp.lastName}</div>
            ${emp.email ? `<div class="employee-subtext">${emp.email}</div>` : ''}
        </div>
    </div>
</td>

<td>${emp.department}</td>

<td>${emp.position}</td>

<td>
    <span class="status-badge ${statusClass}">
        ${status}
    </span>
</td>

${role==="ADMIN"?`
<td>

<button class="action-btn edit-btn"
onclick="editEmployee(${emp.id})">
Edit
</button>

<button class="action-btn delete-btn"
onclick="deleteEmployee(${emp.id})">
Delete
</button>

</td>`:""}

</tr>`;
    });

    container.innerHTML=`

<div class="table-header-wrap">
    <h2>Employee Management</h2>
    <span class="count-badge">${employees.length} Employees</span>
</div>

<table>

<thead>

<tr>

<th>Employee No.</th>

<th>Employee</th>

<th>Department</th>

<th>Position</th>

<th>Status</th>

${role==="ADMIN"?"<th>Actions</th>":""}

</tr>

</thead>

<tbody>

${rows || "<tr><td colspan='6'>No employees found.</td></tr>"}

</tbody>

</table>`;
}

function showEmployeeForm(){

    document.getElementById("contentArea").innerHTML = `

    <div class="form-card">

        <h2>Add Employee</h2>

        <input id="firstname"
               placeholder="First Name"
               autocomplete="given-name">

        <input id="lastname"
               placeholder="Last Name"
               autocomplete="family-name">

        <input id="email"
               type="email"
               placeholder="Email"
               autocomplete="email">

        <input id="phoneNumber"
               type="tel"
               placeholder="Phone Number"
               autocomplete="tel">

        <input id="department"
               placeholder="Department"
               autocomplete="organization">

        <input id="position"
               placeholder="Position"
               autocomplete="organization-title">

        <input id="hiredDate"
               type="date">

        <select id="employeeStatus">
            <option value="">Select Employee status</option>
            <option>ACTIVE</option>
            <option>ON_LEAVE</option>
            <option>RESIGNED</option>
            <option>TERMINATED</option>
        </select>

        <button onclick="addEmployee()">
            Add Employee
        </button>

        <p id="employeeMessage"></p>

    </div>`;
}

async function updateEmployee(id){

    const data = {
        employeeNumber: document.getElementById("employeeNumber").value,
        firstname: document.getElementById("firstname").value,
        lastName: document.getElementById("lastname").value,
        email: document.getElementById("email").value,
        phoneNumber: document.getElementById("phoneNumber").value,
        department: document.getElementById("department").value,
        position: document.getElementById("position").value,
        hiredDate: document.getElementById("hiredDate").value,
        employeeStatus: document.getElementById("employeeStatus").value
    };

    const response = await fetch(API_URL + "/employee/" + id, {
        method: "PUT",
        headers: getHeaders(),
        body: JSON.stringify(data)
    });

    if(response.ok){
        loadEmployees();
    }else{
        console.log(await response.text());
        alert("Update failed.");
    }
}

async function addEmployee(){

    const data = {
        firstname: document.getElementById("firstname").value,
        lastName: document.getElementById("lastname").value,
        email: document.getElementById("email").value,
        phoneNumber: document.getElementById("phoneNumber").value,
        department: document.getElementById("department").value,
        position: document.getElementById("position").value,
        hiredDate: document.getElementById("hiredDate").value,
        employeeStatus: document.getElementById("employeeStatus").value
    };

    console.log("Sending:", data);

    const response = await fetch(API_URL + "/employee", {
        method: "POST",
        headers: getHeaders(),
        body: JSON.stringify(data)
    });

    const message = document.getElementById("employeeMessage");

    if(response.ok){
        message.style.color = "green";
        message.innerText = "Employee added successfully.";
        setTimeout(loadEmployees, 1000);
    }else{
        message.style.color = "red";
        message.innerText = await response.text();
        console.error(await response.text());
    }
}

async function editEmployee(id){

    const response=await fetch(API_URL+"/employee/"+id,{
        headers:getHeaders()
    });

    const emp=await response.json();

    document.getElementById("contentArea").innerHTML=`

<div class="form-card">

<h2>Edit Employee</h2>

<input id="employeeNumber"
       value="${emp.employeeNumber}"
       placeholder="Employee Number"
       autocomplete="off">
<input id="firstname" value="${emp.firstname}">

<input id="lastname" value="${emp.lastName}">

<input id="email" value="${emp.email}">

<input id="phoneNumber" value="${emp.phoneNumber}">

<input id="department" value="${emp.department}">

<input id="position" value="${emp.position}">

<input id="hiredDate" type="date" value="${emp.hiredDate}">

<select id="employeeStatus">
    <option value="ACTIVE" ${emp.employeeStatus ==="ACTIVE"?"selected":""}>ACTIVE</option>
    <option value="ON_LEAVE" ${emp.employeeStatus==="ON_LEAVE"?"selected":""}>ON_LEAVE</option>
    <option value="RESIGNED" ${emp.employeeStatus==="RESIGNED"?"selected":""}>RESIGNED</option>
    <option value="TERMINATED" ${emp.employeeStatus==="TERMINATED"?"selected":""}>TERMINATED</option>
</select>

<button onclick="updateEmployee(${id})">
Save Changes
</button>

</div>`;
}

async function deleteEmployee(id){

    if(!confirm("Delete this employee?")) return;

    const response=await fetch(API_URL+"/employee/"+id,{

        method:"DELETE",

        headers:getHeaders()

    });

    if(response.ok){

        loadEmployees();

    }else{

        alert("Delete failed.");

    }
}

/* -----------------------------
SUBMIT LEAVE
----------------------------- */
function showLeaveForm(){

    document.getElementById("contentArea").innerHTML=`

<div class="form-card">

<h2>Submit Leave</h2>

<select id="leaveType">

<option value="">Select Leave Type</option>

<option>SICK</option>

<option>VACATION</option>

<option>EMERGENCY</option>

<option>MATERNITY</option>

<option>PATERNITY</option>

</select>

<input id="startDate" type="date">

<input id="endDate" type="date">

<textarea id="reason" placeholder="Reason"></textarea>

<button onclick="submitLeave()">
Submit Leave
</button>

<p id="formMessage"></p>

</div>
`;

}

async function submitLeave(){

    const data={

        leaveType:document.getElementById("leaveType").value,

        startDate:document.getElementById("startDate").value,

        endDate:document.getElementById("endDate").value,

        reason:document.getElementById("reason").value

    };

    const response=await fetch(API_URL+"/leave",{

        method:"POST",
        headers: getHeaders(),

        body:JSON.stringify(data)

    });

    const message=document.getElementById("formMessage");

    if(response.ok){

        message.style.color="green";

        message.innerText="Leave submitted successfully.";

        setTimeout(showMyLeaves,1200);

    }
    else{

        const error=await response.text();

        message.style.color="red";

        message.innerText=error;

    }

}
