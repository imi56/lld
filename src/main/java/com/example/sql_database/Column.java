package com.example.sql_database;

public abstract class Column {
  private String name;
  private ColumnType type;

  Column(String name, ColumnType type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public ColumnType getType() {
    return type;
  }

  public abstract boolean validate(Object value);
} 
