

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckKmit")
public class CheckKmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("My sql driver loaded");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/fest_management", "root", "1234");
		    if(con!=null) System.out.println("connection established");
		    else System.out.println("connection is not establisshed");
		   
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}


	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("chekkmit");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		String s = (String) session.getAttribute("clg");
		String event = request.getParameter("Submit");
		String[] sarr = event.split(" ");
		int flag=0;
		System.out.println(event);
		try {
			Statement stmt = con.createStatement();
			System.out.println(stmt);
			ResultSet rs=stmt.executeQuery("select * from eventregistration where rno='"+(String) session.getAttribute("rno")+"'");
			while(rs.next()) {
				if(rs.getString("events").equals(sarr[sarr.length-1])) {
					flag=1;
				}
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		if(flag==1) {
			out.println("<html><body><h2>Already Registered</h2><a href=\"AfterLogin.html\">click here</a> to Redirect</html></body>");
			return;
		}else {
		if(s.equals("KMIT")) {
			
			
			String rno = (String) session.getAttribute("rno"); //get roll no from session 
			try {
				Statement stmt = con.createStatement();
				System.out.println(stmt);
				stmt.executeUpdate("insert into eventregistration(rno,events) values('"+rno+"','"+sarr[sarr.length-1]+"');");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			RequestDispatcher r = request.getRequestDispatcher("successfulPayment.html");
			r.forward(request, response);
		}
		else {
			session.setAttribute("event", sarr[sarr.length-1]);
			RequestDispatcher r = request.getRequestDispatcher("payment.html");
			r.forward(request, response);
		}
		}
	}

}
