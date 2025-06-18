# WhiteHelmetTask 🎬

A modern Android app to discover and explore movies using [TMDB API](https://www.themoviedb.org/), built with **Jetpack Compose**, **Kotlin**, and **Clean Architecture** principles.

---

## ✨ Features

- 🔍 **Search with Autocomplete**  
  Smart search with suggestion dropdown and instant filtering.

- 📄 **Movie Listing (Paginated)**  
  Infinite scrolling with Jetpack Paging 3, showing movie poster, title, release date, status, and more.

- 📱 **Movie Details Screen**  
  - Dynamic collapsing toolbar based on scroll
  - Backdrop with gradient overlay
  - Genre badges, ratings, release date, overview
  - +18 flag and origin country

- 🔄 **Loading & Error States**  
  - Shimmer UI while loading
  - Clear error messages and retry handling

- 💡 **Responsive UI**  
  Built entirely with Jetpack Compose for a smooth, modern UI experience.

---

## 🧱 Architecture

> Following **Clean Architecture** (Uncle Bob’s style) with proper separation of concerns.


presentation/
│
├── home/ → Movie list + search
├── details/ → Movie detail view
│
domain/
├── model/ → Pure Kotlin data models
├── usecase/ → Business logic (e.g., GetMoviesUseCase)
│
data/
├── remote/ → Retrofit + DTOs
├── repository/ → Implements domain layer contracts

yaml
Copy
Edit

- `MVVM` with `State` & `ViewModel` per screen
- `Hilt` for dependency injection
- `Coroutines` + `Flow`
- `Paging 3` for efficient data loading

---

## 🔧 Tech Stack

| Tech                 | Role                        |
|----------------------|-----------------------------|
| Kotlin               | Language                    |
| Jetpack Compose      | UI toolkit                  |
| Paging 3             | Infinite scroll             |
| Retrofit             | HTTP client                 |
| Hilt                 | DI                          |
| Coil                 | Image loading               |
| Coroutines & Flow    | Async & reactive handling   |
| Clean Architecture   | Scalable architecture       |

---

## 📦 API Key

This project uses TMDB API.  
To run the project, add your API key in:
```kotlin
BuildConfig.TMDB_API_KEY

