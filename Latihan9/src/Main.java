import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class Buku {
    String isbn;
    String judul;

    public Buku(String isbn, String judul) {
        this.isbn = isbn;
        this.judul = judul;
    }

    public void info() {
        System.out.println("  ISBN : " + isbn);
        System.out.println("  Judul: " + judul);
    }
}

class Anggota {
    String idAnggota;
    String nama;
    String tipe;

    public Anggota(String idAnggota, String nama, String tipe) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.tipe = tipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anggota anggota = (Anggota) o;
        return java.util.Objects.equals(idAnggota, anggota.idAnggota);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(idAnggota);
    }

    public void info() {
        System.out.println("  ID   : " + idAnggota);
        System.out.println("  Nama : " + nama);
        System.out.println("  Tipe : " + tipe);
    }
}

public class Main {

    static Map<String, Buku> katalog = new HashMap<>();
    static Set<Anggota> setAnggota = new HashSet<>();
    static LinkedList<String> antreanPinjam = new LinkedList<>();

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("   SOAL 1 — KATALOG BUKU (HashMap)");
        System.out.println("==========================================");
        katalog.put("978-602-03-1234", new Buku("978-602-03-1234", "Laskar Pelangi"));
        katalog.put("978-602-03-5678", new Buku("978-602-03-5678", "Bumi Manusia"));
        katalog.put("978-602-03-9999", new Buku("978-602-03-9999", "Negeri 5 Menara"));
        
        System.out.println("Total buku terdaftar: " + katalog.size());
        System.out.println("\n--- Daftar Semua Buku ---");
        for (Map.Entry<String, Buku> entry : katalog.entrySet()) {
            entry.getValue().info();
            System.out.println();
        }

        System.out.println("--- Simulasi Pencarian by ISBN ---");
        String[] isbnDicari = {"978-602-03-5678", "978-000-0000"};
        for (String isbn : isbnDicari) {
            System.out.println("Mencari ISBN: " + isbn);
            if (katalog.containsKey(isbn)) {
                System.out.println("  >> DITEMUKAN:");
                katalog.get(isbn).info();
            } else {
                System.out.println("  >> Buku dengan ISBN tersebut TIDAK DITEMUKAN.");
            }
            System.out.println();
        }

        System.out.println("==========================================");
        System.out.println("   SOAL 2 — DAFTAR ANGGOTA (HashSet)");
        System.out.println("==========================================");
        daftarAnggota(new Anggota("A001", "Andi Pratama", "Mahasiswa"));
        daftarAnggota(new Anggota("A002", "Budi Santoso", "Dosen"));
        daftarAnggota(new Anggota("A003", "Citra Dewi", "Mahasiswa"));
        
        System.out.println("\n--- Mencoba daftar DUPLIKAT (ID: A001, nama berbeda) ---");
        daftarAnggota(new Anggota("A001", "Andi Tirta (duplikat)", "Mahasiswa"));

        System.out.println("\n--- Hasil Akhir Daftar Anggota (" + setAnggota.size() + " anggota) ---");
        for (Anggota a : setAnggota) {
            a.info();
            System.out.println();
        }

        System.out.println("==========================================");
        System.out.println("   SOAL 3 — ANTREAN PEMINJAMAN (LinkedList)");
        System.out.println("==========================================");
        System.out.println("Format data: \"idAnggota#isbn\"\n");
        System.out.println("--- Simulasi 4 Permintaan Peminjaman ---");
        
        masukAntrean(new Anggota("A001", "Andi Pratama", "Mahasiswa"), katalog.get("978-602-03-1234"));
        masukAntrean(new Anggota("A002", "Budi Santoso", "Dosen"), katalog.get("978-602-03-5678"));
        masukAntrean(new Anggota("A003", "Citra Dewi", "Mahasiswa"), katalog.get("978-602-03-9999"));
        masukAntrean(new Anggota("A001", "Andi Pratama", "Mahasiswa"), katalog.get("978-602-03-5678"));
        
