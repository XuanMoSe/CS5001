package animalchess;

import java.util.ArrayList;

/**
 * Core class. Use to initial the whole data.
 * @author Xiuping Fan
 */
public class Game {

    /** The height of the board. */
    public static final int HEIGHT = 6;
    /** The width of the board. */
    public static final int WIDTH = 5;
    private Player p0;
    private Player p1;
    private ArrayList<Square> squareList = new ArrayList<Square>();

    /**
     * Constructor.
     * @param p0 is the first player
     * @param p1 is the second player
     */
    public Game(Player p0, Player p1) {
        this.p0 = p0;
        this.p1 = p1;
        initialSquareListAndPieces();
    }

    /**
     * Only be used in constructor. Use to initial SquareList and Pieces
     */
    private void initialSquareListAndPieces() {
        // initial Pieces, i is raw, j is column
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Square temp = new Square(this, i, j);
                // initial SquareList
                squareList.add(temp);
                if (i == 0) {
                    switch (j) {
                    case 0:
                    case 4:
                        p0.dropPiece(new Cat(p0, temp), temp);
                        break;
                    case 1:
                    case 3:
                        p0.dropPiece(new Dog(p0, temp), temp);
                        break;
                    case 2:
                        p0.dropPiece(new Lion(p0, temp), temp);
                        break;
                    default:
                        break;
                    }
                } else if (i == 2 && (j == 1 || j == 2 || j == 3)) {
                    p0.dropPiece(new Chick(p0, temp), temp);
                } else if (i == 3 && (j == 1 || j == 2 || j == 3)) {
                    p1.dropPiece(new Chick(p1, temp), temp);
                } else if (i == 5) {
                    switch (j) {
                    case 0:
                    case 4:
                        p1.dropPiece(new Cat(p1, temp), temp);
                        break;
                    case 1:
                    case 3:
                        p1.dropPiece(new Dog(p1, temp), temp);
                        break;
                    case 2:
                        p1.dropPiece(new Lion(p1, temp), temp);
                        break;
                    default:
                        break;
                    }
                }
            }
        }
    }

    /**
     * This is a getter.
     * @param playerNumber is the number of the player
     * @return Player who has this playernumber
     */
    public Player getPlayer(int playerNumber) {
        if (p0.getPlayerNumber() == playerNumber) {
            return p0;
        } else if (p1.getPlayerNumber() == playerNumber) {
            return p1;
        } else {
            throw new IllegalArgumentException("playerNumber should be 0 or 1");
        }
    }

    /**
     * This is a getter.
     * @return Player
     */
    public Player getWinner() {
        if (p0.hasWon() && !p1.hasWon()) {
            return p0;
        } else if (!p0.hasWon() && p1.hasWon()) {
            return p1;
        }
        return null;
    }

    /**
     * This is a getter.
     * @param row of the square
     * @param col of the square
     * @return Square
     */
    public Square getSquare(int row, int col) {
        for (int i = 0; i < squareList.size(); i++) {
            if (squareList.get(i).getRow() == row && squareList.get(i).getCol() == col) {
                return squareList.get(i);
            }
        }
        throw new IllegalArgumentException("parameter is wrong, it should be: 0<=row<=5, 0<=col<=4");
    }

}
