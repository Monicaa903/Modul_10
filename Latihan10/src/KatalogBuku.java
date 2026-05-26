import java.util.HashMap;
import java.util.Map;

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

public class KatalogBuku {
    public static void main(String[] args) {

        Map<String, Buku> katalog = new HashMap<>();
        katalog.put("978-602-03-1234", new Buku("978-602-03-1234", "Laskar Pelangi"));
        katalog.put("978-602-03-5678", new Buku("978-602-03-5678", "Bumi Manusia"));
        katalog.put("978-602-03-9999", new Buku("978-602-03-9999", "Negeri 5 Menara"));

        System.out.println("==============================");
        System.out.println("   SISTEM KATALOG BUKU");
        System.out.println("==============================");
        System.out.println("Total buku terdaftar: " + katalog.size());
        System.out.println();

        System.out.println("--- Daftar Semua Buku ---");
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
    }
}