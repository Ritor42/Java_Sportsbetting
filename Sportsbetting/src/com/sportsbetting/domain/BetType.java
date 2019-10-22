package com.sportsbetting.domain;

public enum BetType {
	WINNER, GOALS, PLAYERS_SCORE, NUMBER_OF_SETS;

	public static BetType lookup(String text) {
		for (BetType type : BetType.values()) {
			if (type.name().equalsIgnoreCase(text)) {
				return type;
			}
		}
		return null;
	}
}