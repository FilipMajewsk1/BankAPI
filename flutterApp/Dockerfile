# Install Operating system and dependencies
FROM ubuntu:20.04 AS build

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update
RUN apt-get install -y curl git wget unzip libgconf-2-4 gdb libstdc++6 libglu1-mesa fonts-droid-fallback python3
RUN apt-get clean

ENV DEBIAN_FRONTEND=dialog

RUN git clone https://github.com/flutter/flutter.git /usr/local/flutter

ENV PATH="/usr/local/flutter/bin:/usr/local/flutter/bin/cache/dart-sdk/bin:${PATH}"

RUN flutter doctor

RUN flutter channel master
RUN flutter upgrade
RUN flutter config --enable-web

RUN mkdir /app/
COPY ./flutterApp/bank_web_app /app/
WORKDIR /app/
ARG API_PROTOCOL
ARG API_HOST
ARG API_PATH
ARG API_PORT
RUN flutter build web --dart-define=API_PROTOCOL=${API_PROTOCOL} --dart-define=API_HOST=${API_HOST} --dart-define=API_PATH=${API_PATH} --dart-define=API_PORT=${API_PORT}

FROM nginx:1.21.1-alpine
COPY --from=build /app/build/web /usr/share/nginx/html