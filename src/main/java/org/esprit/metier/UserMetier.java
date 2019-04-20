package org.esprit.metier;

import java.util.List;
import java.util.Optional;

import org.esprit.entities.Presentation;
import org.esprit.entities.User;

public interface UserMetier {

	public User getUser(Integer codeUser);

	public User saveUser(User user);

	public List<User> getUsers();

	public boolean delete(Integer codeUser);

	public boolean authentification(User user);

	public boolean inscription(User user);
	
	public Presentation getPresentation(Integer codeUser,Presentation presentation );
	
	public User getUserByEmail(String email);

}
