#About
This project is intended as a playground for memory consumption 
investigation of java-based apps.

#How-to run the project
1) `gradlew shadowJar` - build executable jar
2) `docker build -t spark-build . && docker run -p 4567:4567 -p 9999:9999 -p 9998:9998 --name spark-build -m 70m spark-build` - start docker container

#Useful commands:
* `docker exec -it <container name> sh` - run sh session inside a container
* `jcmd 1 VM.native_memory summary` - get native memory stats 
* `java -XX:+PrintFlagsFinal -version |grep ThreadStackSize` - print stack size

#Articles
* java inside Docker. Pitfalls:
https://developers.redhat.com/blog/2017/03/14/java-inside-docker/
* 8u131 cgroups support:
https://bugs.openjdk.java.net/browse/JDK-8170888
* Metaspace in Java 8:
  https://dzone.com/articles/java-8-permgen-metaspace
* jstat documentation:
https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jstat.html
* Investigation on memory consumption:
http://trustmeiamadeveloper.com/2016/03/18/where-is-my-memory-java/

#Tools:
* visualvm: 
https://visualvm.github.io/download.html