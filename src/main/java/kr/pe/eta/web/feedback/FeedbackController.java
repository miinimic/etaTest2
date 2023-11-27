package kr.pe.eta.web.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pe.eta.service.feedback.FeedbackService;

@Controller
@RequestMapping("/feedback/*")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	public FeedbackController() {
		System.out.println(this.getClass());
	}

}
