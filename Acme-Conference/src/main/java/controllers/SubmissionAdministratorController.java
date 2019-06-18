
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.ConferenceService;
import services.SubmissionService;
import domain.Submission;

@Controller
@RequestMapping("/submission/administrator")
public class SubmissionAdministratorController extends AbstractController {

	@Autowired
	SubmissionService	submissionService;

	@Autowired
	AuthorService		authorService;
	@Autowired
	ConferenceService	conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Submission> submissions;

		submissions = this.submissionService.findAll();

		result = new ModelAndView("submission/list");
		result.addObject("submissions", submissions);
		result.addObject("requestURI", "submission/administrator/list.do");

		return result;
	}
	@RequestMapping(value = "/decisionMaking", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int submissionId) {
		ModelAndView result;
		this.submissionService.submissionStatus(submissionId);
		result = new ModelAndView("redirect:list.do");
		return result;
	}

}