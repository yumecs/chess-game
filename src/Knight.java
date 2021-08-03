import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(boolean isWhite, Position newPosition) {
        this.setId(KNIGHT);
        this.setColor(isWhite);
        this.setPosition(newPosition);
        this.setHasMoved(false);
    }

    public char toChar() {
        return this.getIsWhite() ? 'N' : 'n';
    }

    public String toName() {
        return "Knight";
    }

    public String toString() {
        return (this.getIsWhite() ? "White" : "Black") + " knight at: " + this.getPosition();
    }

    public boolean isOnPath(Position endPos) {
        Position startPos = this.getPosition();
        int xDiff = Math.abs(startPos.getX() - endPos.getX());
        int yDiff = Math.abs(startPos.getY() - endPos.getY());
        return xDiff * yDiff == 2;
    }

    public List<Position> getCollisionInterval(Position endPos) {
        return new ArrayList<>(0);
    }
}
