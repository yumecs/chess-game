import java.util.List;

// TODO: make all fields private

public abstract class Piece {
    public int id;
    public boolean isWhite;
    public String position;
    public boolean hasMoved;

    public static int idFromChar(char c) {
        switch (c) {
            case 'N':
                return 1;
            case 'B':
                return 2;
            case 'R':
                return 3;
            case 'Q':
                return 4;
            case 'K':
                return 5;
            default:
                // throw exception
                return -1;
        }
    }

    /** public static Piece fromChar(char c, boolean color, String pos) {
        return fromInt(idFromChar(c), color, pos);
    } **/

    public static Piece fromInt(int i, boolean isWhite, String position) {
        switch (i) {
            case 0:
                return new Pawn(isWhite, position);
            case 1:
                return new Knight(isWhite, position);
            case 2:
                return new Bishop(isWhite, position);
            case 3:
                return new Rook(isWhite, position);
            case 4:
                return new Queen(isWhite, position);
            case 5:
                return new King(isWhite, position);
            default:
                // TODO: throw exception
                return null;
        }
    }

    /** public static boolean inBound(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    } **/

    // TODO: implement public static boolean isOnPath

    // TODO: Chessboard calls onPath instead, returns null if not on path.

    public abstract String toString();

    public abstract String toName();

    public abstract char toChar();

    public abstract boolean isOnPath(String endPosition);

    public abstract List<int[]> getCollisionInterval(String endPosition);
}