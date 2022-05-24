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

import com.bilgeadam.SpringBootRestJDBC.model.Ogrenci;
import com.bilgeadam.SpringBootRestJDBC.repo.OgrenciRepo;

@RestController
@RequestMapping(value = "ogrenci")
public class OgrenciResource {

	@Autowired
	@GetMapping(path = "a")
	public String d() {
		return "Hello";
	}

	@Autowired
	public OgrenciRepo ogrenciRepo;

	// produces yazmazsam 406 hatası alabiliyorum
	@GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Ogrenci> getAll() {
		// localhost:8080/ogrenci/getAll
		ArrayList<Ogrenci> liste = (ArrayList<Ogrenci>) ogrenciRepo.getAll();
		return liste;
	}

	// localhost:8080/ogrenci/getById/6
	@GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ogrenci> getByIdEntity(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(ogrenciRepo.getById(id));
	}

	// localhost:8080/ogrenci/save
	@PostMapping(path = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@RequestBody Ogrenci ogrenci) {
		boolean result = ogrenciRepo.save(ogrenci);
		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ogrenci.getNAME() + " isimli öğrenci başarıyla eklendi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ogrenci.getNAME() + " isimli öğrenci eklenemedi");
		}
	}

//	localhost:8080/ogrenci/deleteById/2
	@DeleteMapping(path = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable Long id) {

		boolean result = ogrenciRepo.deleteById(id);
		if (result) {

			return ResponseEntity.status(HttpStatus.OK)
					.body("Öğrenci başarıyla silindi.");
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Öğrenci silinirken hata oluştu.");
			
		}
	}
}
