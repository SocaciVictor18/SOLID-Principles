package srp;


import java.math.BigDecimal;

// Holds data + tax logic ONLY (no printing, no persistence)
public class Invoice {
    private final String number;
    private final BigDecimal netAmount;
    private final BigDecimal taxRate;

    public Invoice(String number, BigDecimal netAmount, BigDecimal taxRate) {
        this.number = number;
        this.netAmount = netAmount;
        this.taxRate = taxRate;
    }

    public String number() { return number; }
    public BigDecimal netAmount() { return netAmount; }
    public BigDecimal taxRate() { return taxRate; }


    public BigDecimal tax() { return netAmount.multiply(taxRate); }
    public BigDecimal total() { return netAmount.add(tax()); }
}
