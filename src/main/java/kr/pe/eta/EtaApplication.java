package kr.pe.eta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@EnableScheduling
public class EtaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtaApplication.class, args);
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

}
