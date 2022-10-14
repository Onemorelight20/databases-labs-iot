package ua.boretskyi.view.utill;

public enum Message {
    ENTER_FIELD("Enter '%s': "),
    ENTER_NEW_FIELD("Enter new '%s': "),
    TABLE("Table '%s': \n"),
    RECORD_WAS_CREATED("%s %s was created."),
    RECORD_WAS_NOT_CREATED("The %s was not created."),
    RECORD_WAS_UPDATED("%s %s was updated."),
    RECORD_WAS_NOT_UPDATED("The %s was not updated."),
    RECORD_WAS_DELETED("The %s was deleted."),
    RECORD_WAS_NOT_DELETED("The %s was not deleted."),;
    private final String text;

    Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
