package com.example.testproject.service.impl;

import com.example.testproject.data.entity.Listener;
import com.example.testproject.data.repository.ListenerRepository;
import com.example.testproject.service.ListenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListenerServiceImpl implements ListenerService {

    private final ListenerRepository listenerRepository;

    @Override
    public Listener getEntity(Long id) {
        return listenerRepository.findById(id).get();
    }

    @Override
    public void saveEntity(Listener listener) {
        listenerRepository.save(listener);
    }

    @Override
    public void updateEntity(Listener listener) {
        Listener foundListrner = listenerRepository.findById(listener.getId()).get();
        foundListrner.setName(listener.getName());

        listenerRepository.save(foundListrner);
    }

    @Override
    public void removeEntity(Listener listener) {
        listenerRepository.delete(listener);
    }
}
