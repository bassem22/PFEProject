package org.esprit.metier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.esprit.dao.PresentationRepository;
import org.esprit.dao.SlidesRepository;
import org.esprit.dao.UserRepository;
import org.esprit.entities.Presentation;
import org.esprit.entities.Slide;
import org.esprit.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresentationMetierImpl implements PresentationMetier {

	@Autowired
	private PresentationRepository presentationRepository;
	@Autowired
	private UserMetier userMetier;
	@Autowired
	private SlidesRepository slidesRepository;

	@Override
	public Optional<Presentation> getPresentationById(Integer presentationCode) {
		return presentationRepository.findById(presentationCode);
	}

	@Override
	public Collection<Presentation> getAllPresentation(String email) {
		Collection<Presentation> presentations = new ArrayList<>();
		Collection<Presentation> presentationsToReturn = new ArrayList<>();
		User user = userMetier.getUserByEmail(email);
		if (user != null) {
			presentations = user.getPresentations();
			for(Presentation presentation : presentations){
				Presentation presentation2 = new Presentation();
				presentation2.setPresentationCode(presentation.getPresentationCode());
				presentation2.setPresentationName(presentation.getPresentationName());
				presentationsToReturn.add(presentation2);
			}
		}
		return presentationsToReturn;
	}

	@Override
	public Boolean deletePresentation(Integer presentationCode) {
		Optional<Presentation> presentation = presentationRepository.findById(presentationCode);
		if (presentation.isPresent()) {
			presentationRepository.deleteById(presentationCode);
			return true;
		}
		return false;
	}

	@Override
	public Boolean addPresentationByIdUser(Integer codeUser, Presentation presentation) {

		User user = userMetier.getUser(codeUser);
		if (user != null) {
			Presentation present = presentationRepository.save(presentation);
			Collection<Slide> listSlide = presentation.getSlides();
			for (Slide slide : listSlide) {
				slide.setPresentations(present);
				slidesRepository.save(slide);
			}
			return true;
		} else {
			System.out.println("user inexistant");
		}
		return false;
	}
}
