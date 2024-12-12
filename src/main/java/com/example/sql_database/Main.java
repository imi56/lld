package com.example.sql_database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    
    Database database = new Database("EMPLOYEE_DB");

    // Create a table using TableBuilder
    Table employeesTable = new TableBuilder("employees")
      .addColumn(new IntegerColumn("id"))
      .addColumn(new StringColumn("name"))
      .addColumn(new StringColumn("department"))
      .build();

    database.createTable(employeesTable);

    Map<String, Object> employee1 = new HashMap<>();
    employee1.put("id", 1);
    employee1.put("name", "John Doe");
    employee1.put("department", "Engineering");
    employeesTable.insertRow(employee1);

    Map<String, Object> employee2 = new HashMap<>();
    employee2.put("id", 2);
    employee2.put("name", "Jane Smith");
    employee2.put("department", "HR");
    employeesTable.insertRow(employee2);


    // Demonstrate querying
    List<Map<String, Object>> engineeringEmployees = employeesTable.select(
        row -> row.get("department").equals("Engineering")
    );

    System.out.println("Employees in Engineering:");
    engineeringEmployees.forEach(System.out::println);

    // List tables in database
    System.out.println("\nTables in database: " + database.listTables());
  }
}
