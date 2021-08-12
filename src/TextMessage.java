public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";

    // input validation criteria
    private static final int MAX_TEXT_LENGTH = 500;

    public TextMessage(User sender, String text)
            throws OperationDeniedException {
        super.message(sender);
        if (text.length() > 500) {
            throw OperationDeniedException("EXCEED_MAX_LENGTH");
        } else if (text.equal(null) || sender.equal(null)) {
            throw IllegalArgumentException("sender or text is null");
        }
        String content = text;
    }

    // Yuxuan [16:38:36.868882500]: A sample text message.
    public String getContents() {
        String senderName = this.sender.displayname();
        String contentDate = date.toString()
        return null;
    }

}
