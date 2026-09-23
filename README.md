<!--horizontal divider(gradiant)-->
<img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif" width="100%">

<!--h1 header with custom font branding-->
<div id="user-content-toc">
  <ul align="center">
    <summary>
      <h1 style="display: inline-block">
        <a href="https://git.io/typing-svg">
          <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&pause=1000&width=435&lines=EMPLOYEE+LEAVE+MANAGEMENT+SYSTEM" alt="Typing SVG" />
        </a>
      </h1>
    </summary>
  </ul>
</div>

<!--h2 title with custom font styling-->
<div id="user-content-toc">
  <ul align="center">
    <summary>
      <h2 style="display: inline-block">
        <a href="https://readme-typing-svg.demolab.com">
          <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&size=20&pause=1000&color=98C379&center=true&vcenter=true&width=550&lines=Simplified+Workforce+Time-Off+Tracking" alt="Simplified Workforce Time-Off Tracking" />
        </a>
      </h2>
    </summary>
  </ul>
</div>

<!--Features List-->
<ul>
  <li>
    <p>👤 <strong>Employee Portal:</strong> Submit leave requests with custom categories (<code>VACATION</code>, <code>SICK</code>, <code>EMERGENCY</code>, <code>MATERNITY</code>, <code>PATERNITY</code>) and view personal leave history with real-time status updates (<code>PENDING</code>, <code>APPROVED</code>, <code>REJECTED</code>).</p>
  </li>
  <li>
    <p>🛡️ <strong>Manager & Admin Dashboard:</strong> Review all submitted employee leave requests, approve or reject applications, and view company-wide leave metrics.</p>
  </li>
  <li>
    <p>👥 <strong>Employee Management:</strong> Add, edit, or delete employee records, auto-generate employee numbers, and manage active employment statuses (<code>ACTIVE</code>, <code>ON_LEAVE</code>, <code>RESIGNED</code>, <code>TERMINATED</code>).</p>
  </li>
  <li>
    <p>🔐 <strong>User & Account Management:</strong> Register role-based accounts (<code>EMPLOYEE</code>, <code>MANAGER</code>, <code>ADMIN</code>) linked directly to employee records, and manage account statuses via dedicated admin controls.</p>
  </li>
  <li>
    <p>🔑 <strong>JWT Authentication & Security:</strong> Secure stateless token authentication with Spring Security and granular endpoint access control based on user roles.</p>
  </li>
</ul>

<!--Tech Stack Header-->
<div id="user-content-toc">
  <ul align="center">
    <summary>
      <h2 style="display: inline-block">
        <a href="https://readme-typing-svg.demolab.com">
          <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&size=22&pause=1000&color=E06C75&center=true&vcenter=true&width=400&lines=Technologies+Used+%F0%9F%90%A1" alt="Technologies Used" />
        </a>
      </h2>
    </summary>
  </ul>
</div>

<!--Tech Stack Icons-->
<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,spring,mysql,html,css,js,git,github,postman&perline=9">
  </a>
</p>

<!--API & Project Overview Header-->
<div id="user-content-toc">
  <ul align="center">
    <summary>
      <h2 style="display: inline-block">
        <a href="https://readme-typing-svg.demolab.com">
          <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&size=22&pause=1000&color=D19A66&center=true&vcenter=true&width=400&lines=Core+Endpoints+%F0%9F%9A%80" alt="Core Endpoints" />
        </a>
      </h2>
    </summary>
  </ul>
</div>

<table align="center">
  <thead>
    <tr>
      <th>Method</th>
      <th>Endpoint</th>
      <th>Description</th>
      <th>Access</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>POST</code></td>
      <td><code>/auth/login</code></td>
      <td>Authenticate user & return JWT token</td>
      <td>Public</td>
    </tr>
    <tr>
      <td><code>POST</code></td>
      <td><code>/auth/register</code></td>
      <td>Register an Employee user account</td>
      <td>Admin</td>
    </tr>
    <tr>
      <td><code>POST</code></td>
      <td><code>/auth/register/manager</code></td>
      <td>Register a Manager user account</td>
      <td>Admin</td>
    </tr>
    <tr>
      <td><code>POST</code></td>
      <td><code>/auth/register/admin</code></td>
      <td>Register an Admin user account</td>
      <td>Admin</td>
    </tr>
    <tr>
      <td><code>GET</code></td>
      <td><code>/leave/my</code></td>
      <td>Fetch current employee's leave requests</td>
      <td>Employee</td>
    </tr>
    <tr>
      <td><code>POST</code></td>
      <td><code>/leave</code></td>
      <td>Submit a new leave application</td>
      <td>Employee</td>
    </tr>
    <tr>
      <td><code>GET</code></td>
      <td><code>/leave</code></td>
      <td>Retrieve all employee leave requests</td>
      <td>Manager / Admin</td>
    </tr>
    <tr>
      <td><code>PUT</code></td>
      <td><code>/leave/{id}/status</code></td>
      <td>Approve or reject a leave request</td>
      <td>Manager / Admin</td>
    </tr>
    <tr>
      <td><code>DELETE</code></td>
      <td><code>/leave/{id}</code></td>
      <td>Delete a leave request</td>
      <td>Manager / Admin</td>
    </tr>
    <tr>
      <td><code>GET</code></td>
      <td><code>/employee</code></td>
      <td>Fetch all employee profiles</td>
      <td>Manager / Admin</td>
    </tr>
    <tr>
      <td><code>POST</code></td>
      <td><code>/employee</code></td>
      <td>Add a new employee record</td>
      <td>Admin</td>
    </tr>
    <tr>
      <td><code>PUT</code></td>
      <td><code>/employee/{id}</code></td>
      <td>Update employee details & status</td>
      <td>Admin</td>
    </tr>
    <tr>
      <td><code>DELETE</code></td>
      <td><code>/employee/{id}</code></td>
      <td>Delete an employee record</td>
      <td>Admin</td>
    </tr>
    <tr>
      <td><code>GET</code></td>
      <td><code>/users</code></td>
      <td>Retrieve user accounts list & linked status</td>
      <td>Admin</td>
    </tr>
  </tbody>
</table>

<!--Quick Links & Connect-->
<div id="user-content-toc">
  <ul align="center">
    <summary>
      <h2 style="display: inline-block">
        <a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.demolab.com?font=Fira+Code&size=30&pause=1000&width=435&lines=Connect+with+me" alt="Typing SVG" /></a>
      </h2>
    </summary>
  </ul>
</div>

<p align="center">
  <a href="https://github.com" target="_blank"><img align="center" src="https://user-images.githubusercontent.com/88904952/234982627-019fd336-6248-453c-9b05-97c13fd1d207.png" alt="github" height="50" width="50"></a>
  <a href="https://linkedin.com/in/ludivico-oguan-62725b27a" target="_blank"><img align="center" src="https://user-images.githubusercontent.com/88904952/234979284-68c11d7f-1acc-4f0c-ac78-044e1037d7b0.png" alt="linkedin" height="50" width="50"></a>
</p>

<!--horizontal divider(gradiant)-->
<img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif" width="100%">
<hr>
