<h1>🛒 Online Shopping System (Java OOP)</h1>

<p>Hi! This project is an Online Shopping System built with Java Swing. It’s not just a tool for buying and selling; it’s a practical application of clean software architecture and Object-Oriented Programming (OOP).</p>

<p><strong>Usecase Diagram:</strong></p>
<p align="center">
  <img src="https://github.com/user-attachments/assets/c596fef7-60e2-4adc-8aeb-ee7115eb670b" width="650" alt="Use Case Diagram">
</p>

<hr>

<h2><b>🏗️ The MVC Model</b></h2>

<p>I followed the MVC (Model-View-Controller) pattern to ensure a clean separation of concerns:</p>

<ul>
  <li><strong>Model (Entities)</strong>: These are the "building blocks" of the app. Classes like <code>User</code>, <code>Product</code>, and <code>Category</code> represent the data structures.</li>
  <li><strong>View (Presentation)</strong>: This is the face of the app. I used Java Swing to build interactive dashboards for different users.</li>
  <li><strong>Controller (The Brain)</strong>: These classes (e.g., <code>LoginController</code>, <code>ProductController</code>) handle the logic. They take user actions from the View, talk to the database, and update the data accordingly.</li>
  <li><strong>DAO (Data Access Object)</strong>: These are the "data assistants." They handle all the SQL queries, ensuring the rest of the app doesn't have to worry about the messy details of database communication.</li>
</ul>

<h3>🔄 MVC Workflow: How it Works</h3>
<ul>
  <li><strong>User Interaction (View)</strong>: Everything starts when you enter your email and password on the Login screen and click the button.</li>
  <li><strong>Logic Processing (Controller)</strong>: The <code>LoginController</code> catches your input. Instead of doing the "heavy lifting" itself, it asks the <code>UserDao</code> to check those credentials against the database.</li>
  <li><strong>Data Retrieval (Model)</strong>: If the credentials match, the <code>UserDao</code> fetches your data and wraps it into a <code>User</code> object. This object acts as a neat package containing your account details.</li>
  <li><strong>UI Update (View)</strong>: Finally, the Controller looks at the user type inside that Model and decides which dashboard to open for you—whether it's for an Admin, Supplier, or Customer.</li>
</ul>

<p>
  📄 <a href="https://github.com/user-attachments/files/24842753/ShoppingOnlineMVCModel.drawio.pdf">View MVC Model Diagram (PDF)</a>
</p>

<hr>

<h2><b>💡 Using principles of OOP</b></h2>

<p>I used the four core principles of OOP to build a robust system:</p>

<ul>
  <li><strong>Encapsulation (Security)</strong>: All entity/model classes (such as <code>User</code>, <code>Product</code>, <code>Supplier</code>, <code>Purchase</code>) encapsulate their fields using the <code>private</code> access modifier. Access is provided via <code>public</code> getters and setters.
    <br><em>Benefit:</em> Protects the internal state of objects and ensures data integrity.
  </li>
  
  <li><strong>Inheritance (Smart Reusing)</strong>: The project uses inheritance for code reuse and logical hierarchy, particularly in the DAO layer.
    <br><em>Benefit:</em> Promotes code reuse and reduces duplication by defining common database logic in one place.
  </li>

  <li><strong>Abstraction (Simple Blueprints)</strong>: The codebase hides complex details and exposes only relevant operations. For example, DAOs abstract database interactions, and Controllers abstract business logic.
    <br><em>Benefit:</em> The code interacts using abstract interfaces, hiding implementation details (like SQL queries), which makes the system easier to maintain and extend.
  </li>

  <li><strong>Polymorphism (Flexibility)</strong>: The project leverages method overriding and generic programming. The <code>Base_DAO&lt;T&gt;</code> class defines generic CRUD operations specialized by each specific DAO.
    <br><em>Benefit:</em> Allows different types of data objects to be handled through a common interface, increasing flexibility.
  </li>
</ul>

<p>
  📄 <a href="https://github.com/user-attachments/files/24842757/Inheritance.Diagram.pdf">View Inheritance Diagram (PDF)</a>
</p>

<hr>

<h2><b>👥 Roles & Responsibilities</b></h2>

<p>The system is built for three distinct types of users:</p>

<ul>
  <li><strong>Admin</strong>: The "Superuser." They manage the entire catalog (products, categories), handle supplier and user accounts, and keep an eye on all transactions.</li>
  <li><strong>User (Customer)</strong>: The shopper. They can browse products, manage their cart, place orders, and track their purchase history.</li>
  <li><strong>Supplier</strong>: The logistics pro. They focus on delivering orders, tracking their delivery history, and managing their own profile.</li>
</ul>

<p>
  📄 <a href="https://github.com/user-attachments/files/24842756/ClassDiagram.drawio.pdf">View Class Diagram (PDF)</a>
</p>

<hr>

<h2><b>🗄️ Database: The Memory of the App</b></h2>

<p>I used MySQL to keep all the information organized and permanent:</p>

<ul>
  <li><strong>Reliable Storage</strong>: From product stock to user credentials, everything is stored in relational tables.</li>
  <li><strong>Seamless Connection</strong>: The <code>MyConnection</code> class acts as a dedicated bridge, managing how the Java code talks to the SQL server.</li>
  <li><strong>Data Integrity</strong>: I used foreign keys to make sure everything stays connected—like linking a specific purchase back to the right user.</li>
</ul>
