package org.dokumacioglu.sfgpetclinic.services.map;

import org.dokumacioglu.sfgpetclinic.models.Speciality;
import org.dokumacioglu.sfgpetclinic.models.Vet;
import org.dokumacioglu.sfgpetclinic.services.interfaces.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.dokumacioglu.sfgpetclinic.services.interfaces.VetService;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Vet save(Vet vet) {
        if(vet.getSpecialities().size() > 0){
            vet.getSpecialities().forEach(speciality -> {
                if(speciality.getId() == null){
                    Speciality savedSpeciality = specialityService.save(speciality);
                    speciality.setId(savedSpeciality.getId());
                }
            });
        }

        return super.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
