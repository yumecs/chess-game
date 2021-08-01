// TODO: implement Position

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

    public Chessboard(String[][] state, boolean whiteToMove) {
        this.whiteToMove = whiteToMove;
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
        this(INIT_BOARD, true);
    }

    public String[] parseMove (String note) {
        note = note.replaceAll("\\s","");
        note = note.replaceAll("x","");
        // TODO: throw exceptions based on length
        char c = note.charAt(0);
        int id = Character.isUpperCase(c) ? Piece.idFromChar(c) : 0;
        int inc = id == 0 ? 0 : 1;
        String start, end;
        if (note.length() > 3) {
            start = note.substring(inc, inc + 2);
            end = note.substring(inc + 2, inc + 4);
        }
        else {
            end = note.substring(inc, inc + 2);
            start = this.inferStart(id, end);
        }
        return start.equals("") ? null : new String[]{start, end};
    }

    public String inferStart(int id, String end) {
        List<Piece> list = (whiteToMove ? white : black).get(id);
        for (Piece test : list) {
            if (legalMoveTo(test, end)) return test.position;
        }
        // TODO: throw exception if empty string
        System.out.println("Exception: start not found.");
        return "";
    }

    public boolean legalMoveTo(Piece test, String end) {
        if (connectsTo(test, end)) {
            Chessboard nextBoard = new Chessboard(this.getRep(), this.whiteToMove);
            nextBoard.makeMove(new String[]{test.position, end});
            return !nextBoard.inCheck();
        }
        return false;
    }

    public boolean inCheck() {
        List<List<Piece>> self = whiteToMove ? black : white;
        List<List<Piece>> opponent =  whiteToMove ? white : black;
        String kingPos = self.get(5).get(0).position;
        // TODO: check pawn takes separately
        for (int i = 0; i < 6; i++) {
            for (Piece p : opponent.get(i)) {
                if (connectsTo(p, kingPos)) {
                    System.out.println("Check discovered!"
                            + (whiteToMove ? " Black" : " White")
                            + " will be in check.");
                    System.out.println(p);
                    System.out.println(self.get(5).get(0));
                    return true;
                }
            }
        }
        return false;
    }

    public boolean connectsTo(Piece test, String end) {
        int[] endPos = positionToInts(end);
        int[] startPos = positionToInts(test.position);
        Piece dest = board[endPos[0]][endPos[1]];

        if (dest != null) {
            if (dest.white == test.white) {
                return false;
            }
            else if (test.id == 0) {
                // TODO: implement en passant
                int sign = test.white ? 1 : -1;
                return endPos[1] == startPos[1] + sign && Math.abs(endPos[0] - startPos[0]) == 1;
            }
        }

        // TODO: implement castling

        if (!test.isOnPath(end)) {
            return false;
        }

        List<int[]> collision = test.getCollisionInterval(end);
        for (int[] c : collision) {
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
        if (move == null) {
            System.out.println("Invalid move!");
            return;
        }
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
