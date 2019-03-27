<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 
<%@ page import="java.sql.*"%>


<HTML>
<HEAD>
<TITLE>Student register</TITLE>
</HEAD>
<BODY>
	<BR>
	<H2>Student Registration Form</H2>
<form method="get">
		<label>STUDENT NAME :&nbsp </label>
		<INPUT TYPE="text" NAME="uname"><br><br>
		<label>ROLL NO : &nbsp	</label>
		<INPUT TYPE="text" NAME="rno"><br><br>
		<label>PHONE NUMBER :&nbsp</label>
		<INPUT TYPE="text" NAME="phno"><br><br>
		<label>E-MAIL ID :&nbsp 	</label>
		<INPUT TYPE="email" NAME="email"><br><br>
		<label> COLLEGE </label>
		<sub>(Are you from kmit)&nbsp&nbsp </sub>
		<INPUT TYPE="text" NAME="clg">yes
		<label>PASSWORD :&nbsp </label>
		<INPUT TYPE="password" NAME="pwd"><br><br>
		<label>CONFIRM PASSWORD : &nbsp	</label>
		<INPUT TYPE="password" NAME="cpwd"><br><br>
		<INPUT TYPE="submit" NAME="register" VALUE="REGISTER">
	</form>
<% 

if(request.getParameter("submit")!=null){
try {
	System.out.println("check");
	//System.out.println("first");
Class.forName("com.mysql.jdbc.Driver");
System.out.println("JDBC driver loaded");

Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fest_management", "root", "1234");

Statement s = con.createStatement();
System.out.println("check");
System.out.println("inserting..");
String email=request.getParameter("emailid");
s.executeUpdate("insert into registrations values(\""+request.getParameter("uname")+"\",\""+request.getParameter("rno")+"\",\""+request.getParameter("phno")+"\",\""+request.getParameter("email")+"\",\""+request.getParameter("clg")+"\",\""+request.getParameter("pwd")+"\",\""+request.getParameter("cpwd")+"\");");
System.out.println("inserted");


RequestDispatcher rs=request.getRequestDispatcher("validateOTP.jsp");          
rs.forward(request, response);      

} catch (ClassNotFoundException e) {
	System.out.println(e.toString());	
}
}
%>



</BODY>
</HTML>
