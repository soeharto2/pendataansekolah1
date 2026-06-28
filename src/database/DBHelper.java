package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static final String URL =
        "jdbc:sqlite:C:/Users/rikko/Documents/NetBeansProjects/pendataansekolah1/sekolah.db";

    public static Connection getConnection() {
        try {

            File db = new File("C:/Users/rikko/Documents/NetBeansProjects/pendataansekolah1/sekolah.db");

            System.out.println("Database ada? " + db.exists());
            System.out.println("Lokasi: " + db.getAbsolutePath());
            System.out.println("Ukuran: " + db.length() + " bytes");

            Connection conn = DriverManager.getConnection(URL);

            System.out.println("Koneksi SQLite berhasil.");

            return conn;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}