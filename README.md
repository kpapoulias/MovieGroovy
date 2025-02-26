# 🎬 MovieGroovy

MovieGroovy is a modern Android app built using Jetpack Compose, Dagger Hilt, Room, and Retrofit. It follows the MVVM (Model-View-ViewModel) architecture to ensure a clean, scalable, and testable codebase. The app fetches movie data from the TMDB API and presents it in a clean and interactive UI.

## 🚀 Features

- 🎥 Browse popular and top-rated movies
- 🔎 Search for movies by title
- 📄 View detailed movie information
- 📶 Offline support with Room database
- 🛠 Dependency injection with Dagger Hilt
- 🎨 Modern UI with Jetpack Compose

## 🛠️ Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM
- **UI:** Jetpack Compose
- **Networking:** Retrofit
- **Dependency Injection:** Dagger Hilt
- **Local Storage:** Room Database
- **API:** [TMDB API](https://www.themoviedb.org/documentation/api)

## 📁 Project Structure (MVVM)

```
app
│── manifests
│── kotlin+java
│   ├── com.example.moviegroovy
│       ├── data
│       │   ├── local (Room database)
│       │   ├── model (Core models)
│       │   ├── remote (API service & DTOs)
│       │   ├── repository (Repository pattern)
│       ├── di (Dependency Injection modules)
│       ├── ui
│       │   ├── components (Reusable UI components)
│       │   ├── navigation (App navigation logic)
│       │   ├── screens (Movie list & details)
│       │   ├── theme (App themes & styles)
│       ├── viewModel (Business logic layer)
│       ├── MainActivity.kt (Entry point)
│       ├── MovieGroovyApp.kt (Application class)
```

## 🔧 Setup Instructions

1. **Clone the repository**:
   ```sh
   git clone https://github.com/yourusername/MovieGroovy.git
   ```

2. **Navigate to the project directory**:
   ```sh
   cd MovieGroovy
   ```

3. **Add your TMDB API key**:
    - Create a file named `keystore.properties` in the root directory.
    - Add the following line to the file, replacing `your_api_key_here` with your actual TMDB API key:
      ```properties
      TMDB_API_KEY=your_api_key_here
      ```

4. **Sync and run the project**:
    - Open the project in **Android Studio**.
    - Sync Gradle files to apply the changes.
    - Run the app on an emulator or physical device.

## 📜 License
This project is licensed under the MIT License - see the LICENSE file for details.

