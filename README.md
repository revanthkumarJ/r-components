# r-components

**r-components** is an Android library that provides **customized Jetpack Compose UI components**. It allows you to integrate ready-made UI elements into your projects **without building them from scratch**, saving development time and ensuring consistency.

---

## Features
- Ready-to-use Jetpack Compose components  
- Customizable and reusable UI elements  
- Easy integration into any Android project  

---

## Installation

### 1️⃣ Add JitPack repository

In your **root `settings.gradle.kts`** (or `settings.gradle`):

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2️⃣ Add the library dependency

In your app module build.gradle.kts:

```kotlin
dependencies {
    implementation("com.github.revanthkumarJ:r-components:1.0.0") // Replace with the latest version
}
```

For a full working example, check the sample project:  
[Sample Project](https://github.com/revanthkumarJ/sample)
