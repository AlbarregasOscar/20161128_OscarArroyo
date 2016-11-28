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
@WebServlet(name = "ControladorOpciones2", urlPatterns = {"/ControladorOpciones2"})
public class ControladorOpciones2 extends HttpServlet {

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
        String parametro = request.getParameter("p");
        String url = "";
        String where = "";
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IAlumnosDAO adao = daof.getAlumnosDAO();
        ArrayList<Alumno> alumnos = adao.getAlumnos(where);
        request.setAttribute("alumnos", alumnos);
        IEquiposDAO edao = daof.getEquiposDAO();
        ArrayList<Equipo> equipos = edao.getEquipos(where);
        request.setAttribute("equipos", equipos);
        edao.closeConnection();
        adao.closeConnection();
        switch (parametro) {
            case "actualizarAlumnos":

                url = "actualizarAlumnos.jspx";
                break;
            case "actualizarEquipos":
                 ;
                url = "actualizarEquipos.jspx";
                break;
            case "eliminarAlumnos":
                url = "eliminarAlumnos.jspx";
                break;
            case "eliminarEquipos":
                url = "eliminarEquipos.jspx";
                break;
            case "insertarAlumnos":
                url = "insertarAlumnos.jspx";
                break;
            case "insertarEquipos":
                url = "insertarEquipos.jspx";
                break;
        }
        request.setAttribute("p", parametro);
        request.getRequestDispatcher("JSPX/" + url).forward(request, response);
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
