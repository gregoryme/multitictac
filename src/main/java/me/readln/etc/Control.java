package me.readln.etc;

import static java.lang.System.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Control {

    private int quantityOfUsers = 2;

    public Control(int quantityOfUsers) {
        this.quantityOfUsers = quantityOfUsers;
    }

    public void gameSession () {

        final int MAX_DIMENSION = 9;
        //final int QUANTITY_OF_USERS = 3;

        int dimension = 0;
        String userName;
        String hostname = "Computer";

        try
        {
            InetAddress address;
            address = InetAddress.getLocalHost();
            hostname = address.getHostName();
        }
        catch (UnknownHostException e)
        {
            out.println("Hostname can not be resolved");
        }

        User[] users = new User[quantityOfUsers+1];

        users[0] = new User("It's a Draw");             // draw user, ONLY [0] !
        users[1] = new HumanUser("Human User");         // human user
        users[2] = new HumanUser("Second Human User");  // Second human user
        users[3] = new ComputerUser(hostname);                // computer user

        users[0].setCellSymbol(' ');
        users[1].setCellSymbol('X');
        users[2].setCellSymbol('0');
        users[3].setCellSymbol('#');

        User winner = new User("Just a winner");

        out.println();
        out.println("**************************************");
        out.println("*** Simple Console Tic Tac Toe :-) ***");
        out.println("**************************************");
        out.println("\nHello!");

        userName = users[1].getUserAnswer("What's your name, First user?");
        out.println("Nice to meet you, " + userName + "!\n");
        users[1].setName(userName);

        userName = users[1].getUserAnswer("What's your name, Second user?");
        out.println("Nice to meet you, " + userName + "!\n");
        users[2].setName(userName);

        do {
            dimension = users[1].getUserDimensionDecision(MAX_DIMENSION);
            Game game = new Game(users, dimension);
            winner = game.goGame();
            out.println("\nGame over. A winner is " + winner.getName());
        } while (users[1].getUserAnswerYesNo("\nPlay again?"));

        out.println("\nThank you! Goodbye!\n");
    }
}
