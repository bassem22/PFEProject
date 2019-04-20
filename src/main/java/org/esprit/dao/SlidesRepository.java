package org.esprit.dao;


import org.esprit.entities.Slide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlidesRepository extends JpaRepository<Slide, Integer> {

}
