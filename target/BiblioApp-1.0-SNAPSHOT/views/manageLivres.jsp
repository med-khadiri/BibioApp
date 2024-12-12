<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Livres</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            padding: 20px;
        }
        .table-container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .btn-action {
            margin-right: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center mb-4">Gestion des Livres</h1>

    <!-- Tableau des livres -->
    <div class="table-container">
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Titre</th>
                <th scope="col">Auteur</th>
                <th scope="col">Catégorie</th>
                <th scope="col">Disponible</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <!-- Parcourir la liste des livres -->
            <%-- Remplacer "livres" par le nom de l'attribut de requête contenant les livres --%>
            <c:forEach var="livre" items="${livres}">
                <tr>
                    <td>${livre.id}</td>
                    <td>${livre.titre}</td>
                    <td>${livre.auteur}</td>
                    <td>${livre.categorie}</td>
                    <td>
                        <span class="badge ${livre.dispo ? 'bg-success' : 'bg-danger'}">
                            ${livre.dispo ? 'Oui' : 'Non'}
                        </span>
                    </td>
                    <td>
                        <a href="livres?action=edit&id=${livre.id}" class="btn btn-sm btn-warning btn-action">Modifier</a>
                        <a href="livres?action=delete&id=${livre.id}" class="btn btn-sm btn-danger btn-action">Supprimer</a>
                        <%-- Bouton emprunter désactivé si indisponible --%>
                        <a href="emprunts?action=add&livreId=${livre.id}"
                           class="btn btn-sm btn-primary btn-action ${!livre.dispo ? 'disabled' : ''}">
                            Emprunter
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!-- Bouton pour ajouter un livre -->
        <div class="text-end mt-3">
            <a href="livres?action=add" class="btn btn-success">Ajouter un Livre</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>