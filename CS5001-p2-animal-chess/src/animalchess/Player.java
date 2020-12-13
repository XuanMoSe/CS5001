package animalchess;

import java.util.ArrayList;

/**
 * Here is player class.
 * @author Xy
 */
public class Player {
    /**
     * why this need javadoc???
     */
    public static final int PLAYER_0 = 0;
    /**
     * WuWuWu.
     */
    public static final int PLAYER_1 = 1;

    private final String name;
    private final int playerNumber;
    private ArrayList<Piece> hand = new ArrayList<>();
    private boolean isWinner = false;

    /**
     * player constructor.
     * @param name player's name.
     * @param playerNumber player's ID, only 0 and 1 is accept.
     */
    public Player(String name, int playerNumber) {
        if (playerNumber == PLAYER_0 || playerNumber == PLAYER_1) {
            this.name = name;
            this.playerNumber = playerNumber;
        } else {
            throw new IllegalArgumentException("Please enter correct player number, only 0 and 1. "
                    + "Player0 sits at the top of the board, and player1 sits at the bottom of the board.");
        }
    }

    /**
     * get name.
     * @return get name.233333
     */
    public String getName() {
        return this.name;
    }

    /**
     * get player id.
     * @return got the id.
     */
    public int getPlayerNumber() {
        return this.playerNumber;
    }

    /**
     * get hand.
     * @return pieces in hand.
     */
    public ArrayList<Piece> getHand() {
        return this.hand;
    }

    /**
     * got another piece.
     * @param piece the piece for another player.
     */
    public void addPieceToHand(Piece piece) {
//        In test why could pick the piece form your self???????
//        if (piece.getOwner() != this){
//            this.hand.add(piece);
//        } else {
//            throw new IllegalArgumentException("You can not pick your piece form chess board");
//        }
        if (piece.getSquare() != null) {
            piece.getSquare().removePiece();
//            this.hand.add(piece);
        }
        //in here is already clear in beCaputer.
        this.hand.add(piece);
//        else {
//            throw new IllegalArgumentException("You can not pick air form chess board");
//        }
    }

    /**
     * put piece to the chess board.
     * @param piece the piece.
     * @param square the square player want to put
     */
    public void dropPiece(Piece piece, Square square) {
        if (square.getPiece() != null) {
            throw new IllegalArgumentException("You couldn't put piece in here.");
        } else {
            this.hand.remove(piece);
            piece.move(square);
        }
    }

    /**
     * check whether win.
     */
    public void winGame() {
        this.isWinner = true;
    }

    /**
     * Winner Winner Chicken Dinner.
     * @return you are winner yeeeeee!!!
     */
    public boolean hasWon() {
        return this.isWinner;
    }
}
