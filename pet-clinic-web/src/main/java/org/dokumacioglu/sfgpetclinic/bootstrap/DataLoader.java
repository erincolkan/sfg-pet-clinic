package org.dokumacioglu.sfgpetclinic.bootstrap;

import org.dokumacioglu.sfgpetclinic.models.*;
import org.dokumacioglu.sfgpetclinic.services.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");

        PetType savedCatType = petTypeService.save(cat);

        System.out.println("Loaded pet types.");

        Owner owner1 = Owner.builder().firstName("Michael")
                .lastName("Weston").address("123 Brickerel")
                .city("Miami").telephone("123123123123").pets(new HashSet<>()).build();

        Pet mikePet = new Pet();
        mikePet.setPetType(savedDogType);
        mikePet.setOwner(owner1);
        mikePet.setBirthDate(LocalDate.now());
        mikePet.setName("Rosco");
        owner1.addPet(mikePet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("123123123123");

        Pet fionaCat = new Pet();
        fionaCat.setName("Uppercat");
        fionaCat.setOwner(owner2);
        fionaCat.setBirthDate(LocalDate.now());
        fionaCat.setPetType(savedCatType);
        owner2.addPet(fionaCat);

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Speciality radiology = new Speciality();
        radiology.setName("Radiology");
        radiology.setDescription("Radiology on pets");

        Speciality savedRadiology = specialityService.save(radiology);

        Speciality dentistry = new Speciality();
        dentistry.setName("Dentistry");
        dentistry.setDescription("Animal dentistry.");

        Speciality savedDentistry = specialityService.save(dentistry);

        Speciality surgery = new Speciality();
        surgery.setName("Surgery");
        surgery.setDescription("Pet surgery.");

        Speciality savedSurgery = specialityService.save(surgery);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedSurgery);
        vet1.getSpecialities().add(savedDentistry);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Samuel");
        vet2.setLastName("Jackson");
        vet2.getSpecialities().add(savedRadiology);
        vet2.getSpecialities().add(savedDentistry);
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded vets...");
    }
}
