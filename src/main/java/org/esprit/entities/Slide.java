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

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Slide implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer slideCode;

	@ManyToOne
	@JoinColumn(name = "CODE_PRESENTATION")
	private Presentation presentations;
	private String slideText;

	public Slide() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonBackReference
	public Presentation getPresentations() {
		return presentations;
	}

	public void setPresentations(Presentation presentations) {
		this.presentations = presentations;
	}

	public String getSlideText() {
		return slideText;
	}

	public void setSlideText(String slideText) {
		this.slideText = slideText;
	}

	public Integer getSlideCode() {
		return slideCode;
	}

	public void setSlideCode(Integer slideCode) {
		this.slideCode = slideCode;
	}
}
