public class Jamu extends Barang {
    private String fungsi;

    public Jamu(String namaBarang, double harga, int stok, String fungsi, Supplier supplier) {
        super(namaBarang, harga, stok, supplier);
        this.fungsi = fungsi;
    }

    @Override
    public void tampilInfo() {
        System.out.printf("Jamu: %s, Harga: %.2f, Stok: %d, Fungsi: %s, Supplier: %s\n",
                namaBarang, harga, stok, fungsi, supplier.getNamaSupplier());
    }

    @Override
    public boolean cekKadaluwarsa() {
        return false;
    }
}
