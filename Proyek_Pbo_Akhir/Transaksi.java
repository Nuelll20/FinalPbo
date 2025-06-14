import java.time.LocalDate;

public class Transaksi {
    private Pembeli customer;
    private Barang item;
    private int jumlah;
    private LocalDate tanggal;

    public Transaksi(Pembeli customer, Barang item, int jumlah) {
        this.customer = customer;
        this.item = item;
        this.jumlah = jumlah;
        this.tanggal = LocalDate.now();
    }
    public LocalDate getTanggal(){
        return this.tanggal;
    }
    public Pembeli getCustomer() {
        return customer;
    }

    public void setCustomer(Pembeli customer) {
        this.customer = customer;
    }

    public Barang getItem() {
        return item;
    }

    public void setItem(Barang item) {
        this.item = item;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double method(String type) {
        return 0.0; // Placeholder for calculation logic
    }

    public void cetakNota(Barang item, int jumlah, double totalHarga, double uangBayar, java.time.LocalDate tanggal) {
        System.out.println("\n===== NOTA PEMBELIAN =====");
        System.out.println("Tanggal       : " + tanggal);
        System.out.println("Pembeli       : " + customer.getNama());
        System.out.println("Alamat        : " + customer.getAlamat());
        System.out.println("No HP         : " + customer.getNoHp());
        System.out.println("--------------------------");
        System.out.println("Barang        : " + item.getNamaBarang());
        System.out.println("Harga Satuan  : Rp " + item.getHargaJual());
        System.out.println("Jumlah Beli   : " + jumlah);
        System.out.println("Total Harga   : Rp " + totalHarga);
        System.out.println("Uang Bayar    : Rp " + uangBayar);
        System.out.println("Kembalian     : Rp " + (uangBayar - totalHarga));
        System.out.println("==========================\n");
    }
}
