import java.util.ArrayList;
import java.util.List;

public class Chessboard {
    public boolean whiteToMove;
    public Piece[][] board;
    public List<List<Piece>> black;
    public List<List<Piece>> white;
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

    public Chessboard(String[][] state) {
        whiteToMove = true;
        board = new Piece[8][8];
        white = new ArrayList<>(6);
        black = new ArrayList<>(6);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = null;
            }
        }
        for (int i = 0; i < state.length; i++) {
            List<Piece> pieceList = new ArrayList<>(state[i].length);
            (i < 6 ? white : black).add(pieceList);
            for (int j = 0; j < state[i].length; j++) {
                int row = positionToInt(state[i][j])[0];
                int col = positionToInt(state[i][j])[1];
                Piece newPiece = Piece.fromInt(i % 6, i < 6, state[i][j]);
                board[row][col] = newPiece;
                pieceList.add(newPiece);
            }
        }
        winner = -1;
    }

    public Chessboard() {
        this(INIT_BOARD);
    }

    public Move parseMove (String note) {
        note = note.replaceAll("\\s","");
        note = note.replaceAll("x","");
        // throw exceptions based on length
        char c = note.charAt(0);
        int id = Character.isUpperCase(c) ? Piece.idFromChar(c) : 0;
        int inc = id == 0 ? 0 : 1;
        String start;
        String end;
        if (note.length() > 3) {
            start = note.substring(inc, inc + 2);
            end = note.substring(inc + 2, inc + 4);
        }
        else {
            end = note.substring(inc, inc + 2);
            start = this.inferStart(id, end);
        }
        return new Move(start, end);
    }

    public String inferStart(int id, String end) {
        String start = "";
        List<Piece> list = (whiteToMove ? white : black).get(id);
        for (Piece test : list) {
            start = legalMoveTo(test, end) ? test.position : start;
        }
        // throw exception if empty string
        System.out.println("Inferring: " + id + " " + start);
        return start;
    }

    public boolean legalMoveTo(Piece test, String end) {
        if (collidesWith(test.id, test.position, end)) {
            Chessboard nextBoard = new Chessboard(this.getRep());
            nextBoard.makeMove(new Move(test.position, end));
            return !inCheck(test.white);
        }
        return false;
    }

    public boolean inCheck(boolean white) {
        return true;
    }

    public boolean collidesWith(int id, String position, String end) {
        return true;
    }

    public String[][] getRep() {
        String[][] rep = new String[12][];
        for (int i = 0; i < 12; i++) {
            List<Piece> pieces = (i < 6 ? white : black).get(i % 6);
            rep[i] = new String[pieces.size()];
            for (int j = 0; j < pieces.size(); j++) {
                rep[i][j] = pieces.get(j).position;
            }
        }
        return rep;
    }

    public void makeMove (Move move) {
        int[] starting = positionToInt(move.getStarting());
        int[] ending = positionToInt(move.getEnding());
        this.board[ending[0]][ending[1]] = this.board[starting[0]][starting[1]];
        this.board[starting[0]][starting[1]] = null;
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
        return new int[]{row, col};
    }

    public static void printBoard (Piece[][] board) {
        for (int i = 7; i >= 0; i --) {
            for (int j = 0; j < 8; j ++) {
                Piece p = board[i][j];
                System.out.print((p == null ? "-" : p.toChar()) + " ");
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
