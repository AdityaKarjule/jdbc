import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectWhereTest {
	public static void main(String[] args) {
		
		try {
			System.out.println("Registering driver...");    
			DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
			System.out.println("Driver registered....");
			
			System.out.println("Trying to connect to the DB");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
			
			System.out.println("Connected to the DB : "+conn);
			
			System.out.println("trying to make a statment");
			Statement statement = conn.createStatement();
			System.out.println("Statement created : "+statement);
			
			System.out.println("Trying to execute the statement....");
			int eno = 0;
			Scanner scan = new Scanner(System.in);
			eno=scan.nextInt();
			ResultSet rs = statement.executeQuery("SELECT * FROM EMPLOYEE WHERE EMPNO="+eno);
			System.out.println("Statement executed, got the result....");
				
			
			
			if(rs.next()) {
				int empno = rs.getInt(1) ;
				
				String ename = rs.getString(2) ;
				int sal = rs.getInt(3);
				System.out.println("EMP NO   : "+empno);
				System.out.println("EMP NAME : "+ename);
				System.out.println("EMP SAL  : "+sal);
				System.out.println("------------------------");
			}
				else {
					throw new EmployeeNotFoundException(eno+"Employee Not Found");
					
				}
			
			
			rs.close();	statement.close();	conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		    
	}
}











