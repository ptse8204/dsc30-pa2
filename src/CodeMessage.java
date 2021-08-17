/*
  Name: Edwin Tse
  PID:  A16616338
 */

import java.util.Locale;

public class CodeMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The source path is not valid.";

    // Error message to use in OperationDeniedException for the invalid line number
    private static final String INVALID_CODE =
            "The files are not long enough.";

    // input validation criteria
    private static final String[] ACCEPTABLE_EXTENSIONS =
            new String[] {"html", "java", "py", "mjs", "ipynb", "md", "yml"};

    // instance variable
    private String extension;
    private int lines;
    private int lineLimit = 10; //min requirment for line

    public CodeMessage(User sender, String codeSource, int lines)
                        throws OperationDeniedException {
        super(sender);
        if (codeSource == null) {
            throw new IllegalArgumentException("codeSource is null");
        }
        if (lines < lineLimit){
            throw new OperationDeniedException(INVALID_CODE);
        }
        this.contents = codeSource;
        extension = contents.toLowerCase();
        boolean throwError = true;
        int extensionIndex;
        this.lines = lines;
        // Locate where the extension locates
        for (int acceptIndex = 0; acceptIndex < ACCEPTABLE_EXTENSIONS.length; acceptIndex++) {
            String currentExtension = "." + ACCEPTABLE_EXTENSIONS[acceptIndex];
            if (extension.contains(currentExtension)){
                throwError = false;
                extensionIndex = extension.indexOf(currentExtension);
                this.extension = ACCEPTABLE_EXTENSIONS[acceptIndex];
            }
        }
        if (throwError) {
            throw new OperationDeniedException(INVALID_INPUT);
        }
    }

    public String getContents() {
        String senderName = this.getSender().displayName();
        String dateStr = this.getDate().toString();
        String codeAt = "]: Code at ";
        return senderName + " [" + dateStr + codeAt + contents;
    }

    public String getExtension() {
        return this.extension;
    }

    public int getLines() {
        return this.lines;
    }

}
