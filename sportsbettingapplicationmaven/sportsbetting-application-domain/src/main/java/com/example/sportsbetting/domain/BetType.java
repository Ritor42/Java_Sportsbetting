package com.example.sportsbetting.domain;

public enum BetType {
    Winner, Goals, Players_Score, Number_of_sets;

    public static BetType lookup(String text) {
        for (BetType type : BetType.values()) {
            if (type.name().equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}