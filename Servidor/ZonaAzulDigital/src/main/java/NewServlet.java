/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.zonaAzulDigital.Excecao.DaoException;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.entidades.Placa;
import com.zonaAzulDigital.DAO.DaoCartaoZonaAzulBD;
import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.DAO.DaoPlacaBD;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JonasJr
 */
public class NewServlet extends HttpServlet {

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
        DAOPlaca daoPlaca = new DaoPlacaBD();
        Placa placa = new Placa();
        placa.setLetras("KHX");
        placa.setNumeros("0066");
        try {
            daoPlaca.cadastrar(placa);
        } catch (DaoException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Placa p1 = new Placa();
        try {
            p1 = daoPlaca.recuperar(placa.getLetras(),placa.getNumeros());
        } catch (DaoException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Motorista motorista = new Motorista();
        motorista.setCpf(10654901430L);
        motorista.setNome("Jonas Ferreira Leal Junior");
        motorista.setSenha("1234");
        motorista.setCredito(new BigDecimal(1000));
        DAOMotorista daoMotorista = new DaoMotoristaBD();
        Motorista m1 = new Motorista();
        try {
            daoMotorista.cadastrar(motorista);
            m1 = daoMotorista.recuperar(motorista.getCpf());
        } catch (DaoException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CartaoZonaAzul cartaoZonaAzul = new CartaoZonaAzul(p1);
        DAOCartaoZonaAzul daoCartaoZonaAzul = new  DaoCartaoZonaAzulBD();
        CartaoZonaAzul c1 = new CartaoZonaAzul();
        try {
            daoCartaoZonaAzul.cadastrar(cartaoZonaAzul);
            c1 = daoCartaoZonaAzul.recuperarUltimo(placa);
        } catch (DaoException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        System.out.println(placa);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + p1.toString() + "</br>"+ m1.getCpf() + "</br>"+ c1.getData() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
