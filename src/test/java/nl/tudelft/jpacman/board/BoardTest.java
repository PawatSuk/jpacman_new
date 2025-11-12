package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoardTest {

    private Board board;
    private Square square;

    @BeforeEach
    void setUp() {
        // Create a simple 1x1 grid with one valid BasicSquare
        square = new BasicSquare();
        Square[][] grid = new Square[][] { { square } };
        board = new Board(grid);
    }

    /**
     * Tests that the board dimensions are correct.
     */
    @Test
    void testBoardDimensions() {
        assertThat(board.getWidth()).isEqualTo(1);
        assertThat(board.getHeight()).isEqualTo(1);
    }

    /**
     * Tests that the single square is correctly accessible.
     */
    @Test
    void testSquareAt() {
        Square retrieved = board.squareAt(0, 0);
        assertThat(retrieved).isNotNull();
        assertThat(retrieved).isEqualTo(square);
    }

    /**
     * Tests that withinBorders works correctly.
     */
    @Test
    void testWithinBorders() {
        // Inside the border
        assertThat(board.withinBorders(0, 0)).isTrue();

        // Outside the border
        assertThat(board.withinBorders(-1, 0)).isFalse();
        assertThat(board.withinBorders(0, -1)).isFalse();
        assertThat(board.withinBorders(1, 0)).isFalse();
        assertThat(board.withinBorders(0, 1)).isFalse();
    }

    /**
     * Tests that the invariant of the board holds true (no null squares).
     */
    @Test
    void testInvariant() {
        assertThat(board.invariant()).isTrue();
    }

    /**
     * Tests a board constructed with a null square.
     * The invariant should fail, and accessing the square should trigger an AssertionError.
     */
    @Test
    void testSquareAtInvalidBoard() {
        Square[][] invalidGrid = new Square[][] { { null } };

        // Constructing the board with null should fail the invariant assertion
        // (Assertions must be enabled for this to throw).
        assertThatThrownBy(() -> new Board(invalidGrid))
            .isInstanceOf(AssertionError.class)
            .hasMessageContaining("Initial grid cannot contain null squares");

        // If you still somehow construct it (for example, assertions disabled),
        // calling squareAt() should still fail due to a null square.
        Board unsafeBoard = null;
        try {
            unsafeBoard = new Board(new Square[][] { { new BasicSquare() } });
            unsafeBoard = new Board(invalidGrid); // attempt invalid assignment
        } catch (AssertionError e) {
            // expected
        }

        if (unsafeBoard != null) {
            final Board finalBoard = unsafeBoard;
            assertThatThrownBy(() -> finalBoard.squareAt(0, 0))
                .isInstanceOf(AssertionError.class);
        }
    }
}
