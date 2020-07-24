package com.example.bdcc.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p1",types= {com.example.bdcc.entities.Projection.class})
public interface ProjectionProj {
	public Long getId();
	public double getPrix();
	public Date getDateProjection();
	public Salle getSalle();
	public Seance getSeance();
	public Film getFilm();
	public Collection<Ticket> getTickets();

}
