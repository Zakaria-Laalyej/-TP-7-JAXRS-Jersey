# TP 7 : JAX-RS / Jersey — Services Web RESTful Bancaires

**Réalisé par :** Zakaria Laalyej (2DSI)  
**Module :** JEE / Architecture Microservices : Conception, Déploiement et Orchestration  

Ce projet met en œuvre un service Web RESTful de gestion de comptes bancaires en utilisant le standard **JAX-RS** et son implémentation de référence **Jersey**, intégré dans un écosystème **Spring Boot** avec persistance des données via **Spring Data JPA** et base de données en mémoire **H2**.

---

## Objectifs

- Définir et implémenter des points de terminaison RESTful conformes à la spécification **JAX-RS** (`@Path`, `@GET`, `@POST`, `@PUT`, `@DELETE`, `@Produces`, `@Consumes`).
- Mettre en œuvre la négociation de contenu pour supporter à la fois les formats **JSON** et **XML** (`@XmlRootElement`).
- Intégrer Jersey dans Spring Boot via une classe de configuration étendant `ResourceConfig` (`@Component`).
- Valider le cycle de vie CRUD complet via Postman, cURL et la console d'administration H2.

---

## Technologies Utilisées

- **Java 17 / 21**
- **Spring Boot 3.2.5**
- **Spring Boot Starter Jersey (JAX-RS)**
- **Spring Data JPA & Hibernate**
- **H2 Database (en mémoire)**
- **Maven**

---

## Architecture du Projet

```
tp7-jaxrs-jersey/
├── pom.xml
├── README.md
├── captures/
└── src/
    ├── main/
    │   ├── java/ma/rest/spring/
    │   │   ├── config/
    │   │   │   └── MyConfig.java               # Enregistrement des ressources Jersey
    │   │   ├── entities/
    │   │   │   ├── Compte.java                 # Entité JPA avec @XmlRootElement
    │   │   │   └── TypeCompte.java             # Enum (COURANT, EPARGNE)
    │   │   ├── repositories/
    │   │   │   └── CompteRepository.java       # Interface Spring Data JPA
    │   │   ├── ws/
    │   │   │   └── CompteRestJaxRSAPI.java     # Ressource REST JAX-RS (@Path("/banque"))
    │   │   └── MsBanqueApplication.java        # Point d'entrée & CommandLineRunner
    │   └── resources/
    │       └── application.properties          # Port 8082, H2 console, JPA ddl-auto
    └── test/java/ma/rest/spring/
        └── MsBanqueApplicationTests.java
```

---

## Configuration (`application.properties`)

```properties
spring.datasource.url=jdbc:h2:mem:banque
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
server.port=8082
spring.jpa.hibernate.ddl-auto=update
```

---

## Endpoints de l'API JAX-RS

L'API est exposée sous le chemin de base `http://localhost:8082/banque` :

| Méthode | URI | Description | Produces / Consumes |
|---|---|---|---|
| `GET` | `/banque/comptes` | Récupère tous les comptes | `application/json`, `application/xml` |
| `GET` | `/banque/comptes/{id}` | Récupère un compte par son identifiant | `application/json`, `application/xml` |
| `POST` | `/banque/comptes` | Crée un nouveau compte | `application/json`, `application/xml` |
| `PUT` | `/banque/comptes/{id}` | Met à jour un compte existant | `application/json`, `application/xml` |
| `DELETE` | `/banque/comptes/{id}` | Supprime un compte | - |

---

## Tests et Exécution

### 1. Démarrer le serveur
```bash
mvn spring-boot:run
```

### 2. Accéder à la console H2
- URL : `http://localhost:8082/h2-console`
- JDBC URL : `jdbc:h2:mem:banque`
- Utilisateur : `sa` (mot de passe vide)

### 3. Exemples de requêtes cURL

**Récupérer tous les comptes en JSON :**
```bash
curl -X GET http://localhost:8082/banque/comptes -H "Accept: application/json"
```

**Récupérer tous les comptes en XML :**
```bash
curl -X GET http://localhost:8082/banque/comptes -H "Accept: application/xml"
```

**Ajouter un compte en JSON :**
```bash
curl -X POST http://localhost:8082/banque/comptes \
  -H "Content-Type: application/json" \
  -d '{"solde": 7500.0, "type": "EPARGNE"}'
```

---

## Captures d'Écran

> Déposez vos captures d'écran dans le dossier `captures/` :
<img width="899" height="378" alt="Capture d’écran 2026-11-04 174321" src="https://github.com/user-attachments/assets/88b04ca6-bd8f-428f-88a2-6612d6b6fb6a" />

<img width="895" height="432" alt="Capture d’écran 2026-11-04 174353" src="https://github.com/user-attachments/assets/d10e37a7-e8f3-4009-b61f-25cbf6aa4dcc" />

<img width="1920" height="992" alt="image" src="https://github.com/user-attachments/assets/8d9bd3fd-8d99-4c1b-a492-effec6e905be" />

<img width="1920" height="1020" alt="image" src="https://github.com/user-attachments/assets/f3d0974c-81f5-448c-965b-d3b7dbddf573" />

<img width="1917" height="1017" alt="image" src="https://github.com/user-attachments/assets/e8a2fabf-9bff-43f4-8d19-13ec5b157764" />

