package com.example.demo.dao;

import com.example.demo.model.person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class fakePersonDataAccess implements PersonDao{

    private static List<person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, person person) {
        DB.add(new person(id,person.getName()));
        return 1;
    }

    @Override
    public List<person> SelectAllPersons() {
        return DB;
    }

    @Override
    public Optional<person> getPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int DeletePersonById(UUID id) {
        Optional<person> personMayBe=getPersonById(id);
        if (personMayBe.isEmpty())
            return 0;

        DB.remove(personMayBe.get());
        return 1;
    }

    @Override
    public int UpdatePersonById(UUID id, person person) {
        return getPersonById(id).map(p -> {
            int indexToUpdate = DB.indexOf(p);
            if (indexToUpdate >= 0){
                DB.set( indexToUpdate, new person(id, person.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }
}
