
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Statement;

/**
 * Servlet implementation class Doc_login
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		//super.init(config);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("jdbc loaded");
			System.out.println("DB_URL");
			System.out.println("DB_USER");
			System.out.println("DB_PASSWORD");
			System.out.println("DB_DRIVER");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fest_management","root","1234");
			System.out.println(con);
			if(con!=null)
			{
				System.out.println("connection established");
			}
			else
			{
				System.out.println("connection Failed");
			}
		} catch(Exception e)
		{
			System.out.println();
		}
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
					}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println("post");
		PrintWriter out=response.getWriter();
		if(request.getParameter("cpwd").equals(request.getParameter("pwd")))
		{
			try {
				System.out.println("check");
				//System.out.println("first");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC driver loaded");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fest_management", "root", "1234");

			Statement s = con.createStatement();
			System.out.println("check");
			System.out.println("inserting..");
			String email=request.getParameter("email");
			String phone=request.getParameter("phno");
			long pno=Long.parseLong(phone);
			s.executeUpdate("insert into registrations values(\""+request.getParameter("uname")+"\",\""+request.getParameter("rno")+"\",\""+request.getParameter("email")+"\","+pno+",\""+request.getParameter("clg")+"\",\""+request.getParameter("pwd")+"\");");
			System.out.println("inserted");
            String rollno=request.getParameter("rno");
            String college=request.getParameter("clg");
            int flag=0;
            String kmitemail="";
            if("KMIT".equalsIgnoreCase(college)) {
            	Statement stmt=con.createStatement();
            	ResultSet rs=stmt.executeQuery("SELECT * from kmitdb where rno=\""+rollno+"\"" );
            	while(rs.next()) {
    					kmitemail=rs.getString("email");
    					flag=1;
    				
            	}
            	
            
            }
            
			HttpSession session=request.getSession();
			session.setAttribute("rno", rollno);
			if(flag==0)
			session.setAttribute("email",email);
			else {
				session.setAttribute("email", kmitemail);
				
			}
				
			RequestDispatcher rs=request.getRequestDispatcher("validateOTP.jsp");          
			rs.forward(request, response);      

			} catch (ClassNotFoundException e) {
				System.out.println(e.toString());	
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}
		else {
			out.println("<html>");
			out.println("<body>");
			out.println("<h2>Passwords donot Match</h2>");
			out.println("<a href = \"Register.html\">Click here</a> To register");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
