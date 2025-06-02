public class AlatMedis extends Barang {
    private String tipe;

    public AlatMedis(String namaBarang, double harga, int stok, String tipe, Supplier supplier) {
        super(namaBarang, harga, stok, supplier);
        this.tipe = tipe;
    }

    @Override
    public void tampilInfo() {
        System.out.printf("Alat Medis: %s, Harga: %.2f, Stok: %d, Tipe: %s, Supplier: %s\n",
                namaBarang, harga, stok, tipe, supplier.getNamaSupplier());
    }

    @Override
    public boolean cekKadaluwarsa() {
        return false;
    }
}
