import java.util.Arrays;

public class Chessboard {
    public boolean whiteToMove;
    public Piece[][] board;
    public int winner;
    public static final String[][] INIT_BOARD = {
        {"a2","b2","c2","d2","e2","f2","g2","h2"},
        {"b1","g1"},
        {"c1","f1"},
        {"a1","h1"},
        {"d1"},
        {"e1"},
        {"a7","b7","c7","d7","e7","f7","g7","h7"},
        {"b8","g8"},
        {"c8","f8"},
        {"a8","h8"},
        {"d8"},
        {"e8"},
    };

    // Initialize a new chessboard.

    public Chessboard() {
        this.whiteToMove = true;
        this.board = makeBoard(INIT_BOARD);
        this.winner = -1;
    }

    public Move parseMove (String note) {
        // basic parse move
        // check validity
        note = note.replaceAll("\\s","");
        return new Move(note.substring(0, 2), note.substring(2));
    }

    public void makeMove (Move move) {
        int[] starting = positionToInt(move.getStarting());
        int[] ending = positionToInt(move.getEnding());
        this.board[ending[0]][ending[1]] = this.board[starting[0]][starting[1]];
        this.board[starting[0]][starting[1]] = new Piece();
        this.winner = move.winner;
        this.whiteToMove = !this.whiteToMove;
    }

    public static int[] positionToInt(String position) {
        if (position.length() != 2) {
            //throw exception;
            return new int[]{-1, -1};
        }
        int col = Character.getNumericValue(position.charAt(0)) - 10;
        int row = Character.getNumericValue(position.charAt(1)) - 1;
        // System.out.println("Converted " + position + " to row: " + row + ", col: " + col + ".");
        return new int[]{row, col};
    }

    public static Piece[][] makeBoard(String[][] state) {
        Piece[][] board = new Piece[8][8];
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Piece();
            }
        }
        for (int i = 0; i < state.length; i ++) {
            for (int j = 0; j < state[i].length; j ++) {
                int row = positionToInt(state[i][j])[0];
                int col = positionToInt(state[i][j])[1];
                board[row][col] = new Piece(i % 6, i < 6 ? 0 : 1);
            }
        }
        return board;
    }

    public static void printBoard (Piece[][] board) {
        for (int i = 7; i >= 0; i --) {
            for (int j = 0; j < 8; j ++) {
                Piece p = board[i][j];
                System.out.print((p.color == 0 ? p.toChar() : p.toChar().toLowerCase()) + " ");
            }
            System.out.println();
        }
    }

    public Piece[][] getBoard() {
        return this.board;
    }

    public boolean isWhiteToMove() {
        return this.whiteToMove;
    }
}
