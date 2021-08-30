package org.dokumacioglu.sfgpetclinic.models;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "specialities")
public class Speciality extends BaseEntity{
    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Builder
    public Speciality(Long id, String description, String name) {
        super(id);
        this.description = description;
        this.name = name;
    }
}
