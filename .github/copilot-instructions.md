# Copilot Instructions for WorkshopDiagnosticApp

## Purpose

Help maintain and extend WorkshopDiagnosticApp — a minimal Android diagnostic tool written in Java. Keep CI green, follow Android/Java conventions, and make small, focused changes.

## Allowed Changes

- Android app source code under `app/src/main/java/`
- Gradle build files (`build.gradle`, `app/build.gradle`, `settings.gradle`, `gradle/wrapper/`)
- Resources under `app/src/main/res/`
- `AndroidManifest.xml`
- CI workflow files under `.github/workflows/`
- This instructions file

## Do NOTs

- Do **not** add, expose, or modify secrets or credentials of any kind.
- Do **not** change release signing configuration or touch `keystore.jks`; never expose keystore credentials in documentation or code.
- Do **not** delete or disable any CI workflow.
- Do **not** alter repository settings, branch protection rules, or permissions.
- Do **not** publish or distribute APKs outside of CI artifacts.
- Do **not** hardcode strings in layouts — always use `res/values/strings.xml`.

## Repo Map

```
.github/
  copilot-instructions.md
  workflows/
    android-build.yml   # debug + release APK build, artifact upload
    codeql.yml          # CodeQL static analysis
    codacy.yml          # Codacy security scan
app/
  build.gradle          # app-level build config
  src/main/
    AndroidManifest.xml
    java/com/workshop/diagnostic/
      MainActivity.java
    res/
      layout/activity_main.xml
      values/strings.xml
build.gradle            # root build file (AGP plugin declaration)
settings.gradle         # root project name and module includes
gradle/wrapper/gradle-wrapper.properties  # Gradle 8.4
gradlew
keystore.jks            # demo keystore (do not modify or read passwords)
```

## Build & Test Commands

```bash
# First-time setup (Linux/macOS)
chmod +x ./gradlew

# Build debug APK
./gradlew clean assembleDebug

# Build release APK
./gradlew assembleRelease

# Run lint
./gradlew lint
```

Output locations:
- Debug: `app/build/outputs/apk/debug/`
- Release: `app/build/outputs/apk/release/`

## CI Nuances

- All workflows trigger on `push` and `pull_request` to `main`.
- Runs on `ubuntu-latest` with JDK 21 (Temurin) and Android SDK 34.
- Debug and release APKs are uploaded as workflow artifacts.
- **First-time contributors and Copilot PRs require maintainer approval** before workflows execute — this is a GitHub security setting, not a code issue.
- The Codacy workflow requires `CODACY_PROJECT_TOKEN`; without it the scan runs unauthenticated (acceptable for open-source).
- When modifying CI files, explain the reason in the PR description and confirm the build still passes.

## Coding Style

- Follow standard Android Java conventions (`onCreate` lifecycle, `setContentView`).
- Register new activities in `AndroidManifest.xml`.
- All user-facing strings go in `res/values/strings.xml` — no inline string literals in layouts.
- Keep changes small and self-contained; prefer one logical change per PR.
- Stack: Java · AGP 8.3.2 · Gradle 8.4 · `compileSdk 34` / `minSdk 26` · `androidx.appcompat:appcompat:1.6.1`
