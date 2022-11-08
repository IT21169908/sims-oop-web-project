/**
 * 
 */
package com.sims.models;

/**
 * @author Indudini
 *
 */
public class Student {
   
   private int studentID;
   
   private String studentName;
   
   private String studentClass;
   
   private String studentDOB;
   
   private String studentParentName;
   
   private String studentMobileNumber;
   
   private String studentAddress;
   
   

   public Student() {
     
   }
   

   public Student(int studentID, String studentName, String studentClass, String studentDOB, String studentParentName,
         String studentMobileNumber, String studentAddress) {
      super();
      this.studentID = studentID;
      this.studentName = studentName;
      this.studentClass = studentClass;
      this.studentDOB = studentDOB;
      this.studentParentName = studentParentName;
      this.studentMobileNumber = studentMobileNumber;
      this.studentAddress = studentAddress;
   }

   public int getStudentID() {
      return studentID;
   }

   public void setStudentID(int studentID) {
      this.studentID = studentID;
   }

   public String getStudentName() {
      return studentName;
   }

   public void setStudentName(String studentName) {
      this.studentName = studentName;
   }

   public String getStudentClass() {
      return studentClass;
   }

   public void setStudentClass(String studentClass) {
      this.studentClass = studentClass;
   }

   public String getStudentDOB() {
      return studentDOB;
   }

   public void setStudentDOB(String studentDOB) {
      this.studentDOB = studentDOB;
   }

   public String getStudentParentName() {
      return studentParentName;
   }

   public void setStudentParentName(String studentParentName) {
      this.studentParentName = studentParentName;
   }

   public String getStudentMobileNumber() {
      return studentMobileNumber;
   }

   public void setStudentMobileNumber(String studentMobileNumber) {
      this.studentMobileNumber = studentMobileNumber;
   }

   public String getStudentAddress() {
      return studentAddress;
   }

   public void setStudentAddress(String studentAddress) {
      this.studentAddress = studentAddress;
   }
   

   

}
