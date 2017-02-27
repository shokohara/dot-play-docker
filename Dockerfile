FROM hseeberger/scala-sbt:latest
RUN apt-get update && apt-get install -y graphviz && rm -rf /var/lib/apt/lists/*
COPY . /app/
RUN cd /app && sbt compile
CMD ["/bin/bash", "-c", "cd /app && sbt run"]
