#  Card Game – Backend (Spring Boot)

API REST robuste de distribution et tri de cartes à jouer.  
Conçue avec une architecture hexagonale simplifiée, elle met l'accent sur la séparation des responsabilités, la testabilité et la maintenabilité.

[![Java](https://img.shields.io/badge/Java-17-007396?logo=java)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-6DB33F?logo=springboot)](https://spring.io/projects/spring-boot)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.32-B7178C?logo=lombok)](https://projectlombok.org/)
[![Swagger](https://img.shields.io/badge/Swagger-UI-85EA2D?logo=swagger)](http://localhost:8080/swagger-ui.html)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?logo=docker)](https://www.docker.com/)
[![Tests](https://img.shields.io/badge/Tests-Passing-2ECC71)]()

---

##  Sommaire

1. [Stack technique](#-stack-technique)
2. [Installation & Exécution](#-installation--exécution)
3. [Endpoints & Documentation](#-endpoints--documentation)
4. [Stratégie de tests](#-stratégie-de-tests)
5. [Structure du projet](#-structure-du-projet)

## Technologies

- Java 17
- Spring Boot 3.2.5
- Maven
- Lombok
- Swagger (OpenAPI)

## Lancer l'application

mvn clean spring-boot:run

L'API est accessible sur : http://localhost:8080

## Endpoints


| Méthode | URL | Description |
| :--- | :--- | :--- |
| `GET` | `/api/random-hand-cards` | Paquet complet (52) + main aléatoire (10) + main triée |
| `GET` | `/api/stats` | Statistiques d'appels |
| `GET` | `/swagger-ui.html` | Documentation Swagger |



## Tests

```bash
mvn test
```
- 6 tests unitaires → logique métier (création, mélange, tirage, tri, exceptions)
- 1 test d'intégration → endpoint HTTP /api/random-hand-cards




## Structure
```

src/main/java/com/example/cardgame/
├── Config/
│   ├── CorsConfig.java           # CORS pour Angular
│   └── OpenAPIConfig.java        # Swagger
├── Constant/
│   └── CardGameConstants.java    # HAND_SIZE = 10
├── Controller/
│   └── CardGameController.java   # Endpoints REST
├── Dto/
│   ├── Mapper/
│   │   └── CardMapper.java       # Entity ↔ DTO
│   └── Response/
│       ├── CardResponseDTO.java
│       └── HandResponseDTO.java
├── Exception/
│   ├── ApiError.java             # Structure d'erreur
│   ├── BusinessException.java    # Exception métier
│   ├── GlobalExceptionHandler.java # Gestion centralisée
│   └── ResourceNotFoundException.java
├── Interface/
│   └── CardGameService.java      # Contrat du service
├── Model/                        # Entités immuables
│   ├── Card.java                # @Value (immuable)
│   ├── Rank.java                # Enum
│   └── Suit.java                # Enum
├── Service/
│   └── CardGameServiceImpl.java # Logique métier
└── CardGameApplication.java     # Point d'entrée
```
