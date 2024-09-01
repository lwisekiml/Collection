package com.example.testproject.controller;

import com.example.testproject.data.entity.Listener;
import com.example.testproject.service.ListenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/listener")
public class ListenerController {

    private final ListenerService listenerService;

    @GetMapping
    public String getListener(Long id) {
        listenerService.getEntity(id);
        return "OK";
    }

    @PostMapping
    public void saveListener(String name) {
        Listener listener = new Listener();
        listener.setName(name);

        listenerService.saveEntity(listener);
    }

    @PutMapping
    public void updateListener(Long id, String name) {
        Listener listener = new Listener();
        listener.setId(id);
        listener.setName(name);

        listenerService.updateEntity(listener);
    }

    @DeleteMapping
    public void deleteListener(Long id) {
        Listener listener = listenerService.getEntity(id);

        listenerService.removeEntity(listener);
    }
}
