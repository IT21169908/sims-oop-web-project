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

import com.sims.models.Subject;
import com.sims.models.TeacherSubject;
import com.sims.models.User;
import com.sims.services.SubjectService;
import com.sims.services.TeacherSubjectService;
import com.sims.services.UserService;
import com.sims.services.interfaces.SubjectInterface;
import com.sims.services.interfaces.TeacherSubjectInterface;
import com.sims.services.interfaces.UserInterface;

/**
 * Servlet implementation class TeacherSubjectServlet
 */
@WebServlet("/admin/subjects-assign")
public class TeacherSubjectServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public TeacherSubjectServlet() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub

      boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
      String action = request.getServletPath();
      String path_info = request.getPathInfo() == null ? action : request.getPathInfo();
      if (ajax) {
         log("=================== TEACHER SUBJECT MANAGER LOG: HANDLE GET AJAX REQUESTS ============================");
      } else {
         log("=================== TEACHER SUBJECT MANAGER LOG: doGet START ============================");
         log("=================== ACTION: " + action);
         log("=================== PATH INFO: " + path_info);

         try {
            log("=================== TEACHER SUBJECT MANAGER LOG: ASSIGN START ============================");

            RequestDispatcher dispatcher;

            if (request.getParameter("subject") != null) {
               try {
                  int subject_id = Integer.parseInt(request.getParameter("subject"));
                  log("Subject_id:" + request.getParameter("subject"));
                  Subject subject = new Subject(subject_id);

                  UserInterface userService = new UserService();
                  ArrayList<User> teachersList = userService.allByType("teacher");

                  TeacherSubjectInterface techSubjectService = new TeacherSubjectService();
                  ArrayList<TeacherSubject> subjectTeachersList = techSubjectService.allBySubject(subject_id);

                  ArrayList<Integer> subjectTeachersIdsList = new ArrayList<Integer>();
                  for (TeacherSubject techSubject : subjectTeachersList) {
                     subjectTeachersIdsList.add(techSubject.getTeacher_id());
                  }

                  request.getSession().setAttribute("subject", subject);
                  request.getSession().setAttribute("teachersList", teachersList);
                  request.getSession().setAttribute("subjectTeachersList", subjectTeachersList);
                  request.getSession().setAttribute("subjectTeachersIdsList", subjectTeachersIdsList);
               } catch (Exception e) {
                  throw new ServletException(e);
               }

               dispatcher = request.getRequestDispatcher("/resources/views/admin/subjects/assign.jsp");
               dispatcher.forward(request, response);
            } else {
               try {
                  SubjectInterface subjectService = new SubjectService();
                  ArrayList<Subject> subjectList = subjectService.allWithTeachers();
                  request.setAttribute("subjectList", subjectList);
               } catch (Exception e) {
                  throw new ServletException(e);
               }

               dispatcher = getServletContext()
                     .getRequestDispatcher("/resources/views/admin/subjects/assign-list.jsp");
               dispatcher.forward(request, response);
            }
            log("==================== TEACHER SUBJECT MANAGER LOG: ASSIGN END =============================");
         } catch (Exception ex) {
            throw new ServletException(ex);
         }

         log("==================== TEACHER SUBJECT MANAGER LOG: doGet END =============================");
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      log("=================== TEACHER SUBJECT MANAGER LOG: doPost START ============================");

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
               TeacherSubject teacherSubject = new TeacherSubject();

               String[] selectedTeachers = request.getParameterValues("selected_teacher");
               String subjectId = request.getParameter("subject");

               TeacherSubjectInterface teacherSubjectService = new TeacherSubjectService();
               boolean techSubjectDestroyed = teacherSubjectService.destroy(subjectId);

               if (selectedTeachers != null && selectedTeachers.length > 0) {
                  
                  if (techSubjectDestroyed) {
                     for (int i = 0; i < selectedTeachers.length; i++) {
                        
                        String teacherId = selectedTeachers[i];

                        teacherSubject.setTeacher_id(Integer.parseInt(teacherId));
                        teacherSubject.setSubject_id(Integer.parseInt(subjectId));
                        
                        boolean techSubjectCreated = teacherSubjectService.create(teacherSubject);

                        if (techSubjectCreated) {
                           request.getSession().setAttribute("teacherSubject", teacherSubject);
                           request.getSession().setAttribute("success", "Your subject has been assigned successfully.!");
                        } else {
                           throw new Exception("Create Failed.!");
                        }

                     }
                  } else {
                     throw new Exception("Create Failed.!");
                  }
                  
               }

            } catch (Exception e) {

               log("==================== TEACHER SUBJECT MANAGER LOG: doPost Exception START =============================");
               log("Exception: " + e.getMessage());
               log("==================== TEACHER SUBJECT MANAGER LOG: doPost Exception END =============================");

               errors.add(e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
               request.getSession().setAttribute("errors", errors);
            } finally {
               log("====================== REDIRECTING =============================");
               response.sendRedirect(request.getContextPath() + "subjects-assign");
            }
            break;
      }

      log("==================== TEACHER SUBJECT MANAGER LOG: doPost END =============================");
   }

}
