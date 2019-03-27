<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import= "otpjsp.*" %>
<%@ page import="java.sql.*"%>


<HTML>
<HEAD>
<TITLE>check otp</TITLE>
</HEAD>
<BODY>
	



<% 
if(request.getParameter("submit")!=null){
try {

Class.forName("com.mysql.jdbc.Driver");
System.out.println("JDBC driver loaded cotp");

Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fest_management", "root", "1234");

Statement s = con.createStatement();
System.out.println("check cotp");
String otpuser=request.getParameter("OTP");
System.out.println(otpuser);
String email=session.getAttribute("email").toString();
System.out.println(email);
String otp=session.getAttribute("otp").toString();
System.out.println(otp);
String roll=session.getAttribute("rno").toString();
if(otp.equals(otpuser)){
	RequestDispatcher rs=request.getRequestDispatcher("Successful.jsp");          
	rs.forward(request, response);      
}
else{
	System.out.println("delete "+email);
	s.executeUpdate("delete from registrations where rollno = '"+roll+"';");
	RequestDispatcher rs=request.getRequestDispatcher("failed.jsp");          
	rs.forward(request, response);  
}
}catch (ClassNotFoundException e) {
	System.out.println(e.toString());	
}
}
%>

</BODY>
</HTML>


