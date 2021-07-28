public class Bishop extends Piece {
    public Bishop(boolean isWhite, String newPosition) {
        this.id = 2;
        this.white = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return white ? 'B' : 'b';
    }

    public String toString() {
        return (white ? "White" : "Black") + " bishop at: " + position + ".";
    }
}
