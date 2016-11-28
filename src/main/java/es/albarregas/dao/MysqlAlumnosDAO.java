/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Alumno;
import es.albarregas.beans.Equipo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class MysqlAlumnosDAO implements IAlumnosDAO {

    Statement sentencia;
    ResultSet resultado;
    PreparedStatement preparada;

    @Override
    public void addAlumno(Alumno a) {
        try {
            String sql = "insert into alumnos (nombre,grupo,idEquipo) values (?,?,?)";
            preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1, a.getNombre());
            preparada.setString(2, a.getGrupo());
            preparada.setInt(3, a.getIdEquipo().getIdEquipo());
            preparada.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Alumno> getAlumnos(String where) {
        ArrayList<Alumno> lista = new ArrayList();
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery("Select * from alumnos " + where);
            while (resultado.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(resultado.getInt("idAlumno"));
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setGrupo(resultado.getString("grupo"));
                lista.add(alumno);
            }
            resultado.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public ArrayList<Alumno> getAlumnosEquipo() {
        ArrayList<Alumno> lista = new ArrayList();
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery("Select nombre,marca,numSerie from alumnos left join equipos using(idEquipo)");
            while (resultado.next()) {
                Alumno alumno = new Alumno();
                Equipo equipo = new Equipo();
                equipo.setMarca(resultado.getString("marca"));
                equipo.setNumSerie(resultado.getString("numSerie"));
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setIdEquipo(equipo);
                lista.add(alumno);
            }
            resultado.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;

    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    @Override
    public void updateAlumno(Alumno a) {
        try {
            String sql = "update alumnos set nombre=?,grupo=? where idAlumno=?";
            preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1, a.getNombre());
            preparada.setString(2, a.getGrupo());
            preparada.setInt(3, a.getIdAlumno());
            preparada.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteAlumno(String clausulaWhere) {
        try {
            String sql = "delete from alumnos " + clausulaWhere;
            sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
