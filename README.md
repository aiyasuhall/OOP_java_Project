🛒 **Online Shopping System (Java OOP)**
Hi! This project is an Online Shopping System built with Java Swing. It’s not just a tool for buying and selling; it’s a practical application of clean software architecture and Object-Oriented Programming (OOP).
**Usecase Diagram:**
![UseCaseDiagram_](https://github.com/user-attachments/assets/c596fef7-60e2-4adc-8aeb-ee7115eb670b)

🏗️ **The MVC Model**
I followed the MVC (Model-View-Controller) pattern:

**Model (Entities):** These are the "building blocks" of the app. Classes like User, Product, and Category represent the data structures.

**View (Presentation):** This is the face of the app. I used Java Swing to build interactive dashboards for different users.

**Controller (The Brain):** These classes (e.g., LoginController, ProductController) handle the logic. They take user actions from the View, talk to the database, and update the data accordingly.

**DAO (Data Access Object):** These are the "data assistants." They handle all the SQL queries, ensuring the rest of the app doesn't have to worry about the messy details of database communication.

🔄 **MVC Workflow: How it Works**
To give you a better idea of how the system operates, let’s look at what happens behind the scenes when a user logs in:

**User Interaction (View):** Everything starts when you enter your email and password on the Login screen and click the button.

**Logic Processing (Controller):** The LoginController catches your input. Instead of doing the "heavy lifting" itself, it asks the UserDao to check those credentials against the database.

**Data Retrieval (Model):** If the credentials match, the UserDao fetches your data and wraps it into a User object. This object acts as a neat package containing your account details.

**UI Update (View):** Finally, the Controller looks at the user type inside that Model and decides which dashboard to open for you—whether it's for an Admin, Supplier, or Customer.

[ShoppingOnlineMVCModel.drawio.pdf](https://github.com/user-attachments/files/24842753/ShoppingOnlineMVCModel.drawio.pdf)


💡 **Using principles of OOP**
I used the four core principles of OOP to make the system robust:

**Encapsulation (Security)**: All entity/model classes (such as User, Product, Supplier, Purchase, etc.) encapsulate their fields using the private access modifier. Access to these fields is provided via public getter and setter methods.
**Benefit:** This protects the internal state of objects and prevents unintended interference from outside classes, ensuring data integrity.

**Inheritance (Smart Reusing):** The project uses inheritance for code reuse and logical hierarchy, particularly in the DAO (Data Access Object) layer.
**Benefit:** This promotes code reuse and reduces duplication by allowing common database logic to be defined in one place.

[Inheritance Diagram.pdf](https://github.com/user-attachments/files/24842757/Inheritance.Diagram.pdf)

**Abstraction (Simple Blueprints):** The code base is structured to hide complex details and expose only relevant operations at each layer. For example:
o	DAOs abstract away all database interaction logic.
o	Controllers abstract business logic away from the UI.
o	Views (Swing classes) handle only user interface logic.
**Benefit:** The rest of your code (like Controllers or other DAOs) interacts with DAOs using the abstract interface.
They don’t need to know how the actual insert/update/delete is implemented.
This makes the code easier to maintain and extend, and hides the implementation complexity.

**Polymorphism (Flexibility):** The project leverages polymorphism mainly in the form of method overriding and generic programming. The Base_DAO<T> class defines abstract or generic CRUD operations, which are implemented or specialized by each specific DAO.
**Benefit:** This allows different types of data objects to be handled through a common interface, increasing flexibility and maintainability.


👥 **Roles & Responsibilities**
The system is built for three distinct types of users:

**Admin:** The "Superuser." They manage the entire catalog (products, categories), handle supplier and user accounts, and keep an eye on all transactions.

**User (Customer):** The shopper. They can browse products, manage their cart, place orders, and track their purchase history.

**Supplier:** The logistics pro. They focus on delivering orders, tracking their delivery history, and managing their own profile.

[ClassDiagram.drawio.pdf](https://github.com/user-attachments/files/24842756/ClassDiagram.drawio.pdf)


🗄️ **Database: The Memory of the App**
I used MySQL to keep all the information organized and permanent:

**Reliable Storage:** From product stock to user credentials, everything is stored in relational tables.

**Seamless Connection:** The MyConnection class acts as a dedicated bridge, managing how the Java code talks to the SQL server.

**Data Integrity:** I used foreign keys to make sure everything stays connected—like linking a specific purchase back to the right user.
