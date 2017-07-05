/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.zonaAzulDigital.DAO.DaoCartaoZonaAzulBD;
import com.zonaAzulDigital.DAO.DaoCompraCartaoZADB;
import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.DAO.DaoPlacaBD;
import com.zonaAzulDigital.entidades.CartaoZonaAzul;
import com.zonaAzulDigital.entidades.Motorista;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;
import com.zonaAzulDigital.model.ModelCartaoZonaAzul;
import com.zonaAzulDigital.model.ModelMotorista;
import java.io.IOException;
import java.io.PrintWriter;
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
public class testaComprarCartao extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        DaoMotoristaBD daoMotoristaBD = new DaoMotoristaBD();
        ModelCartaoZonaAzul modelCartaoZonaAzul = new ModelCartaoZonaAzul(daoMotoristaBD, new DaoCartaoZonaAzulBD(),
                new DaoCompraCartaoZADB(), new DaoPlacaBD());
        List<CartaoZonaAzul> cartaoZonaAzuls = null;
        try {

            Motorista motorista = daoMotoristaBD.recuperar("10654901430");
            cartaoZonaAzuls = modelCartaoZonaAzul.CartoesAtivosPor(motorista);
        } catch (Exception e) {
            Logger.getLogger(testaComprarCartao.class.getName()).log(Level.SEVERE, null, e);
        }
        try (PrintWriter out = response.getWriter()) {


            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testaComprarCartao</title>");
            out.println("</head>");
            out.println("<body>");
            for (CartaoZonaAzul cartaoZonaAzul : cartaoZonaAzuls) {
                            out.println("<h1>Placa: " + cartaoZonaAzul.getPlaca()+ "</h1><br/>");
                            out.println("<h1>Data Compra: " + cartaoZonaAzul.getDataInicio() + "</h1><br/>");
                            out.println("<h1>Tempo restante: " + cartaoZonaAzul.getTempoRestante() + "</h1><br/>");

            }
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
