package org.esprit.metier;

import org.esprit.dao.SlidesRepository;
import org.esprit.entities.Slide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlideMetierImpl implements SlideMetier {
	@Autowired
	private SlidesRepository slidesRepository;

	@Override
	public Boolean addSide(Slide slide) {
		slidesRepository.save(slide);
		return true;

	}

}
