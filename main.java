import java.util.Scanner;

//Kelas Menu untuk merepresentasikan menu makanan dan minuman di restoran
class Resto {
    //Atribut nama, harga, dan kategori
    private String nama;
    private int harga;
    private String kategori;
    //Konstruktor untuk menginisialisasi atribut
    public Resto(String nama, int harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    //Getter untuk mendapatkan nilai atribut
    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public String getKategori() {
        return kategori;
    }


    //Kelas utama yang memiliki berbagai method untuk menampilkan daftar menu, menerima dan mengolah pesanan, menghitung total biaya, dan mencetak struk
   public class menuRestoran {
        //Array untuk menyimpan data menu restoran
        static Resto[] daftarMenu = {
                new Resto("Seblak", 20000, "Makanan"),
                new Resto("Cilok Goang", 15000, "Makanan"),
                new Resto("Martabak Telor", 10000, "Makanan"),
                new Resto("Tea Jus", 5000, "Minuman"),
                new Resto("Pop Ice", 7000, "Minuman"),
                new Resto("Nutrisari", 10000, "Minuman")
        };
        private static int Diskon;

        private static void tampilkanMenu() {
        }
        private static void menuRestoran() {
        }

        //Method untuk menampilkan daftar menu restoran berdasarkan kategori
        public static void tampilkanMenu(String kategori) {
            System.out.println("Daftar menu " + kategori + ":");
            for (int i = 0; i < daftarMenu.length; i++) {
                if (daftarMenu[i].getKategori().equals(kategori)) {
                    System.out.println(daftarMenu[i].getNama() + " - Rp. " + daftarMenu[i].getHarga());
                }
            }
            System.out.println();
        }

        //Method untuk menerima dan mengolah pesanan dari pelanggan
        public static void pesanMenu() {
            //Array untuk menyimpan item-menu yang dipesan dan jumlahnya
            String[] pesanan = new String[4];
            int[] jumlah = new int[4];

            //Variabel untuk menyimpan indeks array pesanan dan jumlah
            int index = 0;

            //Variabel untuk menyimpan pilihan pelanggan
            String pilihan;

            //Scanner untuk menerima input dari pelanggan
            Scanner input = new Scanner(System.in);

            //Menampilkan daftar menu makanan dan minuman
            tampilkanMenu("Makanan");
            tampilkanMenu("Minuman");

            //Meminta pelanggan untuk memasukkan menu-menu yang ingin dipesan dan jumlahnya
            System.out.println("Silakan masukkan menu-menu yang ingin Anda pesan (maksimal 4 menu) dan jumlahnya.");
            System.out.println("Contoh format input: Seblak = 2");
            System.out.println("Ketik \"Selesai\" jika sudah selesai memesan.");

            do {
                //Meminta pelanggan untuk memasukkan pilihan
                System.out.print("Masukkan pilihan Anda: ");
                pilihan = input.nextLine();

                //Jika pilihan bukan "Selesai" dan indeks masih kurang dari 4
                if (!pilihan.equalsIgnoreCase("Selesai") && index < 4) {
                    //Memisahkan pilihan menjadi dua bagian: nama menu dan jumlah
                    String[] bagian = pilihan.split("=");
                    String namaMenu = bagian[0].trim();
                    int jml = Integer.parseInt(bagian[1].trim());

                    //Menyimpan nama menu dan jumlah ke dalam array pesanan dan jumlah
                    pesanan[index] = namaMenu;
                    jumlah[index] = jml;

                    //Menambahkan indeks
                    index++;
                }
                //Mengulangi selama pilihan bukan "Selesai"
            } while (!pilihan.equalsIgnoreCase("Selesai"));

            //Menutup scanner
            input.close();

            //Memanggil method untuk menghitung total biaya pesanan
            hitungTotal(pesanan, jumlah);
        }

        //Method untuk menghitung total biaya pesanan berdasarkan item-menu yang dipilih dan jumlahnya
        public static void hitungTotal(String[] pesanan, int[] jumlah) {
            //Variabel untuk menyimpan total biaya keseluruhan pesanan
            int total = 0;

            //Variabel untuk menyimpan biaya pajak 10% dari total biaya keseluruhan
            int pajak;

            //Variabel untuk menyimpan biaya pelayanan sebesar Rp. 20.000
            int pelayanan = 20000;

            //Variabel untuk menyimpan diskon 10% jika total biaya keseluruhan melebihi Rp. 100.000
            int diskon;

            //Variabel untuk menyimpan penawaran beli satu gratis satu untuk salah satu kategori minuman jika total biaya keseluruhan melebihi Rp. 50.000
            int penawaran;

            //Variabel untuk menyimpan harga setelah diskon atau penawaran
            int hargaAkhir;

            //Menghitung total biaya keseluruhan pesanan dengan menjumlahkan harga per item dan jumlahnya
            for (int i = 0; i < pesanan.length; i++) {
                if (pesanan[i] != null) {
                    for (int j = 0; j < daftarMenu.length; j++) {
                        if (daftarMenu[j].getNama().equals(pesanan[i])) {
                            total += daftarMenu[j].getHarga() * jumlah[i];
                        }
                    }
                }
            }

            //Menghitung biaya pajak 10% dari total biaya keseluruhan
            pajak = (int) (total * 0.1);

            //Mengecek apakah total biaya keseluruhan melebihi Rp. 100.000
            if (total > 100000) {
                //Jika ya, maka menghitung diskon 10% dari total biaya keseluruhan
                diskon = (int) (total * 0.1);
                //Menghitung harga setelah diskon dengan mengurangi total biaya keseluruhan dengan diskon
                hargaAkhir = total - diskon;
                //Memanggil method untuk mencetak struk pesanan dengan parameter pesanan, jumlah, total, pajak, pelayanan, diskon, dan hargaAkhir
                cetakStruk(pesanan, jumlah, total, pajak, pelayanan, diskon, hargaAkhir);
            }
            //Mengecek apakah total biaya keseluruhan melebihi Rp. 50.000
            else if (total > 50000) {
                //Jika ya, maka menghitung penawaran beli satu gratis satu untuk salah satu kategori minuman
                penawaran = 0;
                for (int i = 0; i < pesanan.length; i++) {
                    if (pesanan[i] != null) {
                        for (int j = 0; j < daftarMenu.length; j++) {
                            if (pesanan[i].equals(daftarMenu[j].getNama()) && daftarMenu[j].getKategori().equals("Minuman")) {
                                //Menambahkan penawaran dengan harga minuman yang paling murah dikalikan dengan jumlahnya dibagi dua
                                penawaran += Math.min(daftarMenu[j].getHarga(), penawaran) * jumlah[i] / 2;
                            }
                        }
                    }
                }
                //Menghitung harga setelah penawaran dengan mengurangi total biaya keseluruhan dengan penawaran
                hargaAkhir = total - penawaran;
                //Memanggil method untuk mencetak struk pesanan dengan parameter pesanan, jumlah, total, pajak, pelayanan, penawaran, dan hargaAkhir
                cetakStruk(pesanan, jumlah, total, pajak, pelayanan, penawaran, hargaAkhir);
            }
            //Jika tidak ada diskon atau penawaran
            else {
                //Menghitung harga akhir dengan menjumlahkan total biaya keseluruhan dengan pajak dan pelayanan
                hargaAkhir = total + pajak + pelayanan;
                //Memanggil method untuk mencetak struk pesanan dengan parameter pesanan, jumlah, total, pajak, pelayanan, dan hargaAkhir
                cetakStruk(pesanan, jumlah, total, pajak, pelayanan, hargaAkhir);
            }
        }

        //Method untuk mencetak struk pesanan yang mencantumkan item-menu yang dipesan, jumlahnya, harga per item, total harga per item, total biaya pemesanan,
//informasi pajak dan biaya pajak, dan biaya pelayanan. Jika ada diskon atau penawaran dan harga setelah diskon atau penawaran, tampilkan informasinya ke dalam struk
        public static void cetakStruk(String[] pesanan, int[] jumlah, int total, int pajak, int pelayanan, int... diskonPenawaran) {
            //Variabel untuk menyimpan harga akhir
            int hargaAkhir;

            //Mengecek apakah ada diskon atau penawaran
            if (diskonPenawaran.length > 0) {
                //Jika ya, maka mengambil nilai diskon atau penawaran dari parameter
                hargaAkhir = diskonPenawaran[0];
            } else {
                //Jika tidak, maka mengambil nilai harga akhir dari parameter
                hargaAkhir = diskonPenawaran[1];
            }

            //Mencetak struk pesanan ke layar
            System.out.println("Struk Pesanan");
            System.out.println("-------------");
            System.out.println("Item\t\tJumlah\tHarga\tTotal Harga");
            for (int i = 0; i < pesanan.length; i++) {
                if (pesanan[i] != null) {
                    for (int j = 0; j < daftarMenu.length; j++) {
                        if (pesanan[i].equals(daftarMenu[j].getNama())) {
                            System.out.println(pesanan[i] + "\t\t" + jumlah[i] + "\t" + daftarMenu[j].getHarga() + "\t\t" + (daftarMenu[j].getHarga() * jumlah[i]));
                        }
                    }
                }
            }
            System.out.println("-------------");
            System.out.println("Total Pemesanan: Rp " + total);
            System.out.println("Pajak 10%: Rp " + pajak);
            System.out.println("Biaya Pelayanan: Rp " + pelayanan);


            //Mengecek apakah ada diskon atau penawaran
            if (diskonPenawaran.length > 0) {
                //Jika ya, maka menampilkan informasi diskon atau penawaran
                if (total > 100000) {
                    //Jika total biaya keseluruhan melebihi Rp. 100.000, maka menampilkan informasi diskon 10%
                    int diskon;
                    diskon = (int) (total * 0.1);
                    System.out.println("Diskon 10%: Rp " + diskonPenawaran[0]);
                    //Menampilkan harga setelah diskon
                    int totalPembayaran;
                    totalPembayaran = (total + 20000);
                    System.out.println("Harga Setelah Diskon: Rp " + totalPembayaran);
                    System.out.println("Total Pembayaran: Rp " + totalPembayaran);
                } else {
                    System.out.println("Anda tidak mendapatkan diskon");
                }
                if (total > 50000) {
                    int hargaPopIce = 7000; //harga per pop ice
                    int jumlahPopIce = 1; //jumlah pop ice yang dibeli
                    int totalBiaya = hargaPopIce * jumlahPopIce; //total biaya
                    String jenisBarang = "Pop Ice";//jenis barang yang dibeli
                    if ("Pop Ice".equals(jenisBarang)) {
                        int jumlahPopIceGratis = jumlahPopIce / 2;
                        //Jika total biaya keseluruhan melebihi Rp. 50.000, maka menampilkan informasi penawaran beli satu gratis satu untuk salah satu kategori minuman (tidak berlaku kelipatan)
                        System.out.println("Selamat!! Anda mendapatkan 1  pop ice gratis!");
                    } else {
                        //Jika tidak, maka tampilkan informasi bahwa tidak ada penawaran khusus untuk pop ice
                        System.out.println("Maaf, Anda tidak mendapatkan penawaran khusus untuk pop ice.");
                    }
                } else {
                    //Jika tidak, maka tampilkan informasi bahwa penawaran hanya berlaku untuk pop ice
                    System.out.println("Maaf, penawaran ini hanya berlaku untuk pembelian pop ice.");
                }
            }
            if (total > 50000 && total < 100000) {
                int totalPembayaran;
                pajak = (int) (total * 0.1);
                totalPembayaran = (int) (total * (1 + 0.1) + 20000);
                System.out.println("Total Pembayaran: Rp " + totalPembayaran);
            } else {
                //Jika tidak ada diskon atau penawaran, maka menampilkan harga akhir
                System.out.println("Total Pembayaran: Rp " + hargaAkhir);
            }
        }


        //Method main untuk menjalankan program
        public static void main(String[] args) {
            //Memanggil method untuk pesan menu
            pesanMenu();
        }
    }
}