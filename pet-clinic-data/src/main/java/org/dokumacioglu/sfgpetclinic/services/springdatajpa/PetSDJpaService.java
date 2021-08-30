package org.dokumacioglu.sfgpetclinic.services.springdatajpa;

import org.dokumacioglu.sfgpetclinic.models.Pet;
import org.dokumacioglu.sfgpetclinic.repositories.pet.PetRepository;
import org.dokumacioglu.sfgpetclinic.services.PetService;

import java.util.HashSet;
import java.util.Set;

public class PetSDJpaService implements PetService {
    private final PetRepository petRepository;

    public PetSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);

        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(Pet pet) {
        petRepository.delete(pet);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
