package com.example.demo.service;
import com.example.demo.dao.PersonDao;
import com.example.demo.model.person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class personService {

    private final PersonDao personDao;

    @Autowired
    public personService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(person person){
        return personDao.insertPerson(person);
    }

    public List<person> SelectAllPerson(){
        return personDao.SelectAllPersons();
    }

    public Optional<person> getPersonById(UUID id){
        return personDao.getPersonById(id);
    }

    public int DeletePersonById(UUID id){
        return personDao.DeletePersonById(id);
    }
    public int UpdatePersonById(UUID id,person newPerson){
        return  personDao.UpdatePersonById(id,newPerson);
    }
}
