
---

### 📄 3. README Frontend (Angular)
**Fichier : `cardgame-frontend/README.md`**

```markdown
# Card Game – Frontend (Angular)

Interface utilisateur pour visualiser les cartes.

## Technologies

- Angular 17
- TypeScript 5.4
- RxJS
- HttpClient
- Jasmine / Karma (tests)

## Prérequis

- Node.js 18+
- npm 9+

## Lancer l'application

```bash
# Installer les dépendances
npm install

# Lancer en développement
npm start



L'application est accessible sur : http://localhost:4200



src/
├── app/
│   ├── components/
│   │   └── card-list/           # Composant principal
│   │       ├── card-list.component.ts
│   │       ├── card-list.component.html
│   │       └── card-list.component.css
│   ├── interceptors/
│   │   └── error.interceptor.ts # Gestion d'erreur HTTP
│   ├── models/
│   │   ├── card.model.ts
│   │   └── hand-response.model.ts
│   ├── services/
│   │   └── card.service.ts      # Appel à l'API
│   ├── app.module.ts
│   └── app-routing.module.ts
├── assets/
│   └── img/                     # 52 images de cartes (.png)
├── environments/
│   ├── environment.ts           # Dev
│   └── environment.prod.ts      # Prod
├── index.html
└── main.ts