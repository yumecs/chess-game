import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Chessboard beatrice = new Chessboard();
        while(beatrice.getWinner() == Chessboard.PLAYER_NONE) {
            System.out.println("*");
            beatrice.printBoard();
            Scanner myObj = new Scanner(System.in);
            System.out.print("\n\n" + (beatrice.isWhiteToMove() ? "White" : "Black"));
            System.out.print(" to move. Enter move: ");
            String nextMove = myObj.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            beatrice.makeMove(beatrice.parseMove(nextMove));
        }
        switch(beatrice.getWinner()) {
            case Chessboard.PLAYER_WHITE:
                System.out.println("Checkmate: White wins!");
                break;
            case Chessboard.PLAYER_BLACK:
                System.out.println("Checkmate: Black wins!");
                break;
            default:
                // TODO: throw exception
        }
    }
}
