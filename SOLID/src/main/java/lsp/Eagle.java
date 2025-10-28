package lsp;

public class Eagle extends Bird implements Flyer{
    @Override public String species() { return "Eagle"; }
    @Override public void fly() {
        System.out.println("Fly fly");
    }
}
