package com.cedraz.exams.app.model.constant;

import java.util.stream.Stream;

public enum Difficulty {
    EASY(15), MEDIUM(12), HARD(8);

    private int points;

    Difficulty(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public static Difficulty of(int points) {
        return Stream.of(Difficulty.values())
                .filter(p -> p.getPoints() == points)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
