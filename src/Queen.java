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

    public boolean isOnPath(String end) {
        Piece rook = new Rook(this.white, this.position);
        Piece bishop = new Bishop(this.white, this.position);
        return rook.isOnPath(end) || bishop.isOnPath(end);
    }

    public List<int[]> getCollisionInterval(String end) {
        Piece rook = new Rook(this.white, this.position);
        Piece bishop = new Bishop(this.white, this.position);
        return rook.isOnPath(end) ? rook.getCollisionInterval(end) : bishop.getCollisionInterval(end);
    }
}
