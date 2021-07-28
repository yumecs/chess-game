public class Piece {
    public int id;
    public int color;
    public boolean hasMoved;

    public Piece() {
        this.id = -1;
        this.color = -1;
    }
    public Piece(int newPiece, int newColor) {
        this.id = newPiece;
        this.color = newColor;
    }

    public static int fromChar(char c) {
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

    public String toString() {
        return idToOutput(this.id, false);
    }

    public String toChar() {
        return idToOutput(this.id, true);
    }

    public static String idToOutput(int p, boolean asChar) {
        switch (p) {
            case -1:
                return asChar ? "-" : "Null";
            case 0:
                return asChar ? "P" : "Pawn";
            case 1:
                return asChar ? "N" : "Knight";
            case 2:
                return asChar ? "B" : "Bishop";
            case 3:
                return asChar ? "R" : "Rook";
            case 4:
                return asChar ? "Q" : "Queen";
            case 5:
                return asChar ? "K" : "King";
            default:
                // throw exception
                return "";
        }
    }
}