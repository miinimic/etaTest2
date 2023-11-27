package kr.pe.eta.web.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.eta.service.feedback.FeedbackService;

@Controller
@RequestMapping("/feedback/json/*")
public class FeedbackRestController {

	@Autowired
	private FeedbackService feedbackService;

	public FeedbackRestController() {
		System.out.println(this.getClass());
	}

}
