package org.dokumacioglu.sfgpetclinic.controllers;

import org.dokumacioglu.sfgpetclinic.models.Owner;
import org.dokumacioglu.sfgpetclinic.models.Pet;
import org.dokumacioglu.sfgpetclinic.models.PetType;
import org.dokumacioglu.sfgpetclinic.services.interfaces.OwnerService;
import org.dokumacioglu.sfgpetclinic.services.interfaces.PetService;
import org.dokumacioglu.sfgpetclinic.services.interfaces.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @Mock
    PetService petService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Pet pet;
    Owner owner;
    Set<PetType> petTypes;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).pets(new HashSet<>()).build();
        petTypes = new HashSet<>();
        PetType cat = PetType.builder().id(1L).name("Cat").build();
        petTypes.add(cat);
        PetType dog = PetType.builder().id(2L).name("Dog").build();
        petTypes.add(dog);

        pet = Pet.builder().id(1L).owner(owner).petType(cat).name("Mirmir").build();
        owner.getPets().add(pet);

        petController = new PetController(petService, ownerService, petTypeService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .build();
    }

    @Test
    void initCreationForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM));
    }

    @Test
    void processCreationForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService, times(1)).save(any(Pet.class));
    }

    @Test
    void initUpdateForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("types"))
                .andExpect(view().name(VIEWS_PETS_CREATE_OR_UPDATE_FORM));
    }

    @Test
    void processUpdateForm() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);


        mockMvc.perform(post("/owners/1/pets/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(petService, times(1)).save(any());
    }
}