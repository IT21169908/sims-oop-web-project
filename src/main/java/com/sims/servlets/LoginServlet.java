package com.sims.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sims.models.User;
import com.sims.services.AuthService;
import com.sims.services.ValidationService;
import com.sims.services.interfaces.AuthInterface;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public LoginServlet() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
      String action = request.getServletPath();
      String pathInfo = request.getPathInfo() == null ? action : request.getPathInfo();

      // If a logged user tries to access the login page then redirect to the exact
      // dashboard
      if (request.getSession(false) != null) {
         User user = (User) request.getSession(false).getAttribute("user");

         if (user != null) {
            String userType = user.getType();
            log("====================== LOGIN LOG: logged user redirecting to " + userType
                  + "-dashboard =============================");
            response.sendRedirect(request.getContextPath() + userType + "/dashboard");
            return;
         }
      }

      if (ajax) {
         log("=================== LOGIN LOG: HANDLE GET AJAX REQUESTS ============================");
      } else {
         log("=================== LOGIN LOG: doGet START ============================");
         log("=================== ACTION: " + action);
         log("=================== PATH INFO: " + pathInfo);

         try {
            switch (pathInfo) {
               case "/login":
                  RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/resources/views/auth/index.jsp");
                  dispatcher.forward(request, response);
                  break;
               default:
                  break;
            }
         } catch (Exception ex) {
            throw new ServletException(ex);
         }

         log("==================== LOGIN LOG: doGet END =============================");
      }

   }// End of doGet()

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      log("=================== LOGIN LOG: doPost START ============================");

      String email = request.getParameter("email");
      String password = request.getParameter("password");

      // Validate email and password
      List<String> errors = ValidationService.loginValidator(email, password);

      // If errors found from loginValidator then redirect back to login with errors
      if (errors != null && !errors.isEmpty()) {
         request.setAttribute("email", email);
         request.setAttribute("password", password);
         request.setAttribute("errors", errors);
         request.getSession().setAttribute("errors", errors);
         response.sendRedirect(request.getContextPath() + "login");
         return;
      }

      try {
         // Validate and get user by email and password
         AuthInterface authService = new AuthService();
         User user = authService.userLoginByEmail(email, password);
         String userType = user.getType();

         // If user not found then redirect back to login with errors
         if (user == null || user.getId() <= 0) {
            errors.add("The Username or Password is incorrect..!");
            request.getSession().setAttribute("errors", errors);
            response.sendRedirect(request.getContextPath() + "login");
            return;
//            request.getRequestDispatcher("login").forward(request, response);
         }

         log("==================== LOGIN LOG: set session user =============================");
         // Creating a session
         HttpSession session = request.getSession();
         // If user found set user details to session attribute
         session.setAttribute("user", user);
         
         log(userType);
         // Redirect to dashboard by user type
         if (userType == null) {
            log("====================== LOGIN LOG: redirecting to dashboard =============================");
            response.sendRedirect(request.getContextPath() + "dashboard");
            return;
         } else {
            log("====================== LOGIN LOG: redirecting to " + userType
                  + "-dashboard =============================");
            response.sendRedirect(request.getContextPath() + userType + "/dashboard");
            return;
         }

      } catch (IOException e1) {
         // e1.printStackTrace();
         log("==================== LOGIN LOG: doPost Exception START =============================");
         log("Exception: " + e1.getMessage());
         log("==================== LOGIN LOG: doPost Exception END =============================");

         errors.add("Something went wrong! Please try again.!");
         request.getSession().setAttribute("errors", errors);
      } catch (Exception e2) {
         // e2.printStackTrace();
         log("==================== LOGIN LOG: doPost Exception START =============================");
         log("Exception: " + e2.getMessage());
         log("==================== LOGIN LOG: doPost Exception END =============================");

         errors.add("Something went wrong! Please try again.!");
         request.getSession().setAttribute("errors", errors);
      }

      log("==================== LOGIN LOG: doPost END =============================");

   }// End of doPost()

}
