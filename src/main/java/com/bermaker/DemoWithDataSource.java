package com.bermaker;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoWithDataSource {
  public static void main(String[] args) {
    // Create a new instance of MysqlDataSource
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setURL("jdbc:mysql://localhost:3307/testdb");
    dataSource.setUser("root");
    // No need to set password if password is empty. Setting an empty string is acceptable.
//    dataSource.setPassword("");

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      // Get a connection from the DataSource
      conn = dataSource.getConnection();

      // Create a Statement object for sending SQL statements to the database
      stmt = conn.createStatement();

      // Execute a SQL query and get the result set
      rs = stmt.executeQuery("SELECT id, name FROM people");

      // Process the result set
      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        System.out.println("ID: " + id + " Name: " + name);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      // Close ResultSet, Statement, and Connection objects
      try {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}

