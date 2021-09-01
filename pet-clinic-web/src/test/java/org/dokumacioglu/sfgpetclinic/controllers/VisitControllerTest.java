package org.dokumacioglu.sfgpetclinic.controllers;

import org.dokumacioglu.sfgpetclinic.services.interfaces.PetService;
import org.dokumacioglu.sfgpetclinic.services.interfaces.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    //@TODO: Implement tests for controller.

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void loadPetWithVisit() {
    }

    @Test
    void initNewVisitForm() {
    }

    @Test
    void processNewVisitForm() {
    }
}