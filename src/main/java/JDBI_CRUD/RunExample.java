package JDBI_CRUD;

public class RunExample {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7720959";
        String username = "sql7720959";
        String password = "F1rlGlA7AZ";

        CrudExample crudExample = new CrudExample(jdbcUrl, username, password);

        crudExample.Insert("Ali", "Veli", "Engineer", 40000.00);
        crudExample.Insert("Safinaz", "Nazli", "Worker",20000.00);
        crudExample.Insert("Demir Ege", "Karapinar", "Director", 300000.00);

        crudExample.Write();
        System.out.println("-----------------");

        crudExample.Update("Demir Ege","CEO");

        crudExample.Write();
        System.out.println("-----------------");

        crudExample.Delete("Safinaz");

        crudExample.Write();
    }
}