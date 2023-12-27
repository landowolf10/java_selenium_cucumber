# Use a base image with Java and necessary dependencies
FROM openjdk:latest

#Step 1 : Install the pre-requisite
RUN apt-get update
RUN apt-get install -y curl
RUN apt-get install -y p7zip \
    p7zip-full \
    unace \
    zip \
    unzip \
    bzip2

#Version numbers
ARG FIREFOX_VERSION=78.0.2
ARG CHROME_VERSION=83.0.4103.116
ARG CHROMDRIVER_VERSION=83.0.4103.39
ARG FIREFOXDRIVER_VERSION=0.26.0

#Step 2: Install Chrome
RUN curl http://dl.google.com/linux/chrome/deb/pool/main/g/google-chrome-stable/google-chrome-stable_$CHROME_VERSION-1_amd64.deb -o /chrome.deb
RUN dpkg -i /chrome.deb
RUN rm /chrome.deb

#Step 3: Install chromedriver for Selenium
RUN mkdir -p /app/bin
RUN curl https://chromedriver.storage.googleapis.com/$CHROMDRIVER_VERSION/chromedriver_linux64.zip -o /tmp/chromedriver.zip \
    && unzip /tmp/chromedriver.zip -d /app/bin/ \
    && rm /tmp/chromedriver.zip
RUN chmod +x /app/bin/chromedriver

#Step 4 : Install firefox
RUN wget --no-verbose -O /tmp/firefox.tar.bz2 https://download-installer.cdn.mozilla.net/pub/firefox/releases/$FIREFOX_VERSION/linux-x86_64/en-US/firefox-$FIREFOX_VERSION.tar.bz2 \
  && bunzip2 /tmp/firefox.tar.bz2 \
  && tar xvf /tmp/firefox.tar \
  && mv /firefox /opt/firefox-$FIREFOX_VERSION \
  && ln -s /opt/firefox-$FIREFOX_VERSION/firefox /usr/bin/firefox \

#Step 5: Install Geckodriver
RUN wget https://github.com/mozilla/geckodriver/releases/download/v$FIREFOXDRIVER_VERSION/geckodriver-v$FIREFOXDRIVER_VERSION-linux64.tar.gz \
    && tar -xf geckodriver-v0.26.0-linux64.tar.gz \
    && cp geckodriver /app/bin/geckodriver
RUN chmod +x /app/bin/geckodriver

# Install Gradle
RUN wget -q -O /tmp/gradle.zip https://services.gradle.org/distributions/gradle-8.0-bin.zip \
    && unzip -d /opt/gradle /tmp/gradle.zip \
    && rm /tmp/gradle.zip

ENV GRADLE_HOME /opt/gradle/gradle-8.0
ENV PATH $PATH:$GRADLE_HOME/bin

#Step 7: Copy our project
COPY . /

#Making our working directory as /app
WORKDIR /app

# Run the tests using Gradle
CMD ["./gradlew", "clean", "test", "--tests", "LoginRunner"]