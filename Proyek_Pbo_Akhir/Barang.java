public abstract class Barang {
    protected String namaBarang;
    protected double harga;
    protected int stok;
    protected Supplier supplier;

    public Barang(String namaBarang, double harga, int stok, Supplier supplier) {
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stok = stok;
        this.supplier = supplier;
    }

    public abstract void tampilInfo();
    public abstract boolean cekKadaluwarsa();

    public String getNamaBarang() {
        return namaBarang;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void kurangiStok(int jumlah) {
        stok -= jumlah;
    }
}
