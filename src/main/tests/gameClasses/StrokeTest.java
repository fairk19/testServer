package gameClasses;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.SysInfo;

/**
 * Created by Александр on 29.03.14.
 */
public class StrokeTest {

    private int toX = 1;
    private int toY = 2;
    private int fromX = 2;
    private int fromY = 1;
    private String status = "win";
    private String colorB = "b";
    private String colorW = "w";

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testInstance(){
        Stroke strokeFull = new Stroke(toX, toY, fromX, fromY, status, colorB);
        Assert.assertTrue(strokeFull.getTo_X() == toX);
        Assert.assertTrue(strokeFull.getTo_Y() == toY);
        Assert.assertTrue(strokeFull.getFrom_X() == fromX);
        Assert.assertTrue(strokeFull.getFrom_Y() == fromY);
        Assert.assertFalse(strokeFull.isEmpty());

        Assert.assertTrue(strokeFull.getColor().equals(colorB));
        strokeFull = strokeFull.getInverse();
        Assert.assertTrue(strokeFull.getColor().equals(colorW));

        strokeFull.clear();
        Assert.assertTrue(strokeFull.isEmpty());

        Stroke strokeNew = new Stroke(strokeFull);
        strokeNew.setColor(colorW);
        strokeNew = strokeNew.getInverse();
        Assert.assertTrue(strokeNew.getColor().equals(colorB));

        Stroke strokeEmpty = new Stroke();
        Assert.assertTrue(strokeEmpty.isEmpty());

        Stroke strokeSt = new Stroke(status);
        Assert.assertTrue(strokeSt.getStatus().equals(status));
    }
}
