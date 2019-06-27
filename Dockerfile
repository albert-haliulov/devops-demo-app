FROM open-liberty:kernel

LABEL maintainer "Albert Haliulov <albert.haliulov@gmail.com>"

#COPY --chown=1001:0 src/main/liberty/config/ /config/

#COPY --chown=1001:0 target/demo-0.1.war /config/dropins/
USER root 

COPY /target/demo.zip /opt/ol/

RUN apt-get update -y \
&& apt-get install -y --no-install-recommends \
unzip \
&& cd /opt/ol/ \
&& unzip -o demo.zip \
&& chown -R 1001:0 /opt/ol/wlp \
&& rm -f demo.zip

USER 1001

#ENTRYPOINT ["/opt/ol/wlp/bin/server", "run"]
#CMD ["defaultServer"]

# This script will add the requested XML snippets and grow image to be fit-for-purpose
RUN configure.sh