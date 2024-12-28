Student Registration Web Application
A simple Java web application to register students, store their information in a MySQL database, and display registered students in a styled table.

Features
Student Registration Form: Allows users to register with their name, email, and year.
Database Integration: Stores student data in a MySQL database.
Display Registered Students: Shows all registered students in a styled table.
Technologies Used
Backend: Java (Servlets)
Frontend: HTML, CSS
Database: MySQL
Server: Apache Tomcat
Setup Instructions
Clone the repository:

bash
Copy code
git clone https://github.com/your-username/student-registration-app.git
Set up the database:

Create a database named registration_db.
Run the following SQL command to create the Students table:
sql
Copy code
CREATE TABLE Students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    year INT NOT NULL
);
Update database credentials in StudentServlet.java:

java
Copy code
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_db", "root", "your_password");
Deploy the project:

Import the project into Eclipse.
Configure Apache Tomcat as the server.
Deploy and run the project.
Access the application:

Open your browser and navigate to http://localhost:8080/your-project-name/register.
