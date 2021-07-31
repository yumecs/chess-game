import java.util.ArrayList;
import java.util.Arrays;
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
                int row = positionToInts(state[i][j])[0];
                int col = positionToInts(state[i][j])[1];
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

    public String[] parseMove (String note) {
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
        return new String[]{start, end};
    }

    public String inferStart(int id, String end) {
        List<Piece> list = (whiteToMove ? white : black).get(id);
        for (Piece test : list) {
            System.out.println("Checking move from " + test);
            boolean legal = legalMoveTo(test, end);
            if (legal) {
                System.out.println("Inferring: " + test.position);
                return test.position;
            }
            System.out.println("Failed. Next candidate.");
        }
        // throw exception if empty string
        System.out.println("Exception: start not found.");
        return "";
    }

    public boolean legalMoveTo(Piece test, String end) {
        if (connectsTo(test, end)) {
            System.out.println("Found connection from " + test + "!");
            // Chessboard nextBoard = new Chessboard(this.getRep());
            // nextBoard.makeMove(new String[]{test.position, end});
            // return !inCheck(test.white);
            return true;
        }
        return false;
    }

    public boolean inCheck(boolean white) {
        return false;
    }

    public boolean connectsTo(Piece test, String end) {
        int[] endPos = positionToInts(end);
        int[] startPos = positionToInts(test.position);
        Piece dest = board[endPos[0]][endPos[1]];

        // Friendly takes and pawn takes.
        if (dest != null) {
            System.out.println("Collision detected!");
            if (dest.white == test.white) {
                System.out.println("Friendly collision.");
                return false;
            }
            else if (test.id == 0) {
                int sign = test.white ? 1 : -1;
                return endPos[1] == startPos[1] + sign && Math.abs(endPos[0] - startPos[0]) == 1;
            }
        }

        // Get the possible squares that the piece can travel to.
        if (test.naiveValue(end) == 0) {
            return false;
        }

        // Test for pieces between the start and end piece.
        List<int[]> collision = test.getCollision(end);
        for (int[] c : collision) {
            System.out.println(Arrays.toString(c));
            if (board[c[0]][c[1]] != null) {
                return false;
            }
        }
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

    public void makeMove (String[] move) {
        int[] start = positionToInts(move[0]);
        int[] end = positionToInts(move[1]);
        Piece startPiece = board[start[0]][start[1]];
        Piece endPiece = board[end[0]][end[1]];
        board[end[0]][end[1]] = startPiece;
        startPiece.position = positionToString(end);
        startPiece.hasMoved = true;
        board[start[0]][start[1]] = null;
        if (endPiece != null) {
            List<Piece> pieceList = (endPiece.white ? white : black).get(endPiece.id);
            pieceList.remove(endPiece);
            System.out.println("Removing piece!");
        }
        whiteToMove = !whiteToMove;
    }

    public static int[] positionToInts(String position) {
        if (position.length() != 2) {
            //throw exception;
            return new int[]{-1, -1};
        }
        int row = Character.getNumericValue(position.charAt(0)) - 10;
        int col = Character.getNumericValue(position.charAt(1)) - 1;
        return new int[]{row, col};
    }

    public static String positionToString(int[] position) {
        if (position.length != 2) {
            //throw exception;
            return "";
        }
        char row = (char) (position[0] + 97);
        char col = (char) (position[1] + 49);
        return String.copyValueOf(new char[] {row, col});
    }
    
    public static void printBoard (Piece[][] board) {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Piece p = board[j][i];
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
