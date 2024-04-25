package com.github.yuliyaks.service.impl;

import com.github.yuliyaks.observer.NoteUpdatedEvent;
import lombok.RequiredArgsConstructor;
import com.github.yuliyaks.repository.NoteRepository;
import com.github.yuliyaks.model.Note;
import com.github.yuliyaks.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    // Создание репозитория
    private final NoteRepository noteRepository;

    // Создание объекта для публикации события
    @Autowired
    private final ApplicationEventPublisher publisher;

    // Создать заметку
    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    // Получить все заметки
    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Получить заметку по ID
    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

    // Обновить заметку
    @Override
    public Note updateNote(Note note) {
        Note noteById = getNoteById(note.getId());

        noteById.setTitle(note.getTitle());
        noteById.setContents(note.getContents());
        noteById.setCreationDate(LocalDateTime.now());

        // Публикация события (обновления заметки) в консоль
        publisher.publishEvent(new NoteUpdatedEvent(this, noteById));

        noteRepository.save(noteById);

        return noteById;
    }

    // Удалить заметку
    @Override
    public void deleteNote(Long id) {
        Note noteById = getNoteById(id);
        noteRepository.delete(noteById);
    }

}
