package dip;

public class SmsSender implements MessageSender{
    @Override public void send(String to, String message) {

        System.out.println("SMS to "+to+": "+message);
    }
}
