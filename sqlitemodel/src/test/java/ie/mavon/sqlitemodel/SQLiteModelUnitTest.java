package ie.mavon.sqlitemodel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

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
        SQLiteModel.getDbHandler().getWritableDatabase().execSQL("CREATE TABLE employees (_id INTEGER PRIMARY KEY, name TEXT)");
    }

    @Test
    public void getDatabaseName() throws Exception {
        assertEquals("testDb", SQLiteModel.getDbHandler().getDatabaseName());
    }

    @Test
    public void testExists()
    {
        Employee employee = new Employee();
        employee.setName("Joe Blogs");
        assertTrue(employee.save());

        assertTrue(employee.exists());

    }
}