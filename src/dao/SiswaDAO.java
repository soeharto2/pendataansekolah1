package dao;

import database.DBHelper;
import model.Siswa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiswaDAO {

    // ==========================
    // SIMPAN
    // ==========================
    public boolean simpan(Siswa siswa) {

        String sql = "INSERT INTO spp(nis,nama,kelas,bulan,status) VALUES(?,?,?,?,?)";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, siswa.getNis());
            ps.setString(2, siswa.getNama());
            ps.setString(3, siswa.getKelas());
            ps.setString(4, siswa.getBulan());
            ps.setString(5, siswa.getStatus());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    // ==========================
    // UPDATE
    // ==========================
    public boolean update(Siswa siswa) {

        String sql = "UPDATE spp SET nama=?, kelas=?, bulan=?, status=? WHERE nis=?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, siswa.getNama());
            ps.setString(2, siswa.getKelas());
            ps.setString(3, siswa.getBulan());
            ps.setString(4, siswa.getStatus());
            ps.setString(5, siswa.getNis());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    // ==========================
    // HAPUS
    // ==========================
    public boolean hapus(String nis) {

        String sql = "DELETE FROM spp WHERE nis=?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nis);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    // ==========================
    // TAMPIL SEMUA DATA
    // ==========================
    public List<Siswa> getAll() {

        List<Siswa> list = new ArrayList<>();

        String sql = "SELECT * FROM spp";

        try (Connection conn = DBHelper.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Siswa s = new Siswa();

                s.setNis(rs.getString("nis"));
                s.setNama(rs.getString("nama"));
                s.setKelas(rs.getString("kelas"));
                s.setBulan(rs.getString("bulan"));
                s.setStatus(rs.getString("status"));

                list.add(s);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;

    }

    // ==========================
    // SEARCH
    // ==========================
    public List<Siswa> cari(String keyword) {

        List<Siswa> list = new ArrayList<>();

        String sql = """
                SELECT *
                FROM spp
                WHERE nis LIKE ?
                   OR nama LIKE ?
                   OR kelas LIKE ?
                   OR bulan LIKE ?
                   OR status LIKE ?
                """;

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String cari = "%" + keyword + "%";

            ps.setString(1, cari);
            ps.setString(2, cari);
            ps.setString(3, cari);
            ps.setString(4, cari);
            ps.setString(5, cari);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Siswa s = new Siswa();

                s.setNis(rs.getString("nis"));
                s.setNama(rs.getString("nama"));
                s.setKelas(rs.getString("kelas"));
                s.setBulan(rs.getString("bulan"));
                s.setStatus(rs.getString("status"));

                list.add(s);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;

    }

}