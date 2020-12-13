package animalchess;

/**
 * Cat is a piece which can be promoted.
 * @author Xiuping Fan
 *
 */
public class Cat extends PromotablePiece {

    /**
     * Constructor.
     * @param owner is the player who own this piece
     * @param square is the position where this piece located
     */
    public Cat(Player owner, Square square) {
        super(owner, square);
    }

    @Override
    public boolean checkIfObeyRule(int row, int col) {
        if (getIsPromoted()) {
            return new DogRule().checkIfObeyDogRule(row, col);
        } else {
            if (getOwner().getPlayerNumber() == 0) {
                if (getSquare().getRow() + 1 == row) {
                    if (getSquare().getCol() == col || getSquare().getCol() == col - 1 || getSquare().getCol() == col + 1) {
                        return true;
                    }
                } else if (getSquare().getRow() - 1 == row) {
                    if (getSquare().getCol() == col - 1 || getSquare().getCol() == col + 1) {
                        return true;
                    }
                }
            } else {
                if (getSquare().getRow() - 1 == row) {
                    if (getSquare().getCol() == col || getSquare().getCol() == col - 1 || getSquare().getCol() == col + 1) {
                        return true;
                    }
                } else if (getSquare().getRow() + 1 == row) {
                    if (getSquare().getCol() == col - 1 || getSquare().getCol() == col + 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
