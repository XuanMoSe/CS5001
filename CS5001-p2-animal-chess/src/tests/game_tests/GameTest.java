package game_tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import animalchess.*;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    private Game myGame;
    private Player p0;
    private Player p1;

    @Before
    public void setup() {
        p0 = new Player("Michael", 0);
        p1 = new Player("Oz", 1);
        myGame = new Game(p0, p1);
    }

    @Test
    public void testGameExists() {
        assertNotNull(myGame);
    }

    @Test
    public void testGetPlayer() {
        assertEquals(myGame.getPlayer(0), p0);
        assertEquals(myGame.getPlayer(1), p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPlayerBad() {
        myGame.getPlayer(3);
        fail("the last line was supposed to throw an IllegalArgumentException");
    }

    @Test
    public void testGetWinnerNone() {
        assertNull(myGame.getWinner());
    }

    @Test
    public void testGetWinnerP0() {
        p0.winGame();
        assertNotNull(myGame.getWinner());
        assertEquals(myGame.getWinner(), p0);
    }

    @Test
    public void testGetSquareEmpty() {
        Square emptySquare = myGame.getSquare(1,0);
        assertNull(emptySquare.getPiece());
    }

    @Test
    public void testGetSquareLion() {
        Square lionSquare = myGame.getSquare(0,2);
        Piece p0Lion = lionSquare.getPiece();
        assertNotNull(p0Lion);
        assertTrue(p0Lion instanceof Lion);
    }

    @Test
    public void fullGame() {
        // This last test plays out a full game, from the beginning, only making
        // legal moves and checking various properties along the way.

        // P0 moves first, moves his right-side chick to (3,3) (takes P1's chick)
        Chick chick0r = (Chick) myGame.getSquare(2, 3).getPiece();
        chick0r.move(myGame.getSquare(3, 3));
        assertEquals(1, p0.getHand().size());
        assertFalse(chick0r.getIsPromoted());

        // P1 moves right-side cat diagonally forward to (4,3)
        Piece cat1r = myGame.getSquare(5, 4).getPiece();
        cat1r.move(myGame.getSquare(4, 3));
        assertEquals(0, p1.getHand().size());
        assertEquals(4, cat1r.getSquare().getRow());

        // P0 moves his lion forward to (1,2)
        Piece lion0 = myGame.getSquare(0, 2).getPiece();
        assertTrue(lion0 instanceof Lion);
        assertEquals(3, lion0.getLegalMoves().size());
        lion0.move(myGame.getSquare(1, 2));
        assertEquals(4, lion0.getLegalMoves().size());

        // P1 moves his right-hand dog sideways
        Dog dog1r = (Dog) myGame.getSquare(5, 3).getPiece();
        dog1r.move(myGame.getSquare(5, 4));
        assertEquals(4, dog1r.getSquare().getCol());
        assertNull(myGame.getSquare(5, 3).getPiece());

        // P0's right-hand chick takes P1's cat, and promotes
        chick0r.move(cat1r.getSquare());
        assertEquals(4, chick0r.getSquare().getRow());
        assertEquals(2, p0.getHand().size());
        assertTrue(p0.getHand().contains(cat1r));
        assertTrue(chick0r.getIsPromoted());

        // P1 takes back with his right-hand dog
        dog1r.move(myGame.getSquare(4, 3));
        assertFalse(chick0r.getIsPromoted());  // chick unpromotes when taken
        assertEquals(p1, chick0r.getOwner());  // P1 owns the chick now
        assertEquals(1, p1.getHand().size());

        // P0 drops the cat he captured earlier
        assertNull(cat1r.getSquare());
        p0.dropPiece(cat1r, myGame.getSquare(4, 2));
        assertEquals(1, p0.getHand().size());

        // P1 advances his central chick
        Chick chick1c = (Chick) myGame.getSquare(3, 2).getPiece();
        List<Square> moves = chick1c.getLegalMoves();
        assertEquals(1, moves.size());
        Square toSquare = moves.get(0);
        assertNotNull(toSquare.getPiece());  // contains P0's chick
        chick1c.move(toSquare);  // take P0's chick
        assertEquals(2, p1.getHand().size());

        // P0 moves his lion to the right
        Square square12 = myGame.getSquare(1, 2);
        assertEquals(lion0, square12.getPiece());
        assertEquals(5, lion0.getLegalMoves().size());
        lion0.move(myGame.getSquare(1, 3));
        assertEquals(3, lion0.getSquare().getCol());

        // P1 advances his central chick into the gap and it promotes
        assertFalse(chick1c.getIsPromoted());
        assertNull(square12.getPiece());
        chick1c.move(square12);
        assertNotNull(square12.getPiece());
        assertTrue(chick1c.getIsPromoted());

        // P0 drops a chick on the centre file
        Piece chick0new = p0.getHand().get(0);
        p0.dropPiece(chick0new, myGame.getSquare(3, 2));
        assertTrue(p0.getHand().isEmpty());
//        System.out.println(chick0new.getLegalMoves().isEmpty());
        ArrayList<Square> legal = chick0new.getLegalMoves();
//        for(Square s: legal){
//            System.out.println(s.getRow() + " " + s.getCol() + "\n");
//            System.out.println(s.getPiece().getOwner().getPlayerNumber());
//        }
        assertTrue(chick0new.getLegalMoves().isEmpty());

        // P1's promoted chick takes P0's lion!
        chick1c.move(lion0.getSquare());
        assertEquals(p1, myGame.getWinner());
        assertTrue(p1.hasWon());
        assertFalse(p0.hasWon());
    }
}
