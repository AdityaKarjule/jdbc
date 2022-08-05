import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {
	public static void main(String[] args) throws EmployeeAlreadyExists
	{
		
		try {
			System.out.println("Registering driver...");    
			DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
			System.out.println("Driver registered....");
			
			System.out.println("Trying to connect to the DB");
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "");
			
			System.out.println("Connected to the DB : "+conn);
			
			
			Scanner scan1 = new Scanner(System.in);
			System.out.println("Enter employee number to delete  : ");
			int eno = scan1.nextInt();
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from employee where empno="+eno);
			
			if(rs.next()) {
				
				System.out.println("trying to make a Prepared statment");
				PreparedStatement pst = conn.prepareStatement("DELETE FROM EMPLOYEE WHERE EMPNO=?");
				System.out.println("Prepared Statement created : "+pst);
				pst.setInt(1, eno);
				
				System.out.println("Trying to execute the prepared statement....");
				int rows = pst.executeUpdate();
				
				System.out.println("Prepred Statement executed, deleted the records :..."+rows);
					
				
				pst.close();	conn.close();
				
			}
			else {
				EmployeeNotFoundException empNotFndEx = new EmployeeNotFoundException("Employee NOT found exception : "+eno);
				throw empNotFndEx;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		    
	}
}






