package animalchess;

/**
 * A square represent a position in the board.
 * @author Xiuping Fan
 */
public class Square {

    private int row;
    private int col;
    private Game game;
    private Player player = null;
    private Piece piece = null;

    /**
     * Constructor.
     * @param game is the instance of the core class Game
     * @param row is the row of the square
     * @param col is the column of the square
     */
    public Square(Game game, int row, int col) {
        this.row = row;
        this.col = col;
        this.game = game;
    }

    /**
     * Constructor.
     * @param game is the instance of the core class Game
     * @param row is the row of the square
     * @param col is the column of the square
     * @param promotesPlayer is the player whose piece will be promoted
     */
    public Square(Game game, int row, int col, Player promotesPlayer) {
        this(game, row, col);
        this.player = promotesPlayer;
    }

    /**
     * Place the piece at the square.
     * @param piece which is about to be put
     */
    public void placePiece(Piece piece) {
        if (this.piece == null) {
            this.piece = piece;
        } else if (!this.piece.equals(piece)) {
            if (this.piece.getOwner().equals(piece.getOwner())) {
                throw new IllegalArgumentException("This square is already occupied by own piece");
            } else {
                this.piece.beCaptured(piece.getOwner());
                this.piece = piece;
            }
        }
    }

    /**
     * Remove the piece from the square.
     */
    public void removePiece() {
        this.piece = null;
    }

    /**
     * Judge this square is whether in Promotion Zone for cat and chick.
     * @param player is the person whose piece will be promoted
     * @return true if this square is in Promotion Zone
     */
    public boolean isPromotionZone(Player player) {
        if (player.getPlayerNumber() == 0 && (row == 0 || row == 1)) {
            return true;
        }
        if (player.getPlayerNumber() == 1 && (row == 4 || row == 5)) {
            return true;
        }
        return false;
    }

    /**
     * This is a getter.
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * This is a getter.
     * @return piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * This is a getter.
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * This is a getter.
     * @return col
     */
    public int getCol() {
        return col;
    }

}
