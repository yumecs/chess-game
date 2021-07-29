import java.util.List;

public abstract class Piece {
    public int id;
    public boolean white;
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

    public static Piece fromChar(char c, boolean color, String pos) {
        return fromInt(idFromChar(c), color, pos);
    }

    public static Piece fromInt(int i, boolean color, String pos) {
        switch (i) {
            case 0:
                return new Pawn(color, pos);
            case 1:
                return new Knight(color, pos);
            case 2:
                return new Bishop(color, pos);
            case 3:
                return new Rook(color, pos);
            case 4:
                return new Queen(color, pos);
            case 5:
                return new King(color, pos);
            default:
                // throw exception
                return null;
        }
    }

    public abstract String toString();

    public abstract char toChar();

    public abstract List<String> getNaive();
}