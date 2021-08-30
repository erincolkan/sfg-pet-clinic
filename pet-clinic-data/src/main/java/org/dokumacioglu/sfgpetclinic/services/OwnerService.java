package org.dokumacioglu.sfgpetclinic.services;

import org.dokumacioglu.sfgpetclinic.models.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
}
