import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class main {
    private static Supplier[] suppliers = new Supplier[9]; // nilai dummy
    private static int supplierCount = 0;
    private static Barang[] daftarBarang = new Barang[100];
    private static int barangCount = 0;

    private static Transaksi[] daftarTransaksi = new Transaksi[100];
    private static int transaksiCount = 0;

    private static Pembeli[] daftarPembeli = new Pembeli[100];
    private static int pembeliCount = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        tambahSupplier(new Supplier("PT Sehat Jaya", "SETA69", "021-123456", "Obat"));
        tambahSupplier(new Supplier("CV Sehat Makmur", "KENGKI01", "021-654321", "Obat"));
        tambahSupplier(new Supplier("PT Jamu Prima", "PTR1U", "022-111222", "Jamu"));
        tambahSupplier(new Supplier("CV Jamu Sejahtera", "BTG667", "022-333444", "Jamu"));
        tambahSupplier(new Supplier("PT Medis Sehat", "XND1R", "023-555666", "Alat medis"));
        tambahSupplier(new Supplier("CV Alat Medis", "CLS2T", "023-777888", "Alat medis"));
        tambahSupplier(new Supplier("PT Makanan Segar", "BRJ3U", "024-999000", "Makanan"));
        tambahSupplier(new Supplier("CV Minuman Mantap", "OU23T", "024-111222", "Minuman"));

        daftarBarang[0] = new Obat(
                "Paracetamol", "20-12-2025", "Diminum", "Y", "3x sehari", "Tablet",
                "OBT001", "Paracetamol", 500, 1000, 50, suppliers[0]);

        daftarBarang[1] = new Jamu(
                "Tolak Angin", "Meredakan masuk angin", "Sido Muncul", "10-11-2024",
                "JMU001", "Tolak Angin", 1200, 2500, 30, suppliers[2]);

        daftarBarang[2] = new AlatMedis(
                "Termometer", "Mengukur suhu tubuh", "Omron",
                "ALT001", "Termometer Digital", 15000, 30000, 10, suppliers[5]);
        ((AlatMedis) daftarBarang[2]).setBerat(150);

        daftarBarang[3] = new MakanMinum(
                "Roti", "01-08-2024", "Susu",
                "MKM001", "Roti & Susu", 3000, 6000, 20, suppliers[6]);

        barangCount = 4; // Jumlah data awal yang diisi

        boolean running = true;
        while (running) {
            System.out.println("\n===== APLIKASI MANAJEMEN BARANG =====");
            System.out.println("1. Lihat Data Supplier");
            System.out.println("2. Tambah Barang");
            System.out.println("3. Tampilkan Semua Barang");
            System.out.println("4. Cari Barang Berdasarkan Nama");
            System.out.println("5. Cek Barang Kedaluwarsa");
            System.out.println("6. Penjualan & Cetak Nota");
            System.out.println("7. Lihat Semua Transaksi");
            System.out.println("8. Lihat Laporan Keuangan");
            System.out.println("9. Keluar");
            System.out.print("Pilih menu (1-9): ");

            int pilihan = 0;
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.println("\n=== DAFTAR SUPPLIER ===");
                    String garis = "+-----+----------------------+------------+-----------------+";
                    String format = "| %-3s | %-20s | %-10s | %-15s |\n";

                    System.out.println(garis);
                    System.out.printf(format, "No", "Nama Perusahaan", "Kode", "No. Telepon");
                    System.out.println(garis);

                    if (supplierCount == 0) {
                        System.out.println("| Tidak ada supplier yang tersedia.                                  |");
                    } else {
                        for (int i = 0; i < supplierCount; i++) {
                            try {
                                Supplier s = suppliers[i];
                                if (s != null) {
                                    System.out.printf(format, (i + 1), s.getNamaPerusahaan(), s.getKode(), s.getNoHp());
                                }
                            } catch (Exception e) {
                                System.out.printf("| %3d | Error membaca supplier.                           |\n",
                                        i + 1);
                            }
                        }
                    }

                    System.out.println(garis);
                    break;

                case 2: {
                    System.out.println("=== TAMBAH BARANG ===");

                    int jenis = 0;
                    while (true) {
                        System.out.println("1. Obat");
                        System.out.println("2. Jamu");
                        System.out.println("3. Alat Medis");
                        System.out.println("4. Makanan/Minuman");
                        System.out.print("Pilih jenis barang (1-4): ");

                        try {
                            jenis = Integer.parseInt(scanner.nextLine());
                            if (jenis >= 1 && jenis <= 4)
                                break;
                            else
                                System.out.println("Jenis barang tidak valid. Harus 1-4.");
                        } catch (NumberFormatException e) {
                            System.out.println("Input harus berupa angka.");
                        }
                    }
                    // Pilih supplier berdasarkan kode
                    Supplier pemasok = null;
                    while (pemasok == null) {
                        System.out.println("Daftar Supplier untuk jenis: " + (jenis == 1 ? "Obat"
                                : jenis == 2 ? "Jamu" : jenis == 3 ? "Alat Medis" : "Makan/Minum"));
                        String kategoriDicari = switch (jenis) {
                            case 1 -> "Obat";
                            case 2 -> "Jamu";
                            case 3 -> "Alat Medis";
                            case 4 -> "Makan/Minum";
                            default -> "";
                        };
                        while (pemasok == null) {
                            for (int i = 0; i < supplierCount; i++) {
                                Supplier s = suppliers[i];
                                if (s.getKategori().equalsIgnoreCase(kategoriDicari)) {
                                    System.out.printf("- %s (%s)\n", s.getNamaPerusahaan(), s.getKode());
                                }
                            }

                            System.out.print("Masukkan KODE Supplier: ");
                            String kodeInput = scanner.nextLine();
                            for (int i = 0; i < supplierCount; i++) {
                                if (suppliers[i].getKode().equalsIgnoreCase(kodeInput)
                                        && suppliers[i].getKategori().equalsIgnoreCase(kategoriDicari)) {
                                    pemasok = suppliers[i];
                                    break;
                                }
                            }

                            if (pemasok == null) {
                                System.out.println("Kode supplier tidak cocok dengan kategori, coba lagi.");
                            }
                        }
                    }

                    Barang barangBaru = null;

                    if (jenis == 1) {
                        System.out.println("--- INPUT DATA OBAT ---");
                        System.out.print("Nama Obat: ");
                        String namaObat = scanner.nextLine();

                        System.out.print("Kode Obat: ");
                        String kode = scanner.nextLine();

                        // Kadaluarsa default ke hari ini
                        LocalDate today = LocalDate.now();
                        String kadaluarsa = String.format("%02d-%02d-%d", today.getDayOfMonth(), today.getMonthValue(),
                                today.getYear());

                        System.out.print("Cara Penggunaan: ");
                        String caraPenggunaan = scanner.nextLine();

                        System.out.print("Resep Dokter (Y/N): ");
                        String resep = scanner.nextLine();

                        System.out.print("Dosis: ");
                        String dosis = scanner.nextLine();

                        System.out.print("Jenis Obat (Tablet/Sirup): ");
                        String jenisObat = scanner.nextLine();

                        // ---- Submenu Atur Harga ----
                        System.out.println("--- ATUR HARGA ---");
                        double hargaBeli = 0, hargaJual = 0;
                        int stok = 0;

                        while (true) {
                            try {
                                System.out.print("Harga Beli: ");
                                hargaBeli = Double.parseDouble(scanner.nextLine());

                                System.out.print("Harga Jual: ");
                                hargaJual = Double.parseDouble(scanner.nextLine());

                                System.out.print("Stok: ");
                                stok = Integer.parseInt(scanner.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Input harga atau stok tidak valid. Coba lagi.");
                            }
                        }

                        barangBaru = new Obat(namaObat, kadaluarsa, caraPenggunaan, resep, dosis, jenisObat,
                                kode, namaObat, hargaBeli, hargaJual, stok, pemasok);
                    }

                    if (barangBaru != null) {
                        daftarBarang[barangCount++] = barangBaru;
                        System.out.println("Barang berhasil ditambahkan:");
                        barangBaru.tampilkanInfo();
                    }

                    break;
                }
                case 3: {
                    tampilkanSemuaBarangDalamTabel();
                    break;
                }
                case 4:
                    System.out.print("Masukkan nama barang: ");
                    String cari = scanner.nextLine();
                    boolean ditemukan = false;

                    for (int i = 0; i < barangCount; i++) {
                        Barang b = daftarBarang[i];

                        if (b.getNamaBarang().equalsIgnoreCase(cari)) {
                            System.out.println("\n=== INFORMASI BARANG DITEMUKAN ===");
                            System.out.println("Jenis: " + (b instanceof Obat ? "Obat"
                                    : b instanceof Jamu ? "Jamu"
                                            : b instanceof AlatMedis ? "Alat Medis"
                                                    : b instanceof MakanMinum ? "Makanan/Minuman" : "Tidak Diketahui"));
                            b.tampilkanInfo(); // Akan otomatis memanggil implementasi sesuai subclass
                            ditemukan = true;
                        }
                    }
                    if (!ditemukan) {
                        System.out.println("Barang dengan nama tersebut tidak ditemukan.");
                    }
                    break;
                case 5: {
                    boolean adaKadaluarsa = false;

                    System.out.println("\n=== BARANG KADALUWARSA ===");
                    String line = "+----+---------------+------------------------+------------+------------+------+----------------------+\n";
                    String header = "| No | Jenis         | Nama Barang            | Harga Beli | Harga Jual | Stok | Kadaluarsa           |\n";

                    System.out.print(line);
                    System.out.print(header);
                    System.out.print(line);

                    int no = 1;
                    for (int i = 0; i < barangCount; i++) {
                        Barang b = daftarBarang[i];
                        if (b.cekKadaluwarsa()) {
                            String jenis = (b instanceof Obat) ? "Obat"
                                    : (b instanceof Jamu) ? "Jamu"
                                            : (b instanceof AlatMedis) ? "Alat Medis"
                                                    : (b instanceof MakanMinum) ? "Makan/Minum" : "Lainnya";

                            String kadaluarsa = "-";
                            if (b instanceof Obat obat)
                                kadaluarsa = obat.getKadaluarsa();
                            if (b instanceof Jamu jamu)
                                kadaluarsa = jamu.getKadaluarsa();
                            if (b instanceof MakanMinum mm)
                                kadaluarsa = mm.getKadaluarsa();

                            System.out.printf("| %-2d | %-13s | %-22s | %-10.0f | %-10.0f | %-4d | %-20s |\n",
                                    no++,
                                    jenis,
                                    b.getNamaBarang(),
                                    b.getHargaBeli(),
                                    b.getHargaJual(),
                                    b.getStok(),
                                    kadaluarsa);

                            adaKadaluarsa = true;
                        }
                    }

                    if (!adaKadaluarsa) {
                        System.out.println(
                                "| Tidak ada barang yang kadaluwarsa.                                                           |");
                    }

                    System.out.print(line);
                    break;
                }

                case 6: {
                    System.out.println("=== PENJUALAN & CETAK NOTA ===");

                    // Input nama & cari pembeli
                    System.out.print("Masukkan nama pembeli: ");
                    String namaPembeli = scanner.nextLine().trim();

                    Pembeli pembeli = null;
                    for (int i = 0; i < pembeliCount; i++) {
                        if (daftarPembeli[i] != null
                                && daftarPembeli[i].getNama().equalsIgnoreCase(namaPembeli)) {
                            pembeli = daftarPembeli[i];
                            break;
                        }
                    }

                    if (pembeli == null) {
                        // Input baru
                        System.out.print("Masukkan alamat pembeli: ");
                        String alamatPembeli = scanner.nextLine();

                        String noHpPembeli;
                        while (true) {
                            System.out.print("Masukkan No HP pembeli (hanya angka): ");
                            noHpPembeli = scanner.nextLine().trim();
                            if (noHpPembeli.matches("\\d+"))
                                break;
                            System.out.println("Nomor HP tidak valid. Harus hanya angka.");
                        }

                        pembeli = new Pembeli(namaPembeli, alamatPembeli, noHpPembeli);
                        daftarPembeli[pembeliCount++] = pembeli;
                    } else {
                        // Tampilkan data lama
                        System.out.println("Data pembeli ditemukan:");
                        System.out.println("- Nama   : " + pembeli.getNama());
                        System.out.println("- Alamat : " + pembeli.getAlamat());
                        System.out.println("- No HP  : " + pembeli.getNoHp());
                    }

                    // Pilih jenis barang
                    int jenis;
                    while (true) {
                        System.out.println("Pilih jenis barang:");
                        System.out.println("1. Obat");
                        System.out.println("2. Jamu");
                        System.out.println("3. Alat Medis");
                        System.out.println("4. Makanan/Minuman");
                        System.out.print("Pilihan (1-4): ");
                        try {
                            jenis = Integer.parseInt(scanner.nextLine());
                            if (jenis >= 1 && jenis <= 4)
                                break;
                        } catch (NumberFormatException ignored) {
                        }
                        System.out.println("Input salah. Harus angka.");
                    }

                    // Tampilkan hanya barang valid (jenis + tidak kadaluarsa)
                    int[] idxBarang = new int[barangCount];
                    int count = 0;
                    System.out.println("\nDaftar Barang Tersedia :");
                    for (int i = 0; i < barangCount; i++) {
                        Barang b = daftarBarang[i];
                        boolean matchJenis = switch (jenis) {
                            case 1 -> b instanceof Obat;
                            case 2 -> b instanceof Jamu;
                            case 3 -> b instanceof AlatMedis;
                            case 4 -> b instanceof MakanMinum;
                            default -> false;
                        };
                        if (matchJenis && !b.cekKadaluwarsa()) {
                            System.out.printf("%d. %s (Stok: %d, Harga: Rp %.0f)\n",
                                    count + 1, b.getNamaBarang(), b.getStok(), b.getHargaJual());
                            idxBarang[count++] = i;
                        }
                    }

                    if (count == 0) {
                        System.out.println("Tidak ada barang layak untuk dipilih.");
                        break;
                    }

                    // Pilih barang
                    int pilihanBarang;
                    while (true) {
                        System.out.print("Pilih barang nomor: ");
                        try {
                            pilihanBarang = Integer.parseInt(scanner.nextLine());
                            if (pilihanBarang >= 1 && pilihanBarang <= count)
                                break;
                        } catch (NumberFormatException ignored) {
                        }
                        System.out.println("Nomor tidak valid.");
                    }

                    Barang dipilih = daftarBarang[idxBarang[pilihanBarang - 1]];

                    // Input jumlah beli
                    int jumlahBeli;
                    while (true) {
                        System.out.print("Jumlah yang dibeli: ");
                        try {
                            jumlahBeli = Integer.parseInt(scanner.nextLine());
                            if (jumlahBeli > 0 && jumlahBeli <= dipilih.getStok())
                                break;
                            System.out.println("Stok tidak mencukupi!");
                        } catch (NumberFormatException ignored) {
                        }
                    }

                    double total = jumlahBeli * dipilih.getHargaJual();
                    System.out.printf("Total: Rp %.0f\n", total);

                    double bayar;
                    while (true) {
                        System.out.print("Masukkan uang bayar: ");
                        try {
                            bayar = Double.parseDouble(scanner.nextLine());
                            if (bayar >= total)
                                break;
                            System.out.println("Uang tidak cukup.");
                        } catch (NumberFormatException ignored) {
                        }
                    }

                    // Proses
                    dipilih.setStok(dipilih.getStok() - jumlahBeli);
                    Transaksi trx = new Transaksi(pembeli, dipilih, jumlahBeli);
                    daftarTransaksi[transaksiCount++] = trx;

                    System.out.println("\n===== NOTA PEMBELIAN =====");
                    trx.cetakNota(dipilih, jumlahBeli, total, bayar, LocalDate.now());
                    System.out.println("==========================");
                    break;
                }
                case 7: {
                    LocalDate hariIni = LocalDate.now();
                    boolean adaTransaksi = false;

                    System.out.println("\n=== TRANSAKSI HARI INI ===");
                    System.out.println("Tanggal: " + hariIni);
                    System.out.println(
                            "+----+----------------------+----------------------------+--------+--------------+");
                    System.out.println(
                            "| No | Nama Pembeli         | Nama Barang                | Jumlah | Total Harga  |");
                    System.out.println(
                            "+----+----------------------+----------------------------+--------+--------------+");

                    int no = 1;
                    for (int i = 0; i < transaksiCount; i++) {
                        Transaksi t = daftarTransaksi[i];
                        if (t.getTanggal().equals(hariIni)) {
                            double totalHarga = t.getItem().getHargaJual() * t.getJumlah();
                            System.out.printf("| %-2d | %-20s | %-26s | %-6d | Rp %-9.0f |\n",
                                    no++,
                                    t.getCustomer().getNama(),
                                    t.getItem().getNamaBarang(),
                                    t.getJumlah(),
                                    totalHarga);
                            adaTransaksi = true;
                        }
                    }

                    if (!adaTransaksi) {
                        System.out.println(
                                "| Tidak ada transaksi yang tercatat hari ini.                              |");
                    }

                    System.out.println(
                            "+----+----------------------+----------------------------+--------+--------------+");
                    break;
                }
                case 8: {
                    System.out.println("\n=== LAPORAN KEUANGAN HARI INI ===");
                    LocalDate today = LocalDate.now();

                    double totalPendapatan = 0;
                    double totalModal = 0;

                    System.out.println("Daftar Transaksi Hari Ini:");
                    System.out.printf("%-4s | %-20s | %-6s | %-12s | %-12s\n",
                            "No", "Nama Barang", "Jumlah", "Pendapatan", "Modal");
                    System.out.println("---------------------------------------------------------------");

                    int noTrans = 0;
                    for (int i = 0; i < transaksiCount; i++) {
                        Transaksi t = daftarTransaksi[i];
                        if (t.getTanggal().isEqual(today)) {
                            noTrans++;
                            Barang b = t.getItem();
                            int qty = t.getJumlah();
                            double pendapatan = b.getHargaJual() * qty;
                            double modal = b.getHargaBeli() * qty;

                            totalPendapatan += pendapatan;
                            totalModal += modal;

                            System.out.printf("%-4d | %-20s | %-6d | Rp%10.0f | Rp%10.0f\n",
                                    noTrans, b.getNamaBarang(), qty, pendapatan, modal);
                        }
                    }

                    if (noTrans == 0) {
                        System.out.println("Belum ada transaksi pada hari ini (" + today + ").");
                    } else {
                        System.out.println("---------------------------------------------------------------");
                        double labaBersih = totalPendapatan - totalModal;

                        System.out.printf("Total Pendapatan: Rp %.0f\n", totalPendapatan);
                        System.out.printf("Total Modal     : Rp %.0f\n", totalModal);
                        System.out.printf("Laba Bersih     : Rp %.0f — %s\n",
                                Math.abs(labaBersih),
                                labaBersih > 0 ? "UNTUNG 👍" : labaBersih < 0 ? "RUGI 👎" : "IMPAS ⚖️");
                    }
                    break;
                }

                case 9:
                    running = false;
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void tampilkanSemuaBarangDalamTabel() {
        String garis = "+----+---------------+--------+------------------------------+------------+------+----------------------+----------------------+\n";
        String header = "| No | Jenis         | Kode   | Nama Barang                  | Harga      | Stok | Kadaluarsa           | Supplier             |\n";

        if (barangCount == 0) {
            System.out.println("Belum ada data barang yang ditambahkan.");
            return;
        }

        System.out.print(garis);
        System.out.print(header);
        System.out.print(garis);

        for (int i = 0; i < barangCount; i++) {
            Barang b = daftarBarang[i];
            String jenis = "";
            String kadaluarsa = "-";

            if (b instanceof Obat obat) {
                jenis = "Obat";
                kadaluarsa = obat.getKadaluarsa();
            } else if (b instanceof Jamu jamu) {
                jenis = "Jamu";
                kadaluarsa = jamu.getKadaluarsa();
            } else if (b instanceof AlatMedis alat) {
                jenis = "Alat Medis";
            } else if (b instanceof MakanMinum mm) {
                jenis = "Makan/Minum";
                kadaluarsa = mm.getKadaluarsa();
            }

            System.out.printf("| %-2d | %-13s | %-6s | %-28s | %-10.2f | %-4d | %-20s | %-20s |\n",
                    i + 1,
                    jenis,
                    b.getKode(),
                    b.getNamaBarang(),
                    b.getHargaJual(),
                    b.getStok(),
                    kadaluarsa,
                    b.getPemasok().getNamaPerusahaan());
        }

        System.out.print(garis);
    }

    private static void tambahSupplier(Supplier s) {
        if (supplierCount >= suppliers.length) {
            Supplier[] temp = new Supplier[suppliers.length * 2];
            System.arraycopy(suppliers, 0, temp, 0, suppliers.length);
            suppliers = temp;
        }
        suppliers[supplierCount++] = s;
    }
}
