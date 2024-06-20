package com.convention.domain.services;

import com.convention.domain.entities.Conference;

import java.util.List;

public interface ConferenceService {
    Conference insert(Conference conference);
    List<Conference> listAllRegisters();

}
