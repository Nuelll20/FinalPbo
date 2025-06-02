public class Supplier {
    private String namaSupplier;
    private String namaPerusahaan;
    private String noTlp;

    public Supplier(String namaSupplier, String namaPerusahaan, String noTlp) {
        this.namaSupplier = namaSupplier;
        this.namaPerusahaan = namaPerusahaan;
        this.noTlp = noTlp;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    public String getNoTlp() {
        return noTlp;
    }

    public void tampilInfoSupplier() {
        System.out.printf("Supplier: %s, Perusahaan: %s, No.Telp: %s\n", namaSupplier, namaPerusahaan, noTlp);
    }
}
