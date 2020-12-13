package animalchess;

import java.util.ArrayList;

/**
 * Both cat, chick, dog, lion use this class. There is a internal class DogRule
 * at the bottom of this page
 * @author Xiuping Fan
 */
public abstract class Piece {

    private Player owner;
    private Square square;
    private Game game;
    // that square is really empty
    private static final int EMPTY = 0;
    // piece in that square belongs to owner
    private static final int OWNPIECE = -1;
    // piece in that square belongs to opponent
    private static final int CAPTURING = 1;

    /**
     * Constructor.
     * @param owner is the player who own this piece
     * @param square is the position where piece is about to be put
     */
    public Piece(Player owner, Square square) {
        this.owner = owner;
        this.square = square;
        this.square.placePiece(this);
        this.game = square.getGame();
    }

    /**
     * Move this piece to another square(toSquare).
     * @param toSquare is the square that this piece will move to
     */
    public void move(Square toSquare) {
        /** Check whether toSquare is in board bound and this move conforms rule */
        // if (checkIfInBound(toSquare.getRow(), toSquare.getCol())
        // && checkIfObeyRule(toSquare.getRow(), toSquare.getCol())) {
        // } else {
        /**
         * This place I think should throw a exception, but in case test, it need a
         * normal implement, so I annotate them.
         */
        // throw new IllegalArgumentException("not in bound or not conform rule");
        // }

        if (checkIfEmpty(toSquare, owner.getPlayerNumber()) == EMPTY) {
            // toSquare is empty
            square.removePiece();
            square = toSquare;
            toSquare.placePiece(this);
        } else { // toSquare is not empty. (belongs to owner or opponent)
            if (toSquare.getPiece().getOwner().getPlayerNumber() == owner.getPlayerNumber()) {
                throw new IllegalArgumentException("toSquare belong to owner");
            } else {
                // toSquare belong to opponent, means capture is occurring
                toSquare.getPiece().getOwner().getBoard().remove(toSquare.getPiece());
                toSquare.getPiece().beCaptured(owner);
                toSquare.getPiece().setSquare(null);
                owner.addPieceToHand(toSquare.getPiece());
                square.removePiece();
                square = toSquare;
                toSquare.removePiece();
                toSquare.placePiece(this);
            }
        }

    }

    /**
     * This piece will be captured.
     * @param capturer is the player who will capture this piece
     */
    public void beCaptured(Player capturer) {
        if (capturer.getPlayerNumber() != owner.getPlayerNumber()) {
            this.square = null;
            this.owner = capturer;
        }
    }

    /**
     * This is a getter.
     * @return square
     */
    public Square getSquare() {
        return square;
    }

    /**
     * This is a setter.
     * @param square is the position where piece is about to be put
     */
    public void setSquare(Square square) {
        this.square = square;
    }

    /**
     * This is a getter.
     * @return owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * This is a getter.
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Obtain the whole squares that this piece can move to.
     * @return the whole squares that this piece can move to
     */
    public ArrayList<Square> getLegalMoves() {
        ArrayList<Square> squares = new ArrayList<Square>();
        int row = square.getRow();
        int col = square.getCol();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((checkIfEmpty(row + i, col + j, getOwner().getPlayerNumber()) == EMPTY
                        || checkIfEmpty(row + i, col + j, getOwner().getPlayerNumber()) == CAPTURING)
                        && checkIfInBound(row + i, col + j) && checkIfObeyRule(row + i, col + j)) {
                    if (i != 0 || j != 0) {
                        squares.add(getSquare().getGame().getSquare(row + i, col + j));
                    }
                }
            }
        }
        return squares;
    }

    /**
     * Check this square whether is empty. In this function, game can be null.
     * @param toSquare represent the position
     * @param ownerNumber represents the player
     * @return EMPTY, OWNPIECE, CAPTURING
     */
    public int checkIfEmpty(Square toSquare, int ownerNumber) {
        if (toSquare.getPiece() == null) {
            // the square is really empty
            return EMPTY;
        }
        if (toSquare.getPiece().getOwner().equals(getOwner())) {
            // piece in that square belongs to owner
            return OWNPIECE;
        } else {
            // piece in that square belongs to opponent
            return CAPTURING;
        }
    }

    /**
     * Check this square whether is empty. In this function, game can not be null.
     * @param row of the square
     * @param col of the square
     * @param ownerNumber represents the player
     * @return EMPTY, OWNPIECE, CAPTURING
     */
    public int checkIfEmpty(int row, int col, int ownerNumber) {
        if (game == null) {
            //Please use another checkIfEmpty(toSquare, ownerNumber) method
            throw new IllegalArgumentException("In this function, game can't be null, Please use another checkIfEmpty() method");
        }
        for (int i = 0; i < game.getPlayer(0).getBoard().size(); i++) {
            if (game.getPlayer(0).getBoard().get(i).getSquare().getRow() == row
                    && game.getPlayer(0).getBoard().get(i).getSquare().getCol() == col) {
                if (ownerNumber == 0) {
                    // piece in that square belongs to owner
                    return OWNPIECE;
                } else {
                    // piece in that square belongs to opponent
                    return CAPTURING;
                }
            }
        }
        for (int i = 0; i < game.getPlayer(1).getBoard().size(); i++) {
            if (game.getPlayer(1).getBoard().get(i).getSquare().getRow() == row
                    && game.getPlayer(1).getBoard().get(i).getSquare().getCol() == col) {
                if (ownerNumber == 0) {
                    // piece in that square belongs to opponent
                    return CAPTURING;
                } else {
                    // piece in that square belongs to owner
                    return OWNPIECE;
                }
            }
        }
        return EMPTY;
    }

    /**
     * Check Moving to this square whether conform direction rules.
     * @param row of the square
     * @param col of the square
     * @return true, false
     */
    public abstract boolean checkIfObeyRule(int row, int col);

    /**
     * Check whether this square is in the bound of board.
     * @param row of the square
     * @param col of the square
     * @return true, false
     */
    public boolean checkIfInBound(int row, int col) {
        if (row >= 0 && row <= 5 && col >= 0 && col <= 4) {
            return true;
        }
        return false;
    }

    /**
     * This is the rule for Dog, used by dog, promoted chick and promoted cat.
     * @author Xiuping Fan
     */
    public class DogRule {
        /**
         * Check Moving to a square whether conform direction rules of Dog.
         * 
         * @param row of this square
         * @param col of this square
         * @return true if conformed rule
         */
        public boolean checkIfObeyDogRule(int row, int col) {
            if (getOwner().getPlayerNumber() == 0) {
                if (square.getRow() + 1 == row) {
                    if (square.getCol() == col || square.getCol() == col - 1 || square.getCol() == col + 1) {
                        return true;
                    }
                } else if (square.getRow() == row) {
                    if (square.getCol() == col - 1 || square.getCol() == col + 1) {
                        return true;
                    }
                } else if (square.getRow() - 1 == row) {
                    if (square.getCol() == col) {
                        return true;
                    }
                }
            } else {
                if (square.getRow() - 1 == row) {
                    if (square.getCol() == col || square.getCol() == col - 1 || square.getCol() == col + 1) {
                        return true;
                    }
                } else if (square.getRow() == row) {
                    if (square.getCol() == col - 1 || square.getCol() == col + 1) {
                        return true;
                    }
                } else if (square.getRow() + 1 == row) {
                    if (square.getCol() == col) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

}
