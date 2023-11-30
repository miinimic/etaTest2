package kr.pe.eta.web.callres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import kr.pe.eta.domain.Location;

@Controller
public class LocationWebSocketController {

	private static final Logger logger = LoggerFactory.getLogger(LocationWebSocketController.class);

	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/sendLocation")
	public void receiveLocation(Location location) {
		// 로그에 위치 데이터 출력
		logger.info("Received location: Lat = " + location.getLat() + ", Lng = " + location.getLng());

		// 'user01'에게 위치 데이터 전송
		template.convertAndSend("/topic/location/user01", location);
	}
}
