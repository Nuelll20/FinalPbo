// File: Obat.java

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Obat extends Barang {
    private String efekSamping;
    private String kadaluarsa;
    private String caraPenggunaan;
    private String resepDokter;
    private String namaObat;
    private String dosis;
    private String jenisObat;

    public Obat(String namaObat, String kadaluarsa, String caraPenggunaan, String resepDokter, String dosis,
            String jenisObat, String kode, String namaBarang, double hargaJual, double hargaBeli, int stok,
            Supplier pemasok) {
        super(kode, namaBarang, hargaBeli, hargaJual, stok, pemasok);
        this.namaObat = namaObat;
        this.kadaluarsa = kadaluarsa;
        this.caraPenggunaan = caraPenggunaan;
        this.resepDokter = resepDokter;
        this.dosis = dosis;
        this.jenisObat = jenisObat;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public String getKadaluarsa() {
        return kadaluarsa;
    }

    public void setKadaluarsa(String kadaluarsa) {
        this.kadaluarsa = kadaluarsa;
    }

    public String getResepDokter() { // ✔ Diperbaiki
        return resepDokter;
    }

    public void setResepDokter(String resepDokter) {
        this.resepDokter = resepDokter;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getJenisObat() {
        return jenisObat;
    }

    public void setJenisObat(String jenisObat) {
        this.jenisObat = jenisObat;
    }

    public String getEfekSamping() {
        return efekSamping;
    }

    public void setEfekSamping(String efekSamping) {
        this.efekSamping = efekSamping;
    }

    public String getCaraPenggunaan() {
        return caraPenggunaan;
    }

    public void setCaraPenggunaan(String caraPenggunaan) {
        this.caraPenggunaan = caraPenggunaan;
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
        System.out.println("Cara Pakai    : " + getCaraPenggunaan());
        System.out.println("Resep Dokter  : " + getResepDokter()); // ✔ Disesuaikan
        System.out.println("Dosis         : " + getDosis());
        System.out.println("Jenis Obat    : " + getJenisObat());
        System.out.println("Supplier      : " + getPemasok().getNamaPerusahaan());
    }
}
