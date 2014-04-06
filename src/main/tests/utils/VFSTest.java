package utils;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Александр on 25.03.14.
 */
public class VFSTest {

    private Map<String, String> testFilePaths = new HashMap();
    private void writeFile(String path, String data){
        try {
            File file = new File(path);

            if(file.exists()){
                file.delete();
                file.createNewFile();
            }else {
                file.createNewFile() ;
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private boolean readFileEqualData(String path, String data){

        BufferedReader br = null;
        try {
            String contentTestFile;
            br = new BufferedReader(new FileReader(path));
            while ((contentTestFile = br.readLine()) != null){
                return contentTestFile.equals(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    private void deleteFile(File file){
        if(!file.exists())
            return;
        if(file.isDirectory())
        {
            for(File f : file.listFiles())
            {
                deleteFile(f);
            }
             file.delete();
        }
        else
        {
            file.delete();
        }
    }

    @BeforeTest
    public void setUp() throws Exception {
        testFilePaths.put("Error", "/");
        testFilePaths.put("Error", "/testFileOne");
        testFilePaths.put("Success", "/testDir/testFileOne");

        File myPath = new File(System.getProperty("user.dir") + "/testDir");
        myPath.mkdir();
        myPath.mkdirs();

        this.writeFile(myPath.getPath() + "/testFileOne", "Test read!");
        this.writeFile(myPath.getPath() + "/testFileTwo", "Test read!");
    }

    @AfterTest
    public void tearDown() throws Exception {
        File myPath = new File(System.getProperty("user.dir") + "/testDir");
        this.deleteFile(myPath);
    }

    @Test
    public void testIsExist() throws Exception {
        String pathFileExist =  "\\testDir\\testFileOne";
        String pathFileNotExist =  "\\testDir\\testFileThree";

        Assert.assertTrue(VFS.isExist(pathFileExist));
        Assert.assertFalse(VFS.isExist(pathFileNotExist));
    }

    @Test
    public void testIsFile() throws Exception {
        String pathIsFile =  "\\testDir\\testFileOne";
        String pathIsNotFile =  "\\testDir";

        Assert.assertTrue(VFS.isFile(pathIsFile));
        Assert.assertFalse(VFS.isFile(pathIsNotFile));

    }

    @Test
    public void testGetAbsolutePath() throws Exception {
        String absPath = System.getProperty("user.dir") + "\\testDir\\testFileOne";
        String path = "testDir\\testFileOne";

        Assert.assertTrue(VFS.getAbsolutePath(path).equals(absPath));
        Assert.assertTrue(VFS.getAbsolutePath(absPath).equals(absPath));
    }

    @Test
    public void testGetRelativePath() throws Exception {
        String absPath = System.getProperty("user.dir") + "\\testDir\\testFileOne";
        String path = "testDir\\testFileOne";

        Assert.assertTrue(VFS.getRelativePath(path).equals(path));
        Assert.assertTrue(VFS.getRelativePath(absPath).equals(path));
    }

    @Test
    public void testWriteToFile() throws Exception {
        String absPath = System.getProperty("user.dir") + "\\testDir\\testFileWrite";
        String path = "\\testDir\\testFileWrite";
        this.writeFile(absPath, "begin text");
        VFS.writeToFile(path, "one text");
        Assert.assertTrue(this.readFileEqualData(absPath, "one text"));

        path = "/testDir/testDir1/testDir2/testFileWrite";
        VFS.writeToFile(path, "one text");
        Assert.assertTrue(this.readFileEqualData(absPath, "one text"));
    }

    @Test
    public void testWriteToEndOfFile() throws Exception {
        String absPath = System.getProperty("user.dir") + "\\testDir\\testFileWriteToEnd";
        String path = "\\testDir\\testFileWriteToEnd";
        this.writeFile(absPath, "begin text");
        VFS.writeToEndOfFile(path, " end");
        Assert.assertTrue(this.readFileEqualData(absPath, "begin text end"));
    }

    @Test
    public void testReadFile() throws Exception {

       for (String key: this.testFilePaths.keySet()){
            String testText = VFS.readFile(this.testFilePaths.get(key));
            if (key.equals("Error")){
                Assert.assertFalse(testText.equals("Test read!"));
            } else {
                Assert.assertTrue(testText.equals("Test read!"));
            }
        }
    }

    @Test
    public void testBfs() throws Exception {
        String absPath = System.getProperty("user.dir") + "\\testDir\\testFileOne";
        File file = new File(absPath);
        List<File> testFiles = VFS.bfs("\\testDir");
        Assert.assertTrue(testFiles.contains(file));
    }

}

