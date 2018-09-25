package com.loomadevp.loomadevp.repository;

import com.loomadevp.loomadevp.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    // SELECT * FROM animals
    List<Animal> findAll();
}
