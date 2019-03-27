

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyEvents1
 */
@WebServlet("/MyEvents1")
public class MyEvents1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		try {
			Statement stmt = con.createStatement();
			System.out.println(stmt);
			ResultSet rs=stmt.executeQuery("select * from eventregistration where rno=\""+session.getAttribute("rno")+"\""); 
			while(rs.next()) {
				out.println("<html><body><h3>"+rs.getString("events")+"</h3></body></html>");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
