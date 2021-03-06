// TODO: implement Promotion
// TODO: implement Checkmate
// TODO: separate Chess Board and Chess Game

import common.functional.Tuple;

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

    public static final int PLAYER_NONE = -1;
    public static final int PLAYER_WHITE = 0;
    public static final int PLAYER_BLACK = 1;

    public static final String[][] INIT_BOARD = {
            {"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"},
            {"b1", "g1"},
            {"c1", "f1"},
            {"a1", "h1"},
            {"d1"},
            {"e1"},
            {"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"},
            {"b8", "g8"},
            {"c8", "f8"},
            {"a8", "h8"},
            {"d8"},
            {"e8"},
    };

    // Initialize a new chessboard.

    public Chessboard(String[][] state, boolean whiteToMove) {
        this.whiteToMove = whiteToMove;
        this.winner = PLAYER_NONE;
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
                Position newPos = new Position(state[i][j]);
                Piece newPiece = Piece.fromInt(i % PIECE_TYPES, i < PIECE_TYPES, newPos);
                board[newPos.first()][newPos.second()] = newPiece;
                pieceList.add(newPiece);
            }
        }
        winner = -1;
    }

    public Chessboard() {
        this(INIT_BOARD, true);
    }

    public Tuple<Position> parseMove(String note) {
        note = note.replaceAll("\\s", "");
        note = note.replaceAll("x", "");
        // TODO: throw exceptions based on length
        // TODO: better parse checker (exd5), info from rank or file
        char c = note.charAt(0);
        int id = Character.isUpperCase(c) ? Piece.idFromChar(c) : Piece.PAWN;
        int inc = id == 0 ? 0 : 1;
        Position start, end;
        if (note.length() > 3) {
            start = new Position(note.substring(inc, inc + 2));
            end = new Position(note.substring(inc + 2, inc + 4));
        } else {
            end = new Position(note.substring(inc, inc + 2));
            start = this.inferStart(id, end);
        }
        return start == null ? null : new Tuple<>(start, end);
    }

    public Position inferStart(int id, Position end) {
        List<Piece> pieceList = (whiteToMove ? whitePieces : blackPieces).get(id);
        for (Piece test : pieceList) {
            if (legalMoveTo(test, end)) return test.getPosition();
        }
        return null;
    }

    public boolean legalMoveTo(Piece test, Position end) {
        // Verify if there is a possible move to the end position.
        if (connectsTo(test, end)) {
            // Verify if the next move will result in a checkmate.
            Chessboard nextBoard = new Chessboard(this.getRep(), this.whiteToMove);
            nextBoard.makeMove(new Tuple<>(test.getPosition(), end));
            return !nextBoard.inCheck();
        }
        return false;
    }

    public boolean inCheck() {
        List<List<Piece>> self = whiteToMove ? blackPieces : whitePieces;
        List<List<Piece>> opponent = whiteToMove ? whitePieces : blackPieces;
        Position kingPos = self.get(Piece.KING).get(0).getPosition();
        // TODO: check pawn takes separately
        for (List<Piece> pList : opponent) {
            for (Piece p : pList) {
                if (connectsTo(p, kingPos)) return true;
            }
        }
        return false;
    }

    public boolean connectsTo(Piece test, Position endPos) {
        Position startPos = test.getPosition();
        Piece dest = board[endPos.first()][endPos.second()];

        if (dest != null) {
            if (dest.getIsWhite() == test.getIsWhite()) {
                // A piece cannot move to a friendly piece.
                return false;
            } else if (test.getId() == Piece.PAWN) {
                // Pawn takes are implemented separately.
                // TODO: implement en passant
                int sign = test.getIsWhite() ? 1 : -1;
                return endPos.second() == startPos.second() + sign && Math.abs(endPos.first() - startPos.first()) == 1;
            }
        }

        // TODO: implement castling

        if (!test.isOnPath(endPos)) return false;

        List<Position> collision = test.getCollisionInterval(endPos);
        for (Position c : collision) {
            if (board[c.first()][c.second()] != null) {
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
                rep[i][j] = pieces.get(j).getPosition().toString();
            }
        }
        return rep;
    }

    public void makeMove(Tuple<Position> move) {
        if (move == null) {
            System.out.println("Illegal move!");
            return;
        }

        Position start = move.first(), end = move.second();
        Piece startPiece = board[start.first()][start.second()], endPiece = board[end.first()][end.second()];

        // Move the piece.
        board[end.first()][end.second()] = startPiece;
        startPiece.setPosition(end);
        startPiece.setHasMoved(true);
        board[start.first()][start.second()] = null;

        // If the piece takes, remove the taken piece.
        if (endPiece != null) {
            List<Piece> pieceList = (endPiece.getIsWhite() ? whitePieces : blackPieces)
                    .get(endPiece.getId());
            pieceList.remove(endPiece);
        }
        whiteToMove = !whiteToMove;
    }

    public void printBoard() {
        Chessboard.printBoard(board);
    }

    public static void printBoard(Piece[][] board) {
        for (int i = BOARD_SIDE - 1; i >= 0; i--) {
            for (int j = 0; j < BOARD_SIDE; j++) {
                Piece p = board[j][i];
                System.out.print((p == null ? "-" : p.toChar()) + " ");
            }
            System.out.println();
        }
    }

    /**
     * public Piece[][] getBoard() {
     * return this.board;
     * }
     **/

    public boolean isWhiteToMove() {
        return this.whiteToMove;
    }

    public int getWinner() {
        return this.winner;
    }
}
