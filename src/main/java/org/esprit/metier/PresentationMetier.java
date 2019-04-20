package org.esprit.metier;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.esprit.entities.Presentation;
import org.esprit.entities.Slide;
import org.esprit.entities.User;

public interface PresentationMetier {

	public Optional<Presentation> getPresentationById(Integer presentationCode);

	public Collection<Presentation> getAllPresentation(String email);

	public Boolean deletePresentation(Integer presentationCode);

	public Boolean addPresentationByIdUser(Integer codeUser , Presentation presentation);

}
