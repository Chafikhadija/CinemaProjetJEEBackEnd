package com.example.bdcc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.bdcc.entities.Film;
import com.example.bdcc.entities.Salle;
import com.example.bdcc.entities.Ticket;
import com.example.bdcc.services.CinemaInitServiceImpl;



@SpringBootApplication
public class CinemasProjectApplication implements CommandLineRunner {

	@Autowired
	private CinemaInitServiceImpl cinemaInitServiceImpl;
	@Autowired
	private RepositoryRestConfiguration restConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(CinemasProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	restConfiguration.exposeIdsFor(Film.class,Salle.class,Ticket.class);
	cinemaInitServiceImpl.initVilles();
	cinemaInitServiceImpl.initCinemas();
	cinemaInitServiceImpl.initSalles();
	cinemaInitServiceImpl.initPlaces();
	cinemaInitServiceImpl.initSeances();
	cinemaInitServiceImpl.initCategories();
	cinemaInitServiceImpl.initFilms();
	cinemaInitServiceImpl.initProjections();
	cinemaInitServiceImpl.initTickets();

	}

}









/*@RestController
class CinemaRestController{
	@Autowired
	private CinemaRepository cinemaRepository;
	@GetMapping("/Cinemas")
	public List<Cinema> cinemas(){
		return cinemaRepository.findAll();
	}
	@GetMapping("/cinemas/{id}")
	public Cinema cinema(@PathVariable Long id) {
		return cinemaRepository.findById(id).get();
	}
	
	@PostMapping("/cinemas")
	public Cinema save(@RequestBody Cinema cinema) {
		return cinemaRepository.save(cinema);
	}
	
	@DeleteMapping("/cinemas/{id}")
	public void delete(@PathVariable Long id) {
		 cinemaRepository.deleteById(id);
	}
	
	@PutMapping("/cinemas/{id}")
	public Cinema update(@RequestBody Cinema cinema,@PathVariable Long id) {
		cinema.setId(id);
		return cinemaRepository.save(cinema);
	}
}*/

