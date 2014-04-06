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
        String testResource = "<class type=\"resource.GameSettings\">\n<fieldSize>"
                + this.fieldSize + "</fieldSize>\n</class>";
        this.writeFile(fileRes.getPath() + "/testRes.xml", testResource);
    }

    @AfterTest
    public void tearDown() throws Exception {
        this.deleteFile(this.fileRes);
    }

    @Test
    public void testGetResource() throws Exception {
        GameSettings gameSettings = (GameSettings) ResourceFactory.instanse().getResource(fileRes.getPath() + "/testRes.xml");
        Assert.assertTrue(gameSettings.getFieldSize() == this.fieldSize);
    }
}
