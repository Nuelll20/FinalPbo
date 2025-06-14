import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MakanMinum extends Barang {
    private String kadaluarsa;
    private String namaMakanMinum;

    public MakanMinum(String namaMakanan, String kadaluarsa, String namaMinum, String kode, String namaBarang,
            double hargaJual, double hargaBeli, int stok, Supplier pemasok) {
        super(kode, namaBarang, hargaJual, hargaBeli, stok, pemasok);
        this.namaMakanMinum = namaMakanan + " & " + namaMinum;
        this.kadaluarsa = kadaluarsa;
    }

    public String getNamaMakanMinum() {
        return namaMakanMinum;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanMinum = namaMakanan;
    }

    public String getKadaluarsa() {
        return kadaluarsa;
    }

    public void setKadaluarsa(String kadaluarsa) {
        this.kadaluarsa = kadaluarsa;
    }

    @Override
    public boolean cekKadaluwarsa() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate tanggalKadaluarsa = LocalDate.parse(this.kadaluarsa, formatter);
            return tanggalKadaluarsa.isBefore(LocalDate.now());
        } catch (Exception e) {
            return false; // Jika parsing gagal, dianggap belum kadaluarsa
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Kode Barang   : " + getKode());
        System.out.println("Nama Barang   : " + getNamaBarang());
        System.out.println("Harga Beli    : " + getHargaBeli());
        System.out.println("Harga Jual    : " + getHargaJual());
        System.out.println("Stok          : " + getStok());
        System.out.println("Kadaluarsa    : " + getKadaluarsa());
        System.out.println("Supplier      : " + getPemasok().getNamaPerusahaan());
    }

}
