package me.readln.etc;

import java.util.Scanner;

public class HumanUser extends User {

    public HumanUser(String name) {
        super(name);
    }

    @Override
    public int getUserDimensionDecision(int MaxDimension) {
        // console version

        int dimension=0;

        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("What dimension (one digit) do you want to set for the game? For example: input digit 3 for dimension 3x3.");
            System.out.print("Dimension: ");
            try {
                dimension = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Sorry, but there is mistake...");
            }
            if (3 <= dimension && dimension <= MaxDimension) break;
            else {
                System.out.printf("Dimension can be in [3..%d]. Sorry, try again.\n\n", MaxDimension);
            }
        } while (true);

        return dimension;
    }

    @Override
    public String getUserAnswer(String question) {
        System.out.print(question + " >> ");
        String answer = "Empty";
        do {
            Scanner scan = new Scanner(System.in);
            try {
                answer = scan.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Sorry, but there is mistake... Please, try again :-)\n\n");
                break;
            }
        } while(true);
        return answer;
    }

    @Override
    public boolean getUserAnswerYesNo(String question) {
        String answer = "n";
        do {
            answer = this.getUserAnswer(question + " (y/n)");
            switch (answer) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Type 'y' or 'n' ;-) Please, try again!");
            }
        } while (true);
    }

    @Override
    public Tuple getUserStep(GameField gameField, User[] users) {
        int x=0;
        int y=0;
        do {
            Scanner scan = new Scanner(System.in);
            System.out.print("\n" + this.getName() + ", ");
            System.out.printf("input two numbers separated by a space, coordinates of your step row (x) and column (y)," +
                    " for example: %d %d \n", gameField.getDimension(), gameField.getDimension());
            System.out.print("> ");
            try {
                x = scan.nextInt();
                y = scan.nextInt();
            } catch (Exception e) {
                System.out.println("Sorry, but there is mistake... Please, try again :-)\n\n");
                continue;
            }
            if (1<=x && x<=gameField.getDimension() && 1<=y && y<=gameField.getDimension()) {
                if (gameField.isCellFree(x-1, y-1)) break;
                else {
                    System.out.println("Sorry, this cell is busy... Please, try again :-)");
                    continue;
                }
            } else {
                System.out.printf("Sorry, coordinates can be in interval [1..%d]... Please, try again :-)\n", gameField.getDimension());
                continue;
            }
        } while(true);
        Tuple step = new Tuple(x-1, y-1);
        return step;
    }
}
