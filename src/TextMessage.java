/*
    Name: Edwin Tse
    PID:  A16616338
 */

/**
 * The TextMessage class will ‘extend’ the abstract class Message.
 * Message has already implemented therefore TextMessage has access to those methods
 * without having to implement anything
 */
public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";

    // input validation criteria
    private static final int MAX_TEXT_LENGTH = 500;

    /**
     * This constructor will initizalize the Textmessage class
     * @param sender User the sender of the message
     * @param text String the text that the message
     * @throws OperationDeniedException throw if the length of text exceeds the maximum length
     * limit (500)
     * @throws IllegalArgumentException throw if the sender or text is null
     *
     */
    public TextMessage(User sender, String text)
            throws OperationDeniedException {
        super(sender);
        if (text == null || sender == null) {
            throw new IllegalArgumentException("sender or text is null");
        } else if (text.length() > MAX_TEXT_LENGTH) {
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        }
        contents = text;
    }

    /**
     * Returns a String
     * @return String in form Yuxuan [16:38:36.868882500]: A sample text message.
     */
    public String getContents() {
        String senderName = this.getSender().displayName();
        String contentDate = this.getDate().toString();
        return senderName + " [" + contentDate + "]: " + contents;
    }

}
