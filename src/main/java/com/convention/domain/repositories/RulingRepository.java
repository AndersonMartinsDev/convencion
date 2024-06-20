package com.convention.domain.repositories;

import com.convention.domain.entities.Ruling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulingRepository extends JpaRepository<Ruling,Long> {
}
