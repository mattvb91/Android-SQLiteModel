# Android-SQLiteModel
Basic SQLite ORM library for Android

[![Download](https://api.bintray.com/packages/mattvb91/libraries/SQLiteModel/images/download.svg) ](https://bintray.com/mattvb91/libraries/SQLiteModel/_latestVersion)
[![Build Status](https://travis-ci.org/mattvb91/Android-SQLiteModel.svg?branch=master)](https://travis-ci.org/mattvb91/Android-SQLiteModel)

### Basic Usage

SQLiteModel allows you to make saving/retrieving objects to sqlite very easy:

Employee Class:

```java
public class Employee extends SQLiteModel {

    private static Map<String, Integer> dbColumns = null;

    private String name;

    @Override
    public Map<String, Integer> getDbColumns() {
        if (dbColumns == null) {
            dbColumns = new HashMap<>();

            dbColumns.put("name", TYPE_TEXT);

            SQLiteModel.setDBColumns(dbColumns);
        }

        return dbColumns;
    }

    @Override
    protected String getTableName() {
        return "employees";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

```

```java
Employee employee = new Employee();
employee.setName("Joe Blogs";
employee.save(); //Persisted to the db
```
