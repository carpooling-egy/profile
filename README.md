# 👤 Profile Service

The Profile Service is responsible for managing user profile data in a dedicated profile database. It provides RESTful endpoints to create, retrieve, update, delete, and enrich user profiles. It also exposes minimal read views used by other services to support profile enrichment and user-related queries.

---

## 📌 Responsibilities

- 🔍 Retrieve all user profiles
- 🔍 Retrieve a profile by **email**
- 🔍 Retrieve a profile by **user ID**
- ➕ Create a new user profile
- ✏️ Update a user profile by **email**
- ✏️ Update a user profile by **user ID**
- ❌ Delete a user profile by **email**
- ❌ Delete a user profile by **user ID**
- 🚻 Retrieve a user's **gender**
- 🧠 Enrich a user profile with **basic information**

---

## 🔁 Interactions

The Profile Service is used by other microservices, such as the **Trip Management Service**, for enriching trip or user-related data. All communication happens through **RESTful APIs**.

---

## 📣 Maintainers

This service is part of the **3alsekka Carpooling System** (Graduation Project - Alexandria University, 2025).

