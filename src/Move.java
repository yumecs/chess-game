public class Move {
    public String starting;
    public String ending;
    public int winner;

    public Move(String a, String b) {
        this.starting = a;
        this.ending = b;
        this.winner = -1;
    }

    public String getStarting() {
        return this.starting;
    }

    public String getEnding() {
        return this.ending;
    }
}
