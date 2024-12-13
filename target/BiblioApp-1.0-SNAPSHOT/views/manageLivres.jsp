<!DOCTYPE html>
<html lang="fr">
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
        .btn-action:last-child {
            margin-right: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center mb-4">Liste des Livres</h1>

    <!-- Formulaire de recherche -->
    <form action="livres" method="get" class="mb-4">
        <div class="input-group">
            <input type="text" name="search" class="form-control" placeholder="Rechercher un livre..." value="${search}" />
            <button class="btn btn-outline-secondary" type="submit">Rechercher</button>
        </div>
    </form>

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
            <!-- Liste des livres -->
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
                        <a href="livres?action=delete&id=${livre.id}" class="btn btn-sm btn-danger btn-action" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce livre ?')">Supprimer</a>
                        <a href="livres?action=emprunt&id=${livre.id}" class="btn btn-sm btn-primary btn-action" ${!livre.dispo ? 'disabled' : ''}>Emprunter</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Bouton pour ajouter un nouveau livre -->
        <div class="text-end mt-3">
            <a href="livres?action=add" class="btn btn-success">Ajouter un Livre</a>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>