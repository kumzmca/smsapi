# SMS api
This project is about Web service api to handle inbound and outbound sms ( logging and others functionality )
 ----------------------------------  ----------------------------------  ---------------------------------- 
To install this project you need some requirements which mentioned below 
 ----------------------------------  ----------------------------------  ---------------------------------- 
Download this project 
 ----------------------------------  ----------------------------------  ---------------------------------- 
extract it to your project location
 ----------------------------------  ----------------------------------  ---------------------------------- 
Open eclipse
 ----------------------------------  ----------------------------------  ---------------------------------- 
file -> import -> Existing maven projects -> browse to download path and select the project and finish
 ----------------------------------  ----------------------------------  ---------------------------------- 
project will imported in your workspace
 ----------------------------------  ----------------------------------  ---------------------------------- 
right click the project -> maven -> update project
 ----------------------------------  ----------------------------------  ---------------------------------- 
now project is in working condition.
 ----------------------------------  ----------------------------------  ---------------------------------- 

Final change whatever you want to make it
 ----------------------------------  ----------------------------------  ---------------------------------- 
export as war and put it in your web container as it as or file system.
 ----------------------------------  ----------------------------------  ---------------------------------- 
For database you need to change your db.properties under root/resources/db.properties -configurable
 ----------------------------------  ----------------------------------  ---------------------------------- 
To change redis environment, go to Constants.java and change  - not configurable coz it relays on same server
usually ppl will put redis server on developement rather than some other server. so we don't want to make it
configurable


Required Technologies used in this project  
 ---------------------------------- 
 Tech		  	    Purpose  
 Java 		      (J2se, j2ee) 
 Web Service		(Jersey-Restfull web serice) 
 Json		      	(Jackson) 
 Redis 			    (Cache-No SQL) 
 PostgreSQL 		(Database) Maven			
 (Project Deployment)   
 ------------------------------------------------------------ 
 1			To install redis do following
 ------------------------------------------------------------
 curl -O http://download.redis.io/redis-stable.tar.gz tar -xvzf redis-stable.tar.gz
 rm redis-stable.tar.gz  cd redis-stable  make  sudo make install 
 ------------------------------------------------------------
 2			To install java  
 ------------------------------------------------------------ 
 Download and install from http://www.oracle.com/technetwork/java/javase/downloads/index.html Developed version 8 
 ------------------------------------------------------------ 
 3			To Install Eclipse
 ------------------------------------------------------------ 
 Download and install from https://www.eclipse.org/downloads/ Developed version j2ee (neon) 
 ------------------------------------------------------------ 
 4			To Install Postgresql 
 ------------------------------------------------------------ 	
 *	Install Postgresql for database communication 	
 *	Download and install from https://www.postgresql.org/download/ Developed Version 9.6
 ------------------------------------------------------------ 
 5			To Install Postgresql
 ------------------------------------------------------------ 
 https://tomcat.apache.org/download-80.cgi Developed version 8.5 
 ------------------------------------------------------------ 
 6			To Install maven
 ------------------------------------------------------------ 
 import a project as maven project in eclipse find a pom.xml file in this project which resides root  directory of this project ------------------------------------------------------------  BUILD	-	This project build on maven tool  SERVER	-	Developed and  on tomcat server	and deployed in wildfly (cloud) server.
