javac serveur/*.java
rmic serveur.HelloImpl
java -Djava.rmi.server.codebase=file:C:/Users/toto/RMI/src/serveur/ -Djava.rmi.server.hostname=192.168.1.38 -Djava.security.policy=server.policy serveur.Serveur