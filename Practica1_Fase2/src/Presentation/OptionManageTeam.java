package Presentation;

public enum OptionManageTeam {
    CREATE_TEAM,
    LIST_TEAMS,
    DELETE_TEAM,
    BACK,
    ELSE;

    /**
     * Converts an integer value to its corresponding Option enum value.
     *
     * @param value the integer value to convert to an Option.
     * @return the corresponding Option enum value. If the value is not within the valid range, returns Option.ELSE.
     */
    public static OptionManageTeam convertIntToEnum(int value) {

        if (value <= 4){
            return OptionManageTeam.values()[value - 1];
        } else {
            return OptionManageTeam.ELSE;
        }
    }
}