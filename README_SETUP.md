# Race API - Setup

## Prérequis

- Java 17+
- Maven
- PostgreSQL (en local ou Docker)

## Installation

### 1. Base de données

Lancez PostgreSQL avec Docker :
```bash
docker-compose up -d
```

Cela crée la base de données `race_db` et applique les migrations Flyway automatiquement.

### 2. Lancer le projet

```bash
mvn spring-boot:run
```

L'API sera disponible sur `http://localhost:8080`

## Endpoints principaux

### Runners
- `GET /runners` - Tous les coureurs
- `GET /runners/{id}` - Un coureur
- `POST /runners` - Créer un coureur
- `PUT /runners/{id}` - Modifier un coureur
- `DELETE /runners/{id}` - Supprimer un coureur
- `GET /runners/{runnerId}/races` - Les courses d'un coureur

### Races
- `GET /races` - Toutes les courses
- `GET /races?location=Paris` - Courses par lieu (bonus)
- `GET /races/{id}` - Une course
- `POST /races` - Créer une course
- `PUT /races/{id}` - Modifier une course

### Registrations
- `POST /races/{raceId}/registrations` - Inscrire un coureur
- `GET /races/{raceId}/registrations` - Participants d'une course
- `GET /races/{raceId}/participants/count` - Nombre de participants

## Tester l'API

Utilisez Postman avec la collection `postman-export.json`.\
**Attention le bonus du lieu n'est pas ecrit dans le fichier.**

## Règles métier

- Un coureur ne peut pas être inscrit deux fois à la même course
- Les emails doivent contenir un `@`
- Une course ne peut pas dépasser son nombre maximum de participants
- Les ressources doivent exister
