package com.springboot.app.springboot.people.app.models.services;

import com.springboot.app.springboot.people.app.models.dao.IPersonDao;
import com.springboot.app.springboot.people.app.models.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPersonServiceImpl implements IPersonService{

    @Autowired
    IPersonDao iPersonDao;

    @Override
    public Page<Person> findAll(Pageable pageable) {
        return this.iPersonDao.findAll(pageable);
    }

    @Override
    public Person save(Person person) {
        return this.iPersonDao.save(person);
    }

    @Override
    public Person findById(Long id) {
        return this.iPersonDao.findById(id).orElse(null);
    }

    @Override
    public Person findByDni(String dni) {
        return this.iPersonDao.findByDni(dni);
    }
}
