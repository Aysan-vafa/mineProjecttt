package src;

import java.util.Scanner;

public class Player {
    private Scanner scanner;

    public Player(){
        this.scanner = new Scanner(System.in);
    }

    public String getMove(){
        String move = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.print("\n\n\nEnter your move like (col row) format or like (col row M):\n\n\n ");
            move = scanner.nextLine().trim();

            if (move.isEmpty()) {
                System.out.print("\n\n\nInput can't be empty.\n\n\n");
                continue;
            }

            String[] parts = move.split(" ");
            if (parts.length < 2 || parts.length > 3) {
                System.out.print("\n\n\nInvalid input format. Use (col row) or (col row M).\n\n\n");
                continue;
            }

            // Check if the last part is "M" and process accordingly
            if (parts.length == 3 && !parts[2].equals("M")) {
                System.out.print("\n\n\nInvalid input format. If marking, use 'M'.\n\n\n");
                continue;
            }

            validInput = true;
        }
        return move;
    }


    public int[] parseMove(String move){
        String[] parts = move.split(" ");
        int[] result = new int[2];
        result[0] = Integer.parseInt(parts[0])-1;
        result[1] = parts[1].charAt(0) - 'A';
        return result;
    }


}
