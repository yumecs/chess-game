public class King extends Piece {
    public King(boolean isWhite, String newPosition) {
        this.id = 2;
        this.white = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return white ? 'K' : 'k';
    }

    public String toString() {
        return (white ? "White" : "Black") + " king at: " + position + ".";
    }
}
