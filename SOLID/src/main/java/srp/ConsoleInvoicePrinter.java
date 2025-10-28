package srp;


// Simple console printer (keeps printing concerns out of Invoice)
public class ConsoleInvoicePrinter implements  InvoicePrinter {
    @Override
    public void print(Invoice invoice) {
        System.out.printf("Invoice %s -> net = %s, total = %s%n",
                invoice.number(), invoice.netAmount(), invoice.tax(), invoice.total());
    }
}
