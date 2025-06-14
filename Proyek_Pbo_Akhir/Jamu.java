// File: Jamu.java

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Jamu extends Barang {
    private String efekSamping;
    private String kadaluarsa;
    private String fungsi;
    private String merk;
    private String namaJamu;

    public Jamu(String namaJamu, String fungsi, String merk, String kadaluarsa,
            String kode, String namaBarang, double hargaJual, double hargaBeli,
            int stok, Supplier pemasok) {
        super(kode, namaBarang, hargaJual, hargaBeli, stok, pemasok);
        this.namaJamu = namaJamu;
        this.fungsi = fungsi;
        this.merk = merk;
        this.kadaluarsa = kadaluarsa;
    }

    public String getNamaJamu() {
        return namaJamu;
    }

    public void setNamaJamu(String namaJamu) {
        this.namaJamu = namaJamu;
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

    public String getKadaluarsa() {
        return kadaluarsa;
    }

    public void setKadaluarsa(String kadaluarsa) {
        this.kadaluarsa = kadaluarsa;
    }

    public String getEfekSamping() {
        return efekSamping;
    }

    public void setEfekSamping(String efekSamping) {
        this.efekSamping = efekSamping;
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
        System.out.println("Fungsi        : " + getFungsi());
        System.out.println("Merk          : " + getMerk());
        System.out.println("Supplier      : " + getPemasok().getNamaPerusahaan());
    }

}
