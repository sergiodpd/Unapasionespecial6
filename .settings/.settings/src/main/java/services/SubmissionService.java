
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubmissionRepository;
import domain.Author;
import domain.Report;
import domain.Submission;

@Service
@Transactional
public class SubmissionService {

	//Managed repository
	@Autowired
	private SubmissionRepository	submissionRepository;

	//Supporting services

	@Autowired
	private AuthorService			authorService;
	@Autowired
	private ConfigurationService	configurationService;
	@Autowired
	private ReportService			reportService;
	@Autowired
	private PaperService			paperService;

	@Autowired
	private MessageService			messageService;


	// SIMPLE CRUD METHODS

	public Submission create() {
		this.authorService.checkPrincipal();

		Submission sub;
		sub = new Submission();
		final Date actualMoment = new java.util.Date();
		sub.setMoment(actualMoment);
		sub.setStatus("UNDER-REVIEW");
		final char a = this.authorService.findByPrincipal().getName().charAt(0);
		final char b = this.authorService.findByPrincipal().getSurname().charAt(0);
		final String x = "XX";
		char c = x.charAt(0);
		if (this.authorService.findByPrincipal().getMiddleName().length() > 1)
			c = this.authorService.findByPrincipal().getMiddleName().charAt(0);
		else
			c = x.charAt(0);

		sub.setTicker(a + "" + b + "" + c + "-" + this.configurationService.createTicker());
		return sub;
	}

	public Collection<Submission> findAll() {

		Collection<Submission> fms;
		fms = this.submissionRepository.findAll();

		return fms;

	}
	public Submission findOne(final int submissionId) {

		Assert.notNull(submissionId);
		Submission fm;
		fm = this.submissionRepository.findOne(submissionId);

		return fm;
	}
	public Submission save(final Submission a) {

		Assert.notNull(a);
		Submission res;
		final Author b = this.authorService.findByPrincipal();
		final Collection<Submission> f = b.getSubmissions();
		res = this.submissionRepository.save(a);
		if (!f.contains(res))
			f.add(res);

		return res;
	}

	public void delete(final Submission a) {
		Assert.notNull(a);
		Assert.isTrue(a.getId() != 0);

		final Author adm = this.authorService.findByPrincipal();
		adm.getSubmissions().remove(a);
		final Collection<Report> reports = this.reportService.findAll();
		for (final Report r : reports)
			if (r.getSubmission().equals(a))
				this.reportService.delete(r);

		this.paperService.delete(a.getPaper());

		this.submissionRepository.delete(a);

	}
	//	public Collection<Submission> submissionByExperience() {
	//
	//		final Collection<Submission> subs = new ArrayList<>();
	//		final Collection<String> experience = this.reviewerService.findByPrincipal().getExperience();
	//
	//		for (final Submission s : this.findAll())
	//			if (experience.contains(s.getConference().getTitle()))
	//				subs.add(s);
	//		return subs;
	//
	//	}

	public void submissionStatus(final int submissionId) {
		final Submission res;

		final Collection<Report> reports = new ArrayList<>();
		final Collection<Report> reportsAc = new ArrayList<>();
		final Collection<Report> reportsRe = new ArrayList<>();

		Collection<Report> reportsAll;

		reportsAll = this.reportService.findAll();
		for (final Report r : reportsAll)
			if (r.getSubmission().equals(this.findOne(submissionId)))
				reports.add(r);

		for (final Report rep : reports)
			if (rep.getDecision().equals("ACCEPT"))
				reportsAc.add(rep);
			else if (rep.getDecision().equals("REJECT"))
				reportsRe.add(rep);

		if (reportsRe.size() > reportsAc.size()) {
			this.findOne(submissionId).setStatus("REJECTED");
			res = this.submissionRepository.save(this.findOne(submissionId));
		} else {
			this.findOne(submissionId).setStatus("ACCEPTED");
			res = this.submissionRepository.save(this.findOne(submissionId));

		}

		//Notificación Decisión
		this.messageService.notificationDecision(res);

		System.out.println("reportsAll" + reportsAll.size());
		System.out.println("reports" + reports.size());

		System.out.println("acepted" + reportsAc.size());
		System.out.println("rejected" + reportsRe.size());

	}

}
