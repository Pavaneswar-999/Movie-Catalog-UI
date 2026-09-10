# Movie Catalog UI

Premium Material 3 Android application built with Kotlin. Demonstrates advanced UI synchronization and multi-view layout architectures.

## Key Features
*   **Multi-View Architecture**: Toggle between Table, Grid, Card, and Recycler views using Fragments.
*   **Triple-Controller Synchronization**: Real-time sync between RadioButtons, CheckBoxes, and a Spinner. Selecting an option in one updates all others automatically.
*   **Premium Dark Theme**: High-contrast Material 3 dark palette.
*   **Audited Codebase**: Zero unused XML or Kotlin files. Optimized resource management.

## UI Sync Logic
Robust state management using an `isUpdating` flag to prevent recursive listener triggers. Ensures consistent UI state across all inputs (RadioGroup, CheckBoxes, Spinner).

## Technical Audit
*   Zero redundant layout XML files.
*   Zero unused Kotlin classes or imports.
*   100% density-independent scaling (dp).
*   All strings extracted to `strings.xml`.

## Screenshots
<p align="center">
  <img src="screenshots/table_view.png" width="45%" alt="Table View" />
  <img src="screenshots/grid_view.png" width="45%" alt="Grid View" />
</p>
<p align="center">
  <img src="screenshots/card_view.png" width="45%" alt="Card List View" />
  <img src="screenshots/recycler_view.png" width="45%" alt="Recycler View" />
</p>

## Technical Stack
*   **Language**: Kotlin
*   **UI Framework**: Android XML / Material 3
*   **Architecture**: Fragment-based single activity
*   **Tools**: Android Studio, ADB, Android Lint
