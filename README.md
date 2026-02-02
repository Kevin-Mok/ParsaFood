# ParsaFood

An Android app that reads food ingredient labels using OCR and alerts users to allergens and dietary conflicts.

![Android](https://img.shields.io/badge/Android-Java-green)
![OCR](https://img.shields.io/badge/Google-Mobile%20Vision-blue)
![Hackathon](https://img.shields.io/badge/Platterz-Hackathon-orange)

![parsafood-logo](https://i.imgur.com/XXJC9XJ.png)
![main-menu](https://i.imgur.com/YQS1ZTOm.png)

## Overview

A mobile application developed at the Platterz Hackathon that uses Google's Mobile Vision API to scan ingredient labels and identify potential allergens or dietary conflicts. Designed to help users with food allergies and dietary restrictions make informed purchasing decisions.

## Features

- **OCR Scanning** - Camera-based ingredient label reading
- **Allergy Detection** - Identifies common allergens
- **Dietary Filtering** - Vegetarian and vegan support
- **User Preferences** - Customizable restriction settings

### Supported Allergies
- Egg, Gluten, Milk, Nut, Seafood, Soy, Wheat

### Supported Diets
- Vegetarian, Vegan

## Tech Stack

| Component | Technology |
|-----------|------------|
| **Platform** | Android |
| **Language** | Java |
| **OCR** | Google Mobile Vision API |
| **Build** | Gradle |
| **Storage** | SharedPreferences |

## Project Structure

```
ParsaFood/
└── ocr-reader/
    ├── app/src/main/java/
    │   ├── MainActivity.java         # Main activity
    │   ├── OcrCaptureActivity.java   # Camera capture
    │   ├── AfterCaptureActivity.java # Post-scan processing
    │   └── TextParser.java           # Ingredient parsing
    ├── app/src/main/res/             # Layouts, strings
    └── build.gradle                  # Build configuration
```

## Installation

### Prerequisites
- Android Studio
- Android SDK
- Physical Android device (camera required)

### Build

```bash
# Clone repository
git clone git@github.com:Kevin-Mok/ParsaFood.git
cd ParsaFood/ocr-reader

# Open in Android Studio
# Build and run on device
```

## Why This Project is Interesting

### Technical Highlights

1. **Computer Vision**
   - Google Mobile Vision API integration
   - Real-time OCR processing
   - Camera stream handling

2. **Text Processing**
   - Ingredient label parsing
   - Allergen keyword matching
   - Multi-word ingredient handling

3. **Hackathon Development**
   - Rapid prototyping
   - Team collaboration
   - MVP delivery under time pressure

4. **User-Centric Design**
   - Solves real-world problem
   - Accessible interface
   - Customizable preferences

### Skills Demonstrated

- **Android Development**: Activities, Intents, Layouts
- **Computer Vision**: OCR, Mobile Vision API
- **Java**: Text processing, data structures
- **Team Collaboration**: Hackathon teamwork

## Team

- [David Kwon](https://github.com/xorye)
- [Kavan Lam](https://github.com/lamkavan)
- [Kevin Mok](https://github.com/Kevin-Mok)

## Credits

- [Google Mobile Vision Text Recognition API](https://developers.google.com/vision/android/text-overview)
- [Android Vision API Demo](https://github.com/googlesamples/android-vision/tree/master/visionSamples/ocr-reader)

## Author

[Kevin Mok](https://github.com/Kevin-Mok)
