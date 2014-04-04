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

    private void kingSteps(GameSession gameSession){
        GameSession.codeError codeError = this.gameSession.checkStroke(this.id1, 6, 5, 7, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id2, 0, 5, 1, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id1, 5, 6, 6, 5);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id2, 4, 5, 5, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id1, 4, 5, 5, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id2, 1, 4, 3, 2);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id1, 3, 6, 5, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id2, 3, 6, 4, 5);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id1, 5, 4, 6, 3);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id2, 2, 5, 3, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id1, 6, 3, 7, 2);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id2, 2, 7, 3, 6);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id1, 7, 2, 5, 0);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
        codeError = this.gameSession.checkStroke(this.id2, 4, 5, 2, 3);
        Assert.assertTrue(codeError.equals(GameSession.codeError.success));
    }
    @BeforeMethod
    public void setUp() throws Exception {
        this.gameSession = new GameSession(id1, id2);
        this.clazz = this.gameSession.getClass();
    }

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
        kingSteps(this.gameSession);
        codeError = this.gameSession.checkStroke(this.id1, 5, 0, 1, 4);
        Assert.assertTrue(codeError.equals(GameSession.codeError.notCheckEating));

        this.gameSession = new GameSession(id1, id2);
        kingSteps(this.gameSession);
        codeError = this.gameSession.checkStroke(this.id1, 5, 0, 3, 2);
        System.out.println(codeError);
    }
    @Test
    public void testGetNext() throws Exception {
        this.gameSession = new GameSession(this.id1, this.id2);
        char color = this.gameSession.getNext();
        System.out.println(color);
    }
}
