package ie.mavon.sqlitemodel.Models;

import java.util.HashMap;
import java.util.Map;

import ie.mavon.sqlitemodel.SQLiteModel;

/**
 * Test Employee Class
 */
public class Employee extends SQLiteModel {

    private static Map<String, Integer> dbColumns = null;

    private String name,
        surname;

    @Override
    public Map<String, Integer> getDbColumns() {
        if (dbColumns == null) {
            dbColumns = new HashMap<>();

            dbColumns.put("name", TYPE_TEXT);
            dbColumns.put("surname", TYPE_TEXT);

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
