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
import com.sims.models.LeaveRequest;
import com.sims.models.User;
import com.sims.services.LeaveRequestService;
import com.sims.services.interfaces.LeaveRequestInterface;

/**
 * Servlet implementation class LeaveRequest
 * 
 * @author M.M.N.H.Fonseka
 * 
 */
@WebServlet("/teacher/leave-requests/*")
public class LeaveRequestServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public LeaveRequestServlet() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
      String action = request.getServletPath();
      String path_info = request.getPathInfo() == null ? action : request.getPathInfo();
      if (ajax) {
         log("===================LEAVE MANAGER LOG: HANDLE GET AJAX REQUESTS============================");
      } else {
         log("===================LEAVE MANAGER LOG: doGet START============================");
         log("===================ACTION: " + action);
         log("===================PATH INFO: " + path_info);

         try {
            switch (path_info) {
               case "/teacher/leave-requests":
                  User loggedUser = null;
                  try {
                    loggedUser = (User) request.getSession(false).getAttribute("user"); 
                  } catch (Exception e) {
                     // TODO: handle exception
                  } finally {
                     if (loggedUser == null || loggedUser.getId() <= 0) {
                        response.sendRedirect(request.getContextPath() + "/login");
                        return;
                     }
                  } 
                  
                  LeaveRequestInterface LeaveReqService = new LeaveRequestService();
                  ArrayList<LeaveRequest> LeaveRequestList = LeaveReqService.allByUser(loggedUser.getId());

                  request.setAttribute("LeaveRequestList", LeaveRequestList);
                  RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/resources/views/teacher/leaveRequests/index.jsp");
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

         log("====================LEAVE MANAGER LOG: doGet END=============================");
      }
   }

   private void doCreate(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      RequestDispatcher dispatcher = getServletContext()
            .getRequestDispatcher("/resources/views/teacher/leaveRequests/create.jsp");
      dispatcher.forward(request, response);
   }

   private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log("===================LEAVE MANAGER LOG: EDIT START============================");
      // TODO: validate GET parameter leave is exists or redirect to safe page
      int leave_id = Integer.parseInt(request.getParameter("leave"));
      log("Leave_id:" + request.getParameter("leave"));
      LeaveRequest RequestLeave = new LeaveRequest(leave_id);
      request.getSession().setAttribute("leaveRequest", RequestLeave);
      RequestDispatcher dispatcher = request.getRequestDispatcher("/resources/views/teacher/leaveRequests/edit.jsp");
      dispatcher.forward(request, response);

      log("====================LEAVE MANAGER LOG: EDIT END=============================");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      log("===================LEAVE MANAGER LOG: doPost START============================");

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
               LeaveRequest RequestLeave = new LeaveRequest();

               User loggedUser = (User) request.getSession(false).getAttribute("user");
               if (loggedUser.getId() <= 0) {
                  throw new Exception("unathorizes request!");
               }

               RequestLeave.setUser_id(loggedUser.getId());
               RequestLeave.setDate(request.getParameter("date"));
               RequestLeave.setDays_count(request.getParameter("days_count"));
               RequestLeave.setReason(request.getParameter("reason"));
               RequestLeave.setStatus("pending");

               LeaveRequestInterface RequestLeaveService = new LeaveRequestService();

               boolean create_response = RequestLeaveService.create(RequestLeave);

               if (create_response) {
                  request.getSession().setAttribute("leaveRequest", RequestLeave);
                  request.getSession().setAttribute("success", "Your request has been recorded successfully.!");
               } else {
                  throw new Exception("Create Failed.!");
               }

            } catch (Exception e) {

               log("====================LEAVE MANAGER LOG: doPost Exception START=============================");
               log("Exception: " + e.getMessage());
               log("====================LEAVE MANAGER LOG: doPost Exception END=============================");

               errors.add(e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
               // request.setAttribute("errors", errors);
               request.getSession().setAttribute("errors", errors);
            } finally {
               log("======================REDIRECTING=============================");
               response.sendRedirect(request.getContextPath() + "leave-requests/add");
            }
            break;
      }

      log("====================LEAVE MANAGER LOG: doPost END=============================");
   }

   @Override
   protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      List<String> errors = new ArrayList<>();

      try {
         int leave_id = Integer.parseInt(request.getParameter("leave"));
         log("Leave_id:" + request.getParameter("leave"));
         LeaveRequest RequestLeave = new LeaveRequest(leave_id);

         RequestLeave.setDate(request.getParameter("date"));
         RequestLeave.setDays_count(request.getParameter("days_count"));
         RequestLeave.setReason(request.getParameter("reason"));
         RequestLeave.setStatus("pending"); // TODO: facility to select status of leave for teacher and get value from
                                            // input
         RequestLeave.setCancel_reason(request.getParameter("cancel_reason")); // TODO: if status cancel require cancel
                                                                               // reason

         LeaveRequestInterface RequestLeaveService = new LeaveRequestService();

         boolean result = RequestLeaveService.update(RequestLeave);

         if (result) {
            request.getSession().setAttribute("leaveRequest", RequestLeave);
            request.getSession().setAttribute("success", "Your request has been updated successfully.!");
         } else {
            throw new Exception("Update Failed.!");
         }

      } catch (Exception e) {

         log("====================LEAVE MANAGER LOG: doPut Exception START=============================");
         log("Exception: " + e.getMessage());
         log("====================LEAVE MANAGER LOG: doPut Exception END=============================");

         errors.add(e.getMessage() != null ? e.getMessage() : "Something went wrong! Please try again.!");
         // request.setAttribute("errors", errors);
         request.getSession().setAttribute("errors", errors);
      } finally {
         log("======================REDIRECTING=============================");
         response.sendRedirect(request.getContextPath() + "leave-requests/edit?leave=" + request.getParameter("leave"));
      }
   }

   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

      if (ajax) {
         log("===================LEAVE MANAGER LOG: doDelete START============================");
         JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
         String leave_id = data.get("leave_id").getAsString();

         LeaveRequestInterface RequestLeaveService = new LeaveRequestService();

         boolean result = RequestLeaveService.destroy(leave_id);

         Map<String, String> options = new LinkedHashMap<>();
         if (result) {
            options.put("status", "deleted");
            options.put("icon", "success");
         } else {
            options.put("status", "failed");
            options.put("icon", "error");
         }

         String json = new Gson().toJson(options);

         resp.setContentType("application/json");
         resp.setCharacterEncoding("UTF-8");
         resp.getWriter().write(json);
         log("===================LEAVE MANAGER LOG: doDelete EXITING============================");
      }

   }

}
