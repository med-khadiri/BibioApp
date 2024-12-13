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
        super.init();
        livreDAO = new LivreDAO();
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "add":
                    addLivre(request, response);
                    break;
                case "update":
                    updateLivre(request, response);
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
        System.out.println("Livres récupérés : " + livres); // Debug
        request.setAttribute("livres", livres);
        request.getRequestDispatcher("/manageLivres.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("livre-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Livre existingLivre = livreDAO.getLivreById(id);
        request.setAttribute("livre", existingLivre);
        request.getRequestDispatcher("livre-form.jsp").forward(request, response);
    }

    private void addLivre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        boolean dispo = Boolean.parseBoolean(request.getParameter("dispo"));
        String categorie = request.getParameter("categorie");

        Livre newLivre = new Livre(titre, auteur, dispo, categorie);
        livreDAO.addLivre(newLivre);
        response.sendRedirect("livres");
    }

    private void updateLivre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        boolean dispo = Boolean.parseBoolean(request.getParameter("dispo"));
        String categorie = request.getParameter("categorie");

        Livre updatedLivre = new Livre(id, titre, auteur, dispo, categorie);
        livreDAO.updateLivre(updatedLivre);
        response.sendRedirect("livres");
    }

    private void deleteLivre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        livreDAO.deleteLivre(id);
        response.sendRedirect("livres");
    }
}

