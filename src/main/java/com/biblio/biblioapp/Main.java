package com.biblio.biblioapp;

import com.biblio.biblioapp.controlers.DatabaseConfig;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            System.out.println("Connexion réussie !");
            
            // Exemple de requête
            String query = "SELECT * FROM livre"; // Remplacez par votre table
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id_livre") + ", Titre: " + rs.getString("titre"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
    }
}
