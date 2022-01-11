package me.readln.etc;

import java.util.ArrayList;
import java.util.HashMap;

public class ComputerUser extends User {

    public ComputerUser(String name) {
        super(name);
    }

    // Parsing
    private void parsing (int i,
                          GameField gameField,
                          User[] users,
                          HashMap<User, ArrayList<Tuple>> priorityCellsForStep,
                          FuncCellIndex getX, FuncCellIndex getY) {

        final int emptyIndexForCell = -1;

        int[] counterCellsOccupiedByUser = new int[users.length];
        for (int counter : counterCellsOccupiedByUser) counter = 0;
        Tuple justFreeCell = new Tuple(emptyIndexForCell, emptyIndexForCell);
        // parsing the row
        for (int k = 0; k < gameField.getDimension(); k++) {
            for (int u = 0; u < users.length; u++) {
                if (gameField.getCellValue(getX.get(i, k), getY.get(i, k)) == users[u].getCellValue()) {
                    counterCellsOccupiedByUser[u]++;
                    if (u==0) { // if cell is "empty"
                        justFreeCell.setX(getX.get(i, k));
                        justFreeCell.setY(getY.get(i, k));
                    }
                }
            }
        }
        // after parsing the row
        for (int u = 0; u < users.length; u++) {
            if ( (counterCellsOccupiedByUser[u] == gameField.getDimension()-1) || (u==0) ) {
                if ((int)justFreeCell.getX()!=emptyIndexForCell && priorityCellsForStep.containsKey(users[u])) {
                    priorityCellsForStep.get(users[u]).add(justFreeCell);
                }
            }
        }
    } // end of Parsing

    // the computer makes its game :)
    @Override
    public Tuple getUserStep(GameField gameField, User[] users) {

        final int emptyIndexForCell = -1;
        Tuple step = new Tuple(emptyIndexForCell, emptyIndexForCell);

        int dimension = gameField.getDimension();

        boolean invariant = true; // if True then computer makes more hard game
        // invariant - is an imitation of randomness
        final int DEGREE_IMITATION_OF_RANDOMNESS = 32; // ATTENTION: the less, the less often the computer "yields"
        double random = 0;
        random = Math.random() * 100;   // comment this line to off an imitation of randomness
        if (random < (double) DEGREE_IMITATION_OF_RANDOMNESS) invariant = false;  // comment this line to off an imitation of randomness

        HashMap<User, ArrayList<Tuple>> priorityCellsForStep = new HashMap<>();

        for (User user : users) {
            priorityCellsForStep.put(user, new ArrayList<Tuple>());
        }

        // rows parsing
        for (int i=0; i < gameField.getDimension(); i++) {
            parsing(i, gameField, users, priorityCellsForStep, (i1, k1) -> {return i1;}, (i2, k2) -> {return k2;});
        }

        // columns parsing
        for (int i=0; i < gameField.getDimension(); i++) {
            parsing(i, gameField, users, priorityCellsForStep, (i1, k1) -> {return k1;}, (i2, k2) -> {return i2;});
        }

        // main diagonal
        parsing(0, gameField, users, priorityCellsForStep, (i1, k1) -> {return k1;}, (i2, k2) -> {return k2;});

        // anti diagonal
        parsing(0, gameField, users, priorityCellsForStep, (i1, k1) -> {return dimension-1-k1;}, (i2, k2) -> {return k2;});

        // first of all
        if (priorityCellsForStep.containsKey(this)) { // just check
            if (!priorityCellsForStep.get(this).isEmpty()) {
                step = priorityCellsForStep.get(this).get(0);
                return step;
            }
        }

        // second: are there critical cells of users?
        for (int u = 1; u < users.length; u++) {
            if (priorityCellsForStep.containsKey(users[u])) {
                if ( (!priorityCellsForStep.get(users[u]).isEmpty()) && invariant ) {
                    step = priorityCellsForStep.get(users[u]).get(0);
                    return step;
                }
            }
        }

        // so, there are no critical cells

        if (priorityCellsForStep.containsKey(users[0])) { // just check
            if (!priorityCellsForStep.get(users[0]).isEmpty()) {
                // yes, there are free cells

                random = (double) 0;
                int rate = 0;
                int index = 0;
                if (gameField.getDimension() >  0  && gameField.getDimension() < 10)  rate = 100;
                if (gameField.getDimension() >= 10 && gameField.getDimension() < 100) rate = 1000;
                do {
                    random = Math.random() * rate;
                    index = (int)random;
                    if ( index < priorityCellsForStep.get(users[0]).size() ) break;
                } while(true);

                step = priorityCellsForStep.get(users[0]).get(index);

                return step;
            }
        }

        // there are no free sells
        step.setX(-1); // going to have an exception in the next version
        step.setY(-1); // going to have an exception in the next version
        return step;

    } // getUserStep

}
