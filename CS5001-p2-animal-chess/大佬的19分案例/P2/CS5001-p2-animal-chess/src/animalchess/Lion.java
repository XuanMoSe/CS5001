package animalchess;

/**
 * Lion is a piece which can't be promoted.
 * @author Xiuping Fan
 *
 */
public class Lion extends Piece {

    /**
     * Constructor.
     * @param owner is the player who own this piece
     * @param square is the position where this piece located
     */
    public Lion(Player owner, Square square) {
        super(owner, square);
    }

    @Override
    public void beCaptured(Player capturer) {
        //generate the winner, finish the game
        capturer.winGame();
    }

    @Override
    public boolean checkIfObeyRule(int row, int col) {
        return true;
    }

}
