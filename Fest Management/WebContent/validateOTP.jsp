<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import= "otpjsp.*" %>
<%@ page import="java.sql.*"%>


<HTML>
<HEAD>
<TITLE>validate otp</TITLE>
</HEAD>



<% 


try {
System.out.println("try");
Class.forName("com.mysql.jdbc.Driver");
System.out.println("JDBC driver loaded");

Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fest_management", "root", "1234");

Statement s = con.createStatement();
System.out.println("check");
String email=(String)session.getAttribute("email");
		
otpSendMail obj=new otpSendMail(email);
obj.setMailServerProperties();
int a=obj.otp;
//String email=request.getParameter("emailid");
String otp="";
otp+=a;
System.out.println(email);
System.out.println(otp);
session.setAttribute("email",email);
session.setAttribute("otp",otp);
System.out.println(a);

} catch (ClassNotFoundException e) {
	System.out.println(e.toString());	
}

%>
<BODY>
	<BR>
	<H2>Email Validation</H2>
<BR><form action="checkOTP.jsp" method="get" >
	 Enter otp :<input type="text" name="OTP">
    
    <input type="submit" name="submit" value="submit">
</form>
</BODY>
</HTML>


