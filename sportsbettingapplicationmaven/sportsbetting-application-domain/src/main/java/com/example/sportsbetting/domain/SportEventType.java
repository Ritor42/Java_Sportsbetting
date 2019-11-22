package com.example.sportsbetting.domain;

public enum SportEventType {
    Tennis, Football;

    public static SportEventType lookup(String text) {
        for (SportEventType type : SportEventType.values()) {
            if (type.name().equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}