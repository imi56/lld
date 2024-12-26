package com.practice.sql_database;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TableBuilder {
  private String name;
  private List<Column> columns;

  public TableBuilder(String name) {
    this.name = name;
    this.columns = new ArrayList<>();
  }

  public TableBuilder addColumn(Column column) {
    columns.add(column);
    return this;
}

  public Table build() {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Table must have a non-empty name");
    }

    if (columns.isEmpty()) {
      throw new IllegalArgumentException("Table must have at least one column");
    }

    // Ensure no duplicate column names
    Set<String> columnNames = columns.stream()
      .map(Column::getName)
      .collect(Collectors.toSet());

    if (columnNames.size() != columns.size()) {
      throw new IllegalArgumentException("Duplicate column names are not allowed");
    }
    
    return new Table(name, columns);
  }
}
