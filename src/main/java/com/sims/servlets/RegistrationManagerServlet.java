package com.sims.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sims.models.User;
import com.sims.services.RegistrationManagerService;
import com.sims.services.interfaces.RegistrationManagerInterface;

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
                        .getRequestDispatcher("/resources/views/admin/users/index.jsp");
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
               .getRequestDispatcher("/resources/views/admin/users/edit.jsp");
         dispatcher.forward(request, response);
      }
   }

   private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log("=================== REGISTRATION MANAGER LOG: SHOW REGISTER PAGE START ============================");
      RequestDispatcher dispatcher = getServletContext()
            .getRequestDispatcher("/resources/views/admin/users/register-user.jsp");
      dispatcher.forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      log("=================== REGISTRATION MANAGER LOG: doPost START ============================");

      String _METHOD = request.getParameter("_method") == null ? request.getMethod().toLowerCase()
            : request.getParameter("_method").toLowerCase();

      log("=================== REQUEST _METHOD: " + _METHOD);
      switch (_METHOD) {
         case "put":
            doPut(request, response);
            break;
         default:
            List<String> errors = new ArrayList<>();

            try {
               User user = new User();
                
               String password = request.getParameter("password");
               String confirm_password = request.getParameter("confirm-password");
               if(!password.equals(confirm_password)) {
                  throw new Exception("Confirmation password mismatch.!");
               }
               
               String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                
               user.setName(request.getParameter("name")); 
               user.setGender(request.getParameter("gender")); 
               user.setDob(request.getParameter("dob")); 
               user.setMobile_number(request.getParameter("mobile_number")); 
               user.setEmail(request.getParameter("email")); 
               user.setNic(request.getParameter("nic")); 
               user.setPassword(hashedPassword); 
               user.setType(request.getParameter("type")); 

               RegistrationManagerInterface registerService = new RegistrationManagerService();

               boolean result = registerService.create(user);

               if (result) {
                  request.getSession().setAttribute("addeduser", user);
                  request.getSession().setAttribute("success", "user registration has been successfully.!");
               } 

            } catch (Exception e) {

               log("==================== REGISTRATION MANAGER LOG: doPost Exception START =============================");
               log("Exception: " + e.getMessage());
               log("==================== REGISTRATION MANAGER LOG: doPost Exception END =============================");

               errors.add(e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
               request.getSession().setAttribute("errors", errors);
            } finally {
               log("====================== REDIRECTING =============================");
               response.sendRedirect(request.getContextPath() + "users/register");
            }
            break;
      }

      log("==================== REGISTRATION MANAGER LOG: doPost END =============================");
   }

   @Override
   protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      List<String> errors = new ArrayList<>();

      try {
         int user_id = Integer.parseInt(request.getParameter("id"));
         log("User_id:" + request.getParameter("id"));
         User user = new User(user_id);

         user.setName(request.getParameter("name"));
         user.setGender(request.getParameter("gender"));
         user.setDob(request.getParameter("dob"));
         user.setMobile_number(request.getParameter("mobile_number"));

         RegistrationManagerService registerService = new RegistrationManagerService();

         boolean result = registerService.update(user);

         if (result) {
            request.getSession().setAttribute("editUser", user);
            request.getSession().setAttribute("success", "User has been updated successfully.!");
         } 
      } catch (Exception e) {

         log("==================== REGISTRATION MANAGER LOG: doPut Exception START =============================");
         log("Exception: " + e.getMessage());
         log("==================== REGISTRATION MANAGER LOG: doPut Exception END =============================");

         errors.add(e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
         request.getSession().setAttribute("errors", errors);
      } finally {
         log("====================== REDIRECTING =============================");
         response.sendRedirect(request.getContextPath() + "users/edit?user=" + request.getParameter("id"));
      }
   }

   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

      if (ajax) {
         log("=================== REGISTRATION MANAGER LOG: doDelete START ============================");
         JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
         int user_id = Integer.parseInt(data.get("user_id").getAsString());

         RegistrationManagerInterface registerService = new RegistrationManagerService();
 
         Map<String, String> options = new LinkedHashMap<>();

         try {
            boolean result = registerService.destroy(user_id);

            if (result) {
               options.put("status", "deleted");
               options.put("icon", "success");
            } else {
               options.put("status", "failed");
               options.put("icon", "error");
            }
         } catch (Exception e) {
            e.printStackTrace();
            options.put("status", "failed");
            options.put("icon", "error");
            options.put("message",
                  e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
         }

         String json = new Gson().toJson(options);

         resp.setContentType("application/json");
         resp.setCharacterEncoding("UTF-8");
         resp.getWriter().write(json);
         log("=================== REGISTRATION MANAGER LOG: doDelete EXITING ============================");

      }

   }

}
