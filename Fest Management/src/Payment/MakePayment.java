package Payment;
//import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import otpjsp.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MakePayment
 */
@WebServlet(
		urlPatterns = { "/MakePayment" }, 
		initParams = { 
				@WebInitParam(name = "DB_DRIVER", value = "com.mysql.jdbc.Driver"), 
				@WebInitParam(name = "DB_URL", value = "jdbc:mysql://localhost:3306/fest_management"), 
				@WebInitParam(name = "DB_USER", value = "root"), 
				@WebInitParam(name = "DB_PASSWORD", value = "1234")
		})
public class MakePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
    /**
     * Default constructor. 
     */
    public MakePayment() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName(config.getInitParameter("DB_DRIVER"));
			con = DriverManager.getConnection(config.getInitParameter("DB_URL"),config.getInitParameter("DB_USER"), config.getInitParameter("DB_PASSWORD"));
			if(con != null) {
				System.out.println("Connection established with JDBC....");
			}
			
			else {
				System.out.println("DB Connection Failed....");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out = response.getWriter();
		String cardno=request.getParameter("cardno");
		String name=request.getParameter("cname");
		String cvv= request.getParameter("cvv");
		String expiry_m=request.getParameter("Expiry_m");
		String expiry_y=request.getParameter("Expiry_y");
		try {
			System.out.println("OK");
			Statement stmt = con.createStatement();
			HttpSession session=request.getSession(false);
			//System.out.println("\""+cardno+"\"");
			ResultSet rs=stmt.executeQuery("select * from bank where cardno=\""+cardno+"\"");
			System.out.println(rs);
			//int CVV=Integer.parseInt(rs.getString("cvv"));
			int flag=0;
			while(rs.next()) {
				//System.out.println(rs.getInt("cvv"));
				if(Integer.parseInt(cvv)==rs.getInt("cvv")) {
					System.out.println("22222");
					//System.out.println("\""+cardno+"\"");
					if(Integer.parseInt(expiry_m)==rs.getInt("expiry_month")) {
						System.out.println("3333");
						if(Integer.parseInt(expiry_y)==rs.getInt("expiry_year")) {
							System.out.println("44444");
								System.out.println("55555");
								if(name.equals(rs.getString("card_holder"))) {
									System.out.println("ACCEPTED");
									String email=(String)session.getAttribute("email");
									otpSendMail obj=new otpSendMail((String)session.getAttribute("email"));
									flag=1;
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
									// out.println("ACCEPTED");
									RequestDispatcher rd=request.getRequestDispatcher("enterPaymentOtp.html");
									rd.forward(request,response);
									return;
								}
							
						}
					}
				}
			}
			if(flag==0) {
				out.println("<html><body>Incorrect Details <a href=\"AfterLogin.html\">click here</a> to redirect</html></body>");
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
