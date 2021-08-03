public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected int getX () {
        return this.x;
    }

    protected int getY () {
        return this.y;
    }

    public Position (String position) {
        if (position.length() != 2) {
            //throw exception;
            this.x = -1;
            this.y = -1;
            return;
        }
        x = Character.getNumericValue(position.charAt(0)) - 10;
        y = Character.getNumericValue(position.charAt(1)) - 1;
    }

    public String toString () {
        char row = (char) (this.x + 97);
        char col = (char) (this.y + 49);
        return String.copyValueOf(new char[] {row, col});
    }
}
