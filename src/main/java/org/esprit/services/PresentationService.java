package org.esprit.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.esprit.dao.PresentationRepository;
import org.esprit.entities.Presentation;
import org.esprit.entities.Slide;
import org.esprit.entities.User;
import org.esprit.metier.PresentationMetier;
import org.esprit.metier.SlideMetier;
import org.esprit.metier.UserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class PresentationService {

	@Autowired
	private PresentationMetier presentationMetier;
	@Autowired
	private UserMetier userMetier;
	@Autowired
	private SlideMetier slideMetier;
	@Autowired
	private PresentationRepository presentationRepository;

	@RequestMapping(value = "/presentation/{presentationCode}", method = RequestMethod.GET)
	public Optional<Presentation> getPresentationById(@PathVariable Integer presentationCode) {
		return presentationMetier.getPresentationById(presentationCode);
	}

	@RequestMapping(value = "/presentations/{email}", method = RequestMethod.GET)
	public Collection<Presentation> getAllPresentations(@PathVariable String email) {
		return presentationMetier.getAllPresentation(email);
	}

	@RequestMapping(value = "/presentation/{presentationCode}", method = RequestMethod.DELETE)
	public Boolean deletePresentation(@PathVariable Integer presentationCode) {
		return presentationMetier.deletePresentation(presentationCode);
	}

	@RequestMapping(value = "/presentation/{codeUser}", method = RequestMethod.POST)
	public Boolean addPresentation(@RequestBody Presentation prsentaation, @PathVariable Integer codeUser) {
		presentationMetier.addPresentationByIdUser(codeUser, prsentaation);

		return true;
	}

	@RequestMapping(value = "/presentation", method = RequestMethod.POST)
	@Transactional
	public Boolean savePresentation(@RequestBody org.esprit.pojo.Presentation presentationPojo) {
		List<User> users = new ArrayList<>();
		boolean userExist = false;
		users = userMetier.getUsers();
		User userPresentation = null;
		for (User user : users) {
			if (user.getEmail().equals(presentationPojo.getEmail())) {
				userExist = true;
				userPresentation = user;
				break;
			}
		}
		if (userExist) {
			Presentation presentation = new Presentation();
			presentation.setUser(userPresentation);
			presentation.setPresentationName(presentationPojo.getNomPresentation());
			List<Slide> slides = new ArrayList<>();
			presentationRepository.save(presentation);
			for (String slidePojo : presentationPojo.getSlide()) {
				Slide slide = new Slide();
				slide.setPresentations(presentation);
				slide.setSlideText(slidePojo);
				slideMetier.addSide(slide);
				slides.add(slide);
			}
			presentation.setSlides(slides);
			return true;
		} else {
			return false;
		}
	}
}
