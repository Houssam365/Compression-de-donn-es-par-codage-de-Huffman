# Projet de Compression de Données par Codage de Huffman

## Description
Ce projet implémente l'algorithme de compression de données par codage de Huffman en Java. Le codage de Huffman est une méthode de compression sans perte qui remplace les caractères par des séquences de bits de longueur variable, permettant ainsi de réduire la taille des données tout en conservant leur intégrité.

## Objectifs
- **Détermination de l'alphabet et des fréquences de caractères** : Lire le texte d'entrée et déterminer les fréquences des caractères.
- **Construction de l'arbre de codage** : Construire l'arbre de Huffman à partir des fréquences de caractères.
- **Codage et compression du texte** : Remplacer chaque caractère du texte par son code de Huffman et écrire le texte compressé dans un fichier binaire.
- **Détermination du taux de compression** : Calculer le taux de compression en comparant le volume initial et le volume final du texte.
- **Détermination du nombre moyen de bits de stockage d’un caractère** : Calculer le nombre moyen de bits nécessaires pour stocker un caractère dans le texte compressé.

## Structure du Projet
Le projet est divisé en plusieurs étapes, chacune implémentée dans un fichier Java distinct :

1. **Etape1.java** : Détermination de l'alphabet et des fréquences de caractères.
2. **Etape2.java** : Construction de l'arbre de codage.
3. **Etape3.java** : Codage et compression du texte.
4. **Etape4_5.java** : Détermination du taux de compression et du nombre moyen de bits de stockage d’un caractère.
5. **App.java** : Fichier principal qui orchestre les différentes étapes du projet.

## Utilisation
Pour exécuter le projet, suivez les étapes ci-dessous :

1. **Compilation** : Compilez les fichiers Java avec la commande suivante :
    ```sh
    javac App.java Etape1.java Etape2.java Etape3.java Etape4_5.java
    ```

2. **Exécution** : Exécutez le fichier principal `App.java` en fournissant le fichier texte à compresser en argument :
    ```sh
    java App <input_file>
    ```

## Exemple
Supposons que vous avez un fichier texte `textesimple.txt` à compresser. Vous pouvez exécuter le projet comme suit :
```sh
java App textesimple.txt