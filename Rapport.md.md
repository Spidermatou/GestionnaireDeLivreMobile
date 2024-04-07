# Rapport

## Architecture

Nous disposons de plusieurs packages : un pour les fonctionnalités liées aux livres et un autre pour celles liées aux auteurs. À la racine, nous avons le MainActivity ainsi qu'un fichier contenant le code pour personnaliser le Spinner (sorte de "liste à puce" ou de Combobox winform) qui affiche les auteurs lors de la création d'un livre.

## Ce qui fonctionne

Toutes les fonctionnalités mentionnées dans l'énoncé ont été intégrées et fonctionnent correctement. Toutes les requêtes vers l'API sont opérationnelles.

## Les difficultés

Nous avons rencontré des difficultés lors de la mise en place de la fonctionnalité permettant de visualiser la description d'un auteur en cliquant sur son nom à partir de la description d'un livre. Nous pensons que cela est dû à notre architecture : la description d'un livre est contenue dans un fragment qui s'affiche dans un FragmentContainerView, lui-même situé dans un fragment de la barre de navigation. Cette architecture est similaire pour les auteurs. Nous n'avons pas réussi à lancer la description de l'auteur depuis la description d'un livre.
