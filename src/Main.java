import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Chessboard beatrice = new Chessboard();
        while(true) {
            System.out.println("*");
            Chessboard.printBoard(beatrice.board);
            Scanner myObj = new Scanner(System.in);
            System.out.print("\n\n" + (beatrice.whiteToMove ? "White" : "Black"));
            System.out.print(" to move. Enter move: ");
            String nextMove = myObj.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            beatrice.makeMove(beatrice.parseMove(nextMove));
        }
    }
}
