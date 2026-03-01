# Add project-specific ProGuard rules here.
# By default, the flags in this file are appended to the flags specified in
# the Android SDK's proguard-android-optimize.txt.

# Keep the application entry point (including all framework-called methods)
-keep public class com.workshop.diagnostic.MainActivity { *; }

# Keep AppCompat-related classes used via reflection
-keep class androidx.appcompat.** { *; }
-dontwarn androidx.appcompat.**
