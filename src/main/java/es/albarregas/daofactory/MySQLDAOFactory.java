package es.albarregas.daofactory;

import es.albarregas.dao.EquiposDAO;
import es.albarregas.dao.IAlumnosDAO;
import es.albarregas.dao.IEquiposDAO;
import es.albarregas.dao.MysqlAlumnosDAO;


public class MySQLDAOFactory extends DAOFactory{

    public IAlumnosDAO getAlumnosDAO() {
        return new MysqlAlumnosDAO();
    }
    public IEquiposDAO getEquiposDAO() {
        return new EquiposDAO();
    }
}
