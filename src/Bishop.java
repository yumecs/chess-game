import java.util.List;

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
        return (white ? "White" : "Black") + " bishop at: " + position;
    }

    public int naiveValue(String end) {
        return -1;
    }

    public List<int[]> getCollision(String end) {
        return null;
    }
}
