package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String year = request.getParameter("year");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_db", "root", "1425");

            // Insert into database
            String query = "INSERT INTO Students (name, email, year) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, Integer.parseInt(year));

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                out.println("<h1>Registration Successful!</h1>");
            }

            // Display registered students
            out.println("<h2>Registered Students:</h2>");
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Email</th><th>Year</th></tr>");

            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Students");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("name") + "</td><td>" + rs.getString("email") + "</td><td>" + rs.getInt("year") + "</td></tr>");
            }
            out.println("</table>");

            con.close();
        } catch (Exception e) {
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Student Registration</title></head><body>");
        out.println("<h1>Register Student</h1>");
        out.println("<form action='register' method='post'>");
        out.println("<label>Name:</label><br>");
        out.println("<input type='text' name='name' required><br><br>");
        out.println("<label>Email:</label><br>");
        out.println("<input type='email' name='email' required><br><br>");
        out.println("<label>Year:</label><br>");
        out.println("<input type='number' name='year' required><br><br>");
        out.println("<button type='submit'>Register</button>");
        out.println("</form>");
        out.println("</body></html>");
    }
}