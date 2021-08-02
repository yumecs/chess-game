// TODO: implement Position
// TODO: implement Promotion
// TODO: implement Checkmate

import java.util.ArrayList;
import java.util.List;

public class Chessboard {
    private boolean whiteToMove;
    private final Piece[][] board;
    private final List<List<Piece>> blackPieces;
    private final List<List<Piece>> whitePieces;
    private int winner;

    public static final int PIECE_TYPES = 6;
    public static final int BOARD_SIDE = 8;
    public static final int NONE = -1;
    public static final int WHITE = 0;
    public static final int BLACK = 1;

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
        this.winner = NONE;
        board = new Piece[BOARD_SIDE][BOARD_SIDE];
        whitePieces = new ArrayList<>(PIECE_TYPES);
        blackPieces = new ArrayList<>(PIECE_TYPES);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = null;
            }
        }
        for (int i = 0; i < state.length; i++) {
            List<Piece> pieceList = new ArrayList<>(state[i].length);
            (i < PIECE_TYPES ? whitePieces : blackPieces).add(pieceList);
            for (int j = 0; j < state[i].length; j++) {
                int row = positionToInts(state[i][j])[0];
                int col = positionToInts(state[i][j])[1];
                Piece newPiece = Piece.fromInt(i % PIECE_TYPES, i < PIECE_TYPES, state[i][j]);
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
        int id = Character.isUpperCase(c) ? Piece.idFromChar(c) : Piece.PAWN;
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
        List<Piece> pieceList = (whiteToMove ? whitePieces : blackPieces).get(id);
        for (Piece test : pieceList) {
            if (legalMoveTo(test, end)) return test.getPosition();
        }
        // TODO: throw exception if empty string
        System.out.println("Exception: start not found.");
        return "";
    }

    public boolean legalMoveTo(Piece test, String end) {
        // Verify if there is a possible move to the end position.
        if (connectsTo(test, end)) {
            // Verify if the next move will result in a checkmate.
            Chessboard nextBoard = new Chessboard(this.getRep(), this.whiteToMove);
            nextBoard.makeMove(new String[]{test.getPosition(), end});
            return !nextBoard.inCheck();
        }
        return false;
    }

    public boolean inCheck() {
        List<List<Piece>> self = whiteToMove ? blackPieces : whitePieces;
        List<List<Piece>> opponent =  whiteToMove ? whitePieces : blackPieces;
        String kingPos = self.get(Piece.KING).get(0).getPosition();
        // TODO: check pawn takes separately
        for (List<Piece> pList : opponent) {
            for (Piece p : pList) {
                if (connectsTo(p, kingPos)) return true;
            }
        }
        return false;
    }

    public boolean connectsTo(Piece test, String end) {
        int[] endPos = positionToInts(end);
        int[] startPos = positionToInts(test.getPosition());
        Piece dest = board[endPos[0]][endPos[1]];

        if (dest != null) {
            if (dest.getIsWhite() == test.getIsWhite()) {
                // A piece cannot move to a friendly piece.
                return false;
            }
            else if (test.getId() == Piece.PAWN) {
                // Pawn takes are implemented separately.
                // TODO: implement en passant
                int sign = test.getIsWhite() ? 1 : -1;
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
        String[][] rep = new String[PIECE_TYPES * 2][];
        for (int i = 0; i < PIECE_TYPES * 2; i++) {
            List<Piece> pieces = (i < PIECE_TYPES ? whitePieces : blackPieces)
                    .get(i % PIECE_TYPES);
            rep[i] = new String[pieces.size()];
            for (int j = 0; j < pieces.size(); j++) {
                rep[i][j] = pieces.get(j).getPosition();
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
        startPiece.setPosition(positionToString(end));
        startPiece.setHasMoved(false);
        board[start[0]][start[1]] = null;
        if (endPiece != null) {
            List<Piece> pieceList = (endPiece.getIsWhite() ? whitePieces : blackPieces).get(endPiece.getId());
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

    public void printBoard () {
       Chessboard.printBoard(board);
    }

    public static void printBoard (Piece[][] board) {
        for (int i = BOARD_SIDE - 1; i >= 0; i--) {
            for (int j = 0; j < BOARD_SIDE; j++) {
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

    public int getWinner() {
        return this.winner;
    }
}
