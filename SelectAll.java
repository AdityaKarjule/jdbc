import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectAll {
	public static void main(String[] args) throws ClassNotFoundException {
		
		try {
			System.out.println("Registering driver...");    
			Class.forName("com.mysql.cj.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			System.out.println("Driver registered....");
			
			System.out.println("Trying to connect to the DB");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/java_training", "root", "Aditya@9909");
			
			System.out.println("Connected to the DB : "+conn);
			
			System.out.println("trying to make a statment");
			Statement statement = conn.createStatement();
			System.out.println("Statement created : "+statement);
			
			System.out.println("Trying to execute the statement....");
			ResultSet rs = statement.executeQuery("SELECT * FROM EMP");
			System.out.println("Statement executed, got the result....");
				
			while(rs.next()) {
				int empno = rs.getInt(1) ;
				String ename = rs.getString(2) ;
				String job = rs.getString(3);
				System.out.println("EMP NO   : "+empno);
				System.out.println("EMP NAME : "+ename);
				System.out.println("EMP SAL  : "+job);
				System.out.println("------------------------");
			}
			rs.close();	statement.close();	conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		    
	}
}











