public class Queen extends Piece {
    public Queen(boolean isWhite, String newPosition) {
        this.id = 2;
        this.white = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return white ? 'Q' : 'q';
    }

    public String toString() {
        return (white ? "White" : "Black") + " queen at: " + position + ".";
    }
}
