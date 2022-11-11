package com.sims.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.sims.models.User;
import com.sims.services.AuthService;
import com.sims.services.UserService;
import com.sims.services.interfaces.AuthInterface;
import com.sims.services.interfaces.UserInterface;

/**
 * Servlet implementation class AdminProfileServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/profile/*" })
public class AdminProfileServlet extends HttpServlet {
   
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public AdminProfileServlet() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      RequestDispatcher dispatcher = getServletContext()
            .getRequestDispatcher("/resources/views/admin/profile.jsp");
      dispatcher.forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log("===================PERIOD LOG: doPost START============================");

      List<String> errors = new ArrayList<>();

      try {
         User loggedUser = null;
         try {
           loggedUser = (User) request.getSession(false).getAttribute("user"); 
         } catch (Exception e) {
            // TODO: handle exception
         } finally {
            if (loggedUser == null || loggedUser.getId() <= 0) {
               errors.add("Please logged in first");
               response.sendRedirect(request.getContextPath() + "/login");
               return;
            }
         } 
         
         String password = request.getParameter("current_password");
         String new_password = request.getParameter("new_password");
         String confirm_password = request.getParameter("confirm_password");
         
         AuthInterface authService = new AuthService();
         User user = authService.userLoginByEmail(loggedUser.getEmail(), password);
         
         if (user == null || user.getId() <= 0) {
            throw new Exception("current password is not correct!"); 
         }
           
         if(!new_password.equals(confirm_password)) {
            throw new Exception("Confirmation password mismatch.!");
         }
         
         String hashedPassword = BCrypt.hashpw(new_password, BCrypt.gensalt()); 
         user.setPassword(hashedPassword);  
 
         UserInterface UserService = new UserService();
         boolean result = UserService.changePassword(user);

         if (result) { 
            request.getSession().setAttribute("success", "Password changed successfully.!");
         } else {
            throw new Exception("Password changed Failed.!");
         }

      } catch (Exception e) {

         log("====================PERIOD LOG: doPost Exception START=============================");
         log("Exception: " + e.getMessage());
         log("====================PERIOD LOG: doPost Exception END=============================");

         errors.add(e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
         // request.setAttribute("errors", errors);
         request.getSession().setAttribute("errors", errors);
      } finally {
         log("======================REDIRECTING=============================");
         response.sendRedirect(request.getContextPath() + "/admin/profile");
      }
   }

}
