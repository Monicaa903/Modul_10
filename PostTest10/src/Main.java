import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Map<String, Film> jadwalFilm = new HashMap<>();
        jadwalFilm.put("F01", new Film("Avengers: Endgame", 40000));
        jadwalFilm.put("F02", new Film("Inception", 35000));
        jadwalFilm.put("F03", new Film("Interstellar", 35000));

        Set<String> kursiTerisi = new HashSet<>();
        List<Pesanan> riwayatTransaksi = new ArrayList<>();

        System.out.println("~~~ SIMULASI PEMESANAN TIKET BIOSKOP ~~~");
        System.out.println();

        prosesPemesanan(jadwalFilm, kursiTerisi, riwayatTransaksi, "Munmun", "F01", "A1");
        prosesPemesanan(jadwalFilm, kursiTerisi, riwayatTransaksi, "Atin", "F02", "B4");
        prosesPemesanan(jadwalFilm, kursiTerisi, riwayatTransaksi, "Angga", "F01", "A2");
        prosesPemesanan(jadwalFilm, kursiTerisi, riwayatTransaksi, "Haru", "F03", "A1");

        System.out.println("\n~~~ RIWAYAT TRANSAKSI (URUTAN SUKSES) ~~~");
        for (Pesanan p : riwayatTransaksi) {
            System.out.println("- " + p);
        }
    }

    public static void prosesPemesanan(Map<String, Film> jadwal, Set<String> kursi, List<Pesanan> riwayat, 
        String nama, String kodeFilm, String kodeKursi) {
        
        System.out.println("Memproses Pesanan -> " + nama + " [" + kodeFilm + " - Kursi: " + kodeKursi + "]");

        if (!jadwal.containsKey(kodeFilm)) {
            System.out.println("GAGAL! Kode film " + kodeFilm + " tidak valid.");
            return;
        }

        if (kursi.contains(kodeKursi)) {
            System.out.println("GAGAL! Kursi " + kodeKursi + " sudah dipesan oleh pelanggan lain.");
            return;
        }

        Film film = jadwal.get(kodeFilm);
        kursi.add(kodeKursi);
        riwayat.add(new Pesanan(nama, film.judul, kodeKursi, film.hargaTiket));
        System.out.println("BERHASIL! Tiket berhasil dipesan");
    }
}
