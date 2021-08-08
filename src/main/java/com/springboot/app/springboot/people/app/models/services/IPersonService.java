package com.springboot.app.springboot.people.app.models.services;

import com.springboot.app.springboot.people.app.models.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPersonService {

    public Page<Person> findAll(Pageable pageable);

    public Person save(Person person);

    public Person findById(Long id);

    public Person findByDni(String dni);
}
