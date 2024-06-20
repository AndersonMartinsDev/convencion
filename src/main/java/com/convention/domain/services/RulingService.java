package com.convention.domain.services;

import com.convention.domain.entities.Ruling;

import java.util.List;

public interface RulingService {
    Ruling insert(Ruling ruling);
    List<Ruling> listAllByConferenceId(Integer conferenceID);
}
