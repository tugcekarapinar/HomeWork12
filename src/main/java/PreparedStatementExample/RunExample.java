package PreparedStatementExample;

public class RunExample {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "password";
        String databaseName = "tugce_karapinar";
        PreparedStatementExample preparedStatementExample = new PreparedStatementExample(jdbcUrl, username, password, databaseName);

        preparedStatementExample.Insert("Ali", "Veli", "Engineer", 40000.00);
        preparedStatementExample.Insert("Safinaz", "Nazli", "Worker",20000.00);
        preparedStatementExample.Insert("Demir Ege", "Karapinar", "Director", 300000.00);

        preparedStatementExample.Write();
        System.out.println("-----------------");

        preparedStatementExample.Update("Demir Ege","CEO");

        preparedStatementExample.Write();
        System.out.println("-----------------");

        preparedStatementExample.Delete("Safinaz");

        preparedStatementExample.Write();
}
}