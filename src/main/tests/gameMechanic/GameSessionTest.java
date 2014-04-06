package gameMechanic;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.testng.Assert;
import org.testng.annotations.*;
import resource.GameSettings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import gameClasses.Field.checker;

/**
 * Created by Александр on 30.03.14.
 */
public class GameSessionTest {

    private GameSession gameSession;
    private final int id1 = 1;
    private final int id2 = 2;
    private String pathLog = "";

    private void stepsToKing(GameSession gameSession){
        GameSession.codeError codeError = gameSession.checkStroke(this.id1, 6, 5, 7, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id2, 0, 5, 1, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id1, 5, 6, 6, 5);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id2, 4, 5, 5, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id1, 4, 5, 5, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id2, 1, 4, 3, 2);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id1, 3, 6, 5, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id2, 3, 6, 4, 5);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id1, 5, 4, 6, 3);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id2, 2, 5, 3, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id1, 6, 3, 7, 2);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id2, 2, 7, 3, 6);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id1, 7, 2, 5, 0);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = gameSession.checkStroke(this.id2, 4, 5, 2, 3);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
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
        return "";
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
    public void setUpBeforeTest() throws Exception {
        this.gameSession = new GameSession(id1, id2);
    }

    @AfterTest
    public void tearDown() throws Exception {
        if (!this.pathLog.equals(""))
            this.deleteFile(new File(this.pathLog).getParentFile());
    }

    @Test(groups = "testSteps")
    public void testCheckStroke() throws Exception {
        GameSession.codeError codeError = this.gameSession.checkStroke(this.id1, 0, 5, 1, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));

        codeError = this.gameSession.checkStroke(this.id1, 1, 4, 0, 3);
        Assert.assertTrue(codeError.equals(GameSession.codeError.idEqualLastStroke));

        codeError = this.gameSession.checkStroke(this.id2, 1, 4, 0, 3);
        Assert.assertTrue(codeError.equals(GameSession.codeError.fieldTypeNotEqualPlayerColor));

        codeError = this.gameSession.checkStroke(this.id2, 0, 5, 1, 3);
        Assert.assertTrue(codeError.equals(GameSession.codeError.isOdd));

        codeError = this.gameSession.checkStroke(this.id2, 0, 5, -1, 2);
        Assert.assertTrue(codeError.equals(GameSession.codeError.isBorder));

        codeError = this.gameSession.checkStroke(this.id2, 0, 5, 1, 2);
        Assert.assertTrue(codeError.equals(GameSession.codeError.fieldTypeNotEqualNothing));

        this.gameSession = new GameSession(id1, id2);
        codeError = this.gameSession.checkStroke(this.id1, 0, 5, 2, 3);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));

        this.gameSession = new GameSession(id1, id2);
        this.gameSession.checkStroke(this.id1, 0, 5, 1, 4);
        this.gameSession.checkStroke(this.id2, 6, 5, 7, 4);
        this.gameSession.checkStroke(this.id1, 2, 5, 3, 4);
        codeError = this.gameSession.checkStroke(this.id2, 7, 4, 5, 2);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));

        this.gameSession = new GameSession(id1, id2);
        this.gameSession.checkStroke(this.id1, 0, 5, 1, 4);
        this.gameSession.checkStroke(this.id2, 6, 5, 7, 4);
        this.gameSession.checkStroke(this.id1, 2, 5, 3, 4);
        codeError = this.gameSession.checkStroke(this.id2, 7, 4, 3, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.notMakeUsualStroke));

        this.gameSession = new GameSession(id1, id2);
        codeError = this.gameSession.checkStroke(this.id2, 0, 5, 1, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.idEqualLastStroke));

        this.gameSession = new GameSession(id1, id2);
        stepsToKing(this.gameSession);
        codeError = this.gameSession.checkStroke(this.id1, 5, 0, 1, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.notCheckEating));

        //test method with used reflection
        this.gameSession = new GameSession(id1, id2);
        Class clazz = GameSession.class;
        Field currentPositionsField = clazz.getDeclaredField("currentPositions");
        currentPositionsField.setAccessible(true);
        gameClasses.Field[][] currentPositions = (gameClasses.Field[][]) currentPositionsField.get(this.gameSession);
        currentPositions[0][5].setType(checker.nothing);
        currentPositions[1][6].setType(checker.black);

        Class[] paramTypes = new Class[] { int.class, int.class, int.class, int.class};
        Method makeUsualStrokeMethod = clazz.getDeclaredMethod("makeUsualStroke", paramTypes);
        makeUsualStrokeMethod.setAccessible(true);
        Object[] args = new Object[] {6, 1, 5, 0};
        Boolean makeUsualStroke = (Boolean)makeUsualStrokeMethod.invoke(this.gameSession, args);
        Assert.assertTrue(makeUsualStroke);

        paramTypes = new Class[] { int.class, int.class};
        Method canMoveMethod = clazz.getDeclaredMethod("canMove", paramTypes);
        canMoveMethod.setAccessible(true);
        currentPositions[2][3].setType(checker.white);
        args = new Object[] {3, 2};
        Boolean canMove = (Boolean)canMoveMethod.invoke(this.gameSession, args);
        Assert.assertTrue(canMove);

        currentPositions[3][4].setType(checker.black);
        currentPositions[3][2].setType(checker.black);
        canMove = (Boolean)canMoveMethod.invoke(this.gameSession, args);
        Assert.assertFalse(canMove);

        args = new Object[] {4, 3};
        canMove = (Boolean)canMoveMethod.invoke(this.gameSession, args);
        Assert.assertTrue(canMove);

        currentPositions[2][5].setType(checker.black);
        currentPositions[2][1].setType(checker.black);
        canMove = (Boolean)canMoveMethod.invoke(this.gameSession, args);
        Assert.assertFalse(canMove);
    }

    @Test
    public void testGetNext() throws Exception {
        this.pathLog = System.getProperty("user.dir") + "/log/AI/"+String.valueOf(this.gameSession.getIdLog())+".txt";
        this.gameSession = new GameSession(this.id1, this.id2);
        char color = this.gameSession.getNext();
        Assert.assertFalse(color == 'b');
        this.gameSession.checkStroke(this.id1, 0, 5, 1, 4);
        color = this.gameSession.getNext();
        Assert.assertFalse(color == 'w');
    }

    @Test(dependsOnGroups = "testSteps")
    public void testSaveAILog() throws Exception {
        this.pathLog = System.getProperty("user.dir") + "/log/AI/"+String.valueOf(this.gameSession.getIdLog())+".txt";
        this.gameSession.saveAILog("I am winner!");
        String testStringLine = this.readFile(this.pathLog);
        Assert.assertTrue(testStringLine.equals("I am winner!"));
        this.deleteFile(new File(this.pathLog));
    }

    @Test(dependsOnGroups = "testSteps")
    public void testSaveLog() throws Exception{

        this.gameSession.saveLog(1);
        String testStringLine = this.readFile(this.pathLog);
        Assert.assertTrue(testStringLine.equals("white"));

        this.gameSession.saveLog(2);
        testStringLine = this.readFile(this.pathLog);
        Assert.assertTrue(testStringLine.equals("black"));
    }

    @Test
    public void testGetFields() throws Exception {
        this.gameSession = new GameSession(id1, id2);
        int fields[] = this.gameSession.getFields();
        Assert.assertTrue(fields.length == 24);

        this.stepsToKing(this.gameSession);
        fields = this.gameSession.getFields();
        Assert.assertTrue(fields.length == 21);
    }
}
