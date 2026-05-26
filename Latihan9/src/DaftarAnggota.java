import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// ===== CLASS ANGGOTA =====
class Anggota {
    String idAnggota;
    String nama;
    String tipe; 

    public Anggota(String idAnggota, String nama, String tipe) {
        this.idAnggota = idAnggota;
        this.nama     = nama;
        this.tipe     = tipe;
    }

    // WAJIB DIOVERRIDE: dua objek Anggota dianggap SAMA jika idAnggota-nya sama,
    // meskipun dibuat dengan 'new' yang berbeda di memori.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anggota anggota = (Anggota) o;
        return Objects.equals(idAnggota, anggota.idAnggota);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idAnggota);
    }

    public void info() {
        System.out.println("  ID   : " + idAnggota);
        System.out.println("  Nama : " + nama);
        System.out.println("  Tipe : " + tipe);
    }
}
public class DaftarAnggota {
    public static void main(String[] args) {

        Set<Anggota> daftarAnggota = new HashSet<>();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("  SISTEM PENDAFTARAN ANGGOTA");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        daftarAnggota(daftarAnggota, new Anggota("A001", "Andi Pratama",   "Mahasiswa"));
        daftarAnggota(daftarAnggota, new Anggota("A002", "Budi Santoso",   "Dosen"));
        daftarAnggota(daftarAnggota, new Anggota("A003", "Citra Dewi",     "Mahasiswa"));

        System.out.println();
        System.out.println("~~~ Mencoba daftar DUPLIKAT (ID: A001, nama berbeda) ~~~");

        daftarAnggota(daftarAnggota, new Anggota("A001", "Andi Tirta (duplikat)", "Mahasiswa"));

        System.out.println();
        System.out.println("~~~ Hasil Akhir Daftar Anggota (" + daftarAnggota.size() + " anggota) ~~~");
        for (Anggota a : daftarAnggota) {
            a.info();
            System.out.println();
        }
    }
    static void daftarAnggota(Set<Anggota> set, Anggota a) {
        boolean berhasil = set.add(a);
        if (berhasil) {
            System.out.println("[BERHASIL] Anggota '" + a.nama + "' (ID: " + a.idAnggota + ") terdaftar.");
        } else {
            System.out.println("[DITOLAK ] ID '" + a.idAnggota + "' sudah terdaftar! Input diabaikan.");
        }
    }
}