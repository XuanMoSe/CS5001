package animalchess;

/**
 * Dog is a piece which can't be promoted.
 * @author Xiuping Fan
 *
 */
public class Dog extends Piece {

    /**
     * Constructor.
     * @param owner is the player who own this piece
     * @param square is the position where this piece located
     */
    public Dog(Player owner, Square square) {
        super(owner, square);
    }

    @Override
    public boolean checkIfObeyRule(int row, int col) {
        return new DogRule().checkIfObeyDogRule(row, col);
    }

}
