
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuthorService;
import services.ConferenceService;
import services.RegistrationService;
import domain.Conference;
import domain.Registration;

@Controller
@RequestMapping("/registration/author")
public class RegistrationAuthorController extends AbstractController {

	@Autowired
	RegistrationService	registrationService;

	@Autowired
	AuthorService		authorService;
	@Autowired
	ConferenceService	conferenceService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Registration> registrations;

		registrations = this.authorService.findByPrincipal().getRegistrations();

		result = new ModelAndView("registration/list");
		result.addObject("registrations", registrations);
		result.addObject("requestURI", "registration/author/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Registration registration;

		registration = this.registrationService.create();
		result = this.createEditModelAndView(registration);

		return result;
	}

	//Edit

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int registrationId) {
		ModelAndView result;
		final Registration registration;

		registration = this.registrationService.findOne(registrationId);
		Assert.notNull(registration);

		result = this.createEditModelAndView(registration);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Registration registration) {
		ModelAndView result;

		result = this.createEditModelAndView(registration, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Registration registration, final String messageCode) {
		final ModelAndView result;

		final Collection<Conference> conferences = this.conferenceService.conferencesToRegist();

		result = new ModelAndView("registration/edit");

		result.addObject("registration", registration);
		result.addObject("conferences", conferences);

		result.addObject("message", messageCode);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Registration registration, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			System.out.print(binding);
			result = this.createEditModelAndView(registration);

		} else
			try {
				System.out.print("Entra");

				this.registrationService.save(registration);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				System.out.print(oops);

				result = this.createEditModelAndView(registration, "registration.commit.error");
			}
		return result;
	}
	//delete

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Registration registration, final BindingResult binding) {
		ModelAndView result;
		try {
			this.registrationService.delete(registration);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			System.out.println(oops);
			result = this.createEditModelAndView(registration, "registration.commit.error");
		}
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int registrationId) {
		final ModelAndView result;
		final Registration registration;

		registration = this.registrationService.findOne(registrationId);

		result = new ModelAndView("registration/show");
		result.addObject("requestURI", "registration/author/show.do");
		result.addObject("registration", registration);
		return result;
	}

}
