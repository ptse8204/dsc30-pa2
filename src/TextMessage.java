/*
    Name: Edwin Tse
    PID:  A16616338
 */
public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";

    // input validation criteria
    private static final int MAX_TEXT_LENGTH = 500;

    public TextMessage(User sender, String text)
            throws OperationDeniedException {
        super(sender);
        if (text.length() > MAX_TEXT_LENGTH) {
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        } else if (text == null || sender == null) {
            throw new IllegalArgumentException("sender or text is null");
        }
        contents = text;
    }

    // Yuxuan [16:38:36.868882500]: A sample text message.
    public String getContents() {
        String senderName = this.getSender().displayName();
        String contentDate = this.getDate().toString();
        return senderName + " [" + contentDate + "]: " + contents;
    }

}
