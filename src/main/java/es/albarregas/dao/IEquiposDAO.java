/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Alumno;
import es.albarregas.beans.Equipo;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public interface IEquiposDAO {
    public ArrayList<Equipo> getEquipos(String where);
    public void closeConnection();
    public void updateEquipo(Equipo e);
    public void deleteEquipo(String clausulaWhere);
    
}
