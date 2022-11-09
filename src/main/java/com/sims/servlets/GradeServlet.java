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
import com.sims.models.Grade;
import com.sims.services.GradeService;
import com.sims.services.interfaces.GradeInterface;

/**
 * Servlet implementation class Grade
 */
@WebServlet("/admin/grades/*")
public class GradeServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public GradeServlet() {
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
         log("=================== GRADE MANAGER LOG: HANDLE GET AJAX REQUESTS ============================");
      } else {
         log("=================== GRADE MANAGER LOG: doGet START ============================");
         log("=================== ACTION: " + action);
         log("=================== PATH INFO: " + path_info);

         try {
            switch (path_info) {
               case "/admin/grades":
                  GradeInterface gradeService = new GradeService();
                  ArrayList<Grade> gradeList = gradeService.all();

                  request.setAttribute("gradeList", gradeList);
                  RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/resources/views/admin/grades/index.jsp");
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

         log("==================== GRADE MANAGER LOG: doGet END =============================");
      }
   }

   private void doCreate(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      RequestDispatcher dispatcher = getServletContext()
            .getRequestDispatcher("/resources/views/admin/grades/create.jsp");
      dispatcher.forward(request, response);
   }

   private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log("=================== GRADE MANAGER LOG: EDIT START ============================");
      int grade_id = Integer.parseInt(request.getParameter("grade"));
      log("Grade_id:" + request.getParameter("grade"));
      Grade grade = new Grade(grade_id);
      request.getSession().setAttribute("grade", grade);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/resources/views/admin/grades/edit.jsp");
      dispatcher.forward(request, response);

      log("==================== GRADE MANAGER LOG: EDIT END =============================");
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      log("=================== GRADE MANAGER LOG: doPost START ============================");

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
               Grade grade = new Grade();

               grade.setTitle(request.getParameter("grade_title"));
               
               GradeInterface gradeService = new GradeService();

               boolean gradeCreated = gradeService.create(grade);

               if (gradeCreated) {
                  request.getSession().setAttribute("grade", grade);
                  request.getSession().setAttribute("success", "Your grade has been added successfully.!");
               } 
               else {
                  throw new Exception("Create Failed.!");
               }

            } catch (Exception e) {

               log("==================== GRADE MANAGER LOG: doPost Exception START =============================");
               log("Exception: " + e.getMessage());
               log("==================== GRADE MANAGER LOG: doPost Exception END =============================");

               errors.add(e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
               request.getSession().setAttribute("errors", errors);
            } 
            finally {
               log("====================== REDIRECTING =============================");
               response.sendRedirect(request.getContextPath() + "grades/add");
            }            
            break;
      }

      log("==================== GRADE MANAGER LOG: doPost END =============================");
   }

   @Override
   protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      List<String> errors = new ArrayList<>();

      try {
         int grade_id = Integer.parseInt(request.getParameter("grade"));
         log("grade_id:" + request.getParameter("grade"));
         Grade grade = new Grade(grade_id);

         grade.setTitle(request.getParameter("grade_title"));
         
         GradeInterface gradeService = new GradeService();

         boolean gradeUpdated = gradeService.update(grade);

         if (gradeUpdated) {
            request.getSession().setAttribute("grade", grade);
            request.getSession().setAttribute("success", "Your grade has been updated successfully.!");
         } 
         else {
            throw new Exception("Update Failed.!");
         }
      } 
      catch (Exception e) {

         log("==================== GRADE MANAGER LOG: doPut Exception START =============================");
         log("Exception: " + e.getMessage());
         log("==================== GRADE MANAGER LOG: doPut Exception END =============================");

         errors.add(e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
         request.getSession().setAttribute("errors", errors);
      } finally {
         log("====================== GRADE =============================");
         response.sendRedirect(request.getContextPath() + "grades/edit?grade=" + request.getParameter("grade"));
      }
   }

   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

      if (ajax) {
         log("=================== GRADE MANAGER LOG: doDelete START ============================");
         JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
         String grade_id = data.get("grade_id").getAsString();


         GradeInterface gradeService = new GradeService();

         boolean gradeDeleted;
         Map<String, String> options = new LinkedHashMap<>();
         
         try {
            gradeDeleted = gradeService.destroy(grade_id);
            
            if (gradeDeleted) {
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
         log("=================== GRADE MANAGER LOG: doDelete EXITING ============================");

      }

   }

}
