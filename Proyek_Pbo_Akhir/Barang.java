public abstract class Barang {
    protected String kode;
    protected String namaBarang;
    protected double hargaJual;
    protected double hargaBeli;
    protected int stok;
    protected Supplier pemasok;

    public Barang(String kode, String namaBarang, double hargaJual, double hargaBeli, int stok, Supplier pemasok) {
        this.kode = kode;
        this.namaBarang = namaBarang;
        this.hargaJual = hargaJual;
        this.hargaBeli = hargaBeli;
        this.stok = stok;
        this.pemasok = pemasok;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setNamaBarang(String nama) {
        this.namaBarang = nama;
    }

    public double getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(double harga) {
        this.hargaJual = harga;
    }

    public double getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(double harga) {
        this.hargaBeli = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public Supplier getPemasok() {
        return pemasok;
    }

    public void setPemasok(Supplier p) {
        this.pemasok = p;
    }

    public void kurangiStok(int jumlah) {
        this.stok -= jumlah;
    }

    public double hitungLaba() {
        return hargaJual - hargaBeli;
    }
    public String getNamaBarang(){
        return namaBarang;
    }

    public abstract void tampilkanInfo();
    public abstract boolean cekKadaluwarsa();
}
