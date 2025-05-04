# JSync

**JSync** est une application Java de synchronisation de fichiers entre deux répertoires. Elle permet la gestion de profils, la comparaison d’arborescences, la synchronisation dans les deux sens, ainsi que la détection de conflits.

## 📦 Fonctionnalités principales

- Synchronisation bidirectionnelle de fichiers entre deux dossiers
- Enregistrement des configurations sous forme de profils (`.sync`)
- Interface graphique simple pour sélectionner et lancer une synchronisation
- Détection et gestion manuelle des conflits
- Sauvegarde possible vers une URL distante configurable

---

## 🔧 Configuration initiale

Avant de tester le logiciel, vous devez configurer l’URL de base utilisée pour la sauvegarde des fichiers.

- Ouvrez la classe `Config` située dans le package `config`.
- Modifiez la variable `BASE_PATH` pour y indiquer le chemin absolu de votre répertoire de sauvegarde.

```java
// Exemple dans Config.java
public static final String BASE_PATH = "/home/utilisateur/Documents/profiles/";
```

---

## 🧪 Tester l’interface graphique

1. Rendez-vous dans le package `UI`.
2. Exécutez la classe `MainUI`.

Une première interface s’affichera listant les profils existants :

- Sélectionnez un profil.
- Vous serez redirigé vers une **deuxième interface** depuis laquelle vous pourrez lancer la synchronisation.

---

## 📝 Création de profils

> 🛠️ L’interface graphique de création de profil n’a pas pu être implémentée par manque de temps.

Pour créer un profil :

1. Exécutez la classe `Main` (dans le package principal).
2. Le programme vous demandera de saisir :
   - Le **nom du profil**
   - Le **chemin du répertoire source (A)**
   - Le **chemin du répertoire cible (B)**
3. Le profil sera automatiquement sauvegardé dans le dossier `profiles/` sous forme de fichier `.sync`.

Ce fichier sera ensuite utilisé par l’interface graphique pour lancer la synchronisation.

---

## ⚠️ Gestion des conflits

> 🚧 La gestion des conflits n’est actuellement disponible que via la console.

Lorsqu’un conflit est détecté (par exemple, un même fichier modifié des deux côtés), un message s’affiche dans la **console** indiquant les fichiers concernés.

Vous serez invité à choisir la direction de la synchronisation :

- Taper `1` pour synchroniser du répertoire A vers B
- Taper `2` pour synchroniser du répertoire B vers A

Aucune action automatique n’est effectuée sans votre validation.
