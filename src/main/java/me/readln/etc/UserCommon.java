package me.readln.etc;

public interface UserCommon {

    default int getUserDimensionDecision(int MaxDimension) {
        return MaxDimension;
    }
    default String getUserAnswer(String question) {
        return question;
    }
    default boolean getUserAnswerYesNo(String question) {
        return true;
    }
    default Tuple getUserStep(GameField gameField, User[] users) {
        Tuple step = new Tuple(0, 0);
        return step;
    }
}
