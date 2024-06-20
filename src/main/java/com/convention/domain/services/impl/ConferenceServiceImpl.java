package com.convention.domain.services.impl;

import com.convention.domain.entities.Conference;
import com.convention.domain.repositories.ConferenceRepository;
import com.convention.domain.services.ConferenceService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    public final ConferenceRepository repository;

    @Autowired
    public ConferenceServiceImpl(ConferenceRepository repository) {
        this.repository = repository;
    }

    @Override
    @SneakyThrows
    public Conference insert(Conference conference) {
        return repository.save(conference);
    }

    @Override
    public List<Conference> listAllRegisters() {
        return repository.findAll();
    }
}
