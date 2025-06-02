import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class main {
    private static Supplier[] suppliers = new Supplier[8];
    private static Barang[] daftarBarang = new Barang[100];
    private static int barangCount = 0;

    private static Pembeli[] daftarPembeli = new Pembeli[100];
    private static int pembeliCount = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inisialisasi supplier
        suppliers[0] = new Supplier("Seta", "PT Sehat Jaya", "021-123456");
        suppliers[1] = new Supplier("Kengki", "CV Sehat Makmur", "021-654321");
        suppliers[2] = new Supplier("Petrus", "PT Jamu Prima", "022-111222");
        suppliers[3] = new Supplier("Bintang", "CV Jamu Sejahtera", "022-333444");
        suppliers[4] = new Supplier("Xander", "PT Medis Sehat", "023-555666");
        suppliers[5] = new Supplier("Carlos", "CV Alat Medis", "023-777888");
        suppliers[6] = new Supplier("Burjo", "PT Makanan Segar", "024-999000");
        suppliers[7] = new Supplier("OU 23", "CV Minuman Mantap", "024-111222");

        boolean running = true;
        while (running) {
            System.out.println("\n===== APLIKASI MANAJEMEN BARANG =====");
            System.out.println("1. Lihat Data Supplier");
            System.out.println("2. Tambah Barang");
            System.out.println("3. Tampilkan Semua Barang");
            System.out.println("4. Cari Barang Berdasarkan Nama");
            System.out.println("5. Cek Barang Kedaluwarsa");
            System.out.println("6. Penjualan & Cetak Nota");
            System.out.println("7. Laporan Supplier (Perbandingan Harga)");
            System.out.println("8. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = 0;
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
                continue;
            }

            switch (pilihan) {
                case 1:
                    lihatDataSupplier();
                    break;
                case 2:
                    tambahBarang();
                    break;
                case 3:
                    tampilSemuaBarang();
                    break;
                case 4:
                    cariBarang();
                    break;
                case 5:
                    cekKadaluwarsa();
                    break;
                case 6:
                    prosesPenjualan();
                    break;
                case 7:
                    laporanSupplier();
                    break;
                case 8:
                    running = false;
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static boolean inputButuhResepDokter() {
        while (true) {
            System.out.print("Butuh resep dokter? (Iya/Y / Tidak/No/N): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("IYA") || input.equals("Y")) {
                return true;
            } else if (input.equals("TIDAK") || input.equals("NO") || input.equals("N")) {
                return false;
            } else {
                System.out.println("Input salah! Harap jawab dengan Iya, Y, Tidak, No, atau N.");
            }
        }
    }

    private static void lihatDataSupplier() {
        System.out.println("\n=== DATA SUPPLIER ===");
        for (int i = 0; i < suppliers.length; i++) {
            Supplier s = suppliers[i];
            System.out.printf("Supplier: %s, Perusahaan: %s, No.Telp: %s\n",
                    s.getNamaSupplier(), s.getNamaPerusahaan(), s.getNoTlp());

            if ((i + 1) % 2 == 0) {
                System.out.println("------------------------");
            }
        }
    }

    private static String inputTanggalKadaluarsa() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true) {
            System.out.print("Tanggal Kadaluarsa (dd-MM-yyyy): ");
            String input = scanner.nextLine().trim();

            try {
                LocalDate.parse(input, formatter);
                return input; // valid, kembalikan string tanggal
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal salah! Harap masukkan dengan format dd-MM-yyyy.");
            }
        }
    }

    private static String inputKategoriObat() {
        while (true) {
            System.out.print("Kategori obat (Tablet atau Sirup): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Tablet") || input.equalsIgnoreCase("Sirup")) {
                return input;
            } else {
                System.out.println("Input salah! Harap masukkan 'Tablet' atau 'Sirup'.");
            }
        }
    }

    private static void tambahBarang() {
        System.out.println("\n=== TAMBAH BARANG ===");
        System.out.println("1. Tambah Obat");
        System.out.println("2. Tambah Jamu");
        System.out.println("3. Tambah Alat Medis");
        System.out.println("4. Tambah Makanan/Minuman");
        System.out.println("5. Kembali ke Menu Utama");

        int pilih = 0;
        while (true) {
            System.out.print("Pilih: ");
            try {
                pilih = Integer.parseInt(scanner.nextLine());
                if (pilih >= 1 && pilih <= 5)
                    break;
                else
                    System.out.println("Pilihan harus antara 1 sampai 5.");
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, masukkan angka.");
            }
        }
        if (pilih == 5)
            return;

        Supplier[] pilihanSupplier = new Supplier[2];
        switch (pilih) {
            case 1:
                pilihanSupplier[0] = suppliers[0];
                pilihanSupplier[1] = suppliers[1];
                break;
            case 2:
                pilihanSupplier[0] = suppliers[2];
                pilihanSupplier[1] = suppliers[3];
                break;
            case 3:
                pilihanSupplier[0] = suppliers[4];
                pilihanSupplier[1] = suppliers[5];
                break;
            case 4:
                pilihanSupplier[0] = suppliers[6];
                pilihanSupplier[1] = suppliers[7];
                break;
        }

        System.out.println("Pilih Supplier:");
        System.out.println("1. " + pilihanSupplier[0].getNamaSupplier());
        System.out.println("2. " + pilihanSupplier[1].getNamaSupplier());

        int pilihSup = 0;
        while (true) {
            System.out.print("Masukkan pilihan supplier (1 atau 2): ");
            try {
                pilihSup = Integer.parseInt(scanner.nextLine());
                if (pilihSup == 1 || pilihSup == 2)
                    break;
                else
                    System.out.println("Pilihan supplier harus 1 atau 2.");
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, masukkan angka 1 atau 2.");
            }
        }

        Supplier supplierTerpilih = pilihanSupplier[pilihSup - 1];

        // Cek barang supplier ada atau tidak (sama seperti sebelumnya)
        Barang barangAda = null;
        for (int i = 0; i < barangCount; i++) {
            if (daftarBarang[i].getSupplier() == supplierTerpilih) {
                barangAda = daftarBarang[i];
                break;
            }
        }

        if (barangAda != null) {
            System.out.println("Supplier sudah memiliki barang dalam sistem.");
            System.out.println("Pilih:");
            System.out.println("1. Tambah stok barang yang sudah ada");
            System.out.println("2. Buat barang baru");

            int opsi = 0;
            while (true) {
                System.out.print("Pilihan: ");
                try {
                    opsi = Integer.parseInt(scanner.nextLine());
                    if (opsi == 1 || opsi == 2)
                        break;
                    else
                        System.out.println("Pilihan harus 1 atau 2.");
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid, masukkan angka 1 atau 2.");
                }
            }

            if (opsi == 1) {
                System.out.println("Barang dari supplier " + supplierTerpilih.getNamaSupplier() + ":");
                int countBarangSupplier = 0;
                for (int i = 0; i < barangCount; i++) {
                    if (daftarBarang[i].getSupplier() == supplierTerpilih) {
                        System.out.printf("%d. %s (Stok: %d)\n", countBarangSupplier + 1,
                                daftarBarang[i].getNamaBarang(), daftarBarang[i].getStok());
                        countBarangSupplier++;
                    }
                }
                if (countBarangSupplier == 0) {
                    System.out.println("Tidak ada barang dari supplier ini.");
                    return;
                }

                int nomorBarang = 0;
                while (true) {
                    System.out.print("Pilih nomor barang untuk ditambah stok: ");
                    try {
                        nomorBarang = Integer.parseInt(scanner.nextLine());
                        if (nomorBarang >= 1 && nomorBarang <= countBarangSupplier)
                            break;
                        else
                            System.out.println("Nomor barang tidak valid.");
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid, masukkan angka.");
                    }
                }

                Barang barangPilih = null;
                int hitung = 0;
                for (int i = 0; i < barangCount; i++) {
                    if (daftarBarang[i].getSupplier() == supplierTerpilih) {
                        hitung++;
                        if (hitung == nomorBarang) {
                            barangPilih = daftarBarang[i];
                            break;
                        }
                    }
                }

                int stokTambah = 0;
                while (true) {
                    System.out.print("Masukkan jumlah stok tambahan: ");
                    try {
                        stokTambah = Integer.parseInt(scanner.nextLine());
                        if (stokTambah > 0)
                            break;
                        else
                            System.out.println("Jumlah stok harus lebih dari 0.");
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid, masukkan angka.");
                    }
                }

                barangPilih.stok += stokTambah;
                System.out.println("Stok berhasil ditambahkan. Stok sekarang: " + barangPilih.getStok());
                return;
            } else {
                System.out.println("Lanjut buat barang baru.");
            }
        }

        System.out.print("Nama Barang: ");
        String nama = scanner.nextLine();

        double markup = 0;
        switch (pilih) {
            case 1:
                markup = 2000;
                break;
            case 2:
                markup = 2000;
                break;
            case 3:
                markup = 10000;
                break;
            case 4:
                markup = 1000;
                break;
        }

        double hargaDasar = 0;
        while (true) {
            System.out.print("Harga dasar: ");
            try {
                hargaDasar = Double.parseDouble(scanner.nextLine());
                if (hargaDasar > 0)
                    break;
                else
                    System.out.println("Harga harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, masukkan angka.");
            }
        }

        double hargaJual = hargaDasar + markup;
        // Harga jual tidak ditampilkan saat tambah barang

        int stok = 0;
        while (true) {
            System.out.print("Stok: ");
            try {
                stok = Integer.parseInt(scanner.nextLine());
                if (stok >= 0)
                    break;
                else
                    System.out.println("Stok harus nol atau lebih.");
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, masukkan angka.");
            }
        }

        Barang newBarang = null;
        switch (pilih) {
            case 1:
                String kadaluarsa = inputTanggalKadaluarsa();
                String kategori = inputKategoriObat();

                String dosis = "";
                if (kategori.equals("Tablet")) {
                    System.out.print("Masukkan dosis: ");
                    dosis = scanner.nextLine().trim();
                }

                boolean butuhResep = inputButuhResepDokter();
                newBarang = new Obat(nama, hargaJual, stok, kadaluarsa, kategori, dosis, butuhResep, supplierTerpilih);
                break;
            case 2:
                System.out.print("Fungsi Jamu: ");
                String fungsi = scanner.nextLine();
                newBarang = new Jamu(nama, hargaJual, stok, fungsi, supplierTerpilih);
                break;
            case 3:
                System.out.print("Tipe Alat Medis: ");
                String tipe = scanner.nextLine();
                newBarang = new AlatMedis(nama, hargaJual, stok, tipe, supplierTerpilih);
                break;
            case 4:
                String kd = inputTanggalKadaluarsa();
                newBarang = new MakanMinum(nama, hargaJual, stok, kd, supplierTerpilih);
                break;
        }

        if (barangCount < daftarBarang.length) {
            daftarBarang[barangCount++] = newBarang;
            System.out.println("Barang berhasil ditambahkan.");
        } else {
            System.out.println("Kapasitas barang sudah penuh.");
        }
    }

    private static void tampilSemuaBarang() {
        System.out.println("\n=== DAFTAR SEMUA BARANG ===");

        String format = "| %-3s | %-15s | %-10s | %-7s | %-12s | %-15s |\n";
        String garis = "+-----+-----------------+------------+---------+--------------+-----------------+";

        System.out.println(garis);
        System.out.printf(format, "No", "Nama Barang", "Jenis", "Stok", "Harga (Rp)", "Supplier");
        System.out.println(garis);

        for (int i = 0; i < barangCount; i++) {
            Barang b = daftarBarang[i];
            String jenis = b.getClass().getSimpleName();
            System.out.printf(format,
                    (i + 1),
                    b.getNamaBarang(),
                    jenis,
                    b.getStok(),
                    String.format("%.2f", b.getHarga()),
                    b.getSupplier().getNamaSupplier());
        }
        System.out.println(garis);
    }

    private static void cariBarang() {
        System.out.print("\nMasukkan nama barang yang dicari: ");
        String cariNama = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < barangCount; i++) {
            if (daftarBarang[i].getNamaBarang().equalsIgnoreCase(cariNama)) {
                daftarBarang[i].tampilInfo();
                found = true;
            }
        }
        if (!found)
            System.out.println("Barang tidak ditemukan.");
    }

    private static void cekKadaluwarsa() {
        System.out.println("\n=== CEK BARANG KEDALUWARSA ===");
        boolean adaKadaluarsa = false;
        for (int i = 0; i < barangCount; i++) {
            if (daftarBarang[i].cekKadaluwarsa()) {
                System.out.print("Barang kadaluwarsa: ");
                daftarBarang[i].tampilInfo();
                adaKadaluarsa = true;
            }
        }
        if (!adaKadaluarsa)
            System.out.println("Tidak ada barang yang kedaluwarsa.");
    }

    private static void prosesPenjualan() {
        Pembeli pembeliTerpilih = null;

        if (pembeliCount == 0) {
            System.out.println("Belum ada data pembeli, silakan tambah pembeli baru.");
            tambahPembeliBaru();
        }

        while (pembeliTerpilih == null) {
            System.out.println("\n=== DAFTAR PEMBELI ===");
            for (int i = 0; i < pembeliCount; i++) {
                System.out.printf("%d. %s\n", i + 1, daftarPembeli[i].getNama());
            }
            System.out.printf("%d. Tambah Pembeli Baru\n", pembeliCount + 1);

            System.out.print("Pilih pembeli: ");
            int pilihPembeli = 0;
            try {
                pilihPembeli = Integer.parseInt(scanner.nextLine());
                if (pilihPembeli >= 1 && pilihPembeli <= pembeliCount + 1) {
                    if (pilihPembeli == pembeliCount + 1) {
                        tambahPembeliBaru();
                    } else {
                        pembeliTerpilih = daftarPembeli[pilihPembeli - 1];
                    }
                } else {
                    System.out.println("Pilihan tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
            }
        }

        boolean ulangPembelian = true;

        while (ulangPembelian) {
            System.out.println("Pilih jenis barang yang ingin dibeli:");
            System.out.println("1. Obat");
            System.out.println("2. Jamu");
            System.out.println("3. Alat Medis");
            System.out.println("4. Makanan/Minuman");
            System.out.println("5. Kembali ke Menu Utama");

            int jenisPilih = 0;
            while (true) {
                System.out.print("Pilihan: ");
                try {
                    jenisPilih = Integer.parseInt(scanner.nextLine());
                    if (jenisPilih >= 1 && jenisPilih <= 5)
                        break;
                    else
                        System.out.println("Pilihan harus antara 1-5.");
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid, masukkan angka.");
                }
            }

            if (jenisPilih == 5) {
                System.out.println("Kembali ke menu utama...");
                return; // keluar dari prosesPenjualan(), kembali ke menu utama
            }
            int count = 0;
            for (int i = 0; i < barangCount; i++) {
                String jenisBarang = daftarBarang[i].getClass().getSimpleName().toLowerCase();
                boolean cocok = false;
                switch (jenisPilih) {
                    case 1:
                        cocok = jenisBarang.equals("obat");
                        break;
                    case 2:
                        cocok = jenisBarang.equals("jamu");
                        break;
                    case 3:
                        cocok = jenisBarang.equals("alatmedis");
                        break;
                    case 4:
                        cocok = jenisBarang.equals("makanminum");
                        break;
                }
                if (cocok) {
                    count++;
                    System.out.printf("%d. %s - Harga: %.2f\n", count, daftarBarang[i].getNamaBarang(),
                            daftarBarang[i].getHarga());
                }
            }

            if (count == 0) {
                System.out.println("Tidak ada barang dari jenis ini.");
                continue;
            }

            System.out.print("Masukkan nama barang yang ingin dibeli (ketik persis): ");
            String namaBarangBeli = scanner.nextLine();

            Barang barangDibeli = null;
            for (int i = 0; i < barangCount; i++) {
                if (daftarBarang[i].getNamaBarang().equalsIgnoreCase(namaBarangBeli)) {
                    barangDibeli = daftarBarang[i];
                    break;
                }
            }
            if (barangDibeli == null) {
                System.out.println("Barang tidak ditemukan.");
                continue;
            }
            if (barangDibeli instanceof Obat) {
                Obat obat = (Obat) barangDibeli;
                if (obat.isButuhResepDokter()) {
                    System.out.println("Obat ini memerlukan resep dokter.");
                    System.out.print("Apakah Anda memiliki resep dokter? (Y/N): ");
                    String jawaban = scanner.nextLine().trim().toUpperCase();
                    if (!jawaban.equals("Y")) {
                        System.out.println("Maaf, pembelian obat ini memerlukan resep dokter.");
                        continue; // Kembali ke awal loop pembelian
                    }
                }
            }
            int jumlahBeli = 0;
            while (true) {
                System.out.print("Jumlah beli: ");
                try {
                    jumlahBeli = Integer.parseInt(scanner.nextLine());
                    if (jumlahBeli <= 0) {
                        System.out.println("Jumlah beli harus lebih dari 0.");
                    } else if (jumlahBeli > barangDibeli.getStok()) {
                        System.out.println("Stok tidak cukup. Stok tersedia: " + barangDibeli.getStok());
                        System.out.println("Pilih opsi:");
                        System.out.println("1. Pilih jenis barang lain");
                        System.out.println("2. Kembali ke menu utama");
                        int opsi = 0;
                        while (true) {
                            System.out.print("Pilihan: ");
                            try {
                                opsi = Integer.parseInt(scanner.nextLine());
                                if (opsi == 1) {
                                    break;
                                } else if (opsi == 2) {
                                    return;
                                } else {
                                    System.out.println("Pilihan harus 1 atau 2.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Input tidak valid, masukkan angka 1 atau 2.");
                            }
                        }
                        if (opsi == 1)
                            break;
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid, masukkan angka.");
                }
            }

            if (jumlahBeli > barangDibeli.getStok()) {
                continue;
            }

            pembeliTerpilih.inputBarang(barangDibeli, jumlahBeli);

            double totalHarga = pembeliTerpilih.getTotalHarga();
            System.out.printf("Total harga: %.2f\n", totalHarga);

            double uangBayar = 0;
            boolean bayarCukup = false;

            while (!bayarCukup) {
                System.out.print("Masukkan uang bayar: ");
                try {
                    uangBayar = Double.parseDouble(scanner.nextLine());
                    if (uangBayar < totalHarga) {
                        System.out.println("Uang tidak cukup. Silakan masukkan ulang.");
                    } else {
                        bayarCukup = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid, masukkan angka.");
                }
            }

            double kembalian = uangBayar - totalHarga;
            barangDibeli.kurangiStok(jumlahBeli);

            pembeliTerpilih.cetakStruk();
            System.out.printf("Uang Bayar: %.2f\n", uangBayar);
            System.out.printf("Kembalian : %.2f\n", kembalian);

            System.out.println("\nApakah ingin membeli barang lain? (Y/N)");
            String jawab = scanner.nextLine();
            if (!jawab.equalsIgnoreCase("Y")) {
                ulangPembelian = false;
            }
        }
    }

    private static void tambahPembeliBaru() {
        System.out.println("\n=== TAMBAH PEMBELI BARU ===");
        System.out.print("Nama Pembeli: ");
        String nama = scanner.nextLine();
        System.out.print("Alamat Pembeli: ");
        String alamat = scanner.nextLine();

        Pembeli pembeliBaru = new Pembeli();
        pembeliBaru.inputNama(nama);
        pembeliBaru.inputAlamat(alamat);

        if (pembeliCount < daftarPembeli.length) {
            daftarPembeli[pembeliCount++] = pembeliBaru;
            System.out.println("Pembeli berhasil ditambahkan.");
        } else {
            System.out.println("Kapasitas pembeli penuh.");
        }
    }

    private static void laporanSupplier() {
        System.out.println("\n=== LAPORAN SUPPLIER (Perbandingan Harga dan Stok) ===");

        String[] jenisBarang = { "Obat", "Jamu", "Alat Medis", "Makanan/Minuman" };

        for (int i = 0; i < jenisBarang.length; i++) {
            System.out.println("\n" + jenisBarang[i] + ":");

            Supplier sup1 = suppliers[i * 2];
            Supplier sup2 = suppliers[i * 2 + 1];

            // Cari barang yang sama dimiliki kedua supplier (nama sama)
            Barang barangSup1 = null;
            Barang barangSup2 = null;

            for (int j = 0; j < barangCount; j++) {
                if (daftarBarang[j].getSupplier() == sup1 && daftarBarang[j].getClass().getSimpleName()
                        .equalsIgnoreCase(jenisBarang[i].replaceAll("\\s", ""))) {
                    barangSup1 = daftarBarang[j];
                    break;
                }
            }
            for (int j = 0; j < barangCount; j++) {
                if (daftarBarang[j].getSupplier() == sup2 && daftarBarang[j].getClass().getSimpleName()
                        .equalsIgnoreCase(jenisBarang[i].replaceAll("\\s", ""))) {
                    barangSup2 = daftarBarang[j];
                    break;
                }
            }

            if (barangSup1 == null) {
                System.out.println("  Tidak ada barang dari supplier " + sup1.getNamaSupplier());
                continue;
            }
            if (barangSup2 == null) {
                System.out.println("  Tidak ada barang dari supplier " + sup2.getNamaSupplier());
                continue;
            }

            // Hitung total modal (stok * harga)
            double totalModalSup1 = barangSup1.getStok() * barangSup1.getHarga();
            double totalModalSup2 = barangSup2.getStok() * barangSup2.getHarga();

            System.out.printf("  Supplier %s - Barang: %s, Stok: %d, Harga: Rp %.2f, Total Modal: Rp %.2f\n",
                    sup1.getNamaSupplier(), barangSup1.getNamaBarang(), barangSup1.getStok(), barangSup1.getHarga(),
                    totalModalSup1);

            System.out.printf("  Supplier %s - Barang: %s, Stok: %d, Harga: Rp %.2f, Total Modal: Rp %.2f\n",
                    sup2.getNamaSupplier(), barangSup2.getNamaBarang(), barangSup2.getStok(), barangSup2.getHarga(),
                    totalModalSup2);

            if (totalModalSup1 < totalModalSup2) {
                System.out
                        .println("  Supplier " + sup1.getNamaSupplier() + " lebih menguntungkan (modal lebih kecil).");
            } else if (totalModalSup1 > totalModalSup2) {
                System.out
                        .println("  Supplier " + sup2.getNamaSupplier() + " lebih menguntungkan (modal lebih kecil).");
            } else {
                System.out.println("  Modal kedua supplier sama.");
            }
        }
    }
}
