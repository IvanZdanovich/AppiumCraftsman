# Appium Testing Framework

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Setup](#setup)
- [Usage](#usage)
   - [Running Tests](#running-tests)
   - [Writing Tests](#writing-tests)
- [Contributing](#contributing)
- [License](#license)

### Introduction
This repository provides a comprehensive testing framework for mobile applications using Appium, Kotlin, Gradle, and TestNG. The framework is designed for both Android and iOS platforms, supporting parallel execution, and includes utilities for element interaction and assertion management.

### Features
- Appium Integration: Automated testing for Android and iOS applications.
- Kotlin Support: Uses Kotlin for writing tests and managing configurations.
- Gradle Build System: Multi-project setup with Gradle for dependency management and build automation.
- TestNG for Testing: Supports parallel test execution and suite management using TestNG.
- Page Object Model (POM): Structured approach to manage UI elements and interactions.

### Setup
- Clone the Repository
- Install Dependencies
- Ensure you have all necessary dependencies installed: `./gradlew clean build`
- Install and run android emulator, ios simulator. Device setup is not in the scope of the project. 
- Install Appium: `npm install -g appium`
- Run Appium: `appium`
- Modify the .json files located in the resource directory for your specific devices and environments.
Example android_device1.json:
```
{
  "hubUrl": "http://localhost:4723/wd/hub",
  "capabilities": {
    "platformName": "Android",
    "automationName": "UiAutomator2",
    "app": "/path/to/your/app.apk",
    "deviceName": "emulator-5554",
    "platformVersion": "11.0",
    "udid": "emulator-5554"
  }
}
```

### Usage
#### Running Tests
To run the tests, you can specify the suite or individual test parameters as needed.

`./gradlew runAppiumTests -Psuite=testng.xml`
or set of parameters:

`./gradlew runAppiumTests -PhubUrl=http://localhost:4723/wd/hub -Pplatform=android -PappPath=path/to/app.apk -PdeviceName=device1 -PplatformVersion=11.0 -Pudid=emulator-5554`

#### Writing Tests
BaseTest: Extend the BaseTest class to create new test cases.
Page Objects: Create new Page Object classes to encapsulate element interactions.
Components: Create Component classes for reusable UI parts.

### Contributing
- Fork the repository
- Create a new branch (`git checkout -b feature/branch`)
- Make your changes
- Commit your changes
- Push to the branch
- Create a Pull Request

### License
This project is licensed under the MIT License - see the [LICENSE file](LICENSE.md) for details.