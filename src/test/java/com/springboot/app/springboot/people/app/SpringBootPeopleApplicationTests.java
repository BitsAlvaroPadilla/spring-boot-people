package com.springboot.app.springboot.people.app;

import com.github.javafaker.Faker;
import com.springboot.app.springboot.people.app.models.entity.Person;
import com.springboot.app.springboot.people.app.models.services.IPersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Random;


@SpringBootTest
class SpringBootPeopleApplicationTests {

    private final static Faker faker = new Faker();

    @Autowired
    IPersonService iPersonService;

    @Test
    void save_a_person_without_parents() throws ParseException {
        Person person = new Person();

        String gender = from("FEMALE", "MALE");

        person.setDni(String.valueOf(faker.number().numberBetween(900000,949999)));
        person.setFullName(faker.lorem().word());
        person.setBirth(faker.date().birthday());
        person.setGender(Person.Gender.valueOf(gender));
        person.setParent(null);
        person.setMother(null);

        this.iPersonService.save(person);
    }

    @Test
    void find_a_person_without() throws ParseException {
        Person person = new Person();

        String gender = from("FEMALE", "MALE");

        person.setDni(String.valueOf(faker.number().numberBetween(900000,949999)));
        person.setFullName(faker.lorem().word());
        person.setBirth(faker.date().birthday());
        person.setGender(Person.Gender.valueOf(gender));
        person.setParent(null);
        person.setMother(null);

        Person children = this.iPersonService.save(person);

        assertNotNull(this.iPersonService.findById(children.getId()));
    }


    public static <T> T from(T... elements) {
        Random rand = new Random();

        return elements[rand.nextInt(elements.length)];
    }

}
