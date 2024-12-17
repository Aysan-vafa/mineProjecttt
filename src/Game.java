package src;

public class Game {
    private Board board;
    private Player player;
    private int mineCounts;

    public Game(int rows,int cols,int mineCounts){
        this.mineCounts=mineCounts;
        this.board=new Board(cols,rows,mineCounts);
        this.player=new Player();

    }

    public void start(){
        board.printBoard();
            while(true){
            String move= player.getMove();
            int[] moveParts= player.parseMove(move);

            int r=moveParts[1];
            int c=moveParts[0];

            if (move.contains(String.valueOf('M'))){
                board.toggleMark(r,c);
//                board.printBoard();
            }
            else if (board.isMine(r,c)){
                System.out.println("Game over! you hit a mine.");
                board.revealMines();
                board.printBoard();
                break;
            }

            else if (ckeckWin()){
                System.out.println("Congratulations! You won!");
                break;
            }
            else {
                board.revealed[r][c]=true;
            }
            board.printBoard();
        }
    }
    private boolean ckeckWin(){
        int c=0;
        for (int i=0 ; i< board.getRows() ; i++){
            for (int j=0 ; j< board.getCols() ; j++){
                if (board.isRevealed(i,j) && !board.isMine(i,j)){
                    c++;
                }
            }
        }
        if (c == board.emptyHouse){
            return true;
        }
        else {
            return false;
        }
    }
}
