package com.github.yuliyaks.observer;

import com.github.yuliyaks.model.Note;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

// Создание события - обновление заметки
@Getter
public class NoteUpdatedEvent extends ApplicationEvent {

    private final Note note;
    public NoteUpdatedEvent(Object source, Note note) {
        super(source);
        this.note= note;
    }

}
