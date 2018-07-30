package com.evomatik.repository;

import com.evomatik.jdbc.JDBCConnection;
import com.evomatik.entitie.Persona;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para establecer operacion CRUD a la tabla <b>CS_PERSONA</b>
 */
public class PersonaRepository {

    private JDBCConnection connection = null;
    private final String INSERT = "INSERT INTO cs_persona(curp, nombres, paterno, materno, fecha_nacimiento, sexo) VALUES (?,?,?,?,?,?)";
    private final String DELETE = "DELETE FROM cs_persona where curp = ?";
    private final String SELECT = "SELECT * FROM cs_persona where curp = ?";
    private final String SELECTALL = "SELECT * FROM cs_persona";
    private final String UPDATE = "UPDATE cs_persona SET nombres = ?, paterno = ?, materno = ?, fecha_nacimiento = ?, sexo = ? where curp = ?";

    /**
     * Constructor default para el cual inicializan las conexiones para operaciones posteriores
     * @param usuario nombre del usuario de la base de datos
     * @param clave clave de acceso del usuario a la base de datos
     */
    public PersonaRepository(String usuario, String clave) {
        connection = new JDBCConnection("jdbc:postgresql://localhost/postgres", usuario, clave, false);
        System.out.println("Conexion establecida");
    }

    /**
     * Metodo encargado de registrar una persona en la base de datos
     * @param persona
     * @return persona
     */
    public Persona save(Persona persona) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getConnection().prepareStatement(INSERT);
            preparedStatement.setString(1, persona.getCurp());
            preparedStatement.setString(2, persona.getNombres());
            preparedStatement.setString(3, persona.getPaterno());
            preparedStatement.setString(4, persona.getMaterno());
            preparedStatement.setDate(5, new Date(persona.getFechaNacimiento().getTime()));
            preparedStatement.setString(6, persona.getSexo());
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Persona guardada con exito");

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            return persona;
        }
    }

    public void update(Persona persona){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getConnection().prepareStatement(UPDATE);
            preparedStatement.setString(1, persona.getNombres());
            preparedStatement.setString(2, persona.getPaterno());
            preparedStatement.setString(3, persona.getMaterno());
            preparedStatement.setDate(4, new Date(persona.getFechaNacimiento().getTime()));
            preparedStatement.setString(5, persona.getSexo());
            preparedStatement.setString(6, persona.getCurp());
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Actualizo la persona exitosamente");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String curp){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.getConnection().prepareStatement(DELETE);
            preparedStatement.setString(1, curp);
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Persona eliminada con exito");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Persona findByCurp(String curp){
        PreparedStatement preparedStatement = null;
        Persona persona = new Persona();
        try {
            preparedStatement = connection.getConnection().prepareStatement(SELECT);
            preparedStatement.setString(1, curp);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                persona.setNombres(resultSet.getString("nombres"));
                persona.setPaterno(resultSet.getString("paterno"));
                persona.setMaterno(resultSet.getString("materno"));
                persona.setSexo(resultSet.getString("sexo"));
                persona.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            return persona;
        }
    }

    public List<Persona> findAll(){
        PreparedStatement preparedStatement = null;
        List<Persona> personas = new ArrayList<Persona>();
        try {
            preparedStatement = connection.getConnection().prepareStatement(SELECTALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Persona persona = new Persona();
                persona.setNombres(resultSet.getString("nombres"));
                persona.setPaterno(resultSet.getString("paterno"));
                persona.setMaterno(resultSet.getString("materno"));
                persona.setSexo(resultSet.getString("sexo"));
                persona.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                persona.setCurp(resultSet.getString("curp"));
                personas.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            return personas;
        }
    }
}
