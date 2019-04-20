package org.esprit.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Presentation implements Serializable {
	// @GeneratedValue(strategy=GenerationType.increment)
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer presentationCode;

	

	@ManyToOne
	@JoinColumn(name = "CODE_USER")
	private User user;

	@OneToMany(mappedBy = "presentations", fetch = FetchType.LAZY)
	private Collection<Slide> slides;

	private String presentationName;

	

	

	@JsonBackReference
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<Slide> getSlides() {
		return slides;
	}

	public void setSlides(Collection<Slide> slides) {
		this.slides = slides;
	}

	public String getPresentationName() {
		return presentationName;
	}

	public void setPresentationName(String presentationName) {
		this.presentationName = presentationName;
	}
	public Integer getPresentationCode() {
		return presentationCode;
	}

	public void setPresentationCode(Integer presentationCode) {
		this.presentationCode = presentationCode;
	}
}
