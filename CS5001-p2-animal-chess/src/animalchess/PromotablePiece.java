package animalchess;

/**
 * promotable piece class.
 * @author Xy
 */
public abstract class PromotablePiece extends Piece {
    protected boolean isPromoted = false;

    /**
     * Constructor.
     * @param owner who owe this piece.
     * @param square the location of chess board.
     */
    public PromotablePiece(Player owner, Square square) {
        super(owner, square);
    }

    /**
     * judgment whether promoted.
     * @return true for promoted, false for not promoted.
     */
    public boolean getIsPromoted() {
        return isPromoted;
    }

    /**
     * make this piece to promote.
     */
    public void promote() {
//        if (this.getSquare().isPromotionZone(this.getOwner())) {
//            isPromoted = true;
//        } else {
//            throw new IllegalArgumentException("You could not promote, Because you are not in promote zone!!!!");
//        }
//        In this section test could direct promote,kidding?????????
        isPromoted = true;
    }

    /**
     * move the piece.
     * @param toSquare move location.
     */
    @Override
    public void move(Square toSquare) {
        super.move(toSquare);
        if (this.getSquare().isPromotionZone(this.getOwner())) {
            this.promote();
        }
    }

    /**
     * captured by other piece.
     * @param capturer which piece captured.
     */
    @Override
    public void beCaptured(Player capturer) {
        super.beCaptured(capturer);
        //forget here to clear promoted status.
        this.isPromoted = false;
    }
}
