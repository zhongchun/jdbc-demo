package com.bermaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoWithDriver {

  public static void main(String[] args) {
    // Database connection parameters
    String url = "jdbc:mysql://localhost:3307/testdb";
    String user = "root";
    String password = "";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    PreparedStatement pstmt = null;

    try {
      // Load the JDBC driver
      Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");

      // Establish the database connection
      conn = DriverManager.getConnection(url, user, password);

      // Create a statement to execute SQL queries
      stmt = conn.createStatement();

      // 1. DQL: execute a SQL query and retrieve the result set
      String sql = "SELECT id, name FROM people";
      rs = stmt.executeQuery(sql);

      // Iterate through the result set and print each record
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        System.out.println("ID: " + id + ", Name: " + name);
      }

      // 2. DML: insert some items into the table
      pstmt = conn.prepareStatement("INSERT INTO people (name) VALUES (?)");
      String[] names = {"Adam", "Bill", "Charles"};
      for (String name : names) {
        pstmt.setString(1, name);
        int ret = pstmt.executeUpdate();
        System.out.println("Result of insertion is " + ret);
      }

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // Close the result set, statement, and the connection
      try {
        if (rs != null && !rs.isClosed()) rs.close();
        if (stmt != null && !stmt.isClosed()) stmt.close();
        if (pstmt != null && !pstmt.isClosed()) pstmt.close();
        if (conn != null && !conn.isClosed()) conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
