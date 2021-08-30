package org.dokumacioglu.sfgpetclinic.controllers;

import org.dokumacioglu.sfgpetclinic.models.Owner;
import org.dokumacioglu.sfgpetclinic.models.PetType;
import org.dokumacioglu.sfgpetclinic.repositories.owner.OwnerRepository;
import org.dokumacioglu.sfgpetclinic.repositories.pet.PetRepository;
import org.dokumacioglu.sfgpetclinic.services.OwnerService;
import org.dokumacioglu.sfgpetclinic.services.PetService;
import org.dokumacioglu.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Set<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
}
