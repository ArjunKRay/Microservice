package com.bridgelabz.fundooApp.controller;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.fundooApp.dto.NoteDto;
import com.bridgelabz.fundooApp.model.Note;
import com.bridgelabz.fundooApp.response.Response;
import com.bridgelabz.fundooApp.service.NoteService;

@RestController
@RequestMapping("/noteservice")
public class NoteController {
	@Autowired
	private NoteService noteService;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/note")
	public ResponseEntity<Response> createNote(@RequestBody NoteDto noteDto, @RequestHeader String token)
			throws IOException
	{
		
		       String message = noteService.createNote(noteDto, token);

				boolean isPresent = restTemplate.getForObject("http://localhost:8081/userservice/checkUser/" + token,
				Boolean.class);
				
				
		
		Response response = new Response(HttpStatus.OK.value(), message, isPresent);
		
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("/note")
	public ResponseEntity<Response> updateNote(@RequestBody NoteDto noteDto, @RequestParam String noteId,

			@RequestHeader String token) {
		String message = noteService.updateNote(noteDto, noteId, token);
		boolean isPresent = restTemplate.getForObject("http://localhost:8081/userservice/checkUser/" + token,
				Boolean.class);
	
		Response response = new Response(HttpStatus.OK.value(), message, isPresent);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}


	@GetMapping("/note")
	public Note getNote(@RequestParam String noteId, @RequestHeader String token) {
		restTemplate.getForObject("http://localhost:8081/userservice/checkUser/" + token, Boolean.class);
		Note note = noteService.getNote(noteId, token);
		return note;
	}

	@GetMapping("/notes")
	public List<Note> getAllNote(@RequestHeader String token) {
		restTemplate.getForObject("http://localhost:8081/userservice/checkUser/" + token, Boolean.class);
		List<Note> noteList = noteService.getAllNote(token);
		return noteList;
	}

	@GetMapping("/getTrash")
	public List<Note> getTrash(@RequestHeader String token) {
		restTemplate.getForObject("http://localhost:8081/userservice/checkUser/" + token, Boolean.class);
		List<Note> noteslist = noteService.getTrash(token);
		return noteslist;
	}

	@GetMapping("/getArchive")
	public List<Note> getArchive(@RequestHeader String token) {
		restTemplate.getForObject("http://localhost:8081/userservice/checkUser/" + token, Boolean.class);
		List<Note> noteslist = noteService.getArchive(token);
		return noteslist;
	}

	@GetMapping("/archiveandUnarchive")
	public ResponseEntity<Response> archiveUnarchiveNote(@RequestHeader String token, @RequestParam String noteId) {
		String message = noteService.archiveAndUnarchive(token, noteId);
		boolean isPresent = restTemplate.getForObject("http://localhost:8081/userservice/checkUser/" + token,
				Boolean.class);
		Response response = new Response(HttpStatus.OK.value(), message, isPresent);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping("/trashandUntrash")
	public ResponseEntity<Response> trashAndUntrash(@RequestHeader String token, @RequestParam String noteId) {
		String message = noteService.trashAndUntrash(token, noteId);
		boolean isPresent = restTemplate.getForObject("http://localhost:8081/userservice/checkUser/" + token,
				Boolean.class);
		Response response = new Response(HttpStatus.OK.value(), message, isPresent);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/pinAndunpin")
	public ResponseEntity<Response> pinAndUnpin(@RequestHeader String token, @RequestParam String noteId) {
		String message = noteService.pinAndUnpin(token, noteId);
		boolean isPresent = restTemplate.getForObject("http://localhost:8081/userservice/checkUser/" + token,
				Boolean.class);
		Response response = new Response(HttpStatus.OK.value(), message, isPresent);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
