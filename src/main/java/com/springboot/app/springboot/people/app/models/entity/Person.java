package com.springboot.app.springboot.people.app.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "persons")
public class Person implements Serializable {
    private static final long serialVersionUID = 1l;

    public enum Gender {
        FEMALE,
        MALE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Please enter dni for person")
    private String dni;

    @NotEmpty(message = "Please enter fullname for person")
    @Column(name = "fullname")
    private String fullName;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    @NotNull(message = "Please enter gender FEMALE OR MALE")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Person parent;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Person mother;

    @JsonManagedReference
    @OneToMany(mappedBy = "mother", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Person> childrenMother;

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Person> childrenParent;

    public void adopt(Person child){
        if (this.gender.name().equals("FEMALE")) {
            this.childrenMother.add(child);
        } else {
            this.childrenParent.add(child);
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person getParent() {
        return parent;
    }

    public void setParent(Person parent) {
        this.parent = parent;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public List<Person> getChildrenMother() {
        return childrenMother;
    }

    public void setChildrenMother(List<Person> sonsMothers) {
        this.childrenMother = sonsMothers;
    }

    public List<Person> getChildrenParent() {
        return childrenParent;
    }

    public void setChildrenParent(List<Person> sonsParent) {
        this.childrenParent = sonsParent;
    }

    public Long getId() {
        return id;
    }
}
