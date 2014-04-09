package resource;

import org.testng.Assert;
import org.testng.annotations.*;
import utils.SysInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Александр on 29.03.14.
 */
public class ResourceFactoryTest {

    private String absPathRes;
    private int fieldSize;
    private File fileRes;

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
        this.fieldSize = 5;
        this.absPathRes = System.getProperty("user.dir") + "/testResDir";
        this.fileRes = new File(absPathRes);
        fileRes.mkdir();
        fileRes.mkdirs();
        String testResource = "<class type=\"resource.Rating\">\n" +
                "<maxDiff>15</maxDiff>\n" +
                "<avgDiff>8</avgDiff>\n" +
                "<minDiff>1</minDiff>\n" +
                "<decreaseThreshold>15</decreaseThreshold>\n" +
                "</class>";
        this.writeFile(fileRes.getPath() + "/testRes.xml", testResource);
    }

    @AfterTest
    public void tearDown() throws Exception {
        this.deleteFile(this.fileRes);
    }

    @Test(groups = "readResource")
    public void testGetResource() throws Exception {
        ResourceFactory.instanse().getResource(fileRes.getPath() + "/testRes.xml");
        Assert.assertTrue(Rating.getAvgDiff() == 8);
    }

    @Test(dependsOnGroups = "readResource")
    public void testRating() throws Exception {
       Assert.assertTrue(Rating.getDiff(20, 2) == 7);

        Rating.decreaseThreshold = 0;
        Assert.assertTrue(Rating.getDiff(1, 2) == 8);
    }
}
