package org.dokumacioglu.sfgpetclinic.services.map;

import org.dokumacioglu.sfgpetclinic.models.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;
    PetTypeServiceMap petTypeServiceMap;
    PetServiceMap petServiceMap;

    final Long ownerId = 1L;
    final String lastName = "smith";

    @BeforeEach
    void setUp() {
        petServiceMap = new PetServiceMap();
        petTypeServiceMap = new PetTypeServiceMap();

        ownerServiceMap = new OwnerServiceMap(petTypeServiceMap, petServiceMap);

        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void save() {
        Owner owner2 = Owner.builder().id(2L).build();

        Owner savedOwner = ownerServiceMap.save(owner2);

        assertEquals(owner2.getId(), savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findByLastName(){
        Owner smith = ownerServiceMap.findByLastName(lastName);

        assertNotNull(smith);
        assertEquals(lastName, smith.getLastName());
    }
}