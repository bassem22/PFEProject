package org.esprit.metier;

import java.util.List;
import org.esprit.dao.PresentationRepository;
import org.esprit.dao.UserRepository;
import org.esprit.entities.Presentation;
import org.esprit.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMetierImpl implements UserMetier {

	@Autowired
	private UserRepository userRepository;

	private PresentationRepository presentationRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public boolean delete(Integer codeUser) {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getUserCode() == codeUser) {
				userRepository.delete(user);
				return true;
			}
		}
		return false;
	}

	@Override
	public User getUser(Integer codeUser) {

		List<User> users = userRepository.findAll();
		for (User user : users) {
			if (user.getUserCode() == codeUser) {
				return user;
			}

		}

		return null;
	}

	@Override
	public boolean authentification(User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		List<User> users = userRepository.findAll();
		for (User verifyUser : users) {
			if ((verifyUser.getEmail().equals(email)) && (verifyUser.getPassword().equals(password))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean inscription(User user) {
		String email = user.getEmail();
		List<User> users = userRepository.findAll();
		boolean isNotValid = false;
		for (User verifyUser : users) {
			if (verifyUser.getEmail().equals(email)) {
				isNotValid = true;
				break;
			}
		}
		if (!isNotValid) {
			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public Presentation getPresentation(Integer codeUser, Presentation presentation) {

		List<Presentation> presentations = presentationRepository.findAll();
		for (Presentation presentation1 : presentations) {
			if (presentation1.getUser().getUserCode() == codeUser) {

				return presentation;
			}

		}
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		List<User> users = userRepository.findAll();
		if (!users.isEmpty()) {
			for (User user : users) {
				if (user.getEmail().equals(email)) {
					return user;
				}
			}
		}
		return null;
	}

}
