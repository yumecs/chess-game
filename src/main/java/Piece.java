import java.util.List;

// TODO: make all fields private

public abstract class Piece {
    private int id;
    private boolean isWhite;
    private Position position;
    private boolean hasMoved;

    public static final int PAWN = 0;
    public static final int KNIGHT = 1;
    public static final int BISHOP = 2;
    public static final int ROOK = 3;
    public static final int QUEEN = 4;
    public static final int KING = 5;

    public static int idFromChar(char c) {
        switch (c) {
            case ' ':
                return PAWN;
            case 'N':
                return KNIGHT;
            case 'B':
                return BISHOP;
            case 'R':
                return ROOK;
            case 'Q':
                return QUEEN;
            case 'K':
                return KING;
            default:
                // throw exception
                return -1;
        }
    }

    /**
     * public static Piece fromChar(char c, boolean color, String pos) {
     * return fromInt(idFromChar(c), color, pos);
     * }
     **/

    public static Piece fromInt(int id, boolean isWhite, Position position) {
        switch (id) {
            case PAWN:
                return new Pawn(isWhite, position);
            case KNIGHT:
                return new Knight(isWhite, position);
            case BISHOP:
                return new Bishop(isWhite, position);
            case ROOK:
                return new Rook(isWhite, position);
            case QUEEN:
                return new Queen(isWhite, position);
            case KING:
                return new King(isWhite, position);
            default:
                // TODO: throw exception
                return null;
        }
    }

    /**
     * public static boolean inBound(int x, int y) {
     * return (x >= 0 && x < 8 && y >= 0 && y < 8);
     * }
     **/

    // TODO: implement public static boolean isOnPath

    // TODO: Chessboard calls onPath instead, returns null if not on path.
    protected int getId() {
        return this.id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected boolean getIsWhite() {
        return this.isWhite;
    }

    protected void setColor(boolean isWhite) {
        this.isWhite = isWhite;
    }

    protected Position getPosition() {
        return this.position;
    }

    protected void setPosition(Position position) {
        this.position = position;
    }

    protected boolean getHasMoved() {
        return this.hasMoved;
    }

    protected void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public abstract String toString();

    public abstract String toName();

    public abstract char toChar();

    protected abstract boolean isOnPath(Position endPos);

    protected abstract List<Position> getCollisionInterval(Position endPos);

}