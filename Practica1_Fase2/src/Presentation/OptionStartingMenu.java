package Presentation;

public enum OptionStartingMenu {
    LIST_CHARACTERS,
    MANAGE_TEAMS,
    LIST_ITEMS,
    SIMULATE_COMBAT,
    EXIT,
    ELSE;

    /**
     * Converts an integer value to its corresponding Option enum value.
     *
     * @param value the integer value to convert to an Option.
     * @return the corresponding Option enum value. If the value is not within the valid range, returns Option.ELSE.
     */
    public static OptionStartingMenu convertIntToEnum(int value) {

        if (value <= 6){
            return OptionStartingMenu.values()[value - 1];
        } else {
            return OptionStartingMenu.ELSE;
        }
    }
}