package animalchess;

/**
 * play game, the start.
 * @author Xy
 */
public class Game {
    /**
     * oh dear.
     */
    public static final int HEIGHT = 6;
    /**
     * whole chess board.
     */
    public static final int WIDTH = 5;

    private final Player p0;
    private final Player p1;
    private final Square[][] chessBoard = new Square[HEIGHT][WIDTH];

    /**
     * initialization.
     * @param p0 up player.
     * @param p1 bottom player.
     */
    public Game(Player p0, Player p1) {
        this.p0 = p0;
        this.p1 = p1;

        for (int i = 0; i < this.chessBoard.length; i++) {
            for (int j = 0; j < this.chessBoard[i].length; j++) {
                this.chessBoard[i][j] = new Square(this, i, j);
            }
        }

        //initialization.
        new Cat(p0, this.chessBoard[0][0]);
        new Dog(p0, this.chessBoard[0][1]);
        new Lion(p0, this.chessBoard[0][2]);
        new Dog(p0, this.chessBoard[0][3]);
        new Cat(p0, this.chessBoard[0][4]);
        new Chick(p0, this.chessBoard[2][1]);
        new Chick(p0, this.chessBoard[2][2]);
        new Chick(p0, this.chessBoard[2][3]);
        new Cat(p1, this.chessBoard[5][0]);
        new Dog(p1, this.chessBoard[5][1]);
        new Lion(p1, this.chessBoard[5][2]);
        new Dog(p1, this.chessBoard[5][3]);
        new Cat(p1, this.chessBoard[5][4]);
        new Chick(p1, this.chessBoard[3][1]);
        new Chick(p1, this.chessBoard[3][2]);
        new Chick(p1, this.chessBoard[3][3]);
    }

    /**
     * got player.
     * @param playerNumber player ID.
     * @return a player.
     */
    public Player getPlayer(int playerNumber) {
        if (this.p0.getPlayerNumber() == playerNumber) {
            return this.p0;
        } else if (this.p1.getPlayerNumber() == playerNumber) {
            return this.p1;
        } else {
            throw new IllegalArgumentException("Please enter correct player number, only 0 and 1. "
                    + "Player0 sits at the top of the board, and player1 sits at the bottom of the board.");
        }
    }

    /**
     * got the lucky guy.
     * @return who win.
     */
    public Player getWinner() {
        if (this.p0.hasWon()) {
            return this.p0;
        } else if (this.p1.hasWon()) {
            return this.p1;
        } else {
            return null;
        }
    }

    /**
     * got square.
     * @param row row.
     * @param col column.
     * @return a specific square.
     */
    public Square getSquare(int row, int col) {
        return this.chessBoard[row][col];
    }
}
