
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.ReportService;
import services.ReviewerService;
import services.SubmissionService;
import domain.Report;
import domain.Submission;

@Controller
@RequestMapping("/report/author")
public class ReportAuthorController extends AbstractController {

	@Autowired
	ReportService		reportService;

	@Autowired
	ReviewerService		reviewerService;
	@Autowired
	SubmissionService	submissionService;

	@Autowired
	AuthorService		authorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int submissionId) {
		ModelAndView result;
		try {
			final Submission s = this.submissionService.findOne(submissionId);
			Assert.isTrue(this.authorService.findByPrincipal().getSubmissions().contains(s));

			final Collection<Report> reports = this.reportService.findReportsOfSubmission(s);

			result = new ModelAndView("report/list");
			result.addObject("reports", reports);
			result.addObject("requestURI", "report/author/list.do");
		} catch (final IllegalArgumentException e) {
			// TODO: handle exception

			result = new ModelAndView("misc/403");
		}
		return result;
	}
}
