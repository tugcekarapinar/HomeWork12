package MySqlExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PreparedStatementExample {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/personel_db";
    private String username = "root";
    private String password = "password";

    public void Insert(String ad, String soyad, String pozisyon, double maas) {
        String insertSQL = "INSERT INTO personel (ad, soyad, pozisyon, maas) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, ad);
            pstmt.setString(2, soyad);
            pstmt.setString(3, pozisyon);
            pstmt.setDouble(4, maas);

            pstmt.executeUpdate();
            System.out.println("Kayıt başarıyla eklendi.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Write() {
        String selectSQL = "SELECT * FROM personel";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(selectSQL);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String pozisyon = rs.getString("pozisyon");
                double maas = rs.getDouble("maas");

                System.out.println("Ad: " + ad + ", Soyad: " + soyad + ", Pozisyon: " + pozisyon + ", Maaş: " + maas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Update(String ad, String yeniPozisyon) {
        String updateSQL = "UPDATE personel SET pozisyon = ? WHERE ad = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, yeniPozisyon);
            pstmt.setString(2, ad);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Kayıt güncellendi.");
            } else {
                System.out.println("Kayıt bulunamadı.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Delete(String ad) {
        String deleteSQL = "DELETE FROM personel WHERE ad = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setString(1, ad);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Kayıt silindi.");
            } else {
                System.out.println("Kayıt bulunamadı.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}