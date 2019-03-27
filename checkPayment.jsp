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

try {

Class.forName("com.mysql.jdbc.Driver");
System.out.println("JDBC driver loaded cotp");

Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", "root", "1234");

Statement s = con.createStatement();
System.out.println("check cotp");
String otpuser=request.getParameter("OTP");
System.out.println(otpuser);
String email=session.getAttribute("email").toString();
System.out.println(email);
String otp=session.getAttribute("otp").toString();
System.out.println(otp);
if(otp.equals(otpuser)){
	RequestDispatcher rs=request.getRequestDispatcher("successfulPayment.html");          
	rs.forward(request, response);      
}
else{
	s.executeUpdate("delete from registrations where email = '"+email+"';");
	RequestDispatcher rs=request.getRequestDispatcher("failedPayment.html");          
	rs.forward(request, response);  
}
}catch (ClassNotFoundException e) {
	System.out.println(e.toString());	
}

%>

</BODY>
</HTML>


