package ua.boretskyi.view.utill;

public enum CommandExplanation {
    TABLE_SELECT("   %s - Table: %s"),
    CREATE_RECORD("  %s - Create %s"),
    UPDATE_RECORD("  %s - Update %s"),
    DELETE_RECORD("  %s - Delete from %s"),
    FIND_ALL("  %s - Find all %ss"),
    FIND_BY("  %s - Find %s by %s");
    private final String explanation;

    CommandExplanation(String explanation){
        this.explanation = explanation;
    }

    public String getExplanation(){
        return explanation;
    }
}
