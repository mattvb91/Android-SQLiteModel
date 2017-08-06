package ie.mavon.sqlitemodel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;

/**
 * Unit tests for SQLiteModel for Android
 */
@RunWith(RobolectricTestRunner.class)
public class SQLiteModelUnitTest {

    @Before
    public void setUp() {
        SQLiteModel.setDB(new TestDBHelper(RuntimeEnvironment.application, "testDb", null, 1));
    }

    @Test
    public void getDatabaseName() throws Exception {
        assertEquals("testDb", SQLiteModel.getDbHandler().getDatabaseName());
    }
}