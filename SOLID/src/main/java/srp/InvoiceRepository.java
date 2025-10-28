package srp;

// Persistence responsibility is isolated behind an interface
public interface InvoiceRepository {
    void save(Invoice invoice);
}
