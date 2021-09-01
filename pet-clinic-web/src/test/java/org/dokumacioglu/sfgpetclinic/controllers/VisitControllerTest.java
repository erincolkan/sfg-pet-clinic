package org.dokumacioglu.sfgpetclinic.controllers;

import org.dokumacioglu.sfgpetclinic.models.Pet;
import org.dokumacioglu.sfgpetclinic.models.Visit;
import org.dokumacioglu.sfgpetclinic.services.interfaces.PetService;
import org.dokumacioglu.sfgpetclinic.services.interfaces.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    //@TODO: Implement tests for controller.
    private static final String VIEW_VISIT_CREATE_OR_UPDATE = "pets/createOrUpdateVisitForm";

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;
    Pet pet;

    @BeforeEach
    void setUp() {
        pet = Pet.builder().id(1L).build();
        pet.addVisit(Visit.builder().description("regular checkup").build());

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception{
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_VISIT_CREATE_OR_UPDATE))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void processNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(post("/owners/1/pets/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(visitService, times(1)).save(any(Visit.class));
    }
}