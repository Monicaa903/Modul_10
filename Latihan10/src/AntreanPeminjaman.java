import java.util.LinkedList;

class Buku3 {
    String isbn;
    String judul;

    public Buku3(String isbn, String judul) {
        this.isbn  = isbn;
        this.judul = judul;
    }
}

class Anggota3 {
    String idAnggota;
    String nama;
    String tipe; 

    public Anggota3(String idAnggota, String nama, String tipe) {
        this.idAnggota = idAnggota;
        this.nama      = nama;
        this.tipe      = tipe;
    }
}

public class AntreanPeminjaman {
    public static void main(String[] args) {

        LinkedList<String> antrean = new LinkedList<>();

        System.out.println("==============================");
        System.out.println("  SISTEM ANTREAN PEMINJAMAN");
        System.out.println("==============================");
        System.out.println("Format data: \"idAnggota#isbn\"");
        System.out.println();

        Anggota3 mahasiswa1 = new Anggota3("M001", "Andi",  "Mahasiswa");
        Anggota3 dosen1     = new Anggota3("D001", "Pak Budi", "Dosen");
        Anggota3 mahasiswa2 = new Anggota3("M002", "Citra", "Mahasiswa");
        Anggota3 dosen2     = new Anggota3("D002", "Bu Dewi",  "Dosen");

        Buku3 buku1 = new Buku3("978-602-03-1234", "Laskar Pelangi");
        Buku3 buku2 = new Buku3("978-602-03-5678", "Bumi Manusia");
        Buku3 buku3 = new Buku3("978-602-03-9999", "Negeri 5 Menara");
        Buku3 buku4 = new Buku3("978-602-03-1111", "Pulang");

        System.out.println("--- Simulasi 4 Permintaan Peminjaman ---");
        masukAntrean(antrean, mahasiswa1, buku1);  
        masukAntrean(antrean, dosen1,     buku2);  
        masukAntrean(antrean, mahasiswa2, buku3);  
        masukAntrean(antrean, dosen2,     buku4);  

        System.out.println();
        System.out.println("Kondisi antrean saat ini:");
        tampilAntrean(antrean);

        System.out.println();
        System.out.println("--- Memproses Semua Antrean ---");
        while (!antrean.isEmpty()) {
            String dilayani = antrean.removeFirst();
            System.out.println("[DILAYANI] " + dilayani);
        }

        System.out.println();
        System.out.println("Semua antrean selesai diproses.");
    }

    static void masukAntrean(LinkedList<String> antrean, Anggota3 anggota, Buku3 buku) {
        String data = anggota.idAnggota + "#" + buku.isbn;

        if (anggota.tipe.equals("Dosen")) {
            antrean.addFirst(data);
            System.out.println("[MASUK - DEPAN] " + anggota.nama + " (Dosen) -> " + data);
        } else {
            antrean.addLast(data);
            System.out.println("[MASUK - BELAKANG] " + anggota.nama + " (Mahasiswa) -> " + data);
        }
    }

    static void tampilAntrean(LinkedList<String> antrean) {
        int urut = 1;
        for (String item : antrean) {
            System.out.println("  " + urut + ". " + item);
            urut++;
        }
    }
}
