package animalchess;

import java.util.ArrayList;

/**
 * Represent the person who take part in the game.
 * Player has two kind for a piece: owner, opponent.
 * @author Xiuping Fan
 */
public class Player {

    private String name;
    // 0 or 1
    private int playNumber;
    private boolean isWin = false;
    // pieces that on this player's hand currently
    private ArrayList<Piece> hand = new ArrayList<Piece>();
    // pieces that on the board currently
    private ArrayList<Piece> board = new ArrayList<Piece>();

    /**
     * Constructor.
     * @param name of the player
     * @param playNumber is the number of the player
     */
    public Player(String name, int playNumber) {
        this.name = name;
        if (playNumber != 0 && playNumber != 1) {
            throw new IllegalArgumentException("playNumber only can be 0 or 1");
        }
        this.playNumber = playNumber;
    }

    /**
     * This is a getter.
     * @return name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * This is a getter.
     * @return playerNumber of the player
     */
    public int getPlayerNumber() {
        return playNumber;
    }

    /**
     * This is a getter.
     * @return the pieces which on the board currently
     */
    public ArrayList<Piece> getBoard() {
        return board;
    }

    /**
     * This is a getter.
     * @return the pieces which on the hand currently
     */
    public ArrayList<Piece> getHand() {
        return hand;
    }

    /**
     * Add Piece to hand.
     * @param piece which is about to be added
     */
    public void addPieceToHand(Piece piece) {
        hand.add(piece);
    }

    /**
     * Put down a piece which was in owner's hand before.
     * @param piece is about to put down
     * @param square is the position where piece is about to be put
     */
    public void dropPiece(Piece piece, Square square) {
        if (hand.size() != 0 && !hand.contains(piece)) {
            throw new IllegalArgumentException("This player tries to drop a piece that is not in his hand.");
        }
        square.placePiece(piece);
        piece.setSquare(square);
        board.add(piece);
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).equals(piece)) {
                hand.remove(i);
                break;
            }
        }
    }

    /**
     * Win the game.
     */
    public void winGame() {
        isWin = true;
    }

    /**
     * Whether this player has won.
     * @return true, false
     */
    public boolean hasWon() {
        return isWin;
    }

}
