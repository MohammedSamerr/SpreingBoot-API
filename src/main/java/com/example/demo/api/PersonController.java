package com.example.demo.api;
import com.example.demo.service.personService;
import com.example.demo.model.person;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final personService personService;

    @Autowired
    public PersonController(personService personService){
        this.personService = personService;
    }
    @PostMapping
    public void addPerson(@NonNull @RequestBody person person){
        personService.addPerson(person);

    }

    @GetMapping
    public List<person> getAllPerson(){
        return personService.SelectAllPerson();
    }

    @GetMapping(path = "{id}")
    public person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void DeletePersonById (@PathVariable("id") UUID id){
        personService.DeletePersonById(id);
    }


    @PutMapping(path = "{id}")
    public void UpdatePersonById (@PathVariable("id") UUID id ,@Validated @NonNull @RequestBody person newPerson){
        personService.UpdatePersonById(id,newPerson);
    }

}
