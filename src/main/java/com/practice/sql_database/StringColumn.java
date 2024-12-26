package com.practice.sql_database;

public class StringColumn extends Column{
  public StringColumn(String name) {
    super(name, ColumnType.STRING);
  } 

  @Override
  public boolean validate(Object value) {
    return value instanceof String;
  }
}
