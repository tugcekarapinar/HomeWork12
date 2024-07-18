package org.example;

import MySqlExample.PreparedStatementExample;

public class Main {
    public static void main(String[] args) {
        PreparedStatementExample preparedStatementExample = new PreparedStatementExample();

        preparedStatementExample.Insert("Ali", "Veli", "Mühendis", 40000.00);
        preparedStatementExample.Insert("Safinaz", "Nazlı", "İşçi",20000.00);
        preparedStatementExample.Insert("Demir Ege", "Karapınar", "Müdür", 300000.00);

        preparedStatementExample.Write();
        System.out.println("-----------------");

        preparedStatementExample.Update("Demir Ege","CEO");

        preparedStatementExample.Write();
        System.out.println("-----------------");

        preparedStatementExample.Delete("Safinaz");

        preparedStatementExample.Write();

    }
}