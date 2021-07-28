public class Knight extends Piece {
    public Knight(boolean isWhite, String newPosition) {
        this.id = 1;
        this.white = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return white ? 'N' : 'n';
    }

    public String toString() {
        return (white ? "White" : "Black") + " knight at: " + position + ".";
    }
}
