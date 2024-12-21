package src;

import java.util.Scanner;

public class MineSweeper {
    public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    System.out.println("\n\n\nEnter number of rows:\n\n\n");
    int rows= scanner.nextInt();
    System.out.println("\n\n\nEnter number of columns:\n\n\n");
    int cols= scanner.nextInt();
    System.out.println("\n\n\nEnter the mount of mines:\n\n\n");
    int minesCount= scanner.nextInt();
    if (rows<7 || cols<7){
        System.out.println("\n\n\nboth rows and cols must be at least 7\n\n\n");
        return ;
    }

    Game game = new Game(rows,cols,minesCount);
    game.start();
}
}
