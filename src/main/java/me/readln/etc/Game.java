package me.readln.etc;

public class Game {

    private int dimension;
    private int freeCellValue;
    private final int MINIMAL_ALLOWED_DIMENSION = 3;
    private final int MAXIMAL_ALLOWED_DIMENSION = 9;
    GameField gameField;
    User[] users;

    public Game(User[] users, int dimension) {
        this.dimension = dimension;
        this.freeCellValue = users[0].getCellValue();
        this.users = users;
        if (dimension >= MINIMAL_ALLOWED_DIMENSION) {
            gameField = new GameField(dimension, this.freeCellValue, users);
            gameField.clearField();
        }
        else System.out.println("Internal error: dimension is less than " + MINIMAL_ALLOWED_DIMENSION);
    }

    private boolean parsing (int i, GameField gameField, User user, FuncCellIndex getX, FuncCellIndex getY) {
        int counter = 0;
        for (int k = 0; k < dimension; k++) {
            if (gameField.getCellValue(getX.get(i, k), getY.get(i, k)) == user.getCellValue()) counter++;
        }
        if (counter == dimension) return true;

        return false;
    }

    private boolean winningSituation(User user) {

        // rows
        for (int i = 0; i < dimension; i++) {
            if (parsing(i, gameField, user, (i1, k1) -> {return i1;}, (i2, k2) -> {return k2;})) return true;
        }

        // columns
        for (int i = 0; i < dimension; i++) {
            if (parsing(i, gameField, user, (i1, k1) -> {return k1;}, (i2, k2) -> {return i2;})) return true;
        }

        // main diagonal
        if (parsing(0, gameField, user, (i1, k1) -> {return k1;}, (i2, k2) -> {return k2;})) return true;

        // anti diagonal
        if (parsing(0, gameField, user, (i1, k1) -> {return dimension-1-k1;}, (i2, k2) -> {return k2;})) return true;

        return false;

    }

    public User goGame() {
        Tuple step;
        gameField.displayField();
        do {
            for (int i=1; i<users.length; i++)  {

                step = users[i].getUserStep(gameField, users); // user makes a game

                if ((int)step.getX() != -1)
                    this.gameField.setCellValue((int)step.getX(), (int)step.getY(), users[i].getCellValue());

                System.out.println("\n" + users[i].getName() + " has made this step:");
                gameField.displayField();

                if (this.winningSituation(users[i])) return users[i];

                if (this.gameField.isFieldFull()) { System.out.println("\nDRAW ;-)"); return users[0]; }

            }
        } while (true);
    }
}
