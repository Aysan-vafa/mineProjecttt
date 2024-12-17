package src;

import java.util.Scanner;

public class MineSweeper {
    public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter number of rows:");
    int rows= scanner.nextInt();
    System.out.println("Enter number of columns");
    int cols= scanner.nextInt();
    System.out.println("Enter the mount of mines:");
    int minesCount= scanner.nextInt();
    if (rows<7 || cols<7){
        System.out.println("both rows and cols must be at least 7");
        return ;
    }

    Game game = new Game(rows,cols,minesCount);
    game.start();
}
}
