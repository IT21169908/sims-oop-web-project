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

import com.sims.models.Period;
import com.sims.models.User;
import com.sims.services.PeriodService;
import com.sims.services.interfaces.PeriodInterface;

/**
 * Servlet implementation class TeacherShedulesServlet
 */
@WebServlet("/teacher/scheduled-classes")
public class TeacherShedulesServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#HttpServlet()
    */
   public TeacherShedulesServlet() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      List<String> errors = new ArrayList<>();
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
      
      
      PeriodInterface PeriodService = new PeriodService();
      ArrayList<Period> periodList;
      try {
         periodList = PeriodService.allByTeacher(loggedUser.getId());
         request.setAttribute("periodList", periodList);
         
         RequestDispatcher dispatcher = getServletContext()
               .getRequestDispatcher("/resources/views/teacher/periods/index.jsp");
         dispatcher.forward(request, response);
         response.getWriter().append("Served at: ").append(request.getContextPath());
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
 
     
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

}
