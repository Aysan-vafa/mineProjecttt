import java.util.Scanner;

public class Player {
    private Scanner scanner;

    public Player(){
        this.scanner = new Scanner(System.in);
    }

    public String getMove(){
        String move ="";
        boolean validInput=false;
        while (!validInput){
            System.out.print("Enter your move like (col row) format or like (col row m)");
            move= scanner.nextLine().trim();

            if (move.isEmpty()){
                System.out.print("Input can't be empty.");
                continue;
            }

            String[] parts = move.split(" ");
            if (parts.length<2 || parts.length>3){
                System.out.print("Invalid input format.use (col row) or (col row m)");
                continue;
            }
            validInput=true;
        }
        return move;
    }

    public int[] parseMove(String move){
        String[] parts = move.split(" ");
        int[] result = new int[2];
        result[0] = Integer.parseInt(parts[0]);
        result[1] = parts[1].charAt(0)- 'A';
        return result;
    }


}
