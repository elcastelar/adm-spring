package com.adm.entities;

import com.adm.enums.PetSex;
import com.adm.enums.PetSize;
import com.adm.enums.PetTamperType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Entity(name = "tb_pet")
public class Pet implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    
    @ManyToOne
    @JoinColumn(name = "fk_registeredBy")
    private User registeredBy;

    @ManyToOne
    @JoinColumn(name = "fk_owner")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "fk_pet_type")
    private PetType petType;

    @Enumerated(EnumType.STRING)
    private PetSex petSex;

    private PetSize petSize;

    @ElementCollection(targetClass = PetTamperType.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "tb_pet_tamper_type")
    private Set<PetTamperType> petTamperType;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_pet_to_req_type")
    private Set<ReqType> reqTypeSet;

    private LocalDateTime creationDateTime;

    private LocalDateTime lastUpdateDateTime;

    private String about;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(User registeredBy) {
        this.registeredBy = registeredBy;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Set<ReqType> getReqTypeSet() {
        return reqTypeSet;
    }

    public void setReqTypeSet(Set<ReqType> reqTypeSet) {
        this.reqTypeSet = reqTypeSet;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    @Override
    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
        this.lastUpdateDateTime = lastUpdateDateTime;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public PetSex getPetSex() {
        return petSex;
    }

    public void setPetSex(PetSex petSex) {
        this.petSex = petSex;
    }

    public PetSize getPetSize() {
        return petSize;
    }

    public void setPetSize(PetSize petSize) {
        this.petSize = petSize;
    }

    public Set<PetTamperType> getPetTamperType() {
        return petTamperType;
    }

    public void setPetTamperType(Set<PetTamperType> petTamperType) {
        this.petTamperType = petTamperType;
    }
}
