package com.springboot.app.springboot.people.app.controllers.api;

import com.springboot.app.springboot.people.app.exceptions.ExistingUserException;
import com.springboot.app.springboot.people.app.models.entity.Person;
import com.springboot.app.springboot.people.app.models.services.IPersonService;
import com.springboot.app.springboot.people.app.models.util.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@RestController
@RequestMapping(value = "api/persons", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiPersonController {
    @Autowired
    IPersonService iPersonService;

    @GetMapping("/index")
    public ResponseEntity<HashMap<String, Object>> index(@RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageRequest = PageRequest.of(page, 1);

        Page<Person> persons = this.iPersonService.findAll(pageRequest);

        PageRender<Person> pageRender = new PageRender<Person>("/index", persons);

        return ResponseEntity.ok().body(new HashMap<String, Object>(){{
            put("persons", persons);
            put("pageRender", pageRender);
        }});
    }

    @PostMapping(value = "/store")
    public ResponseEntity<Person> store(@RequestBody Request request) throws ParseException, ExistingUserException {

        Person person = this.iPersonService.findByDni(request.getDni());
        if (person !=null ){
            throw new ExistingUserException("the user with the ID is already registered");
        }

        Person children = new Person();
        children.setDni(request.getDni());
        children.setFullName(request.getFullName());
        children.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse(request.getBirth()));
        children.setGender(Person.Gender.valueOf(request.getGender()));

        if (request.getMotherId() != null && request.getParentId() != null ){
            Person parent = this.iPersonService.findById(request.getParentId());
            Person mother = this.iPersonService.findById(request.getMotherId());

            if (parent != null && mother != null){
                parent.adopt(children);
                mother.adopt(children);
                children.setParent(parent);
                children.setMother(mother);
            }
        }
        return new ResponseEntity<Person>(this.iPersonService.save(children), HttpStatus.CREATED);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<HashMap<String, Object>> show(@PathVariable Long id){

        Person person = this.iPersonService.findById(id);

        if (person != null){
            //return new ResponseEntity<Person>(person, HttpStatus.OK);

            return ResponseEntity.ok().body(new HashMap<String, Object>(){{
                put("persons", person);
                put("parentFullName", person.getParent().getFullName());
                put("motherFullName", person.getMother().getFullName());
            }});
        }
        return new ResponseEntity(new HashMap<String, HttpStatus>() {{
            put(String.format("PersonNotExist"), HttpStatus.NOT_FOUND);
        }}, HttpStatus.NOT_FOUND);
    }
}




final class Request {
    private String dni;
    private String fullName;
    private String birth;
    private String gender;
    private Long parentId;
    private Long motherId;

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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getMotherId() {
        return motherId;
    }

    public void setMotherId(Long motherId) {
        this.motherId = motherId;
    }
}

