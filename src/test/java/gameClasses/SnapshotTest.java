package gameClasses;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Александр on 29.03.14.
 */
public class SnapshotTest {

    private Field[][] field;
    private char color;
    private char next;
    private int fieldSize;

    @BeforeTest
    public void setUp() throws Exception {
        this.color = 'b';
        this.fieldSize = 5;
        this.next = 'w';
        this.field = new Field[this.fieldSize][this.fieldSize];
        for(int i = 0; i < this.fieldSize; i++){
            for (int j = 0; j < this.fieldSize; j++){
                field[i][j] = new Field();
            }
        }
    }

    @Test
    public void testToString() throws Exception {
        Snapshot snapshot = new Snapshot(this.field, this.color, this.fieldSize, this.next);
        int indexStr = snapshot.toString().indexOf("\"color\":\"b\"");
        boolean strIsExist = false;
        if (indexStr > 0)
            strIsExist = true;
        Assert.assertTrue(strIsExist);
        strIsExist = false;
        int indexStrTest = snapshot.toStringTest().indexOf("'next':'w'");
        if (indexStrTest > 0)
            strIsExist = true;
        Assert.assertTrue(strIsExist);
    }

    @Test
    public void testToStringTest() throws Exception {

    }
}
