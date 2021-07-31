import java.util.List;

public class Queen extends Piece {
    public Queen(boolean isWhite, String newPosition) {
        this.id = 2;
        this.white = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return white ? 'Q' : 'q';
    }

    public String toName() {
        return "Queen";
    }

    public String toString() {
        return (white ? "White" : "Black") + " queen at: " + position;
    }

    public int getPathValue(String end) {
        Piece rook = new Rook(this.white, this.position);
        Piece bishop = new Bishop(this.white, this.position);
        return rook.getPathValue(end) > 0 || bishop.getPathValue(end) > 0 ? 1 : 0;
    }

    public List<int[]> getCollisionInterval(String end) {
        Piece rook = new Rook(this.white, this.position);
        Piece bishop = new Bishop(this.white, this.position);
        return rook.getPathValue(end) > 0 ? rook.getCollisionInterval(end) : bishop.getCollisionInterval(end);
    }
}
