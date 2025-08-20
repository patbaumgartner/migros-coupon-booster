# Swiss Coupon Booster

[![Build Status](https://github.com/patbaumgartner/migros-coupon-booster/actions/workflows/ci.yml/badge.svg)](https://github.com/patbaumgartner/migros-coupon-booster/actions/workflows/ci.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=patbaumgartner_migros-coupon-booster&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=patbaumgartner_migros-coupon-booster)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=patbaumgartner_migros-coupon-booster&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=patbaumgartner_migros-coupon-booster)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=patbaumgartner_migros-coupon-booster&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=patbaumgartner_migros-coupon-booster)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=patbaumgartner_migros-coupon-booster&metric=coverage)](https://sonarcloud.io/summary/new_code?id=patbaumgartner_migros-coupon-booster)

A Spring Boot application that helps you automatically activate all available digital coupons from Switzerland's leading retailers, Migros (Cumulus) and Coop (SuperCard).

## About The Project

This project leverages web automation technology (Microsoft Playwright) to log into your Migros and Coop accounts, discover all available digital coupons, and activate them on your behalf. It saves you the time and hassle of manually checking for and activating each coupon, ensuring you never miss out on potential savings.

The application is built with a strong focus on code quality and security, incorporating a comprehensive suite of tools for static analysis, vulnerability scanning, and testing.

### Key Features

- 🎫 Automated coupon discovery and activation for Migros and Coop.
- 🤖 Browser automation powered by Microsoft Playwright for robust interaction with modern web applications.
- 📊 Detailed reporting on activated coupons.
- 🔒 Integrated security vulnerability scanning with OWASP Dependency-Check.
- 📈 High code coverage and mutation testing (PIT) to ensure reliability.
- 🎯 Static code analysis with SpotBugs for identifying potential bugs early.

## Getting Started

Follow these steps to get a local copy up and running.

### Prerequisites

- **Java 21** or higher
- **Maven 3.6+**

### Installation & Running

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/patbaumgartner/migros-coupon-booster.git
    cd migros-coupon-booster
    ```

2.  **Configure your credentials:**
    Open the `src/main/resources/application.yml` file and provide your account credentials for Migros and Coop. See the [Configuration](#configuration) section for more details.

3.  **Build and run the application:**
    You can run the application directly using the Spring Boot Maven plugin:
    ```sh
    mvn spring-boot:run
    ```
    Alternatively, you can build the project and run the JAR file:
    ```sh
    mvn clean install
    java -jar target/coupon-booster-*.jar
    ```

## Configuration

The application requires your personal account credentials to be configured in `src/main/resources/application.yml`.

```yaml
migros:
  user:
    email: 'your-migros-email@example.com'
    password: 'your-migros-password'
  # Optional: Define a specific browser to use (e.g., chromium, firefox, webkit)
  playwright:
    browser: 'chromium'

coop:
  user:
    email: 'your-coop-email@example.com'
    password: 'your-coop-password'
  # Required for Coop login flow due to bot detection
  playwright:
    datadomeCookieValue: 'your-datadome-cookie-value'
```

**Important:** The `datadomeCookieValue` for Coop is necessary to bypass their bot detection measures. You may need to manually acquire this value from a real browser session.

## Usage

Once the application is running, the coupon activation process is triggered automatically on startup. The application will log its progress to the console, detailing the coupons it finds and activates. After the process is complete, the application will exit.

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.
