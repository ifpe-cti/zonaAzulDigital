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
import com.zonaAzulDigital.DAO.DaoCompraCartaoZADB;
import com.zonaAzulDigital.DAO.DaoMotoristaBD;
import com.zonaAzulDigital.DAO.DaoPlacaBD;
import com.zonaAzulDigital.Excecao.CpfException;
import com.zonaAzulDigital.Excecao.LoginException;
import com.zonaAzulDigital.Excecao.MotoristaException;
import com.zonaAzulDigital.Excecao.PlacaException;
import com.zonaAzulDigital.entidades.CartaoZonaAzulInfo;
import com.zonaAzulDigital.interfaces.DAOCartaoZonaAzul;
import com.zonaAzulDigital.interfaces.DAOMotorista;
import com.zonaAzulDigital.interfaces.DAOPlaca;
import com.zonaAzulDigital.interfaces.ModelCartaoZonaAzulInterface;
import com.zonaAzulDigital.interfaces.ModelMotoristaInterface;
import com.zonaAzulDigital.model.ModelCartaoZonaAzul;
import com.zonaAzulDigital.model.ModelMotorista;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
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
//        Placa placa = new Placa();
//        placa.setLetras("KHX");
//        placa.setNumeros("0066");
//        try {
//            daoPlaca.cadastrar(placa);
//        } catch (DaoException ex) {
//            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        Placa p1 = new Placa();
//        try {
//            p1 = daoPlaca.recuperar(placa.getLetras(), placa.getNumeros());
//        } catch (DaoException ex) {
//            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }

        Motorista motorista = new Motorista();
        motorista.setCpf("10654901430");
        motorista.setNome("Jonas Ferreira Leal Junior");
        motorista.setSenha("1234");
        motorista.setCredito(new BigDecimal(1000));
        ModelMotoristaInterface modelMotorista = new ModelMotorista(new DaoMotoristaBD());
        Motorista m1 = new Motorista();
        String credAnt = "";
        try {
            modelMotorista.cadastrar(motorista);
            m1 = modelMotorista.login(motorista.getCpf(), motorista.getSenha());

            credAnt = m1.getCredito().toString();
        } catch (DaoException | LoginException | CpfException | MotoristaException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        DAOCartaoZonaAzul daoZonaAzul = new DaoCartaoZonaAzulBD();
        try {
            CartaoZonaAzulInfo cartaoZonaAzulInfoInfo = new CartaoZonaAzulInfo();
            cartaoZonaAzulInfoInfo.setCidade("Garanhuns");
            cartaoZonaAzulInfoInfo.setPreco(new BigDecimal(2));
            daoZonaAzul.cadastrar(cartaoZonaAzulInfoInfo);
        } catch (Exception ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelCartaoZonaAzulInterface modelCartaoZonaAzul = new ModelCartaoZonaAzul(new DaoMotoristaBD(), new DaoCartaoZonaAzulBD(),
                new DaoCompraCartaoZADB(), new DaoPlacaBD());
        CartaoZonaAzul novoCartao = new CartaoZonaAzul();
        try {
            novoCartao = modelCartaoZonaAzul.comprar(m1, new Placa("KHX", "0066"));
        } catch (MotoristaException | DaoException| PlacaException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            m1 = modelMotorista.recuperar(m1);
        } catch (CpfException | DaoException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"
                    + "Motorista: " + m1.getNome() + "<br/>"
                    + "Placa no cartão: " + novoCartao.getPlaca() + "<br/>"
                    + "Data/Hora: " + novoCartao.getData().toString() + "<br/>"
                    + "Creditos anteriores: " + credAnt + "<br/>"
                    + "Creditos atuais: " + m1.getCredito().toString() + "<br/>"
                    + "</h1>");
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
