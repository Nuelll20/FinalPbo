public class AlatMedis extends Barang {
    private int berat;
    private String fungsi;
    private String merk;
    private String namaAlatMedis;

    public AlatMedis(String namaAlat, String fungsi, String merk, String kode, String namaBarang, double hargaJual,
            double hargaBeli, int stok, Supplier pemasok) {
        super(kode, namaBarang, hargaJual, hargaBeli, stok, pemasok);
        this.namaAlatMedis = namaAlat;
        this.fungsi = fungsi;
        this.merk = merk;
    }

    public String getNamaAlat() {
        return namaAlatMedis;
    }

    public void setNamaAlat(String namaAlat) {
        this.namaAlatMedis = namaAlat;
    }

    public String getFungsi() {
        return fungsi;
    }

    public void setFungsi(String fungsi) {
        this.fungsi = fungsi;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    @Override
    public boolean cekKadaluwarsa() {
        return false;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Kode Barang   : " + getKode());
        System.out.println("Nama Barang   : " + getNamaBarang());
        System.out.println("Harga Beli    : " + getHargaBeli());
        System.out.println("Harga Jual    : " + getHargaJual());
        System.out.println("Stok          : " + getStok());
        System.out.println("Fungsi        : " + getFungsi());
        System.out.println("Merk          : " + getMerk());
        System.out.println("Berat         : " + getBerat() + " gram");
        System.out.println("Supplier      : " + getPemasok().getNamaPerusahaan());
    }

}
