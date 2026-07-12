# AssetFlow – Odoo Hackathon '26

**AssetFlow** is a full‑stack asset management MVP built for the Odoo Hackathon 2026 – Virtual Round.  
It provides a secure REST API (Spring Boot) with JWT authentication and a clean Vue 3 frontend to manage assets.

---

## 🚀 Features

- **User authentication** – Register / Login with JWT tokens.
- **Asset CRUD** – Create, Read, Update, Delete assets.
- **Odoo sync ready** – Stub endpoint to simulate fetching assets from Odoo.
- **Modern frontend** – Vue 3 + TypeScript + Vite, with axios interceptors for token handling.
- **In‑memory H2 database** – no external DB required for evaluation.

---

## 🧰 Tech Stack

### Backend
- Java 21
- Spring Boot 3.4.0
- Spring Security (JWT)
- Spring Data JPA (Hibernate)
- H2 Database
- Maven

### Frontend
- Vue 3 (Composition API)
- TypeScript
- Vite
- Axios

---

## 📦 Getting Started

### Prerequisites
- Java 21 or later
- Node.js (v18+) and npm
- Maven (or use the wrapper)

### Clone the repository
```bash
git clone https://github.com/VanshikaSingh277/AssetFlow-Odoo-Hackathon-2026.git
cd AssetFlow-Odoo-Hackathon-2026
