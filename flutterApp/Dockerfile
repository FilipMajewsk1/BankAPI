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
COPY . ../../flutter_folder/bank_web_app
WORKDIR /app/
RUN flutter build web --dart-define=API_PROTOCOL=https --dart-define=API_HOST=localhost --dart-define=API_PATH=api --dart-define=API_PORT=443

FROM nginx:1.21.1-alpine
COPY --from=build /app/build/web /usr/share/nginx/html