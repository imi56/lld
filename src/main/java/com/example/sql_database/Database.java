package com.example.sql_database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
  private String name;
  private Map<String, Table> tables;
  
  Database(String name) {
    this.name = name;
    this.tables = new HashMap<>();
  }

  public void createTable(Table table) {
    if (tables.containsKey(table.getName())) {
      throw new IllegalArgumentException("Table already exists: " + table.getName());
    }
    tables.put(table.getName(), table);
  }

  public Table getTable(String tableName) {
    Table table = tables.get(tableName);
    if (table == null) {
        throw new IllegalArgumentException("Table not found: " + tableName);
    }
    return table;
  }

  // List all tables in the database
  public List<String> listTables() {
      return new ArrayList<>(tables.keySet());
  }

  // Drop a table
  public void dropTable(String tableName) {
    if (!tables.containsKey(tableName)) {
        throw new IllegalArgumentException("Table does not exist: " + tableName);
    }
    tables.remove(tableName);
}

// Get database name
  public String getDatabaseName() {
      return name;
  }


}
