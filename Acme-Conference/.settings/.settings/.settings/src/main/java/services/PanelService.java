
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PanelRepository;
import domain.Conference;
import domain.Panel;

@Service
@Transactional
public class PanelService {

	@Autowired
	private PanelRepository			panelRepository;

	@Autowired
	private AdministratorService	adminService;

	@Autowired
	private ConferenceService		confService;


	public Panel create(final int conferenceId) {
		Assert.isTrue(this.adminService.checkPrincipal());
		Panel res;
		Conference conf;

		conf = this.confService.findOne(conferenceId);
		res = new Panel();
		res.setConference(conf);

		return res;
	}

	public Panel findOne(final int panelId) {
		Assert.isTrue(this.adminService.checkPrincipal());
		Panel panel;
		panel = this.panelRepository.findOne(panelId);
		return panel;
	}

	public Collection<Panel> findAll() {
		Assert.isTrue(this.adminService.checkPrincipal());
		return this.panelRepository.findAll();
	}

	@SuppressWarnings("deprecation")
	public Panel save(final Panel p) {
		Assert.isTrue(this.adminService.checkPrincipal());
		Panel res;

		Assert.isTrue(p.getStartMoment().getDate() == p.getEndMoment().getDate());
		Assert.isTrue(p.getStartMoment().getMonth() == p.getEndMoment().getMonth());
		Assert.isTrue(p.getStartMoment().getYear() == p.getEndMoment().getYear());

		Assert.isTrue((p.getStartMoment().getHours() > p.getEndMoment().getHours()) || (p.getStartMoment().getMinutes() > p.getEndMoment().getMinutes()));
		res = this.panelRepository.save(p);

		return res;

	}

	public void delete(final Panel p) {
		Assert.isTrue(this.adminService.checkPrincipal());
		Assert.notNull(p);
		this.panelRepository.delete(p);

	}

}
