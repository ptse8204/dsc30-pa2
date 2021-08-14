// This is a class for me to run test visually
public class Runtest {
    public static void main(String[] args) throws OperationDeniedException{
        User newUser = new Student ("COVID", "No vaccine could stop me :)");
        Message newMessage = new TextMessage(newUser, "Hi");
        System.out.println(newMessage.getContents());
        CodeMessage newCodeMessage = new CodeMessage(newUser, "LMAO.HtmL", 10);
        System.out.println(newCodeMessage.getContents());
        System.out.println(newCodeMessage.getExtension());
        System.out.println(newCodeMessage.getLines());
        Tutor errorUser = new Tutor("Jenny", "Good Teacher");
        System.out.println(newUser.displayBio());
        newUser.setBio("Creating a new variant");
        System.out.println(newUser.displayBio());
        System.out.println(newUser.displayName());
        System.out.println(errorUser.displayName());
        errorUser.setCustomTitle("COVID");
        System.out.println(errorUser.displayName());
        //TextMessage testTextMessage = new TextMessage(null, "Tough");
    }
}
