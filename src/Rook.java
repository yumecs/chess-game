import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(boolean isWhite, String newPosition) {
        this.id = 2;
        this.white = isWhite;
        this.position = newPosition;
    }

    public char toChar() {
        return white ? 'R' : 'r';
    }

    public String toString() {
        return (white ? "White" : "Black") + " rook at: " + position;
    }

    public int naiveValue(String end) {
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        if (startPos[0] == endPos[0]) {
            return 1;
        }
        if (startPos[1] == endPos[1]) {
            return 2;
        }
        return 0;
    }

    public List<int[]> getCollision(String end) {
        List<int[]> collision = new ArrayList<>(8);
        int min, max;
        int[] startPos = Chessboard.positionToInts(position);
        int[] endPos = Chessboard.positionToInts(end);
        switch(naiveValue(end)) {
            case(1):
                min = Math.min(startPos[1], endPos[1]);
                max = Math.max(startPos[1], endPos[1]);
                for (int i = min + 1; i < max; i++) {
                    collision.add(new int[]{startPos[0], i});
                }
            case(2):
                min = Math.min(startPos[0], endPos[0]);
                max = Math.max(startPos[0], endPos[0]);
                for (int i = min + 1; i < max; i++) {
                    collision.add(new int[]{i, startPos[1]});
                }
        }
        return collision;
    }
}
