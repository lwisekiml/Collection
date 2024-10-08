package com.example.testproject.data.entity.listrner;

import com.example.testproject.data.entity.Listener;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomListener {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomListener.class);

    @PostLoad
    public void postLoad(Listener entity) {
        LOGGER.info("[postLoad] called!");
    }

    @PrePersist
    public void prePersist(Listener entity) {
        LOGGER.info("[prePersist] called!!");
    }

    @PostPersist
    public void postPersist(Listener entity) {
        LOGGER.info("[postPersist] called!!");
    }

    @PreUpdate
    public void preUpdate(Listener entity) {
        LOGGER.info("[preUpdate] called!!");
    }

    @PostUpdate
    public void postUpdate(Listener entity) {
        LOGGER.info("[postUpdate] called!!");
    }

    @PreRemove
    public void preRemove(Listener entity) {
        LOGGER.info("[preRemove] called!!");
    }

    @PostRemove
    public void postRemove(Listener entity) {
        LOGGER.info("[postRemove] called!!");
    }
}
