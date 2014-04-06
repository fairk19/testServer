package utils;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

import java.io.*;
import java.lang.reflect.Field;


/**
 * Created by Александр on 24.03.14.
 */
public class SysInfoTest {

    private SysInfo sysInfo;
    private String pathMemoryUsage;
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
    private String readFile(String path){

        BufferedReader br = null;
        try {
            String contentTestFile;
            br = new BufferedReader(new FileReader(path));
            while ((contentTestFile = br.readLine()) != null){
                return contentTestFile;
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
        return null;

    }

    @BeforeTest
    public void setUpBeforeTest() throws Exception {
        this.pathMemoryUsage = System.getProperty("user.dir") + "\\statistic\\MemoryUsage";
        this.sysInfo = new SysInfo();
        Field fieldTimeOut = this.sysInfo.getClass().getDeclaredField("timeOut");
        fieldTimeOut.setAccessible(true);
        fieldTimeOut.setInt(this.sysInfo, 10);
    }

    @Test(timeOut = 500, expectedExceptions = ThreadTimeoutException.class, groups = "testRun")
    public void testRunCall() throws Exception {
        this.sysInfo.run();
    }

    @Test(groups = "testAfterRun", dependsOnGroups = "testRun")
    public void testRunAfterCall() throws Exception {
        String memoryUsage = this.readFile(this.pathMemoryUsage);
        Assert.assertTrue(!memoryUsage.isEmpty());
    }

    @Test(dependsOnGroups = "testAfterRun")
    public void testGetStat() throws Exception {

        File file = new File(this.pathMemoryUsage);
        Assert.assertTrue(file.exists());
        this.writeFile(pathMemoryUsage, "test memory usage");
        String memoryUsage = SysInfo.getStat("MemoryUsage");
        Assert.assertTrue(memoryUsage.equals("[test memory usage]"));
    }
}
