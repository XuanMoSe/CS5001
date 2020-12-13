package animalchess;

import java.util.ArrayList;

/**
 * piece class.
 * @author Xy
 */
public abstract class Piece {
    protected static final int ONE_ROUND_MOVE = 1;

    protected int upRow = 0;
    protected int downRow = 0;
    protected int leftCol = 0;
    protected int rightCol = 0;
    protected ArrayList<Square> legalSquareList = new ArrayList<>(8);

    private Player owner;
    private Square square;

    /**
     * constructor.
     * @param owner player own this piece.
     * @param square location.
     */
    public Piece(Player owner, Square square) {
        this.owner = owner;
        this.square = square;
        square.placePiece(this);
    }

    /**
     * abstract class.
     * @return where piece could move.
     */
    public abstract ArrayList<Square> getLegalMoves();

    /**
     * move.
     * @param toSquare the target.
     */
    public void move(Square toSquare) {
        if (toSquare.getPiece() != null) {
            toSquare.getPiece().beCaptured(this.owner);
            this.owner.addPieceToHand(toSquare.getPiece());

            if (toSquare.getPiece() instanceof Lion) {
                this.owner.winGame();
            }

            toSquare.removePiece();
        }
        if (this.square != null) {
            this.square.removePiece();
        }
        this.square = toSquare;
        this.square.placePiece(this);
    }

    /**
     * piece is captured by enemy, unluckily.
     * @param capturer who capturer you.
     */
    public void beCaptured(Player capturer) {
        this.owner = capturer;
        this.square = null;
    }

    /**
     * get square.
     * @return square.
     */
    public Square getSquare() {
        return this.square;
    }

    /**
     * get owner.
     * @return owner.
     */
    public Player getOwner() {
        return this.owner;
    }
}
