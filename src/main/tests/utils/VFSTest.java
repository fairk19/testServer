package utils;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Александр on 25.03.14.
 */
public class VFSTest {

    private Map<String, String> testFilePaths = new HashMap();

    @BeforeTest
    public void setUpBeforeTest() throws Exception {
        testFilePaths.put("Error", "/");
        testFilePaths.put("Error", "/empty");
        testFilePaths.put("Error", "/testFile");
        testFilePaths.put("Success", "/testDir/testFile");
    }

    @BeforeMethod
    public void setUp() throws Exception {

    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testIsExist() throws Exception {
        Assert.assertTrue(true);
    }

    @Test
    public void testIsFile() throws Exception {
        Assert.assertTrue(true);

    }

    @Test
    public void testGetAbsolutePath() throws Exception {
        Assert.assertTrue(true);

    }

    @Test
    public void testGetBytes() throws Exception {
        Assert.assertTrue(true);

    }

    @Test
    public void testGetRelativePath() throws Exception {
        Assert.assertTrue(true);

    }

    @Test
    public void testWriteToFile() throws Exception {
        Assert.assertTrue(true);

    }

    @Test
    public void testWriteToEndOfFile() throws Exception {
        Assert.assertTrue(true);

    }

    @Test
    public void testReadFile() throws Exception {

       for (String key: this.testFilePaths.keySet()){
            String testText = VFS.readFile(this.testFilePaths.get(key));
            if (key.equals("Error")){
                Assert.assertFalse(testText.equals("Test read!"));
            } else {
                System.out.println(System.err.checkError());
                Assert.assertTrue(testText.equals("Test read!"));
            }
        }
    }

    @Test
    public void testBfs() throws Exception {
        List<File> testFiles = VFS.bfs("\\testDirectory");
     }
}
