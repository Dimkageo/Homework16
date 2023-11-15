package com.example.homework15.controller;

import com.example.homework15.servise.NoteService;
import com.example.homework15.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {


    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public String getNoteList(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String getEditNotePage(@RequestParam long id, Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "edit";
    }

    @PostMapping("/edit")
    public String editNote(@ModelAttribute Note updatedNote) {
        noteService.update(updatedNote);
        return "redirect:/note/list";
    }
    @GetMapping("/create")
    public String getCreateNotePage(Model model) {
        model.addAttribute("note", new Note());
        return "create";
    }
    @PostMapping("/save")
    public String saveNote(@ModelAttribute Note newNote) {
        noteService.add(newNote);
        return "redirect:/note/list";
    }


}