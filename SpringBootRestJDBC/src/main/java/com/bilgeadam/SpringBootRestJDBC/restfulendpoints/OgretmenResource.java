package com.bilgeadam.SpringBootRestJDBC.restfulendpoints;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bilgeadam.SpringBootRestJDBC.model.Ogretmen;
import com.bilgeadam.SpringBootRestJDBC.repo.OgretmenRepo;


@RestController
@RequestMapping(value = "ogretmen")
public class OgretmenResource {
	@Autowired
	public OgretmenRepo ogretmenRepo;

	// produces yazmazsam 406 hatası alabiliyorum(spring framework için geçerli)
	@GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Ogretmen> getAll() {
		// localhost:8080/ogretmen/getAll
		ArrayList<Ogretmen> liste = (ArrayList<Ogretmen>) ogretmenRepo.getAll();
		return liste;
	}
	// localhost:8080/ogretmen/getById/1
	@GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ogretmen> getByIdEntity(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(ogretmenRepo.getById(id));
	}
	// localhost:8080/ogretmen/save
	@PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Ogretmen ogretmen) {
		boolean result = ogretmenRepo.save(ogretmen);
		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ogretmen.getNAME() + " isimli öğretmen başarıyla eklendi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ogretmen.getNAME() + " isimli öğretmen eklenemedi");
		}
	}
	
//	localhost:8080/ogretmen/deleteById/2
	@DeleteMapping(path = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		
		boolean result = ogretmenRepo.deleteById(id);
		if (result) {
			
			return ResponseEntity.status(HttpStatus.OK)
					.body("Öğretmen başarıyla silindi.");
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("öğretmen eklenemedi");
		}
		
	}

	
	
	

}
