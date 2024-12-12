package com.example.sql_database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Table {
  private String name;
  private List<Column> columns;
  private List<Map<String, Object>> rows;
                // col name, value

  Table(String name, List<Column> columns) {
    this.name = name;
    this.columns = columns;
    this.rows = new ArrayList<>();
  }

  public String getName() { return name; }
  public List<Column> getColumns() { return columns; }
  public List<Map<String, Object>> getRows() { return new ArrayList<>(rows); }

  public void insertRow(Map<String, Object> row) {
    validateRow(row);
    rows.add(new HashMap<>(row));
  }

  public void validateRow(Map<String, Object> row) {
    // Validate number of columns
    if (row.size() != columns.size()) {
      throw new IllegalArgumentException("Invalid row: Incorrect number of columns");
    }

    // Validate each column -> row object datatype should same as column data type
    for(Column column : columns) {
      Object value = row.get(column.getName());
      if(value == null || !column.validate(value)) {
        throw new IllegalArgumentException("Invalid value for column: " + column.getName());
      }
    }
  }

  public List<Map<String, Object>> select(Predicate<Map<String, Object>> predicate) {
    return rows.stream().filter(predicate).collect(Collectors.toList());
  }
}
