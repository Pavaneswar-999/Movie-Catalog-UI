# Movie Catalog UI

A premium Material 3 Android application built with Kotlin that demonstrates advanced UI synchronization and multi-view layout architectures.

![Premium Dark Theme Dashboard](screenshots/card_view.png)

## Overview
This project showcases a dynamic movie catalog with four distinct visualization modes. The application focuses on a seamless user experience through a unified dark theme and perfect state synchronization across multiple UI controllers.

### Key Features
*   **Multi-View Architecture**: Seamlessly toggle between Table, Grid, Card, and Recycler views using Fragments.
*   **Triple-Controller Synchronization**: Perfect real-time synchronization between RadioButtons, CheckBoxes, and a Spinner. Selecting an option in one automatically updates the others.
*   **Premium Dark Theme**: A high-contrast Material 3 dark palette designed for modern readability.
*   **Optimized Resource Management**: Cleaned and audited codebase with zero unused XML or Kotlin files.

## Visualization Modes
| Mode | Description |
| :--- | :--- |
| **Table View** | Alternating row colors for high-density data readability. |
| **Grid View** | A 2-column responsive grid with 12dp rounded poster corners. |
| **Card List** | A vertical scrollable list featuring rich movie metadata cards. |
| **Recycler View** | A smooth horizontal scroll implementation for poster exploration. |

## UI Synchronization Logic
The application implements a robust state management system using an `isUpdating` flag to prevent recursive listener triggers. This ensures that the UI state remains consistent across all input types (RadioGroup, CheckBoxes, and Spinner) at all times.

## Code Audit & Performance
The project underwent a comprehensive manual and automated audit to ensure:
*   Zero redundant layout XML files.
*   Zero unused Kotlin classes or imports.
*   Density-independent scaling (dp) for all UI elements.
*   String extraction to `strings.xml` for internationalization readiness.

## Screenshots
<p align="center">
  <img src="screenshots/table_view.png" width="30%" />
  <img src="screenshots/grid_view.png" width="30%" />
  <img src="screenshots/recycler_view.png" width="30%" />
</p>

## Technical Stack
*   **Language**: Kotlin
*   **UI Framework**: Android XML / Material 3
*   **Architecture**: Fragment-based single activity
*   **Tools**: Android Studio, ADB, Android Lint
