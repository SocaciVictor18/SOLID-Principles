
import srp.*;
import ocp.*;
import lsp.*;
import isp.*;
import dip.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;


public class Main {
    public static void main(String[] args) {
// SRP
        var invoice = new Invoice("INV-001", new BigDecimal("100.00"), new BigDecimal("0.19"));
        new ConsoleInvoicePrinter().print(invoice);
        new FileInvoiceRepository(Paths.get("invoices.txt")).save(invoice);


// OCP
        var total = new AreaCalculator().totalArea(List.of(new Circle(2), new Rectangle(3, 4)));
        System.out.println("Total area = " + total);


// LSP
        Bird b1 = new Eagle();
        Bird b2 = new Penguin();
        if (b1 instanceof Flyer f) f.fly();
// if (b2 instanceof Flyer f2) f2.fly(); // not a flyer — and that's OK


// ISP
        Printer p = new SimplePrinter();
        p.print("Hello");


// DIP
        var service = new NotificationService(new EmailSender());
        service.notifyUser("alice@example.com", "Welcome!");
    }
}