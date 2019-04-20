package org.esprit.dao;

import org.esprit.entities.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentationRepository extends JpaRepository<Presentation, Integer> {
}
