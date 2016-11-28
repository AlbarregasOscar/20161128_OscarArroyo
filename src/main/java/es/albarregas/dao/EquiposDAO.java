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
public class EquiposDAO implements IEquiposDAO {

    Statement sentencia;
    ResultSet resultado;
    PreparedStatement preparada;

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    @Override
    public void updateEquipo(Equipo e) {
        try {
            String sql = "update equipos set marca=?,numserie=? where idEquipo=?";
            preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1, e.getMarca());
            preparada.setString(2, e.getNumSerie());
            preparada.setInt(3, e.getIdEquipo());
            preparada.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteEquipo(String clausulaWhere) {
        try {
            String sql = "delete from equipos " + clausulaWhere;
            sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Equipo> getEquipos(String where) {
        ArrayList<Equipo> lista = new ArrayList();
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery("Select * from equipos " + where);
            while (resultado.next()) {
                Equipo equipo = new Equipo();
                equipo.setIdEquipo(resultado.getInt("idEquipo"));
                equipo.setMarca(resultado.getString("marca"));
                equipo.setNumSerie(resultado.getString("numSerie"));
                lista.add(equipo);
            }
            resultado.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
