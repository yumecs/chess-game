import java.util.List;

public class Queen extends Piece {
    public Queen(boolean isWhite, String newPosition) {
        this.setId(QUEEN);
        this.setColor(isWhite);
        this.setPosition(newPosition);
        this.setHasMoved(false);
    }

    public char toChar() {
        return this.getIsWhite() ? 'Q' : 'q';
    }

    public String toName() {
        return "Queen";
    }

    public String toString() {
        return (this.getIsWhite() ? "White" : "Black") + " queen at: " + this.getPosition();
    }

    public boolean isOnPath(String endPosition) {
        Piece rook = new Rook(this.getIsWhite(), this.getPosition());
        Piece bishop = new Bishop(this.getIsWhite(), this.getPosition());
        return rook.isOnPath(endPosition) || bishop.isOnPath(endPosition);
    }

    public List<int[]> getCollisionInterval(String endPosition) {
        Piece rook = new Rook(this.getIsWhite(), this.getPosition());
        Piece bishop = new Bishop(this.getIsWhite(), this.getPosition());
        return rook.isOnPath(endPosition) ? rook.getCollisionInterval(endPosition) : bishop.getCollisionInterval(endPosition);
    }
}
