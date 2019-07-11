
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AuthorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Author;
import domain.Message;
import domain.Registration;
import domain.Submission;

@Service
@Transactional
public class AuthorService {

	//Managed repository
	@Autowired
	private AuthorRepository		authorRepository;

	//Supporting services

	@Autowired
	private ActorService			actorService;
	@Autowired
	private ConfigurationService	configurationService;


	// SIMPLE CRUD METHODS

	public Author create() {
		Author author;
		UserAccount userAccount;
		Authority auth;

		//Authority
		author = new Author();
		userAccount = new UserAccount();
		auth = new Authority();

		auth.setAuthority("AUTHOR");
		userAccount.addAuthority(auth);
		author.setUserAccount(userAccount);

		//Relationships

		final Collection<Message> messages = new ArrayList<>();
		final Collection<Registration> registrations = new ArrayList<>();
		final Collection<Submission> submissions = new ArrayList<>();

		author.setMessages(messages);
		author.setRegistrations(registrations);
		author.setSubmissions(submissions);

		return author;
	}

	public Collection<Author> findAll() {
		Collection<Author> directors;
		directors = this.authorRepository.findAll();
		Assert.notNull(directors);
		return directors;

	}
	public Author findOne(final int directorId) {
		Assert.notNull(directorId);
		Author d;
		d = this.authorRepository.findOne(directorId);
		return d;
	}
	public Author save(final Author d) {

		Assert.notNull(d);

		if (d.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final String hash = encoder.encodePassword(d.getUserAccount().getPassword(), null);
			d.getUserAccount().setPassword(hash);
		}
		if (d.getPhoneNumber() != null)
			if (!(d.getPhoneNumber().startsWith("+")))
				d.setPhoneNumber("+" + this.configurationService.find().getCountryCode() + " " + d.getPhoneNumber());
		Author res;
		res = this.authorRepository.save(d);
		return res;
	}
	//	public void delete(final Director d) {
	//		Assert.notNull(d);
	//		Assert.isTrue(d.getId() != 0);
	//		Assert.notNull(this.authorRepository.findOne(d.getId()));
	//
	//		//Eliminamos la creditCard asociada
	//		this.creditCardService.delete(d.getCreditCard());
	//
	//		//Eliminamos MessageBoxes
	//		for (final MessageBox mB : d.getMessageBoxes())
	//			this.messageBoxService.deleteGDPR(mB);
	//
	//		this.authorRepository.delete(d);
	//	}
	public Author findByUserAccountId(final int userAccountId) {
		Assert.isTrue(userAccountId != 0);
		final Author res = this.authorRepository.findByUserAccountId(userAccountId);
		return res;
	}

	public Author findByPrincipal() {
		final UserAccount u = LoginService.getPrincipal();
		final Author res = this.authorRepository.findByUserAccountId(u.getId());
		return res;
	}

	public void checkPrincipal() {

		final UserAccount userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		final Collection<Authority> authorities = userAccount.getAuthorities();
		Assert.notNull(authorities);

		final Authority auth = new Authority();
		auth.setAuthority(Authority.AUTHOR);

		Assert.isTrue(authorities.contains(auth));
	}

	public void flush() {
		this.authorRepository.flush();
	}

}