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

- 💾 **Offline Support with Room**  
  - Caches movie list locally  
  - Full offline search and pagination  
  - Suggestions work even without internet

- 🌓 **Dark Mode Toggle**  
  - Light & dark themes  
  - Manual toggle built into the UI

- 🌐 **Localization**  
  - Auto-detects device language (supports Arabic & English)

- 🚀 **Splash Screen**  
  - Engaging Lottie animation to greet the user on launch

- 🔄 **Loading & Error States**  
  - Shimmer UI while loading  
  - Dialogs with retry button for errors  
  - Empty state when offline and no cache available

- 💡 **Responsive UI**  
  Built entirely with Jetpack Compose for a smooth, modern UI experience.

---

## 🧱 Architecture

> Following **Clean Architecture** (Uncle Bob’s style) with proper separation of concerns.

presentation/
├── home/ → Movie list + search
├── details/ → Movie detail view

domain/
├── model/ → Pure Kotlin data models
├── usecase/ → Business logic (e.g., GetMoviesUseCase)

data/
├── remote/ → Retrofit + DTOs
├── local/ → Room DAOs + entities
├── repository/ → Implements domain layer contracts

yaml
Copy
Edit

- `MVVM` with `State` & `ViewModel` per screen  
- `Hilt` for dependency injection  
- `Coroutines` + `Flow`  
- `Paging 3` for efficient data loading  
- `Room` for caching offline data  

---

## 🔧 Tech Stack

| Tech                 | Role                        |
|----------------------|-----------------------------|
| Kotlin               | Language                    |
| Jetpack Compose      | UI toolkit                  |
| Paging 3             | Infinite scroll             |
| Retrofit             | HTTP client                 |
| Hilt                 | Dependency Injection        |
| Coil                 | Image loading               |
| Room DB              | Offline support             |
| Lottie               | Splash animation            |
| Coroutines & Flow    | Async & reactive handling   |
| Clean Architecture   | Scalable architecture       |

---

## 📦 API Key

This project uses the TMDB API.  
For your convenience, **a test API key is already included** in the project so you can run it without setup.

```kotlin
// Configured in:
BuildConfig.TMDB_API_KEY

Feel free to use it to explore the app during testing.
⚠️ Note: I’m aware this is not the best practice for sharing an API key. In a real production environment, the key should be secured using proper methods (like server-side proxy or encrypted secrets).
This is just a temporary setup to help you try the app easily.
