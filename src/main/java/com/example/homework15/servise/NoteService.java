package com.example.homework15.servise;

import com.example.homework15.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final List<Note> notes = new ArrayList<>();

    public List<Note> listAll() {
        return new ArrayList<>(notes);
    }

    public Note add(Note note) {
        long generatedId = System.currentTimeMillis(); // Генеруємо унікальний ідентифікатор (можна використовувати інші методи)
        note.setId(generatedId);
        notes.add(note);
        return note;
    }

    public void deleteById(long id) {
        notes.removeIf(note -> note.getId() == id);
    }

    public void update(Note updatedNote) {
        long idToUpdate = updatedNote.getId();
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == idToUpdate) {
                notes.set(i, updatedNote);
                return;
            }
        }
    }

    public Note getById(long id) {
        Optional<Note> foundNote = notes.stream()
                .filter(note -> note.getId() == id)
                .findFirst();
        return foundNote.orElse(null);
    }
}