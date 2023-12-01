package kr.pe.eta.web.callres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.pe.eta.domain.Location;

@Controller
public class LocationWebSocketController {

	private static final Logger logger = LoggerFactory.getLogger(LocationWebSocketController.class);

	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/sendLocation/{passengerNo}")
	public void receiveLocation(@DestinationVariable String passengerNo, Location location) {
		// 로그에 위치 데이터 출력
		logger.info("Received location: Lat = " + location.getLat() + ", Lng = " + location.getLng());
		// 'user01'에게 위치 데이터 전송
		template.convertAndSend("/topic/location/" + passengerNo, location);
	}

	@MessageMapping("/sendNotification")
	@SendTo("/topic/notifications")
	public String sendNotification(String message) {
		return message;
	}

	@GetMapping("/drivingP")
	public String drivingP() {
		return "drivingP"; // JSP 파일 이름
	}
}
