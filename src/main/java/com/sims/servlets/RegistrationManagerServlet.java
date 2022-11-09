package com.sims.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sims.models.Subject;
import com.sims.models.User;
import com.sims.services.RegistrationManagerService;
import com.sims.services.SubjectService;
import com.sims.services.interfaces.RegistrationManagerInterface;
import com.sims.services.interfaces.SubjectInterface;

/**
 * Servlet implementation class RegistrationManager
 */
@WebServlet(asyncSupported = true, description = "Register Users to the system", urlPatterns = {
      "/admin/users/*" })
public class RegistrationManagerServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public RegistrationManagerServlet() {
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
      String path_info = request.getPathInfo() == null ? action : request.getPathInfo();
      if (ajax) {
         log("=================== REGISTRATION MANAGER LOG: HANDLE GET AJAX REQUESTS ============================");
      } else {
         log("=================== REGISTRATION MANAGER LOG: doGet START ============================");
         log("=================== ACTION: " + action);
         log("=================== PATH INFO: " + path_info);

         try {
            switch (path_info) {
               case "/admin/users":
                  log("=================== REGISTRATION MANAGER LOG: LIST USERS START ============================");

                  RegistrationManagerInterface RegistrationService = new RegistrationManagerService();
                  ArrayList<User> userList = RegistrationService.all();
                  request.setAttribute("userList", userList);

                  RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/resources/views/admin/student-list.jsp");
                  dispatcher.forward(request, response);
                  break;
               case "/register":
                  create(request, response);
                  break;
               case "/edit":
                  edit(request, response);
                  break;
               default:
                  break;
            }
         } catch (Exception ex) {
            throw new ServletException(ex);
         }

         log("==================== REGISTRATION MANAGER LOG: doGet END =============================");
      }
   }

   private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log("=================== REGISTRATION MANAGER LOG: EDIT START ============================");
      List<String> errors = new ArrayList<>();

      if (request.getParameter("user") == null) {
         errors.add("Invalid request.!");
         request.getSession().setAttribute("errors", errors);
         response.sendRedirect(request.getHeader("referer"));
      } else {
         int user_id = Integer.parseInt(request.getParameter("user"));
         log("suser_id:" + request.getParameter("user"));
         User user = new User(user_id);
         request.getSession().setAttribute("editUser", user);
         RequestDispatcher dispatcher = getServletContext()
               .getRequestDispatcher("/resources/views/admin/edit-student.jsp");
         dispatcher.forward(request, response);
      } 
   }

   private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log("=================== REGISTRATION MANAGER LOG: SHOW REGISTER PAGE START ============================");
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/resources/views/admin/add-student.jsp");
      dispatcher.forward(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

   /**
    * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
    */
   protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
   }

   /**
    * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
    */
   protected void doDelete(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
   }

   /**
    * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
    */
   protected void doHead(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
   }

   /**
    * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
    */
   protected void doOptions(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
   }

   /**
    * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
    */
   protected void doTrace(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
   }

}
