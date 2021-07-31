import java.util.ArrayList;
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

    public String toName() {
        return "Bishop";
    }

    public String toString() {
        return (white ? "White" : "Black") + " bishop at: " + position;
    }

    public int getPathValue(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        if (startPos[0] - endPos[0] == startPos[1] - endPos[1]) {
            return 1;
        }
        if (startPos[0] - endPos[0] == endPos[1] - startPos[1]) {
            return 2;
        }
        return 0;
    }

    public List<int[]> getCollisionInterval(String end) {
        List<int[]> collision = new ArrayList<>(8);
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        int minX = Math.min(startPos[0], endPos[0]);
        int minY = Math.min(startPos[1], endPos[1]);
        int maxX = Math.max(startPos[0], endPos[0]);
        int maxY = Math.max(startPos[1], endPos[1]);
        switch (getPathValue(end)) {
            case (1):
                for (int i = 1; i < maxX - minX; i++) {
                    collision.add(new int[]{minX + i, minY + i});
                }
                break;
            case (2):
                for (int i = 1; i < maxX - minX; i++) {
                    collision.add(new int[]{minX + i, maxY - i});
                }
                break;
        }
        return collision;
    }
}
