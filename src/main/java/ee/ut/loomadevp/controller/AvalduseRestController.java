package ee.ut.loomadevp.controller;

import ee.ut.loomadevp.model.Animal;
import ee.ut.loomadevp.model.AnimalDTO;
import ee.ut.loomadevp.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvalduseRestController {
    @Autowired
    private AnimalRepository animalRepository;

    @PostMapping("/viiols/save")
    public AnimalDTO postProduct(@RequestBody AnimalDTO animal) {
        animalRepository.addAnimal(animal.getLiik(), animal.getVanus(), animal.getLinn(), String.valueOf(animal.getAvatarImage()));

        return animal;
    }
}