package com.biblio.biblioapp.controlers;

import com.biblio.biblioapp.models.Livre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    // Méthode pour obtenir tous les livres
    public List<Livre> getAllLivres() {
        List<Livre> livres = new ArrayList<>();
        String query = "SELECT * FROM livre";
        try (Connection connection = DatabaseConfig.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Livre livre = new Livre();
                livre.setId(rs.getInt("id_livre"));
                livre.setTitre(rs.getString("titre"));
                livre.setAuteur(rs.getString("auteur"));
                livre.setDispo(rs.getBoolean("dispo"));
                livre.setCategorie(rs.getString("categorie"));
                livres.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    // Méthode pour ajouter un livre
    public boolean addLivre(Livre livre) {
        String query = "INSERT INTO livre (titre, auteur, dispo, categorie) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, livre.getTitre());
            pstmt.setString(2, livre.getAuteur());
            pstmt.setBoolean(3, livre.isDispo());
            pstmt.setString(4, livre.getCategorie());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Méthode pour mettre à jour un livre
    public boolean updateLivre(Livre livre) {
        String query = "UPDATE livre SET titre = ?, auteur = ?, dispo = ?, categorie = ? WHERE id_livre = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, livre.getTitre());
            pstmt.setString(2, livre.getAuteur());
            pstmt.setBoolean(3, livre.isDispo());
            pstmt.setString(4, livre.getCategorie());
            pstmt.setInt(5, livre.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Méthode pour supprimer un livre
    public boolean deleteLivre(int id) {
        String query = "DELETE FROM livre WHERE id_livre = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Méthode pour récupérer un livre par son ID
    public Livre getLivreById(int id) {
        String query = "SELECT * FROM livre WHERE id_livre = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Livre livre = new Livre();
                    livre.setId(rs.getInt("id_livre"));
                    livre.setTitre(rs.getString("titre"));
                    livre.setAuteur(rs.getString("auteur"));
                    livre.setDispo(rs.getBoolean("dispo"));
                    livre.setCategorie(rs.getString("categorie"));
                    return livre;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

