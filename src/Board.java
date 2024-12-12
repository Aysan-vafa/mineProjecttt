import java.util.Random;
public class Board {
    private int cols,rows;
    private char[][] board;
    private boolean[][] mines;
    private boolean[][] revealed;
    private boolean[][] marked;

    public Board(int cols,int rows,int mineCount){
        this.rows=rows;
        this.cols=cols;
        this.board=new char[rows][cols];
        this.mines=new boolean[rows][cols];
        this.revealed=new boolean[rows][cols];
        this.marked=new boolean[rows][cols];
        placeMines(mineCount);
    }

    private void placeMines(int mineCount){
        Random random=new Random();
        int count=0;
        while(count<mineCount){
            int r= random.nextInt(rows);
            int c= random.nextInt(cols);
            if (!mines[r][c]) {
                mines[r][c]=true;
                count++;
            }

        }
    }

    public void toggleMark(int r,int c){
        if(mines[r][c]){
            mines[r][c]=false;
        }
        mines[r][c]=true;
    }

    private int countSurroundingMines(int r,int c){
        int count=0;
        for (int i = r-1; i <=r+1 ; i++) {
            for (int j=c-1; j<=c+1 ; j++){
                if(i>=0 && i<rows && j>0 && j<cols && mines[i][j]){
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isMine(int r,int c){
        return mines[r][c];
    }

    public boolean isRevealed(int r,int c){
        return revealed[r][c];
    }

    public boolean isMarked(int r,int c){
        return marked[r][c];
    }

    public void revealMines(){
        for (int i = 1; i <=rows ; i++) {
            for (int j = 1; j <=cols; j++){
                if (mines[i][j]){
                    revealed[i][j]=true;
                }
            }
        }
    }

    public void printBoard(){
        System.out.println(" ");
        for (int i = 0; i <cols ; i++) {
            System.out.print(i +" ");
        }
        for (int i = 0; i <rows ; i++){
            System.out.print((char)('A'+ i)+ " ");
            for (int j = 0; j <cols ; j++){
                if (revealed[i][j]) {
                    if (mines[i][j]) {
                        System.out.print("\u001B[41m\u001B[30m*\u001B[0m");
                    } else {
                        int surroundingMines = countSurroundingMines(i, j);
                        System.out.print("\u001B[34m" + surroundingMines + "\u001B[0m");
                    }
                }
                else if(marked[i][j]){
                    System.out.print("\u001B[33mF\u001B[0m ");
                }
                else {
                    System.out.print("\u001B[37m.\u001B[0m ");
                }
            }
            System.out.println();
        }
    }
}
