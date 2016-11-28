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
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

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
        DAOFactory daof = DAOFactory.getDAOFactory(1);
        IAlumnosDAO adao = daof.getAlumnosDAO();
        IEquiposDAO edao = daof.getEquiposDAO();
        String where = "";
        String url = "";
        if (request.getParameter("cancelar") != null) {
            url = "index.jspx";
        } else if (request.getParameter("confirmar") != null) {
            Alumno alu = new Alumno();
            Equipo eq = new Equipo();
            alu.setNombre(request.getParameter("nombre"));
            alu.setGrupo(request.getParameter("grupo"));
            eq.setIdEquipo(Integer.parseInt(request.getParameter("idEquipo")));
            alu.setIdEquipo(eq);
            adao.addAlumno(alu);
            url = "index.jspx";
            adao.closeConnection();

        } else if (request.getParameter("equipos") != null) {
            ArrayList<Alumno> aluEq = adao.getAlumnosEquipo();
            request.setAttribute("aluEq", aluEq);
            url = "JSPX/visualizarEquipo.jspx";
            adao.closeConnection();
        } else if (request.getParameter("todos") != null || request.getParameter("numero").equals("")) {
            ArrayList<Alumno> alumnos = adao.getAlumnos(where);
            request.setAttribute("alumnos", alumnos);
            url = "JSPX/salidaVisualizar.jspx";
            adao.closeConnection();
        } else if (!request.getParameter("numero").equals("")) {
            where = "limit " + request.getParameter("numero");
            ArrayList<Alumno> alumnos = adao.getAlumnos(where);
            request.setAttribute("alumnos", alumnos);
            url = "JSPX/salidaVisualizar.jspx";
            adao.closeConnection();
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
