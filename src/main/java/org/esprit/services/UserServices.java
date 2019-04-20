package org.esprit.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.esprit.entities.Presentation;
import org.esprit.entities.User;
import org.esprit.metier.UserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserServices {

	@Autowired
	private UserMetier userMetier;

	// get user by id
	@RequestMapping(value = "/users/{codeUser}", method = RequestMethod.GET)
	public User getUser(@PathVariable Integer codeUser) {
		return userMetier.getUser(codeUser);
	}

	// ajouter user
	@RequestMapping(value = "/userr", method = RequestMethod.POST)
	public User saveUser(@RequestBody User user) {
		return userMetier.saveUser(user);
	}

	// get all users
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsers() {
		return userMetier.getUsers();
	}

	// delete user
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable Integer id) {

		return userMetier.delete(id);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public User save(@PathVariable Integer id, @RequestBody User c) {
		c.setUserCode(id);
		return userMetier.saveUser(c);
	}

	// verificarion authentification
	@RequestMapping(value = "/users/authentification", method = RequestMethod.POST)
	public boolean verif(@RequestBody User user) {
		return userMetier.authentification(user);
	}

	// inscription
	@RequestMapping(value = "/users/inscription", method = RequestMethod.POST)
	public boolean verifMail(@RequestBody User user) {
		return userMetier.inscription(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User getUserByEmail(@RequestParam String email) {
		return userMetier.getUserByEmail(email);
	}
}
