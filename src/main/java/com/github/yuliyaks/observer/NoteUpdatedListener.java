package com.github.yuliyaks.observer;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// Создание слушателя события (обновления заметки)
@Component
@AllArgsConstructor
public class NoteUpdatedListener implements ApplicationListener<NoteUpdatedEvent> {

    // Вывод сообщения в консоль при генерации события
    @Override
    public void onApplicationEvent(NoteUpdatedEvent event) {
        System.out.println("Новое событие:");
        System.out.println("Заметка обновлена: " + event.getNote().toString());

    }
}
