package com.evomatik.repository;

import com.evomatik.entitie.Persona;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class PersonaRepositoryTest {

    PersonaRepository personaRepository = new PersonaRepository("evomatik", "evo100518");

    @Test
    public void save() {
        Persona persona = new Persona();
        persona.setCurp("CAII960612HVZBBR02");
        persona.setNombres("Irvin Oswaldo");
        persona.setPaterno("Cabrera");
        persona.setMaterno("Ibañez");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.YEAR,1996);
        calendar.set(calendar.MONTH,5);
        calendar.set(calendar.DAY_OF_MONTH,12);
        persona.setFechaNacimiento(calendar.getTime());
        persona.setSexo("M");

        personaRepository.save(persona);
    }

    @Test
    public void update() {
        Persona persona = new Persona();
        persona.setCurp("CAII960612HVZBBR09");
        persona.setNombres("Irvin Oswaldo");
        persona.setPaterno("Cabrera");
        persona.setMaterno("Ibanez");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.YEAR,1996);
        calendar.set(calendar.MONTH,5);
        calendar.set(calendar.DAY_OF_MONTH,12);
        persona.setFechaNacimiento(calendar.getTime());
        persona.setSexo("M");

        personaRepository.update(persona);
    }

    @Test
    public void delete() {
        String curp = "CAII960612HVZBBR09";

        personaRepository.delete(curp);
    }

    @Test
    public void findByCurp() {
        Persona persona= personaRepository.findByCurp("CAII960612HVZBBR09");
        System.out.println("Se encontro: " + persona.getNombres() + " " + persona.getPaterno() + " " + persona.getMaterno());

    }

    @Test
    public void findAll() {
        List<Persona> personas = personaRepository.findAll();
        if (personas.size() > 0){
            System.out.println("Se encontraron: " + personas.size() + " " + (personas.size()>1?"personas":"persona") );
            for (Persona persona : personas) {
                System.out.println(persona.getNombres());
            }
        }
    }
}