package utils;

import gameClasses.Field;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Observable;

/**
 * Created by Александр on 29.03.14.
 */
public class ReflectionHelperTest {

    private class TestClass{

        private String testStringField;
        private int testIntField;

        public String getTestStringField()
        {
            return this.testStringField;
        }
        public int getTestIntField()
        {
            return this.testIntField;
        }
    }

    private TestClass testClass;

    @BeforeTest
    public void setUp() throws Exception {
        this.testClass = new TestClass();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateInstance(){
        Object object = ReflectionHelper.createInstance("TestNameClass");
        Assert.assertNull(object);
    }

    @Test
    public void testSetFieldValue() throws Exception {
        ReflectionHelper.setFieldValue(this.testClass, "testStringField", "test");
        Assert.assertTrue(this.testClass.getTestStringField().equals("test"));
        ReflectionHelper.setFieldValue(this.testClass, "testIntField", "5");
        Assert.assertTrue(this.testClass.getTestIntField() == 5);
    }
}
