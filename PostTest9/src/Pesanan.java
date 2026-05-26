public class Pesanan {
    String namaPemesan;
    String judulFilm;
    String kodeKursi;
    int harga;

    public Pesanan(String namaPemesan, String judulFilm, String kodeKursi, int harga) {
        this.namaPemesan = namaPemesan;
        this.judulFilm = judulFilm;
        this.kodeKursi = kodeKursi;
        this.harga = harga;
    }

    @Override
    public String toString() {
        return "Nama: " + namaPemesan + ", Film: " + judulFilm + ", Kursi: " + kodeKursi + ", Harga: Rp " + harga;
    }
}
