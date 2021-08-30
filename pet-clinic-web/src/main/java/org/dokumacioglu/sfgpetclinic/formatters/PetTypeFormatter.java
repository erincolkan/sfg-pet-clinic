package org.dokumacioglu.sfgpetclinic.formatters;

import org.dokumacioglu.sfgpetclinic.models.PetType;
import org.dokumacioglu.sfgpetclinic.repositories.pet.PetRepository;
import org.dokumacioglu.sfgpetclinic.repositories.pettype.PetTypeRepository;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.stream.StreamSupport;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeRepository petTypeRepository;

    public PetTypeFormatter(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        return StreamSupport.stream(petTypeRepository.findAll().spliterator(), false)
                .filter(petType -> petType.getName().equals(s))
                .findFirst()
                .orElseThrow(() -> new ParseException("type not found"+s, 0));
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
