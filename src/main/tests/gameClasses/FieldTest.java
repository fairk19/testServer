package gameClasses;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Александр on 29.03.14.
 */
public class FieldTest {

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testField(){
        Field field = new Field(Field.checker.black);
        Assert.assertFalse(field.isEmpty());

        field.clear();
        Assert.assertTrue(field.isEmpty());
    }
}
