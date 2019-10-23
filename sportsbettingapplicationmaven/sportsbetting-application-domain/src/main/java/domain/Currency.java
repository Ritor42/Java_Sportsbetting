package domain;

public enum Currency {
	HUF, EUR, USD;

	public static Currency lookup(String text) {
		for (Currency curr : Currency.values()) {
			if (curr.name().equalsIgnoreCase(text)) {
				return curr;
			}
		}
		return null;
	}
}
