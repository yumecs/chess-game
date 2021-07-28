import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Chessboard beatrice = new Chessboard();
        while(true) {
            Chessboard.printBoard(beatrice.board);
            Scanner myObj = new Scanner(System.in);
            System.out.println("\n\nEnter move");
            String nextMove = myObj.nextLine();
            beatrice.makeMove(beatrice.parseMove(nextMove));
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
}
