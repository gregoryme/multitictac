package me.readln.etc;

public class Tuple <T1, T2> {

    private T1   x; // first tuple's value
    private T2   y; // second tuple's value
    public  Tuple (T1 firstValue, T2 secondValue) { this.x = firstValue; this.y = secondValue; }

    public  T1   getX  ()           { return x;   }
    public  void setX  (T1 x)       { this.x = x; }
    public  T2   getY  ()           { return y;   }
    public void  setY  (T2 y)       { this.y = y; }
}

