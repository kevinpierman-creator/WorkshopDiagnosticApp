# Copilot Instructions for WorkshopDiagnosticApp

## Repository Overview

WorkshopDiagnosticApp is a minimal Android application written in Java. It serves as a diagnostic tool for workshop environments and is intended to be extended with car diagnostic features. The app displays basic information on launch and uses standard Android UI components.

## Tech Stack

- **Language**: Java (Android)
- **Build system**: Gradle 8.4 (via Gradle Wrapper), Android Gradle Plugin 8.3.2
- **JDK**: 21 (required)
- **Android SDK**: compileSdk 34, minSdk 26, targetSdk 34
- **Dependency**: `androidx.appcompat:appcompat:1.6.1`
- **Package name**: `com.workshop.diagnostic`

## Project Structure

```
WorkshopDiagnosticApp/
├── .github/
│   └── workflows/
│       ├── android-build.yml   # Builds debug and release APKs
│       ├── codacy.yml          # Codacy security scanning
│       └── codeql.yml          # CodeQL static analysis (Java + Actions)
├── app/
│   ├── build.gradle            # App-level build config (plugins, android, dependencies)
│   └── src/main/
│       ├── AndroidManifest.xml # App manifest (permissions, activities)
│       ├── java/com/workshop/diagnostic/
│       │   └── MainActivity.java  # Single activity; sets the content view
│       └── res/
│           ├── layout/activity_main.xml  # LinearLayout with two TextViews
│           └── values/strings.xml        # String resources (app_name, title_text, info_text)
├── build.gradle                # Root build file (declares AGP plugin, shared repositories)
├── settings.gradle             # Root project name and module includes
├── gradle/wrapper/
│   └── gradle-wrapper.properties  # Points to Gradle 8.4
├── gradlew / gradlew.bat       # Gradle wrapper scripts
└── keystore.jks                # Release signing keystore (committed for demo purposes)
```

## Building the App

### Prerequisites
- JDK 21 installed and `JAVA_HOME` set
- Android SDK installed (API 34) with Build Tools 34.0.3

### Common Gradle Commands

```bash
# Grant execute permission (Linux/macOS, first time only)
chmod +x ./gradlew

# Build debug APK
./gradlew clean assembleDebug

# Build release APK (uses keystore.jks)
./gradlew assembleRelease

# Run lint
./gradlew lint
```

Output APKs are placed in:
- Debug: `app/build/outputs/apk/debug/`
- Release: `app/build/outputs/apk/release/`

### Release Signing

The release build uses a keystore committed in the repo root (`keystore.jks`):
- Key alias: `workshop`
- Key/store password: `workshop123`

This is for demo/workshop purposes only. Do **not** use this keystore for production.

## CI/CD Workflows

All three workflows trigger on `push` and `pull_request` to `main`:

| Workflow | File | Purpose |
|---|---|---|
| Android APK Build | `android-build.yml` | Builds debug and release APKs and uploads them as artifacts |
| CodeQL Advanced | `codeql.yml` | Static analysis for Java/Kotlin and GitHub Actions code |
| Codacy Security Scan | `codacy.yml` | Security scanning (requires `CODACY_PROJECT_TOKEN` secret) |

### CI Environment

- Runs on `ubuntu-latest`
- Uses JDK 21 via `actions/setup-java@v3` (Temurin distribution)
- Android SDK is set up via `android-actions/setup-android@v2`

## Known Issues and Workarounds

### `settings.gradle` syntax
The `settings.gradle` file uses `rootProject.name ":WorkshopDiagnosticApp"` (missing `=`), which is non-standard Groovy DSL. The correct form is `rootProject.name = "WorkshopDiagnosticApp"`. Additionally, the project name should not start with `:`. This may cause warnings during the build; the workaround is to tolerate the warning or fix the assignment.

### `build.gradle` root plugin declaration
The root `build.gradle` declares the Android Gradle Plugin with:
```groovy
id 'com.android.tools.build:gradle:8.3.2' apply false
```
This uses the Maven artifact coordinate as the plugin ID, which is incorrect. The canonical plugin ID is `com.android.application`. This can cause resolution warnings. The convention-compliant form is:
```groovy
id 'com.android.application' version '8.3.2' apply false
```

### Codacy CI requires a secret
The Codacy workflow uses `${{ secrets.CODACY_PROJECT_TOKEN }}`. If this secret is not configured in the repository, the Codacy scan step will run without authentication (which is supported for open-source projects using default tool configurations).

### CI runs may require manual approval
For first-time contributors and Copilot-created pull requests, GitHub may require a maintainer to approve the workflow run before it executes. This is a GitHub security setting and not a code issue.

## Development Conventions

- All Android source code lives under `app/src/main/java/com/workshop/diagnostic/`.
- New activities should be registered in `app/src/main/AndroidManifest.xml`.
- User-facing strings must be added to `app/src/main/res/values/strings.xml` (no hardcoded strings in layouts).
- There are currently **no unit tests or instrumented tests**. To add tests, create the standard `app/src/test/` (JVM unit tests) or `app/src/androidTest/` (instrumented tests) directory structure and update `app/build.gradle` to add the appropriate test dependencies.
- Follow standard Android Java conventions (e.g., `onCreate` lifecycle pattern, `setContentView` in activities).

## Extending the App

The app is designed to be extended with car diagnostic features. Typical extension points:
- Add new activities under `app/src/main/java/com/workshop/diagnostic/`
- Add OBD-II or Bluetooth dependencies in `app/build.gradle` under `dependencies {}`
- Add new layouts under `app/src/main/res/layout/`
- Add required permissions (e.g., `BLUETOOTH`, `BLUETOOTH_ADMIN`) in `AndroidManifest.xml`
