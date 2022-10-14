package ua.boretskyi.view.utill;

public enum CommandExplanation {
    TABLE_SELECT("   %s - Table: %s"),
    CREATE_RECORD("  %d - Create %s"),
    UPDATE_RECORD("  %d - Update %s"),
    DELETE_RECORD("  %d - Delete from %s"),
    FIND_ALL("  %d - Find all %ss"),
    FIND_BY("  %d - Find %s by %s");
    private final String explanation;

    CommandExplanation(String explanation){
        this.explanation = explanation;
    }

    public String getExplanation(){
        return explanation;
    }
}
