package com.example.bdcc.web;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bdcc.dao.FilmRepository;
import com.example.bdcc.dao.TicketRepository;
import com.example.bdcc.entities.Film;
import com.example.bdcc.entities.Ticket;

import lombok.Data;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
@Autowired
private FilmRepository filmRepository;
@Autowired
private TicketRepository ticketRepository;
@GetMapping(path="/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE )
public byte[] image(@PathVariable (name="id")Long id) throws Exception {
	Film film= filmRepository.findById(id).get();
    if (film != null) {

        // get photo name of a movie
        String photoName = film.getPhoto();

        // get path of photo by it's name
        String photoPath = "/cinema/images/"+photoName;

        File file =
                new File(System.getProperty("user.home") + photoPath);

        Path path = Paths.get(file.toURI());

        // return photo of a movie
        return Files.readAllBytes(path);
    }
    return null;

}

@PostMapping("/payerTickets")
@Transactional
public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
	List<Ticket> payerTickets=new ArrayList<>();
	ticketForm.getTickets().forEach(idTicket->{
		Ticket ticket= ticketRepository.findById(idTicket).get();
		ticket.setNomClient(ticketForm.getNomClient());
		ticket.setReserve(true);
		ticket.setCodePayment(ticketForm.getCodePayment());
		ticketRepository.save(ticket);
		payerTickets.add(ticket);
	});
	return payerTickets;
}

}

@Data
class TicketForm{
	private String nomClient;
	private List<Long> tickets=new ArrayList<>();
	private int codePayment;
}