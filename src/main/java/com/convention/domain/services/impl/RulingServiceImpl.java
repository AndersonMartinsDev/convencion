package com.convention.domain.services.impl;

import com.convention.domain.entities.Ruling;
import com.convention.domain.repositories.RulingRepository;
import com.convention.domain.services.RulingService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulingServiceImpl implements RulingService {
    public final RulingRepository repository;

    @Autowired
    public RulingServiceImpl(RulingRepository repository) {
        this.repository = repository;
    }

    @Override
    @SneakyThrows
    public Ruling insert(Ruling ruling) {
        return repository.save(ruling);
    }

    @Override
    public List<Ruling> listAllByConferenceId(Integer conferenceId) {
        return List.of();
    }
}
