package ie.mavon.sqlitemodel;

import android.util.Pair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.LinkedHashMap;

import ie.mavon.sqlitemodel.Models.Employee;

import static org.junit.Assert.*;

/**
 * Unit tests for SQLiteModel for Android
 */
@RunWith(RobolectricTestRunner.class)
public class SQLiteModelUnitTest {

    @Before
    public void setUp() {
        SQLiteModel.setDB(new TestDBHelper(RuntimeEnvironment.application, "testDb", null, 1));

        SQLiteModel.getDbHandler().getWritableDatabase().execSQL("DROP TABLE IF EXISTS employees");
        SQLiteModel.getDbHandler().getWritableDatabase().execSQL(
                "CREATE TABLE employees (_id INTEGER PRIMARY KEY, " +
                        "name TEXT, " +
                        "surname TEXT)");

        SQLiteModel.getDbHandler().getWritableDatabase().execSQL("INSERT INTO employees ('name', 'surname') VALUES ('John', 'Smith')");
        SQLiteModel.getDbHandler().getWritableDatabase().execSQL("INSERT INTO employees ('name', 'surname') VALUES ('Joe', 'Blogs')");
        SQLiteModel.getDbHandler().getWritableDatabase().execSQL("INSERT INTO employees ('name', 'surname') VALUES ('Matt', 'Brau')");
        SQLiteModel.getDbHandler().getWritableDatabase().execSQL("INSERT INTO employees ('name', 'surname') VALUES ('Niamh', 'Wager')");
        SQLiteModel.getDbHandler().getWritableDatabase().execSQL("INSERT INTO employees ('name', 'surname') VALUES ('Grant', 'Walsh')");
    }

    @Test
    public void getDatabaseName() throws Exception {
        assertEquals("testDb", SQLiteModel.getDbHandler().getDatabaseName());
    }

    @Test
    public void testExists()
    {
        Employee employee = new Employee();
        employee.setName("Joe");
        assertTrue(employee.save());

        assertTrue(employee.exists());
    }

    @Test
    public void testCount()
    {
        Assert.assertEquals(5, new Employee().count());
    }

    @Test
    public void testNextAutoIncrement()
    {
        Assert.assertEquals(6, new Employee().getNextAutoIncrement());
    }

    @Test
    public void testGetOneById()
    {
        Employee matt = (Employee) new Employee().getOneById(3);
        Assert.assertEquals("Matt", matt.getName());
        Assert.assertEquals("Brau", matt.getSurname());
        Assert.assertEquals(3, matt.getId());
    }

    @Test
    public void testGetItemsForSelect()
    {
        LinkedHashMap items = new Employee().getItemsForSelect("name");

        Assert.assertEquals(5, items.size());

        //TODO test each item/ordering etc..
    }

    @Test
    public void testGetItem()
    {
        Employee niamh = (Employee) new Employee().getItem(new Pair<String, String>("name", "Niamh"));

        Assert.assertEquals(4, niamh.getId());
        Assert.assertEquals("Niamh", niamh.getName());
        Assert.assertEquals("Wager", niamh.getSurname());
    }

    @Test
    public void testDelete()
    {
        Employee first = (Employee) new Employee().getOneById(1);
        Assert.assertEquals(true, first.delete());
        Assert.assertEquals(4, new Employee().count());
        Assert.assertEquals(6, new Employee().getNextAutoIncrement());
        Assert.assertEquals(null, new Employee().getOneById(1));
    }
}