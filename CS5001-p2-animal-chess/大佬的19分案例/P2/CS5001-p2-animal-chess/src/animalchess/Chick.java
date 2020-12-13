package animalchess;

/**
 * Chick is a piece which can be promoted.
 * @author Xiuping Fan
 *
 */
public class Chick extends PromotablePiece {

    /**
     * Constructor.
     * @param owner is the player who own this piece
     * @param square is the position where this piece located
     */
    public Chick(Player owner, Square square) {
        super(owner, square);
    }

    @Override
    public boolean checkIfObeyRule(int row, int col) {
        if (getIsPromoted()) {
            return new DogRule().checkIfObeyDogRule(row, col);
        } else {
            if (getOwner().getPlayerNumber() == 0) {
                if (getSquare().getRow() + 1 == row && getSquare().getCol() == col) {
                    return true;
                }
            } else {
                if (getSquare().getRow() - 1 == row && getSquare().getCol() == col) {
                    return true;
                }
            }
        }
        return false;
    }

}
