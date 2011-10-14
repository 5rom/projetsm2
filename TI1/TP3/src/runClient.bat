javac client/*.java
rmic client.HelloImpl
java -Djava.rmi.server.codebase=file:/C:/Users/toto/RMI/src/client/ -Djava.security.policy=client.policy client.Client 192.168.1.38
@pause