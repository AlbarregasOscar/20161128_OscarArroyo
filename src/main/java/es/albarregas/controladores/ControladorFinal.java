/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controladores;

import es.albarregas.beans.Alumno;
import es.albarregas.beans.Equipo;
import es.albarregas.dao.IAlumnosDAO;
import es.albarregas.dao.IEquiposDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "ControladorFinal", urlPatterns = {"/ControladorFinal"})
public class ControladorFinal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IAlumnosDAO adao = daof.getAlumnosDAO();
        IEquiposDAO edao = daof.getEquiposDAO();
        if (request.getParameter("cancelar") != null) {
            url = "index.jspx";
        } else if (request.getParameter("actualizarAlumnos") != null) {
            String where = "where idAlumno =" + request.getParameter("idAlu");
            ArrayList<Alumno> alumnos = adao.getAlumnos(where);
            request.setAttribute("modi", alumnos);
            url = "/JSPX/actAluFinal.jspx";
            adao.closeConnection();
        } else if (request.getParameter("actualizar") != null) {
            Alumno alu = new Alumno();
            alu.setIdAlumno(Integer.parseInt(request.getParameter("idAlumno")));
            alu.setNombre(request.getParameter("nombre"));
            alu.setGrupo(request.getParameter("grupo"));
            adao.updateAlumno(alu);
            url = "index.jspx";
            adao.closeConnection();
        } else if (request.getParameter("actualizarEquipos") != null) {
            String where = " where idEquipo =" + request.getParameter("idEquipo");
            ArrayList<Equipo> equipos = edao.getEquipos(where);
            request.setAttribute("modiEq", equipos);
            url = "/JSPX/actEquiFinal.jspx";
        } else if (request.getParameter("actEquipo") != null) {
            Equipo eq = new Equipo();
            eq.setIdEquipo(Integer.parseInt(request.getParameter("idEquipo")));
            eq.setMarca(request.getParameter("marca"));
            eq.setNumSerie(request.getParameter("numserie"));
            edao.updateEquipo(eq);
            url = "index.jspx";
            edao.closeConnection();
        } else if (request.getParameter("borrarAlumnos") != null) {
            String[] listado = request.getParameterValues("registro");
            // Construimos la clausula where de la forma where anilla in ('a1','a2')
            StringBuilder clausulaWhere = new StringBuilder(" where idAlumno in (");
            for (String idAlumno : listado) {
                clausulaWhere.append("'");
                clausulaWhere.append(idAlumno);
                clausulaWhere.append("',");
            }
            clausulaWhere.replace(clausulaWhere.length() - 1, clausulaWhere.length(), ")");
            adao.deleteAlumno(clausulaWhere.toString());
            url = "index.jspx";
            adao.closeConnection();
        } else if (request.getParameter("borrarEquipos") != null) {
            String[] listado = request.getParameterValues("registro");
            StringBuilder clausulaWhere = new StringBuilder(" where idEquipo in (");
            for (String idEquipo : listado) {
                clausulaWhere.append("'");
                clausulaWhere.append(idEquipo);
                clausulaWhere.append("',");
            }
            clausulaWhere.replace(clausulaWhere.length() - 1, clausulaWhere.length(), ")");
            edao.deleteEquipo(clausulaWhere.toString());
            url = "index.jspx";
            edao.closeConnection();
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
