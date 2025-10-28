package srp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

// Persistence responsibility is isolated behind an interface
public class FileInvoiceRepository implements InvoiceRepository {
    private final Path path;

    public FileInvoiceRepository(Path path) {
        this.path = path;
    }

    @Override
    public void save(Invoice invoice) {
        try{
            String line = invoice.number() + "," + invoice.netAmount() + "," + invoice.taxRate();
            Files.writeString(path, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch (IOException e){
            throw new RuntimeException("Could not save invoice", e);
        }
    }
}
