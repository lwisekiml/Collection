package com.example.testproject.controller;

import com.example.testproject.data.entity.ListenerEntity;
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
        ListenerEntity listenerEntity = new ListenerEntity();
        listenerEntity.setName(name);

        listenerService.saveEntity(listenerEntity);
    }

    @PutMapping
    public void updateListener(Long id, String name) {
        ListenerEntity listenerEntity = new ListenerEntity();
        listenerEntity.setId(id);
        listenerEntity.setName(name);

        listenerService.updateEntity(listenerEntity);
    }

    @DeleteMapping
    public void deleteListener(Long id) {
        ListenerEntity listenerEntity = listenerService.getEntity(id);

        listenerService.removeEntity(listenerEntity);
    }
}
