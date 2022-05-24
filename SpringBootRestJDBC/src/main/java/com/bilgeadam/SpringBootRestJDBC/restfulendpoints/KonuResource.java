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

import com.bilgeadam.SpringBootRestJDBC.model.Konu;
import com.bilgeadam.SpringBootRestJDBC.repo.KonuRepo;

@RestController
@RequestMapping(value = "konu")
public class KonuResource {
	@Autowired
	public KonuRepo konuRepo;

	// produces yazmazsam 406 hatası alabiliyorum
	@GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Konu> getAll() {
		// localhost:8080/konu/getAll
		ArrayList<Konu> liste = (ArrayList<Konu>) konuRepo.getAll();
		return liste;
	}
	// localhost:8080/konu/getById/1
	@GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Konu> getByIdEntity(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(konuRepo.getById(id));
	}
	// localhost:8080/konu/save
	@PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Konu konu) {
		boolean result = konuRepo.save(konu);
		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED).body(konu.getNAME() + " isimli konu başarıyla eklendi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(konu.getNAME() + " isimli konu eklenemedi");
		}
	}
	
//	localhost:8080/konu/deleteById/2
	@DeleteMapping(path = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable Long id) {

		boolean result = konuRepo.deleteById(id);
		if (result) {

			return ResponseEntity.status(HttpStatus.OK)
					.body("Konu başarıyla silindi.");
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Konu silinirken hata oluştu.");
			
		}
	}
}
