public class Pembeli {
    private String nama;
    private String alamat;
    private Barang barang;
    private int jumlah;

    public Pembeli() {}

    public void inputNama(String nama) {
        this.nama = nama;
    }

    public void inputAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void inputBarang(Barang barang, int jumlah) {
        this.barang = barang;
        this.jumlah = jumlah;
    }

    public void cetakStruk() {
        double totalHarga = barang.getHarga() * jumlah;
        System.out.println("\n===== STRUK PEMBELIAN =====");
        System.out.println("Nama Pembeli : " + nama);
        System.out.println("Alamat       : " + alamat);
        System.out.println("Barang       : " + barang.getNamaBarang());
        System.out.println("Jumlah       : " + jumlah);
        System.out.printf("Total Harga  : %.2f\n", totalHarga);
        System.out.println("===========================");
    }

    public double getTotalHarga() {
        return barang.getHarga() * jumlah;
    }

    public Barang getBarang() {
        return barang;
    }

    public int getJumlah() {
        return jumlah;
    }
    public String getNama(){
        return nama;
    }
}
