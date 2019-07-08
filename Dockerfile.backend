FROM open-liberty as server-setup

USER root
RUN apt-get update -y \
    && apt-get install -y --no-install-recommends unzip
COPY /target/demo.zip /config/
RUN cd /opt/ol \
    && unzip -o /config/demo.zip \
    && chown -R 1001:0 /opt/ol/wlp \
    && rm -f /config/demo.zip

FROM open-liberty
LABEL maintainer="Albert Haliulov <albert.haliulov@gmail.com>" github="https://github.com/albert-haliulov/devops-demo-app"
COPY --chown=1001:0 --from=server-setup /config/ /config/
# This script will add the requested XML snippets and grow image to be fit-for-purpose
RUN configure.sh
EXPOSE 9080 9443
