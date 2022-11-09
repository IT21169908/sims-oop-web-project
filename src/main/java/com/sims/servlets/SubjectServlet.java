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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sims.models.Subject;
import com.sims.services.SubjectService;
import com.sims.services.interfaces.SubjectInterface;

/**
 * Servlet implementation class Subject
 */
@WebServlet("/admin/subjects/*")
public class SubjectServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public SubjectServlet() {
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
         log("=================== SUBJECT MANAGER LOG: HANDLE GET AJAX REQUESTS ============================");
      } else {
         log("=================== SUBJECT MANAGER LOG: doGet START ============================");
         log("=================== ACTION: " + action);
         log("=================== PATH INFO: " + path_info);

         try {
            switch (path_info) {
               case "/admin/subjects":
                  SubjectInterface subjectService = new SubjectService();
                  ArrayList<Subject> subjectList = subjectService.all();

                  request.setAttribute("subjectList", subjectList);
                  RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/resources/views/admin/subjects/index.jsp");
                  dispatcher.forward(request, response);
                  break;
               case "/add":
                  doCreate(request, response);
                  break;
               case "/edit":
                  doEdit(request, response);
                  break;
               default:
                  break;
            }
         } catch (Exception ex) {
            throw new ServletException(ex);
         }

         log("==================== SUBJECT MANAGER LOG: doGet END =============================");
      }
   }

   private void doCreate(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      RequestDispatcher dispatcher = getServletContext()
            .getRequestDispatcher("/resources/views/admin/subjects/create.jsp");
      dispatcher.forward(request, response);
   }

   private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log("=================== SUBJECT MANAGER LOG: EDIT START ============================");
      int subject_id = Integer.parseInt(request.getParameter("subject"));
      log("Subject_id:" + request.getParameter("subject"));
      Subject subject = new Subject(subject_id);
      request.getSession().setAttribute("subject", subject);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/resources/views/admin/subjects/edit.jsp");
      dispatcher.forward(request, response);

      log("==================== SUBJECT MANAGER LOG: EDIT END =============================");
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      log("=================== SUBJECT MANAGER LOG: doPost START ============================");

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
               Subject subject = new Subject();

               subject.setCode(request.getParameter("subject_code"));
               subject.setTitle(request.getParameter("subject_title"));
               
               SubjectInterface subjectService = new SubjectService();

               boolean subjectCreated = subjectService.create(subject);

               if (subjectCreated) {
                  request.getSession().setAttribute("subject", subject);
                  request.getSession().setAttribute("success", "Your subject has been added successfully.!");
               } 
               else {
                  throw new Exception("Create Failed.!");
               }

            } catch (Exception e) {

               log("==================== SUBJECT MANAGER LOG: doPost Exception START =============================");
               log("Exception: " + e.getMessage());
               log("==================== SUBJECT MANAGER LOG: doPost Exception END =============================");

               errors.add(e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
               request.getSession().setAttribute("errors", errors);
            } 
            finally {
               log("====================== REDIRECTING =============================");
               response.sendRedirect(request.getContextPath() + "subjects/add");
            }            
            break;
      }

      log("==================== SUBJECT MANAGER LOG: doPost END =============================");
   }

   @Override
   protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      List<String> errors = new ArrayList<>();

      try {
         int subject_id = Integer.parseInt(request.getParameter("subject"));
         log("Subject_id:" + request.getParameter("subject"));
         Subject subject = new Subject(subject_id);

         subject.setCode(request.getParameter("subject_code"));
         subject.setTitle(request.getParameter("subject_title"));
         
         SubjectInterface subjectService = new SubjectService();

         boolean subjectUpdated = subjectService.update(subject);

         if (subjectUpdated) {
            request.getSession().setAttribute("subject", subject);
            request.getSession().setAttribute("success", "Your subject has been updated successfully.!");
         } 
         else {
            throw new Exception("Update Failed.!");
         }
      } 
      catch (Exception e) {

         log("==================== SUBJECT MANAGER LOG: doPut Exception START =============================");
         log("Exception: " + e.getMessage());
         log("==================== SUBJECT MANAGER LOG: doPut Exception END =============================");

         errors.add(e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
         request.getSession().setAttribute("errors", errors);
      } finally {
         log("====================== REDIRECTING =============================");
         response.sendRedirect(request.getContextPath() + "subjects/edit?subject=" + request.getParameter("subject"));
      }
   }

   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

      if (ajax) {
         log("=================== SUBJECT MANAGER LOG: doDelete START ============================");
         JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
         String subject_id = data.get("subject_id").getAsString();


         SubjectInterface subjectService = new SubjectService();

         boolean subjectDeleted;
         Map<String, String> options = new LinkedHashMap<>();
         
         try {
            subjectDeleted = subjectService.destroy(subject_id);
            
            if (subjectDeleted) {
               options.put("status", "deleted");
               options.put("icon", "success");
            } 
            else {
               options.put("status", "failed");
               options.put("icon", "error");
            }
         } 
         catch (Exception e) {
            e.printStackTrace();
            options.put("status", "failed");
            options.put("icon", "error");
            options.put("message", e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
         }

         String json = new Gson().toJson(options);

         resp.setContentType("application/json");
         resp.setCharacterEncoding("UTF-8");
         resp.getWriter().write(json);
         log("=================== SUBJECT MANAGER LOG: doDelete EXITING ============================");

      }

   }

}
