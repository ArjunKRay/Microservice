package com.bridgelabz.fundooApp.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooApp.dto.NoteDto;
import com.bridgelabz.fundooApp.exception.NoteException;
import com.bridgelabz.fundooApp.model.Note;

import com.bridgelabz.fundooApp.repository.NoteRepository;
import com.bridgelabz.fundooApp.utility.JWTTokenGenerator;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private JWTTokenGenerator tokenGenerator;
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private NoteRepository noteRepository;


	@Override
	public String createNote(NoteDto noteDto, String token) {
//		String userId = tokenGenerator.verifyToken(token);
//		//Optional<User> optUser = userRepository.findByUserId(userId);
//		//if (optUser.isPresent()) {
//			Note note = modelMapper.map(noteDto, Note.class);
//			note.setCreationtTime(LocalDateTime.now());
//			note.setUpdateTime(LocalDateTime.now());
//			note.setUserId(userId);
//			noteRepository.save(note);
//		
//			// return new Response(200, "note created ", null);
//			return "note created";
//
//		} else {
//			
//			throw new NoteException("note not created");
		return null;
	}
	

	@Override
	public String updateNote(NoteDto noteDto, String noteId, String token) {
		String userId = tokenGenerator.verifyToken(token);
		Optional<Note> optNote = noteRepository.findByNoteIdAndUserId(noteId, userId);
		if (optNote.isPresent()) {
			Note note = optNote.get();

			note.setUpdateTime(LocalDateTime.now());

			note.setTitle(noteDto.getTitle());
			note.setDescription(noteDto.getDescription());
			noteRepository.save(note);

			return "note updated";

		} else {
			// return new Response(202, "note doesnt exist", null);
			throw new NoteException("note doesnt exist");
		}

	}

	

	@Override
	public Note getNote(String noteId, String token) {

		String userId = tokenGenerator.verifyToken(token);
		Optional<Note> optNote = noteRepository.findByNoteIdAndUserId(noteId, userId);
		if (optNote.isPresent()) {
			Note note = optNote.get();
			return note;
		} else {
			throw new NoteException("noteId or user not match");
		}

	}

	@Override
	public List<Note> getAllNote(String token) {

		String userId = tokenGenerator.verifyToken(token);
		List<Note> notes = noteRepository.findAll();
		List<Note> filteredNotes = notes.stream().filter(note -> {
			return note.getUserId().equals(userId);
		}).collect(Collectors.toList());
		/*
		 * List<NoteDto> noteslist = new ArrayList<NoteDto>(); for (Note userNotes :
		 * note) { NoteDto noteDto = modelMapper.map(userNotes, NoteDto.class);
		 * noteslist.add(noteDto);
		 * 
		 * } return noteslist;
		 */
		return filteredNotes;
	}

	@Override
	public List<Note> getTrash(String token) {
		String userId = tokenGenerator.verifyToken(token);
		List<Note> notes = noteRepository.findByUserId(userId);
		List<Note> noteslist = notes.stream().filter(data -> data.isTrash()).collect(Collectors.toList());
		/*
		 * List<NoteDto> noteslist = new ArrayList<NoteDto>(); for (Note note : notes) {
		 * NoteDto noteDto = modelMapper.map(note, NoteDto.class);
		 * 
		 * if (note.isTrash() == true) { noteslist.add(noteDto); } } return noteslist;
		 */
		return noteslist;
	}

	@Override
	public List<Note> getArchive(String token) {
		String userId = tokenGenerator.verifyToken(token);
		return noteRepository.findByUserIdAndIsArchive(userId, true);
	}

	@Override
	public String archiveAndUnarchive(String token, String noteId) {

		String userId = tokenGenerator.verifyToken(token);
		Optional<Note> optNote = noteRepository.findByNoteIdAndUserId(noteId, userId);
		if (optNote.isPresent()) {
			Note note = optNote.get();
			if (note.isArchive()) {
				note.setArchive(false);
				noteRepository.save(note);
				return "note is unarchived";
			} else {

				note.setArchive(true);
				noteRepository.save(note);
				return "note is archived";
			}
		} else {
			throw new NoteException("Note or User not present");
		}

	}

	@Override
	public String trashAndUntrash(String token, String noteId) {
		String userId = tokenGenerator.verifyToken(token);
		Optional<Note> optNote = noteRepository.findByNoteIdAndUserId(noteId, userId);
		if (optNote.isPresent()) {
			Note note = optNote.get();
			if (note.isTrash()) {
				note.setTrash(false);
				noteRepository.save(note);
				return "note is untrash";
			} else {
				note.setTrash(true);
				noteRepository.save(note);
				return "note in trash";
			}
		} else {
			throw new NoteException("note or user not present");
		}

	}

	@Override
	public String pinAndUnpin(String token, String noteId) {
		String userId = tokenGenerator.verifyToken(token);
		Optional<Note> optNote = noteRepository.findByNoteIdAndUserId(noteId, userId);
		if (optNote.isPresent()) {
			Note note = optNote.get();
			if (note.isPin()) {
				note.setPin(false);
				noteRepository.save(note);
				return "note is unpin";
			} else {
				note.setPin(true);
				noteRepository.save(note);
				return "note is pin";

			}
		} else {
			throw new NoteException("note or User dont exist");
		}

	}


}
