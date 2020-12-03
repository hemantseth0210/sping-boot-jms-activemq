# sping-boot-jms-activemq
Spring boot project with JMS using the ActiveMQ


##Configure ActiveMQ Artemis Server 

    <dependency>
			<groupId>org.apache.activemq</groupId>
			<artifactId>artemis-server</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.activemq</groupId>
			<artifactId>artemis-jms-server</artifactId>
		</dependency>


ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
				.setPersistenceEnabled(false)
				.setJournalDirectory("target/data/journal")
				.setSecurityEnabled(false)
				.addAcceptorConfiguration("invm", "vm://0"));
		server.start(); 


##Configure ActiveMQ Artemis Server using the Docker Image

docker run -it --rm -p 8161:8161 -p 61616:61616 vromero/activemq-artemis

Refer: https://github.com/vromero/activemq-artemis-docker 
