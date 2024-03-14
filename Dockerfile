# Use a base image with Java
FROM openjdk:17-jdk-slim-buster

# Install necessary Linux packages
RUN apt-get update && apt-get install -y curl p7zip p7zip-full unace zip unzip bzip2 wget

# Install Gradle
RUN curl -sL https://services.gradle.org/distributions/gradle-8.0-bin.zip -o gradle.zip && \
    unzip gradle.zip && \
    mv gradle-8.0 /opt/gradle && \
    rm gradle.zip && \
    ln -s /opt/gradle/bin/gradle /usr/bin/gradle

# Install Chrome
RUN curl -sS -o google-chrome.deb https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
    && apt-get install -y ./google-chrome.deb || apt-get install -f -y \
    && rm google-chrome.deb

# Install Firefox
RUN wget --no-verbose -O /tmp/firefox.tar.bz2 https://download-installer.cdn.mozilla.net/pub/firefox/releases/78.0.2/linux-x86_64/en-US/firefox-78.0.2.tar.bz2 \
    && tar -xvjf /tmp/firefox.tar.bz2 -C /opt/ \
    && ln -s /opt/firefox/firefox /usr/bin/firefox

# Set up ChromeDriver /122.0.6261.128/linux64/chromedriver-linux64.zip
ENV CHROME_DRIVER_VERSION="122.0.6261.128"
RUN apt-get install -y jq && \
    wget -q "https://storage.googleapis.com/chrome-for-testing-public/${CHROME_DRIVER_VERSION}/linux64/chromedriver-linux64.zip" -O chromedriver-linux64.zip && \
    unzip chromedriver-linux64.zip && \
    mv chromedriver-linux64 /usr/local/bin/ && \
    chmod +x /usr/local/bin/chromedriver-linux64 && \
    rm chromedriver-linux64.zip

# Install Geckodriver
RUN wget --no-verbose -O /tmp/geckodriver.tar.gz https://github.com/mozilla/geckodriver/releases/download/v0.26.0/geckodriver-v0.26.0-linux64.tar.gz \
    && tar -xvzf /tmp/geckodriver.tar.gz -C /usr/local/bin/ \
    && rm /tmp/geckodriver.tar.gz \
    && chmod +x /usr/local/bin/geckodriver

# Set up your working directory
WORKDIR /usr/src/app

# Copy your project files
COPY . .

# Set permissions
RUN chmod +x gradlew
RUN chmod +x /usr/src/app/src/test/resources/drivers/chromedriver
RUN chmod +x /usr/src/app/src/test/resources/drivers/geckodriver

# Define a default value for the test runner
ARG TEST_RUNNER="LoginRunner"

# Run the test runner with gradlew
CMD ["sh", "-c", "./gradlew clean test --tests \"$TEST_RUNNER\""]