package animalchess;

import java.util.ArrayList;

import static animalchess.Player.PLAYER_0;
import static animalchess.Player.PLAYER_1;

/**
 * cute dog.
 * @author Xy
 */
public class Dog extends Piece {
    /**
     * constructor.
     * @param owner player.
     * @param square location.
     */
    public Dog(Player owner, Square square) {
        super(owner, square);
    }

    /**
     * stupid method again and again. Hope one day fix it.
     * @return where piece could go.
     */
    @Override
    public ArrayList<Square> getLegalMoves() {
        if (this.getOwner().getPlayerNumber() == PLAYER_0) {
            downRow = this.getSquare().getRow() - ONE_ROUND_MOVE;
            upRow = this.getSquare().getRow() + ONE_ROUND_MOVE;
            rightCol = this.getSquare().getCol() - ONE_ROUND_MOVE;
            leftCol = this.getSquare().getCol() + ONE_ROUND_MOVE;
        } else if (this.getOwner().getPlayerNumber() == PLAYER_1) {
            upRow = this.getSquare().getRow() - ONE_ROUND_MOVE;
            downRow = this.getSquare().getRow() + ONE_ROUND_MOVE;
            leftCol = this.getSquare().getCol() - ONE_ROUND_MOVE;
            rightCol = this.getSquare().getCol() + ONE_ROUND_MOVE;
        }
        legalSquareList.clear();

        if (upRow >= 0) {
            if (this.getSquare().getGame().getSquare(upRow, this.getSquare().getCol()).getPiece() == null
                    || this.getSquare().getGame().getSquare(upRow, this.getSquare().getCol()).getPiece().getOwner() != this.getOwner()) {
                legalSquareList.add(this.getSquare().getGame().getSquare(upRow, this.getSquare().getCol()));
            }
            if (leftCol >= 0) {
                if (this.getSquare().getGame().getSquare(upRow, leftCol).getPiece() == null
                        || this.getSquare().getGame().getSquare(upRow, leftCol).getPiece().getOwner() != this.getOwner()) {
                    legalSquareList.add(this.getSquare().getGame().getSquare(upRow, leftCol));
                }
            }
            if (rightCol <= 4) {
                if (this.getSquare().getGame().getSquare(upRow, rightCol).getPiece() == null
                        || this.getSquare().getGame().getSquare(upRow, rightCol).getPiece().getOwner() != this.getOwner()) {
                    legalSquareList.add(this.getSquare().getGame().getSquare(upRow, rightCol));
                }
            }
        }
        if (downRow <= 5) {
            if (this.getSquare().getGame().getSquare(downRow, this.getSquare().getCol()).getPiece() == null
                    || this.getSquare().getGame().getSquare(downRow, this.getSquare().getCol()).getPiece().getOwner() != this.getOwner()) {
                legalSquareList.add(this.getSquare().getGame().getSquare(downRow, this.getSquare().getCol()));
            }
        }
        if (leftCol >= 0) {
            if (this.getSquare().getGame().getSquare(this.getSquare().getRow(), leftCol).getPiece() == null
                    || this.getSquare().getGame().getSquare(this.getSquare().getRow(), leftCol).getPiece().getOwner() != this.getOwner()) {
                legalSquareList.add(this.getSquare().getGame().getSquare(this.getSquare().getRow(), leftCol));
            }
        }
        if (rightCol <= 4) {
            if (this.getSquare().getGame().getSquare(this.getSquare().getRow(), rightCol).getPiece() == null
                    || this.getSquare().getGame().getSquare(this.getSquare().getRow(), rightCol).getPiece().getOwner() != this.getOwner()) {
                legalSquareList.add(this.getSquare().getGame().getSquare(this.getSquare().getRow(), rightCol));
            }
        }
        return legalSquareList;
    }
}

