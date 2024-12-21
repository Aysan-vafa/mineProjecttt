package src;

import java.util.Random;
public class Board {
    private int cols;
    private int rows;
    private char[][] board;
    private boolean[][] mines;
    private boolean[][] marked;
     boolean[][] revealed;
     int emptyHouse;
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
//    public void setRevealed(boolean[][] revealed) {
//        this.revealed = revealed;
//    }


    public Board(int cols,int rows,int mineCount){
        this.rows=rows;
        this.cols=cols;
        this.emptyHouse=rows*cols - mineCount;
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
      marked[r][c] = !marked[r][c];
    }

    private int countSurroundingMines(int r,int c){
        int count=0;
        for (int i = r-1; i <=r+1 ; i++) {
            for (int j=c-1; j<=c+1 ; j++){
                if(i>=0 && i<rows && j>=0 && j<cols && mines[i][j]){
                    count++;
                }
            }
        }
        return count;
    }

    public void revealAdjacent(int r, int c) {
        if (mines[r][c]) {
            return;
        }
        if (revealed[r][c]) {
            return;
        }
        revealed[r][c] = true;

        if (countSurroundingMines(r, c) == 0) {
            for (int i = r - 1; i <= r + 1; i++) {
                for (int j = c - 1; j <= c + 1; j++) {
                    if (i >= 0 && i < rows && j >= 0 && j < cols && !revealed[i][j]) {
                        revealAdjacent(i, j);
                    }
                }
            }
        }
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
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j <cols; j++){
                if (mines[i][j]){
                    revealed[i][j]=true;
                }
            }
        }
    }

//    private void printColoredChar(String colorCode, String charToPrint){
//        System.out.print("\u001B[" + colorCode + "m" + charToPrint + "\u001B[0m ");
//    }
//
//    public void printBoard(){
//        System.out.print("  ");
//        for (int i = 1; i <=cols ; i++) {
//            System.out.print(i +" ");
//        }
//        System.out.println();
//        for (int i = 0; i <rows ; i++) {
//            System.out.print((char) ('A' + i) + " ");
//            for (int j = 0; j < cols; j++) {
//                if (revealed[i][j]) {
//                    if (mines[i][j]) {
//                        printColoredChar("41;30", "#");
//                    } else if (countSurroundingMines(i,j) == 0){System.out.print(" ");}
//
//                     else{   printColoredChar("34", String.valueOf(countSurroundingMines(i, j)));
//                    }
//                } else if (marked[i][j]) {
//                    printColoredChar("33", "M");
//                } else {
//                    printColoredChar("37", "*");
//                }
//
//          }
//             System.out.println();
//        }
//      }

    private static void printColoredChar(String color, String ch) {
        System.out.print((char) 27 + "[" + color + "m" + ch + (char) 27 + "[0m" + " ");
    }
    private static void printColoredChar(String color, char ch) {
        System.out.print((char) 27 + "[" + color + "m" + ch + (char) 27 + "[0m" + " ");
    }

    public void printBoard() {
        System.out.print("\n\n\n");
        System.out.print("  ");
        for (int i = 1; i <= cols; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        // Print top border
        System.out.print("  ");
        for (int i = 0; i < cols; i++) {
            System.out.print("---");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {

            System.out.print((char) ('A' + i) + "|");
            for (int j = 0; j < cols; j++) {

                if (revealed[i][j]) {
                    if (mines[i][j]) {
                        printColoredChar("41;30", "#");
                    } else if (countSurroundingMines(i, j) == 0) {
                        System.out.print("  ");
                    } else {
                        printColoredChar("34", String.valueOf(countSurroundingMines(i, j)));
                    }
                } else if (marked[i][j]) {
                    printColoredChar("33", "M");
                } else {
                    printColoredChar("37", "*");
                }
                System.out.print("|");

            }
            System.out.println();

            System.out.print("  ");
            for (int k = 0; k < cols; k++) {
                System.out.print("---");
            }
            System.out.println();


        }
    }

}