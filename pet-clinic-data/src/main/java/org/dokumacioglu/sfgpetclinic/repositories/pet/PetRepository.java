package org.dokumacioglu.sfgpetclinic.repositories.pet;

import org.dokumacioglu.sfgpetclinic.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
