import java.util.List;

public class Queen extends Piece {
    public Queen(boolean isWhite, String newPosition) {
        this.id = 2;
        this.isWhite = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return isWhite ? 'Q' : 'q';
    }

    public String toName() {
        return "Queen";
    }

    public String toString() {
        return (isWhite ? "White" : "Black") + " queen at: " + position;
    }

    public boolean isOnPath(String endPosition) {
        Piece rook = new Rook(this.isWhite, this.position);
        Piece bishop = new Bishop(this.isWhite, this.position);
        return rook.isOnPath(endPosition) || bishop.isOnPath(endPosition);
    }

    public List<int[]> getCollisionInterval(String endPosition) {
        Piece rook = new Rook(this.isWhite, this.position);
        Piece bishop = new Bishop(this.isWhite, this.position);
        return rook.isOnPath(endPosition) ? rook.getCollisionInterval(endPosition) : bishop.getCollisionInterval(endPosition);
    }
}
