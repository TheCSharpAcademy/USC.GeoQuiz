# GeoQuiz

GeoQuiz is an Android app written in Kotlin. It presents true-or-false geography questions, supports next/previous navigation, preserves quiz state, and includes a cheat screen.

## Requirements

- Android Studio
- Android SDK 36
- JDK 11 or newer (Android Studio's bundled JDK is recommended)
- An Android emulator or physical device running Android 7.0 (API 24) or newer

## Run with Android Studio

1. Open Android Studio and select **Open**.
2. Choose the `GeoQuiz2` project directory.
3. Wait for the Gradle sync to finish. Accept any prompt to install missing Android SDK components.
4. Start an emulator from **Tools > Device Manager**, or connect a physical Android device with USB debugging enabled.
5. Select the device and the `app` run configuration in the toolbar.
6. Click **Run** (the green triangle).

## Build and install from the command line

From the project directory on Windows, build the debug APK with:

```powershell
.\gradlew.bat assembleDebug
```

The APK will be created at:

```text
app\build\outputs\apk\debug\app-debug.apk
```

With an emulator running or a physical device connected, install it with:

```powershell
.\gradlew.bat installDebug
```

On macOS or Linux, replace `.\gradlew.bat` with `./gradlew`.

## Run tests

Run the JVM unit tests:

```powershell
.\gradlew.bat testDebugUnitTest
```

Run the instrumented UI tests on a connected device or running emulator:

```powershell
.\gradlew.bat connectedDebugAndroidTest
```

## Troubleshooting

- If Gradle reports missing SDK components, install them from **Tools > SDK Manager**, then select **File > Sync Project with Gradle Files**.
- If a device is not listed, confirm the emulator is fully started or run `adb devices` to check the physical-device connection.
- If the emulator becomes unresponsive, open **Device Manager**, use the device's menu, and select **Cold Boot Now**.
