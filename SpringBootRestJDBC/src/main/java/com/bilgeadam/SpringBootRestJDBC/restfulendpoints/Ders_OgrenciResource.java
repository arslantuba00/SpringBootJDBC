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

import com.bilgeadam.SpringBootRestJDBC.model.Ders_Ogrenci;
import com.bilgeadam.SpringBootRestJDBC.repo.Ders_OgrenciRepo;

@RestController
@RequestMapping(value = "ders_ogrenci")
public class Ders_OgrenciResource {
	@Autowired
	public Ders_OgrenciRepo ders_OgrenciRepo;

	// produces yazmazsam 406 hatası alabiliyorum
	@GetMapping(path = "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Ders_Ogrenci> getAll() {
		// localhost:8080/ders_ogrenci/getAll
		ArrayList<Ders_Ogrenci> liste = (ArrayList<Ders_Ogrenci>) ders_OgrenciRepo.getAll();
		return liste;
	}
	// localhost:8080/ders_ogrenci/getById/1
	@GetMapping(path = "getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ders_Ogrenci> getByIdEntity(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(ders_OgrenciRepo.getById(id));
	}
	// localhost:8080/ders_ogrenci/save
	@PostMapping(path = "save" /*, consumes = MediaType.APPLICATION_JSON_VALUE*/)
	public ResponseEntity<String> save(@RequestBody Ders_Ogrenci ders_ogrenci) {
		boolean result = ders_OgrenciRepo.save(ders_ogrenci);
		if (result) {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ders_ogrenci.getID() + " id'ye sahip ders_öğrenci verisi başarıyla eklendi");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ders_ogrenci.getID() + " id'ye sahip ders_öğrenci verisi eklenemedi");
		}
	}
	
//	localhost:8080/ders_ogrenci/deleteById/2
	@DeleteMapping(path = "deleteById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteById(@PathVariable Long id) {

		boolean result = ders_OgrenciRepo.deleteById(id);
		if (result) {

			return ResponseEntity.status(HttpStatus.OK)
					.body("Ders öğrenci verisi başarıyla silindi.");
		} 
		else 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Ders öğrenci verisi silinirken hata oluştu.");
			
		}
	}
}
