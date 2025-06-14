public class Pembeli {
    private String nama;
    private String alamat;
    private String noHP;

    public Pembeli(String nama, String alamat, String noHP) {
        this.nama = nama;
        this.alamat = alamat;
        this.noHP = noHP;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHP;
    }

    public void setNoHp(String noHP) {
        this.noHP = noHP;
    }
}
