<%@page language="java" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    </body>

             <a href="javascript:history.go(-1)" style="postion:absolute; top:10px; left:30px">Go Back</a>



       <!--  using jstl tags -->
       <h1>Wellcome</h1>
       <p>Id of the Employee     : ${employee.getEid()}</p>
       <p>Name of the Employee   : ${employee.getEname()}</p>
       <p>Designation of Employee: ${employee.getEdesignation()}</p>
       <p>Salary of Employee     : ${employee.getEsalary()}</p>

           <a href="AddProjects">
           <input type="submit" value="Add Projects" style="height:60px;">
           </a>






    </body>
</html>