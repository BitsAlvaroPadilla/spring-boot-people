package com.springboot.app.springboot.people.app.models.dao;

import com.springboot.app.springboot.people.app.models.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPersonDao extends PagingAndSortingRepository<Person, Long> {

    @Query("select p from Person p where p.dni = ?1")
    public Person findByDni(String term);

}
