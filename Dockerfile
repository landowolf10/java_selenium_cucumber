# Use a base image with Java
FROM kshivaprasad/java:1.8

# Install necessary Linux packages
RUN apt-get update && apt-get install -y curl p7zip p7zip-full unace zip unzip bzip2

# Install Chrome
RUN curl -sS -o google-chrome.deb https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
    && dpkg -i google-chrome.deb || apt-get install -f -y \
    && rm google-chrome.deb

# Install chromedriver for Selenium
RUN CHROMEDRIVER_VERSION=120.0.6099.109 \
    && curl -o /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/$CHROMEDRIVER_VERSION/chromedriver_linux64.zip \
    && unzip /tmp/chromedriver.zip -d /usr/local/bin/ \
    && rm /tmp/chromedriver.zip \
    && chmod +x /usr/local/bin/chromedriver



# Install Firefox
RUN wget --no-verbose -O /tmp/firefox.tar.bz2 https://download-installer.cdn.mozilla.net/pub/firefox/releases/78.0.2/linux-x86_64/en-US/firefox-78.0.2.tar.bz2 \
    && tar -xvjf /tmp/firefox.tar.bz2 -C /opt/ \
    && ln -s /opt/firefox/firefox /usr/bin/firefox

# Install Geckodriver
RUN wget --no-verbose -O /tmp/geckodriver.tar.gz https://github.com/mozilla/geckodriver/releases/download/v0.26.0/geckodriver-v0.26.0-linux64.tar.gz \
    && tar -xvzf /tmp/geckodriver.tar.gz -C /usr/local/bin/ \
    && rm /tmp/geckodriver.tar.gz \
    && chmod +x /usr/local/bin/geckodriver

# Install Gradle
RUN wget -q -O /tmp/gradle.zip https://services.gradle.org/distributions/gradle-8.0-bin.zip \
    && unzip -d /opt/gradle /tmp/gradle.zip \
    && rm /tmp/gradle.zip

ENV GRADLE_HOME /opt/gradle/gradle-8.0
ENV PATH $PATH:$GRADLE_HOME/bin

# Set up your working directory
WORKDIR /usr/src/app

# Copy your project files
COPY . .

# Run the tests using Gradle
CMD ["./gradlew", "clean", "test", "--tests", "LoginRunner"]
