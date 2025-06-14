public class Supplier {
    private String namaPerusahaan;
    private String kode;
    private String noHp;
    private String kategori;

    public Supplier(String namaPerusahaan, String kode, String noHp, String kategori) {
        this.namaPerusahaan = namaPerusahaan;
        this.kode = kode;
        this.noHp = noHp;
        this.kategori = kategori;
    }

    public String getKategori(){
        return kategori;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public void tampilkanInfo() {
        System.out.printf("| %-20s | %-10s | %-15s |\n", namaPerusahaan, kode, noHp);
    }
}
