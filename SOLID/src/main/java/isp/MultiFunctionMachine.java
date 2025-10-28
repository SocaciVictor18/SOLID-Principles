package isp;

// A device that needs both can implement both small interfaces
public class MultiFunctionMachine implements Printer, ScannerDevice {
    @Override public void print(String text) {  }
    @Override public String scan() { return "scanned"; }
}
