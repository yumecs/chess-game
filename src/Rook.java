public class Rook extends Piece {
    public Rook(boolean isWhite, String newPosition) {
        this.id = 2;
        this.white = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return white ? 'R' : 'r';
    }

    public String toString() {
        return (white ? "White" : "Black") + " rook at: " + position + ".";
    }
}
