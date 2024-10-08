/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanpq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tuanpq.cart.CartBean;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "removeItemFromCartServlet", urlPatterns = {"/removeItemFromCartServlet"})
public class removeItemFromCartServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String urlRewriting ="cartAction?btAction=View+cart";
        try {
            //1. cus goes to his/her carts
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. cus takes his/her cart
                CartBean cart = (CartBean)session.getAttribute("CART");
                if (cart != null) {
                    //3. cus gets items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. cust choose item to remove
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if (selectedItems != null) {
                            for (String item : selectedItems) {
                                cart.removeItemToCart(item);
                            }//each item is processed
                            session.setAttribute("CART", cart);
                        }
                    }
                }
            }
        } finally {
           response.sendRedirect(urlRewriting);
           out.close();
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
