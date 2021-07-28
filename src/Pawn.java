public class Pawn extends Piece {
    public Pawn(boolean isWhite, String newPosition) {
        this.id = 0;
        this.white = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return white ? 'P' : 'p';
    }

    public String toString() {
        return (white ? "White" : "Black") + " Pawn at: " + position + ".";
    }
}
