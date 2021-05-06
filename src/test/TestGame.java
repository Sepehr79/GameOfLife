package test;

import com.game.GameLife;
import org.junit.Assert;
import org.junit.Test;

public class TestGame {

    @Test
    public void testCloningGame(){

        boolean[][] arr = {
                {false, true, false},
                {false, true, false},
                {false, true, false}
        };

        GameLife gameLife = new GameLife(3);
        gameLife.setIndex(arr);

        GameLife copied = (GameLife) gameLife.clone();

        System.out.println("Before copy:");
        System.out.println(gameLife);
        System.out.println(copied);

        // Change array index in gamelife it, index of copied should not change!
        gameLife.getIndex()[0][0] = true;
        gameLife.getIndex()[1][1] = false;

        Assert.assertTrue(gameLife.getIndex()[0][0]);
        Assert.assertFalse(copied.getIndex()[0][0]);

        Assert.assertFalse(gameLife.getIndex()[1][1]);
        Assert.assertTrue(copied.getIndex()[1][1]);

        System.out.println("After copy:");
        System.out.println(gameLife);
        System.out.println(copied);
    }

    @Test
    public void testExecute() throws InterruptedException {

        GameLife game = new GameLife(15);
        game.setRandomPosition(.1);

        Thread thread = new Thread(game);

        thread.start();

        thread.join();
    }

}
