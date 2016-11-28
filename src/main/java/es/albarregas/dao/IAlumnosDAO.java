/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Alumno;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public interface IAlumnosDAO {
    
    public void addAlumno(Alumno a);
    public ArrayList<Alumno> getAlumnos(String where);
    public ArrayList<Alumno> getAlumnosEquipo();
    public void closeConnection();
    public void updateAlumno(Alumno a);
    public void deleteAlumno(String clausulaWhere);
    
}
