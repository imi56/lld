package com.practice.sql_database;

class IntegerColumn extends Column {
  public IntegerColumn(String name) {
    super(name, ColumnType.INTEGER);
  } 

  @Override
  public boolean validate(Object value) {
    return value instanceof Integer;
  }
}
