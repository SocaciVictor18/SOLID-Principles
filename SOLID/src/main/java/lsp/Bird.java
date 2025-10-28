package lsp;

// Base abstraction doesn't assume "can fly" (avoids the classic Penguin problem)
public abstract class Bird {
    public abstract String species();
}
