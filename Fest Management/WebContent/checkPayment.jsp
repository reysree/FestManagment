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
	
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fest_management", "root", "1234");
	
	Statement s = con.createStatement();
	System.out.println("check cotp");
	String otpuser=request.getParameter("OTP");
	System.out.println(otpuser);
	//String email=session.getAttribute("email").toString();
	//System.out.println(email);
	String otp=session.getAttribute("otp").toString();
	System.out.println(otp);
	if(otp.equals(otpuser)){
		
		//kentering intpo database
		try {
				Statement stmt = con.createStatement();
				System.out.println(stmt);
				System.out.println(session.getAttribute("rno").toString());
				System.out.println(session.getAttribute("event").toString());
				stmt.executeUpdate("insert into eventregistration(rno,events) values('"+session.getAttribute("rno").toString()+"','"+session.getAttribute("event").toString()+"')");
			}
			catch(Exception e)
			{
				System.out.println("aksjdfh"+e);
			}
		RequestDispatcher rs=request.getRequestDispatcher("successfulPayment.html");  
		System.out.println("rs = "+rs);
		response.sendRedirect("successfulPayment.html");
		rs.forward(request, response);   
		System.out.println("ao;sdifhaiusdbgfadsj");
	}
	else{
		//s.executeUpdate("delete from registrations where email = '"+email+"';");
		RequestDispatcher rs=request.getRequestDispatcher("failedPayment.html");          
		rs.forward(request, response);  
	}
}
catch (Exception e) {
	System.out.println(e.toString());	
}

%>

</BODY>
</HTML>


