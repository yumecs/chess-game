import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Chessboard game = new Chessboard();
        while (game.getWinner() == Chessboard.PLAYER_NONE) {
            System.out.println("*");
            game.printBoard();
            Scanner myObj = new Scanner(System.in);
            System.out.print("\n\n" + (game.isWhiteToMove() ? "White" : "Black"));
            System.out.print(" to move. Enter move: ");
            String nextMove = myObj.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            game.makeMove(game.parseMove(nextMove));
        }
        switch (game.getWinner()) {
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
