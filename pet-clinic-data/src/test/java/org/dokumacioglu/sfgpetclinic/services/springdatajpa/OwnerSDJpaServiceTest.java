package org.dokumacioglu.sfgpetclinic.services.springdatajpa;


import org.dokumacioglu.sfgpetclinic.models.Owner;
import org.dokumacioglu.sfgpetclinic.repositories.owner.OwnerRepository;
import org.dokumacioglu.sfgpetclinic.repositories.pet.PetRepository;
import org.dokumacioglu.sfgpetclinic.repositories.pettype.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
        Owner returnOwner = Owner.builder().lastName("Smith").build();

        when(service.findByLastName(any())).thenReturn(returnOwner);

        Owner owner = service.findByLastName("Smith");

        assertEquals("Smith", owner.getLastName());
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}