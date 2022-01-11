package me.readln.etc;

public class GameField {

    private int[][] field;
    private int dimension;
    private int emptyCellValue;
    private User[] users;

    public GameField(int dimension, int emptyCellValue, User[] users) {
        if ((this.dimension=dimension) >= 3) {
            this.dimension = dimension;
            this.emptyCellValue = emptyCellValue;
            this.field = new int[dimension][dimension];
            this.users = users;
        }
        else System.out.println("Internal error: dimension is less than 3");
    }

    public boolean isCellFree(int x, int y) {
        if (this.field[x][y] == this.emptyCellValue) return true;
        else return false;
    }

    public int getDimension() {
        return this.dimension;
    }

    public void setCellValue(int x, int y, int value) {
        this.field[x][y] = value;
    }

    public int getCellValue(int x, int y) {
        return this.field[x][y];
    }

    public void clearField() {
        for (int i=0; i<this.dimension; i++)
            for (int k=0; k<this.dimension; k++) {
                this.field[i][k] = this.emptyCellValue;
            }
    }

    public void displayField() {
        // ATTENTION! correctly work only for dimensions < 10
        System.out.print("\n   ");
        for (int i=1; i <= this.dimension; i++) System.out.print((i) + " ");
        System.out.println("\u2014 Y");
        for (int x=0; x < this.dimension; x++) {
            System.out.print((x+1) + ": ");
            for (int y=0; y < this.dimension; y++) {
                for (User user : users) {
                    if (this.field[x][y] == user.getCellValue()) System.out.print(user.getCellSymbol() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("|\nX");
    }

    public boolean isFieldFull() {
        for (int i=0; i<this.dimension; i++)
            for (int k=0; k<this.dimension; k++) {
                if (this.isCellFree(i, k)) return false;
            }
        return true;
    }
}
