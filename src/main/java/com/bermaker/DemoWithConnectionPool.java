package com.bermaker;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DemoWithConnectionPool {

  public static void main(String[] args) {
    // Configure HikariCP
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:mysql://localhost:3307/testdb");
    config.setUsername("root");
    config.setPassword("");

    // Optionally configure additional HikariCP settings
    config.setMaximumPoolSize(10); // Set the maximum number of connections in the pool
    config.setAutoCommit(true);

    // Create the HikariDataSource with the configuration
    HikariDataSource ds = new HikariDataSource(config);

    // Use the DataSource to get connections and perform database operations
    try (Connection conn = ds.getConnection();
         PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM people");
         ResultSet rs = stmt.executeQuery()) {

      while (rs.next()) {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        System.out.println("ID: " + id + " Name: " + name);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Close the datasource when application stops
    ds.close();
  }
}


