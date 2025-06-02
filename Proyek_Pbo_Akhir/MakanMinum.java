public class MakanMinum extends Barang {
    private String kadaluarsa;

    public MakanMinum(String namaBarang, double harga, int stok, String kadaluarsa, Supplier supplier) {
        super(namaBarang, harga, stok, supplier);
        this.kadaluarsa = kadaluarsa;
    }

    @Override
    public void tampilInfo() {
        System.out.printf("Makanan/Minuman: %s, Harga: %.2f, Stok: %d, Kadaluarsa: %s, Supplier: %s\n",
                namaBarang, harga, stok, kadaluarsa, supplier.getNamaSupplier());
    }

    @Override
    public boolean cekKadaluwarsa() {
        return kadaluarsa.compareTo("2024-12-31") < 0;
    }
}
