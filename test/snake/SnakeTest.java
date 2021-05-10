package snake;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class SnakeTest {

	Field field;
	Snake snake;
	Apple apple;
	Game game;
	Cell[][] matrix;
    HighScoresBtn hsb;
    HighScore h;

	@Before
	public void setUp() {
        field = new Field(20, 20);
        snake = new Snake(10, 10, 'U');
        matrix = field.getMatrix();
	}

	@Test
	public void testEmptyField() {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                Assert.assertEquals('e', matrix[y][x].getType());
            }
        }
	}

	@Test
	public void testSetCell() {
		field.setCell(10, 10, 'o');
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
            	if (x == 10 && y == 10) {
            		Assert.assertEquals('o', matrix[y][x].getType());
            	} else {
                    Assert.assertEquals('e', matrix[y][x].getType());
            	}
            }
        }
	}

	@Test
	public void testGetCell() {
        Assert.assertEquals(matrix[12][7], field.getCell(7, 12));
	}

	@Test
	public void testGetSize() {
        Assert.assertArrayEquals(new int[] {20, 20}, field.getSize());
    }

    @Test
    public void testSnakePos() {
        int[] p = snake.getPos();
        Assert.assertArrayEquals(new int[] {10, 10}, p);
        Assert.assertEquals('U', snake.getDir());
    }

    @Test
    public void testSnakeBody() {
        ArrayList<int[]> body = snake.getBody();
        Assert.assertArrayEquals(new int[] {10, 11}, body.get(0));
        Assert.assertArrayEquals(new int[] {10, 12}, body.get(1));
    }

    @Test
    public void testSnakeMove() {
        snake.move('L');
        ArrayList<int[]> body = snake.getBody();
        Assert.assertArrayEquals(new int[] {9, 10}, snake.getPos());
        Assert.assertArrayEquals(new int[] {10, 10}, body.get(0));
        Assert.assertArrayEquals(new int[] {10, 11}, body.get(1));
    }

    @Test
    public void testSnakeGrow() {
        snake.grow();
        ArrayList<int[]> body = snake.getBody();
        Assert.assertArrayEquals(new int[] {10, 12}, body.get(2));
        Assert.assertEquals(3, snake.getLength());
    }

    @Test
    public void testGame() {
        game = new Game(field, snake, 0);
        game.updateField();
        matrix = field.getMatrix();
        Assert.assertEquals('U', matrix[10][10].getType());
        Assert.assertEquals('s', matrix[11][10].getType());
        Assert.assertEquals('s', matrix[12][10].getType());
    }

    @Test
    public void testRandomSpace() {
        game = new Game(field, snake, 0);
        int[] rSpace = game.randomSpace();
        Assert.assertTrue(field.getCell(rSpace[0], rSpace[1]).getType() == 'e');
        Assert.assertTrue(game.isInside(rSpace));
    }

    @Test
    public void testSaveHighScore() {
        game = new Game(field, snake, 0);
        try {
            game.saveHighScore(new HighScore("test", 10));
            ArrayList<HighScore> hs = HighScoresBtn.getHighScores();
            ArrayList<HighScore> test = new ArrayList<>();
            test.add(new HighScore("test", 10));
            Assert.assertEquals(test.get(0).getPts(), hs.get(0).getPts());
            Assert.assertEquals(test.get(0).getName(), hs.get(0).getName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testApple() {
        apple = new Apple(new int[] {0, 0});
        Assert.assertArrayEquals(new int[] {0, 0}, apple.getPos());
        apple.setPos(new int[] {12, 5});
        Assert.assertArrayEquals(new int[] {12, 5}, apple.getPos());
    }
}
