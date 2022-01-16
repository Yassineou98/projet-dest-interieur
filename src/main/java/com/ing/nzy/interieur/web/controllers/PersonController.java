package com.ing.nzy.interieur.web.controllers;

import com.ing.nzy.interieur.services.PersonService;
import com.ing.nzy.model.InfractionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@Controller
@RequestMapping("api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<?> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping("/recherchee")
    public ResponseEntity<?> getAllRecherchee() {
        return new ResponseEntity<>(personService.getAllRecherchee(), HttpStatus.OK);
    }

    @GetMapping("/{personId}/id")
    public ResponseEntity<?> getPersonById(@PathVariable UUID personId) {
        return new ResponseEntity<>(personService.getPersonInfoById(personId), HttpStatus.OK);
    }

    @GetMapping("/{cin}/cin")
    public ResponseEntity<?> getPersonByCin(@PathVariable String cin) {
        return new ResponseEntity<>(personService.getPersonInfoByCin(cin), HttpStatus.OK);
    }

    @PostMapping("/{cin}")
    public ResponseEntity<?> saveInfraction(@RequestBody InfractionDto infractionDto, @PathVariable String cin) {
        personService.addInfraction(cin, infractionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
