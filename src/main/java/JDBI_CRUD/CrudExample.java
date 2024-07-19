package JDBI_CRUD;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.core.statement.StatementException;

public class CrudExample {
    private String jdbcUrl;
    private String username;
    private String password;

    public CrudExample(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;

        CreateTable();
    }

    private void CreateTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS personel (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "ad VARCHAR(100) NOT NULL, " +
                "soyad VARCHAR(100) NOT NULL, " +
                "pozisyon VARCHAR(100), " +
                "maas DECIMAL(10,2)" +
                ")";
        Jdbi jdbi = Jdbi.create(jdbcUrl, username, password);
        try (Handle handle = jdbi.open()) {
            handle.execute(createTableSQL);
        } catch (StatementException e) {
            e.printStackTrace();
        }
    }
    public void Insert(String ad, String soyad, String pozisyon, double maas) {
        String insertSQL = "INSERT INTO personel (ad, soyad, pozisyon, maas) VALUES (?, ?, ?, ?)";
        Jdbi jdbi = Jdbi.create(jdbcUrl, username, password);
        try (Handle handle = jdbi.open()) {
            handle.execute(insertSQL, ad, soyad, pozisyon, maas);
            System.out.println("Kayıt başarıyla eklendi.");
        } catch (StatementException e) {
            e.printStackTrace();
        }
    }

    public void Write() {
        String selectSQL = "SELECT * FROM personel";
        Jdbi jdbi = Jdbi.create(jdbcUrl, username, password);
        try (Handle handle = jdbi.open();
             Query query = handle.createQuery(selectSQL)) {

            query.map((rs, ctx) -> {
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String pozisyon = rs.getString("pozisyon");
                double maas = rs.getDouble("maas");

                System.out.println("Ad: " + ad + ", Soyad: " + soyad + ", Pozisyon: " + pozisyon + ", Maaş: " + maas);
                return null;
            }).list();
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    public void Update(String ad, String yeniPozisyon) {
        String updateSQL = "UPDATE personel SET pozisyon = ? WHERE ad = ?";
        Jdbi jdbi = Jdbi.create(jdbcUrl, username, password);
        try (Handle handle = jdbi.open()) {

            int affectedRows = handle.execute(updateSQL,yeniPozisyon,ad);;
            if (affectedRows > 0) {
                System.out.println("Kayıt güncellendi.");
            } else {
                System.out.println("Kayıt bulunamadı.");
            }

        } catch (StatementException e) {
            e.printStackTrace();
        }
    }

    public void Delete(String ad) {
        String deleteSQL = "DELETE FROM personel WHERE ad = ?";
        Jdbi jdbi = Jdbi.create(jdbcUrl, username, password);
        try (Handle handle = jdbi.open()) {

            int affectedRows = handle.execute(deleteSQL, ad);
            if (affectedRows > 0) {
                System.out.println("Kayıt silindi.");
            } else {
                System.out.println("Kayıt bulunamadı.");
            }

        } catch (StatementException e) {
            e.printStackTrace();
        }
    }
}