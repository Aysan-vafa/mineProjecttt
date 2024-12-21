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
                System.out.println("\n\n\nGame over! you hit a mine.\n\n\n");
                board.revealMines();
                board.printBoard();
                break;
            }

//            else if (checkWin()){
//                System.out.println("Congratulations! You won!");
//                break;
//            }
            else {
//                board.revealed[r][c]=true;
                board.revealAdjacent(r,c);
                if(checkWin()){
                    board.printBoard();
                    System.out.println("\n\n\nCongratulations! You won!\n\n\n");
                    break;
                }
            }
            board.printBoard();
        }
    }
    private boolean checkWin(){
        int c=0;
        for (int i=0 ; i< board.getRows() ; i++){
            for (int j=0 ; j< board.getCols() ; j++){
                if (board.isRevealed(i,j) && !board.isMine(i,j)){
                    c++;
                }
            }
        }
      return(c == board.emptyHouse);

    }
}
