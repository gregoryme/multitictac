package me.readln.etc;

public class User implements UserCommon {

    private String name;
    private int cellValue;
    private char cellSymbol;
    private static int userID = 0;

    public User (String name) {
        User.userID++;
        this.name = name;
        this.cellValue = User.userID;
    }

    public char getCellSymbol() {
        return cellSymbol;
    }
    public void setCellSymbol(char cellSymbol) {
        this.cellSymbol = cellSymbol;
    }
    public String getName() {
        return this.name;
    }
    public int getCellValue() {
        return this.cellValue;
    }
    public void setName (String name) {
        this.name = name;
    }
}
