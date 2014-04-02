package gameMechanic;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resource.GameSettings;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import gameClasses.Field.checker;

/**
 * Created by Александр on 30.03.14.
 */
public class GameSessionTest {

    private GameSession gameSession;
    private Class clazz;
    private final int id1 = 1;
    private final int id2 = 2;

    @BeforeMethod
    public void setUp() throws Exception {
        this.gameSession = new GameSession(id1, id2);
        this.clazz = this.gameSession.getClass();
    }

//    @Test
//    public void testGameSession() throws Exception{
//
//        Field currentPositionsField = clazz.getDeclaredField("currentPositions");
//        currentPositionsField.setAccessible(true);
//        gameClasses.Field[][] currentPosition = (gameClasses.Field[][])currentPositionsField.get(this.gameSession);
//        Field settingsField = clazz.getDeclaredField("settings");
//        settingsField.setAccessible(true);
//        GameSettings gameSettings = (GameSettings)settingsField.get(this.gameSession);
//        int emptyField = gameSettings.getFieldSize()/2;
//
//        Assert.assertTrue(currentPosition.length == gameSettings.getFieldSize());
//        for(int i = 0; i < gameSettings.getFieldSize(); i++){
//            Assert.assertTrue(currentPosition[emptyField][i].getType().equals(gameClasses.Field.checker.nothing));
//        }
//        for(int i = 0; i < gameSettings.getFieldSize(); i+=2)
//            Assert.assertTrue(currentPosition[0][i].getType().equals(gameClasses.Field.checker.white));
//
//        for(int i = 1; i < gameSettings.getFieldSize(); i+=2){
//            Assert.assertTrue(currentPosition[gameSettings.getFieldSize()-1][i].getType().equals(gameClasses.Field.checker.black));
//        }
//
//    }
//
//    @Test
//    public void testAnotherColor() throws Exception{
//        Class[] paramTypes = new Class[] {checker.class};
//        Method method = this.clazz.getDeclaredMethod("getAnotherColor", paramTypes);
//        method.setAccessible(true);
//        Object[] args = new Object[] {checker.black};
//        checker testColor = (checker)method.invoke(this.gameSession, args);
//        Assert.assertTrue(testColor.equals(checker.white));
//
//        args = new Object[] {checker.white};
//        testColor = (checker)method.invoke(this.gameSession, args);
//        Assert.assertTrue(testColor.equals(checker.black));
//
//        args = new Object[] {checker.nothing};
//        testColor = (checker)method.invoke(this.gameSession, args);
//        Assert.assertTrue(testColor.equals(checker.nothing));
//    }

    @Test
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
        //сходить шашками и привести к состоянию, когда можно есть
        System.out.println(codeError);
    }
}
