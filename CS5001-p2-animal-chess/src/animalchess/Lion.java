package animalchess;

import java.util.ArrayList;

/**
 * Lion.
 * @author Xy
 */
public class Lion extends Piece {
    /**
     * constructor.
     * @param owner got the lion player.
     * @param square place.
     */
    public Lion(Player owner, Square square) {
        super(owner, square);
    }

    /**
     * stupid method again.
     * @return where player can move.
     */
    @Override
    public ArrayList<Square> getLegalMoves() {
        upRow = this.getSquare().getRow() - ONE_ROUND_MOVE;
        downRow = this.getSquare().getRow() + ONE_ROUND_MOVE;
        leftCol = this.getSquare().getCol() - ONE_ROUND_MOVE;
        rightCol = this.getSquare().getCol() + ONE_ROUND_MOVE;
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
            if (leftCol >= 0) {
                if (this.getSquare().getGame().getSquare(downRow, leftCol).getPiece() == null
                        || this.getSquare().getGame().getSquare(downRow, leftCol).getPiece().getOwner() != this.getOwner()) {
                    legalSquareList.add(this.getSquare().getGame().getSquare(downRow, leftCol));
                }
            }
            if (rightCol <= 5) {
                if (this.getSquare().getGame().getSquare(downRow, rightCol).getPiece() == null
                        || this.getSquare().getGame().getSquare(downRow, rightCol).getPiece().getOwner() != this.getOwner()) {
                    legalSquareList.add(this.getSquare().getGame().getSquare(downRow, rightCol));
                }
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

    /**
     * lion is captured?.?
     * @param capturer who capture.
     */
    @Override
    public void beCaptured(Player capturer) {
        super.beCaptured(capturer);
        capturer.winGame();
    }
}
