import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Obat extends Barang {
    private String kadaluarsa;  // tetap simpan string input untuk info
    private LocalDate tanggalKadaluarsa;
    private boolean butuhResepDokter;
    private String kategori;
    private String dosis;

    public Obat(String namaBarang, double harga, int stok, String kadaluarsa, String kategori, String dosis, boolean butuhResepDokter, Supplier supplier) {
        super(namaBarang, harga, stok, supplier);
        this.kadaluarsa = kadaluarsa;
        this.kategori = kategori;
        this.dosis = dosis;
        this.butuhResepDokter = butuhResepDokter;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            this.tanggalKadaluarsa = LocalDate.parse(kadaluarsa, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Format tanggal kadaluarsa salah, harus dd-MM-yyyy");
            this.tanggalKadaluarsa = null; // atau set default
        }
    }

    public boolean isButuhResepDokter(){
        return butuhResepDokter;
    }

    public void setButuhResepDokter(boolean butuhResepDokter) {
        this.butuhResepDokter = butuhResepDokter;
    }

    public String getKategori(){
        return kategori;
    }
    public void setKategori(String kategori){
        this.kategori = kategori;
    }

    public String getDosis(){
        return dosis;
    }
    public void setDosis(String dosis){
        this.dosis = dosis;
    }

    @Override
    public void tampilInfo() {
        System.out.printf("Obat: %s, Harga: %.2f, Stok: %d, Kadaluarsa: %s, Supplier: %s\n",
                namaBarang, harga, stok, kadaluarsa, supplier.getNamaSupplier());
    }

    @Override
    public boolean cekKadaluwarsa() {
        if (tanggalKadaluarsa == null) return false; // anggap tidak kedaluwarsa kalau error

        LocalDate sekarang = LocalDate.now();
        return tanggalKadaluarsa.isBefore(sekarang) || tanggalKadaluarsa.isEqual(sekarang);
    }
}
