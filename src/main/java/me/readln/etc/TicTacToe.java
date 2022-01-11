/**
 * Simple multi user console TicTacToe game
 * Console etude
 * msg@readln.me
 * ver 0.2beta, July 2021
 */

package me.readln.etc;

public class TicTacToe {
        public static void main( String[] args ) {
            final int QUANTITY_OF_USERS = 3;
            Control control = new Control(QUANTITY_OF_USERS);
            control.gameSession();
        }
}
