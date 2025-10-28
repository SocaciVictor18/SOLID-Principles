package dip;

public class EmailSender implements MessageSender{
    @Override public void send(String to, String message) {

        System.out.println("Email to "+to+": "+message);
    }
}
