package animalchess;

/**
 * square in chess board.
 * @author Xy
 */
public class Square {
    private static final int MIN_ROW_COL = 0;
    private static final int MAX_ROW = 5;
    private static final int MAX_COL = 4;

    private static final int PROMOTION_ZONE_0 = 4;
    private static final int PROMOTION_ZONE_1 = 1;
    private static final int PLAYER_0 = 0;
    private static final int PLAYER_1 = 1;

    private final Game game;
    private Piece piece;
    private final int row;
    private final int col;

    /**
     * constructor.
     * @param game game which start.
     * @param row the row of this square.
     * @param col the column of this square.
     */
    public Square(Game game, int row, int col) {
        if (row >= MIN_ROW_COL && row <= MAX_ROW && col >= MIN_ROW_COL && col <= MAX_COL) {
            this.game = game;
            this.row = row;
            this.col = col;
        } else {
            throw new IllegalArgumentException("You should put the piece into chess board.");
        }
    }

    /**
     * did not used, wait for future.
     * @param game game which start.
     * @param row the row of this square.
     * @param col the column of this square.
     * @param promotesPlayer super power?
     */
    public Square(Game game, int row, int col, Player promotesPlayer) {
        if (row >= MIN_ROW_COL && row <= MAX_ROW && col >= MIN_ROW_COL && col <= MAX_COL) {
            this.game = game;
            this.row = row;
            this.col = col;
        } else {
            throw new IllegalArgumentException("You should put the piece into chess board.");
        }
    }

    /**
     * place piece.
     * @param piece the piece player want to put.
     */
    public void placePiece(Piece piece) {
        if (this.piece == null) {
            this.piece = piece;
        } else {
            throw new IllegalArgumentException("This square is already have a piece");
        }
    }

    /**
     * remove piece from chess board.
     */
    public void removePiece() {
        this.piece = null;
    }

    /**
     * judgment is the zone that could get the super power.
     * @param player whose pieces.
     * @return true or false.
     */
    public boolean isPromotionZone(Player player) {
        return (player.getPlayerNumber() == PLAYER_0 && this.row >= PROMOTION_ZONE_0 && this.row <= MAX_ROW)
                || (player.getPlayerNumber() == PLAYER_1 && this.row <= PROMOTION_ZONE_1 && this.row >= MIN_ROW_COL);
    }

    /**
     * get game.
     * @return game
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * get piece.
     * @return piece.
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * get row.
     * @return row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * get column.
     * @return column.
     */
    public int getCol() {
        return this.col;
    }
}
