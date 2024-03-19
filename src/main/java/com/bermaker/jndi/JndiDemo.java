package com.bermaker.jndi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JndiDemo {

  private static final Logger LOG = LoggerFactory.getLogger(JndiDemo.class);

  public static void main(String[] args) {
    LOG.info("Start to running jndi ...");

    System.setProperty("org.osjava.sj.jndi.trace", "true");
    System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.osjava.sj.SimpleContextFactory");
    System.setProperty(Context.URL_PKG_PREFIXES, "org.osjava.sj");
    // System.setProperty("org.osjava.sj.jndi.shared", "true");
    // System.setProperty("org.osjava.sj.directory", "META-INF");
    System.setProperty("org.osjava.sj.root", "src/main/resources/META-INF");
    System.setProperty("org.osjava.sj.delimiter", "/");

    try {
      // Create the initial context
      Context context = new InitialContext();

      // Perform the JNDI lookup
      DataSource dataSource = (DataSource) context.lookup("datasource");

      // Use the DataSource to get a Connection
      try (Connection conn = dataSource.getConnection();
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery("SELECT 1+1")) {

        if (rs.next()) {
          System.out.println("Query executed successfully: 1+1=" + rs.getInt(1));
        }
      }

      // Close the context when we're done
      context.close();

      System.out.println("DataSource lookup and query executed successfully.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
