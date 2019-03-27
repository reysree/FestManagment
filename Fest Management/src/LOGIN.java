
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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
@WebServlet(
		urlPatterns = { "/LOGIN" }, 
		initParams = { 
				@WebInitParam(name = "DB_DRIVER", value = "com.mysql.jdbc.Driver"), 
				@WebInitParam(name = "DB_URL", value = "jdbc:mysql://localhost:3306/fest_management"), 
				@WebInitParam(name = "DB_USER", value = "root"), 
				@WebInitParam(name = "DB_PASSWORD", value = "1234")
		})
public class LOGIN extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LOGIN() {
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
		PrintWriter out=response.getWriter();
		String rollnumber=request.getParameter("rno");
		String password=request.getParameter("pwd");
		System.out.println(rollnumber);
		System.out.println(password);
		HttpSession session=request.getSession();
		//session.setAttribute("email", arg1);
		try
		{
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * from registrations" );
			int flag=0;
			while(rs.next()) {
				System.out.println("Ok");
				if(rs.getString("rollno").equals(rollnumber)) {
					flag=1;
					if(rs.getString("password").equals(password))
					{
						session.setAttribute("clg",rs.getString("clgname"));
						session.setAttribute("rno",rs.getString("rollno"));
						System.out.println("logged in");
						RequestDispatcher r=request.getRequestDispatcher("AfterLogin.html");
						session.setAttribute("email", rs.getString("email"));
						r.include(request,response);
					}
					else
					{
						out.println("<html><body><h2>Password Incorrect</h2><a href=\"LOGIN123.html\">click here</a>to Redirect</html></body>");
						break;
					}
				}
			}
			if(flag==0) {
				out.println("username invalid");
			}
			
			//System.out.println("check");
		}catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

}
