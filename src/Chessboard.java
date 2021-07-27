public class Chessboard {
    public int toMove;
    public Piece[][] board;

    public Chessboard () {
        this.toMove = 0;
        // this.board = new board
    }

    public int toMove() {
        return board.toMove;
    }


}
