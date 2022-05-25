FROM centos
MAINTAINER Wujiale
WORKDIR /java_docker/jdk
ADD jdk-17_linux-x64_bin.tar.gz /java_docker/jdk

ENV JAVA_HOME /java_docker/jdk/jdk-17.0.2
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH=$JAVA_HOME/bin:$PATH

#CMD ["java","-version"]

