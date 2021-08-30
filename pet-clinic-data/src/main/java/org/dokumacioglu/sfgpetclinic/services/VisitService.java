package org.dokumacioglu.sfgpetclinic.services;

import org.dokumacioglu.sfgpetclinic.models.Visit;

import java.util.Set;

public interface VisitService extends CrudService<Visit, Long> {
    Set<Visit> findByPetId(Long petId);
}
