# **🏫 SIMS -  School Information Management System**

#### 🔖 Object Oriented Programming with Java + JSP & Servlets Web Application.
  In this assignment, we are developing a web application using Java web-based technologies. 
 
      ✅ Java (Web Dynamic Application)   
      ✅ JSP - Java Server Pages   
      ✅ Servlets    
      ✅ MySql


### 🏷️ Prerequisites

- Have **JDK** installed
- Have **Apache Tomcat Server** installed (or **XAMPP**)
- Have **MySQL** installed (or **XAMPP**) 
- Have **Eclipse** installed (Enterprise)

#### 🔛 This Developed On: 
       ☑️ JDK 15 / Dynamic Web Module 3.1
       ☑️ XAMPP-8.1.6-0-VS16 + Apache Tomcat/8.5.78
       ☑️ XAMPP-8.1.6-0-VS16 + Mysql Ver 15.1
       ☑️ Eclipse IDE for Enterprise Java and Web Developers - 2022-06


### 🏷️ Installation

01.  Clone or Download this Repository.
02.  Add Project Folder to Your Local PC `eclipse-workspace`. ( or Clone to directly to it)
03.  Open Project in `Eclipse IDE`.
04.  In IDE Go to `Window -> Show View -> Servers`.
05.  Click `Create a New Server Link`.
06.  Then Select Tomcat Server Version in `Apache` dir and Click Next. (Recommend to use: `Tomcat v8.5 Server`)
    
      > Before that, you have to Identify what is your installed Tomcat version. If you are using XAMPP then you can check that by `XAMPP C-Panel`.  Start `Tomcat` -> Go to <a href="http://localhost:8080/">http://localhost:8080/</a>
    
07.  Then Browse Tomcat Installation Dir and Click Next then Finish.
    
      > The previously selected version should be already installed. If you are using XAMPP then you can select this directory ` C:\xampp\tomcat `

08.  Then Start `Apache Server and MySQL Server`. (if you are using `XAMPP`, otherwise run MySQL server)
09.  Then Create `lib` Folder in ` src\main\webapp\WEB-INF ` dir. (if `lib` dir is there not)
10.  Then Copy JAR Files From ` sims-oop-web-project\external-libraries ` dir to `lib` dir. 

      > <a href="https://jar-download.com/?search_box=mysql-connector-java-8.0.30" target="_blank">mysql-connector-java-8.0.30.jar</a>
      
      > <a href="https://jar-download.com/?search_box=javax.servlet-api-4.0.1" target="_blank">javax.servlet-api-4.0.1.jar</a>
      
      > <a href="https://jar-download.com/?search_box=servlet-api-4.0.0" target="_blank">servlet-api-4.0.0.jar</a>
      
      > <a href="https://jar-download.com/?search_box=jstl-1.2" target="_blank">jstl-1.2.jar</a>
      
      > <a href="https://jar-download.com/?search_box=jstl-api-1.2-rev-1" target="_blank">jstl-api-1.2-rev-1.jar</a>

11. Add Libraries to Project.

        Go to Project Properties 
        -> Java Build Path -> Libraries -> Classpath 
            If the Classpath have already JAR files, select all JARs and remove those from classpath
        -> Add JARs -> Select JARs from src\main\webapp\WEB-INF\lib 
        -> Ok and Apply

12. Then Set Environment Variables in `env.properties`. (If they're not correct values or not set critical values)

13. Go to ` com.sims.project.db_environment_initializer ` Package dir. (See in Project -> Java Resource/src/main/java)
14. Run Java Application Files. (Right click `.java file -> Run As -> 2 Java Application`)
        
        1. CreateDatabase.java
        2. MigrateTables.java
        3. InsertDefaultEntries.java (If you want)
        
15. Change Host URL Path. 

        Go to IDE Project Explorer 
        -> Servers -> Tomcat -> server.xml 
        -> Find project root name (sims-oop-web-project) in server file 
            if can't find <Context> element, project `Run on Server` one time 
        -> Change path attribute to `path="/"` on Context element 
        -> Save 

16. **Run** Web Project. 

         > Right click Project main root `Run As -> 1 Run on Server`
         > Choose Server `localhost -> Tomcat Server`
         > Check `Always use this server ....`
         > Click Next and Finish

_____________________

### 🔖 Project Group Details

* Project Group ID: **SE/OOP_MLB_WD_2022_S2_104**
* Batch No: **Y2.S1.WD.IT.3.1**
* Topic: **Online School Information Management System**
* Module: **Object-Oriented Programming**
* Project link: 
* Project Github link: [https://github.com/IT21169908/sims-oop-web-project](https://github.com/IT21169908/sims-oop-web-project/tree/viva-submitted-main)
* Viva Date: 2022-11-11 (Project closed)

## Online School Information Management System

| Registration Number | Name | Email | Contribution |
| :---: | :---: | :---: | :---: |
| **IT21169908** | **Prashantha K.G.M** | ***it21169908@my.sliit.lk*** | ✅ Full project structure & project initializer methods |
|  |  |  |  ✅ Login (authentication and authorization) |
|  |  |  |  ✅ Landing page and other related designs |
|  |  |  |  ✅ Implements ConnectionProvider (Database connection) with Singleton design pattern |
|  |  |  |  ✅ Subject - CRUD operations |
|  |  |  |  ✅ Assign teachers - CRUD operations |
|  |  |  |  ✅ Grades - CRUD operations |
|  |  |  |  |
| IT21156410 | Fonseka M.M.N.H | *it21156410@my.sliit.lk* | ✅ Project flow pattern changes & design changes |
|  |  |  |  ✅ Template Method Design Pattern in servelet  |
|  |  |  |  ✅ Leave Management - CRUD Operations |
|  |  |  |  ✅ Periods/Class - CRUD Operations |
|  |  |  |  ✅ Admin & Teacher Profile Update  |
|  |  |  |  ✅ Change Password  |
|  |  |  |  | 
| IT21156038 | N.K.N.Nawanjala | *it21156038@my.sliit.lk* | ✅ User registration - CRUD Operations |
|  |  |  |  ✅ Related designs changes |
|  |  |  |  |
| IT21170966 | Thennakoon T.M.I.C | *it21170966@my.sliit.lk* | ✅ Student - CRUD Operations |
|  |  |  |  ✅ Related designs changes |
|  |  |  |  |

______________

![java+jsp+servlets-150](https://user-images.githubusercontent.com/99706983/191255194-30daa1eb-09e7-4dd8-801b-2ce67c8ed9cb.png)

<sub><sup>📌*documented by @Prashantha K.G.M 2022*</sup></sub>


