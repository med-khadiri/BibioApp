package com.biblio.biblioapp.controlers;


import com.biblio.biblioapp.models.Livre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/livres")
public class LivreServlet extends HttpServlet {
    private LivreDAO livreDAO;

    @Override
    public void init() throws ServletException {
        livreDAO = new LivreDAO(); // Le DAO utilise la connexion centralisée via DatabaseConfig
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteLivre(request, response);
                    break;
                default:
                    listLivres(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listLivres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Livre> livres = livreDAO.getAllLivres();
        request.setAttribute("livres", livres);
        request.getRequestDispatcher("livres.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("add-livre.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Livre livre = livreDAO.getLivreById(id);
        request.setAttribute("livre", livre);
        request.getRequestDispatcher("edit-livre.jsp").forward(request, response);
    }

    private void deleteLivre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        livreDAO.deleteLivre(id);
        response.sendRedirect("livres?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addLivre(request, response);
                    break;
                case "edit":
                    updateLivre(request, response);
                    break;
                default:
                    response.sendRedirect("livres?action=list");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void addLivre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        boolean dispo = Boolean.parseBoolean(request.getParameter("dispo"));
        String categorie = request.getParameter("categorie");

        Livre livre = new Livre(0, titre, auteur, dispo, categorie);
        livreDAO.addLivre(livre);
        response.sendRedirect("livres?action=list");
    }

    private void updateLivre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        boolean dispo = Boolean.parseBoolean(request.getParameter("dispo"));
        String categorie = request.getParameter("categorie");

        Livre livre = new Livre(id, titre, auteur, dispo, categorie);
        livreDAO.updateLivre(livre);
        response.sendRedirect("livres?action=list");
    }
}
