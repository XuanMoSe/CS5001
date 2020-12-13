package animalchess;

/**
 * Only cat and chick use this class.
 * @author Xiuping Fan
 *
 */
public abstract class PromotablePiece extends Piece {

    private boolean isPromoted = false;

    /**
     * Constructor.
     * @param owner is the player who own this piece
     * @param square is the position where this piece located
     */
    public PromotablePiece(Player owner, Square square) {
        super(owner, square);
    }

    /**
     * This is a getter.
     * @return true, false
     */
    public boolean getIsPromoted() {
        return isPromoted;
    }

    /**
     * Promote the cat or chick.
     */
    public void promote() {
        isPromoted = true;
    }

    @Override
    public void move(Square toSquare) {
        if (super.getOwner().getPlayerNumber() == 0) {
            if (toSquare.getRow() == 4 || toSquare.getRow() == 5) {
                promote();
            } else {
                isPromoted = false;
            }
        } else {
            if (toSquare.getRow() == 0 || toSquare.getRow() == 1) {
                promote();
            } else {
                isPromoted = false;
            }
        }
        super.move(toSquare);
    }

    @Override
    public void beCaptured(Player capturer) {
        super.beCaptured(capturer);
        isPromoted = false;
    }

}