        System.out.println("\nKondisi antrean saat ini (" + antreanPinjam.size() + " item):");
        int urutan = 1;
        for (String item : antreanPinjam) {
            System.out.println("  " + urutan + ". " + item);
            urutan++;
        }

        System.out.println("\n==========================================");
        System.out.println("   SOAL 4 — PROSES VALIDASI PEMINJAMAN");
        System.out.println("==========================================");
        
        Set<String> bukuDipinjam = new HashSet<>();

        while (!antreanPinjam.isEmpty()) {
            String dataDilayani = antreanPinjam.removeFirst();
            String[] dataPecah = dataDilayani.split("#");
            String idAnggota = dataPecah[0];
            String isbnBuku = dataPecah[1];

            System.out.println(">> Memproses: " + dataDilayani);

            boolean anggotaValid = false;
            String namaAnggota = "";
            for (Anggota agt : setAnggota) {
                if (agt.idAnggota.equals(idAnggota)) {
                    anggotaValid = true;
                    namaAnggota = agt.nama;
                    break;
                }
            }

            boolean isbnValid = katalog.containsKey(isbnBuku);

            if (!anggotaValid) {
                System.out.println("   [GAGAL] Anggota ID '" + idAnggota + "' tidak terdaftar di sistem.");
            } else if (!isbnValid) {
                System.out.println("   [GAGAL] Buku ISBN '" + isbnBuku + "' tidak terdaftar di katalog.");
            } else if (bukuDipinjam.contains(isbnBuku)) {
                System.out.println("   [GAGAL] Buku '" + katalog.get(isbnBuku).judul + "' sedang dipinjam oleh orang lain.");
            } else {
                bukuDipinjam.add(isbnBuku);
                System.out.println("   [BERHASIL] " + namaAnggota + " meminjam '" + katalog.get(isbnBuku).judul + "'");
            }
            System.out.println();
        }
        System.out.println("Semua antrean selesai diproses.");

        System.out.println("\n==========================================");
        System.out.println("   SOAL 5 (BONUS) — LAPORAN PEMINJAMAN");
        System.out.println("==========================================");
        System.out.println("Diurutkan Alfabetis by Judul (A-Z)");
        
        if (bukuDipinjam.isEmpty()) {
            System.out.println("Tidak ada buku yang sedang dipinjam.");
        } else {
            Map<String, String> laporanUrut = new TreeMap<>();
            for (String isbn : bukuDipinjam) {
                Buku b = katalog.get(isbn);
                if (b != null) {
                    laporanUrut.put(b.judul, isbn);
                }
            }

            System.out.printf("%-5s %-22s %s%n", "No.", "Judul Buku", "ISBN");
            System.out.println("-".repeat(45));
            
            int no = 1;
            for (Map.Entry<String, String> entry : laporanUrut.entrySet()) {
                System.out.printf("%-5d %-22s %s%n", no, entry.getKey(), entry.getValue());
                no++;
            }
            System.out.println("-".repeat(45));
            System.out.println("Total: " + bukuDipinjam.size() + " buku dipinjam");
        }
    }

    static void daftarAnggota(Anggota a) {
        boolean berhasil = setAnggota.add(a);
        if (berhasil) {
            System.out.println("[BERHASIL] Anggota '" + a.nama + "' (ID: " + a.idAnggota + ") terdaftar.");
        } else {
            System.out.println("[DITOLAK ] ID '" + a.idAnggota + "' sudah terdaftar! Input diabaikan.");
        }
    }

    static void masukAntrean(Anggota anggota, Buku buku) {
        if (anggota == null || buku == null) return;
        
        String data = anggota.idAnggota + "#" + buku.isbn;

        if (anggota.tipe.equals("Dosen")) {
            antreanPinjam.addFirst(data);
            System.out.println("[MASUK - DEPAN   ] " + anggota.nama + " (Dosen)     -> " + data);
        } else {
            antreanPinjam.addLast(data);
            System.out.println("[MASUK - BELAKANG] " + anggota.nama + " (Mahasiswa) -> " + data);
        }
    }
}