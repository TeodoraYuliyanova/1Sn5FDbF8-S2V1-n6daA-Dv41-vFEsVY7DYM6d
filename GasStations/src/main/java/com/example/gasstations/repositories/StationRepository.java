package com.example.gasstations.repositories;

import com.example.gasstations.domain.entities.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, UUID> {
    List<StationEntity> findByName(String name);

}
