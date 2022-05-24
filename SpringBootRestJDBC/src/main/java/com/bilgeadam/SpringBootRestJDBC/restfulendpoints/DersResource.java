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

import com.bilgeadam.SpringBootRestJDBC.model.Ders;
import com.bilgeadam.SpringBootRestJDBC.repo.DersRepo;

@RestController
@RequestMapping(value = "ders")
public class DersResource {
	@Autowired
	public DersRepo dersRepo;

	// produces yazmazsam 406 hatası alabiliyorum
	@GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Ders> getAll() {
		// localhost:8080/ders/getAll
		ArrayList<Ders> liste = (ArrayList<Ders>) dersRepo.getAll();
		return liste;
	}
	// localhost:8080/ders/getById/1
	@GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ders> getByIdEntity(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(dersRepo.getById(id));
	}
	// localhost:8080/ders/save
	@PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Ders ders) {
		boolean result = dersRepo.save(ders);
		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED).body(ders.getOGR_ID() + " id'ye sahip öğretmenin dersi başarıyla eklendi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ders.getID() + " id'ye sahip ders konu eklenemedi");
		}
	}
	
//	localhost:8080/ders/deleteById/2
	@DeleteMapping(path = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable Long id) {

		boolean result = dersRepo.deleteById(id);
		if (result) {

			return ResponseEntity.status(HttpStatus.OK)
					.body("Ders başarıyla silindi.");
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Ders silinirken hata oluştu.");
			
		}
	}
}
