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
import com.sims.models.Period;
import com.sims.models.Subject;
import com.sims.models.TeacherSubject;
import com.sims.services.GradeService;
import com.sims.services.PeriodService;
import com.sims.services.SubjectService;
import com.sims.services.TeacherSubjectService;
import com.sims.services.interfaces.GradeInterface;
import com.sims.services.interfaces.PeriodInterface;
import com.sims.services.interfaces.SubjectInterface;
import com.sims.services.interfaces.TeacherSubjectInterface;

/**
 * Servlet implementation class PeriodServlet
 * 
 * @author M.M.N.H.Fonseka
 */

@WebServlet("/admin/periods/*")
public class PeriodServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public PeriodServlet() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

      if (ajax) {
         log("===================LEAVE MANAGER LOG: HANDLE GET AJAX REQUESTS============================");
         try {

            int subject_id = Integer.parseInt(request.getParameter("subject_id"));

            log("===================subject_id: " + subject_id);

            log("====================PERIOD LOG: doGet GETTING ALL TEACHERS ASSIGNED TO SUBJECTS=============================");
            TeacherSubjectInterface techSubjectService = new TeacherSubjectService();
            ArrayList<TeacherSubject> subjectTeachersList = techSubjectService.allBySubject(subject_id);

            String json = new Gson().toJson(subjectTeachersList);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

      } else {
         String action = request.getServletPath();
         String path_info = request.getPathInfo() == null ? action : request.getPathInfo();

         log("===================PERIOD LOG: doGet START============================");
         log("===================ACTION: " + action);
         log("===================PATH INFO: " + path_info);

         try {
            switch (path_info) {
               case "/admin/periods":
                  PeriodInterface PeriodService = new PeriodService();
                  ArrayList<Period> periodList = PeriodService.all();

                  request.setAttribute("periodList", periodList);
                  RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/resources/views/admin/periods/index.jsp");
                  dispatcher.forward(request, response);
                  break;
               case "/create":
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
      }
      log("====================PERIOD LOG: doGet END=============================");
   }

   private void create(HttpServletRequest request, HttpServletResponse response) throws Exception {

      log("====================PERIOD LOG: doGet GETTING ALL SUBJECTS=============================");
      SubjectInterface SubjectService = new SubjectService();
      ArrayList<Subject> subjectList = SubjectService.all();

      log("====================PERIOD LOG: doGet GETTING ALL GRADES SUBJECTS=============================");
      GradeInterface gradeService = new GradeService();
      ArrayList<Grade> gradeList = gradeService.all();

      ArrayList<TeacherSubject> subjectTeachersList = new ArrayList<>();

      request.setAttribute("subjectList", subjectList);
      request.setAttribute("gradeList", gradeList);
      request.setAttribute("subjectTeachersList", subjectTeachersList);

      RequestDispatcher dispatcher = getServletContext()
            .getRequestDispatcher("/resources/views/admin/periods/create.jsp");
      dispatcher.forward(request, response);
   }

   private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
      log("===================PERIOD LOG: EDIT START============================");

      int period_id = Integer.parseInt(request.getParameter("period"));
      log("Period_id:" + request.getParameter("period"));

      Period period = new Period(period_id);
      
//      log("====================PERIOD LOG: doGet GETTING ALL SUBJECTS=============================");
//      SubjectInterface SubjectService = new SubjectService();
//      ArrayList<Subject> subjectList = SubjectService.all();
//
//      log("====================PERIOD LOG: doGet GETTING ALL GRADES SUBJECTS=============================");
//      GradeInterface gradeService = new GradeService();
//      ArrayList<Grade> gradeList = gradeService.all();

      log("====================PERIOD LOG: doGet GETTING ALL TEACHERS ASSIGNED TO SUBJECTS=============================");
      TeacherSubjectService techSubjectService = new TeacherSubjectService();
      ArrayList<TeacherSubject> subjectTeachersList = techSubjectService.allBySubject(period.getSubject_id());
      

      request.getSession().setAttribute("period", period);
//      request.getSession().setAttribute("subjectList", subjectList);
//      request.getSession().setAttribute("gradeList", gradeList);
      request.getSession().setAttribute("subjectTeachersList", subjectTeachersList);

      RequestDispatcher dispatcher = request.getRequestDispatcher("/resources/views/admin/periods/edit.jsp");
      dispatcher.forward(request, response);

      log("====================PERIOD LOG: EDIT END=============================");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      String _METHOD = request.getParameter("_method") == null ? request.getMethod().toLowerCase()
            : request.getParameter("_method").toLowerCase();

      log("=================== REQUEST _METHOD: " + _METHOD);
      switch (_METHOD) {
         case "put":
            doPut(request, response);
            break;
         default:

            log("===================PERIOD LOG: doPost START============================");

            List<String> errors = new ArrayList<>();

            try {
               Period period = new Period();

               period.setTitle(request.getParameter("title"));
               period.setSubject_id(Integer.parseInt(request.getParameter("subject_id")));
               period.setGrade_id(Integer.parseInt(request.getParameter("grade_id")));
               period.setTeacher_id(Integer.parseInt(request.getParameter("teacher_id")));
               period.setStart_time(request.getParameter("start_time"));
               period.setEnd_time(request.getParameter("end_time"));
               period.setDay(request.getParameter("day"));
               period.setStart_date(request.getParameter("start_date"));
               period.setEnd_date(request.getParameter("end_date"));

               PeriodInterface periodService = new PeriodService();

               boolean result = periodService.create(period);

               if (result) {
                  request.getSession().setAttribute("period", period);
                  request.getSession().setAttribute("success", "Period has been saved successfully.!");
               } else {
                  throw new Exception("Create Failed.!");
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
               response.sendRedirect(request.getContextPath() + "create");
            }
            break;
      }

      log("====================PERIOD LOG: doPost END=============================");
   }

   protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      log("===================PERIOD LOG: doPut START============================");

      List<String> errors = new ArrayList<>();

      try {
         int period_id = Integer.parseInt(request.getParameter("period"));
         log("period_id:" + request.getParameter("period"));

         Period period = new Period(period_id);

         period.setTitle(request.getParameter("title"));
//         period.setSubject_id(Integer.parseInt(request.getParameter("subject_id")));
//         period.setGrade_id(Integer.parseInt(request.getParameter("grade_id")));
         period.setTeacher_id(Integer.parseInt(request.getParameter("teacher_id")));
         period.setStart_time(request.getParameter("start_time"));
         period.setEnd_time(request.getParameter("end_time"));
         period.setDay(request.getParameter("day"));
         period.setStart_date(request.getParameter("start_date"));
         period.setEnd_date(request.getParameter("end_date"));

         PeriodInterface periodService = new PeriodService();

         boolean result = periodService.update(period);

         if (result) {
            request.getSession().setAttribute("period", period);
            request.getSession().setAttribute("success", "Your request has been updated successfully.!");
         } else {
            throw new Exception("Update Failed.!");
         }

      } catch (Exception e) {

         log("====================PERIOD LOG: doPut Exception START=============================");
         log("Exception: " + e.getMessage());
         log("====================PERIOD LOG: doPut Exception END=============================");

         errors.add(e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
         // request.setAttribute("errors", errors);
         request.getSession().setAttribute("errors", errors);
      } finally {
         log("======================REDIRECTING=============================");
         response.sendRedirect(request.getContextPath() + "edit?period=" + request.getParameter("period"));
      }
   }

   protected void doDelete(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

      if (ajax) {

         log("===================PERIOD LOG: doDelete START============================");

         JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

         int period_id = data.get("period_id").getAsInt();

         Map<String, String> options = new LinkedHashMap<>();

         PeriodInterface periodService = new PeriodService();

         try {

            boolean result = periodService.destroy(period_id);

            if (result) {
               options.put("status", "deleted");
               options.put("icon", "success");
            } else {
               options.put("status", "failed");
               options.put("icon", "error");
            }
         } catch (Exception e) {
            options.put("status", "failed");
            options.put("icon", "error");
            options.put("message", e.getMessage());
            // TODO Auto-generated catch block
            e.printStackTrace();
         }

         String json = new Gson().toJson(options);

         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         response.getWriter().write(json);

         log("===================PERIOD LOG: doDelete EXITING============================");

      }
   }

}
