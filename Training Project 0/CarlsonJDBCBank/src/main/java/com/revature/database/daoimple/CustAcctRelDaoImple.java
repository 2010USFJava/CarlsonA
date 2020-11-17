package com.revature.database.daoimple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.revature.Users.Customer;
import com.revature.Users.Employee;
import com.revature.Users.Employee.EmployeeLevelEnum;
import com.revature.Users.LoginInfo;
import com.revature.Users.User;
import com.revature.accounts.AbstractAccount;
import com.revature.accounts.AbstractAccount.AccountStatusEnum;
import com.revature.accounts.AbstractAccount.AccountTypeEnum;
import com.revature.accounts.CustomerAccountRelationship;
import com.revature.accounts.JointAccount;
import com.revature.accounts.SingleAccount;
import com.revature.database.dao.CustomerAccountRelationshipDAO;
import com.revature.database.factory.ConnFactory;

public class CustAcctRelDaoImple implements CustomerAccountRelationshipDAO {
	   public static ConnFactory cf = ConnFactory.getInstance();
	   private static ArrayList<Customer> customerList;
	   private static ArrayList<Employee> empList;
//	   private static Queue<AbstractAccount> applicationQueue;
	   
//this Also loads login info
	public static  void loadDatabaseInfo() throws SQLException {
		//employee list is loaded so the log in info is up to date
		getEmployeeList();


		
		//generate customer list for future iteration
		getCustomerList();
		for (Customer cust: customerList) {
			Set<AbstractAccount> actSet=getAccountSetByCust(cust);
			for (AbstractAccount act: actSet) {

				CustomerAccountRelationship.updateCustomer(cust, act);
			}	
		}
	}


	
	public static ArrayList<Employee> getEmployeeList() throws SQLException{
		if (empList==null){
		ArrayList<Employee> employeeList = new ArrayList<>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		String sql="select users.*,employee_level_relationships.level_id,logininfos.login_username,logininfos.login_password "
				+ "from employee_level_relationships,users,logininfos "
				+ "where employee_level_relationships.employee_id=users.id and users.id=logininfos.user_id";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			int id = rs.getInt(1);
			String fName=rs.getString(2);
			String lName=rs.getString(3);
			EmployeeLevelEnum level = EmployeeLevelEnum.values()[rs.getInt(4)];
			String username = rs.getString(5);
			String password = rs.getString(6);
			Employee emp;
			if(level.equals(EmployeeLevelEnum.ADMIN)) {
				emp =Employee.createAdmin(id,fName,lName,username,password);
				employeeList.add(emp);
			} else {
				emp=Employee.createEmployee(id,fName,lName,username,password);
				employeeList.add(emp);
			}
			//updateLoginInfoMap
			LoginInfo.createLoginInfoAndAddToMap(username,password,emp);
		}
		empList=employeeList;
		}
		return empList;
	
	}
	
	public static ArrayList<Customer> getCustomerList() throws SQLException{
		if (customerList==null) {
		ArrayList<Customer> custList = new ArrayList<>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		String sql="select users.*,logininfos.login_username,logininfos.login_password "
				+ "from customers,users,logininfos "
				+ "where customers.customer_id=users.id and users.id=logininfos.user_id;";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			int id = rs.getInt(1);
			String fName=rs.getString(2);
			String lName=rs.getString(3);
			String username = rs.getString(4);
			String password = rs.getString(5);
			Customer cust = new Customer(id,fName,lName,username,password);
			custList.add(cust);
			//update Login info at the same time
			LoginInfo.createLoginInfoAndAddToMap(username,password,cust);
		}
		customerList=custList;
		}
		return customerList;
	
	}
	

	
	public static Set<AbstractAccount>getAccountSetByCust(Customer cust) throws SQLException{
		int cutomerId=cust.getUserId();
		Set<AbstractAccount>acctSet = new HashSet<>();
		Connection conn = cf.getConnection();
		String sql = "select accounts.* "
				+ "from accounts,customer_account_relationships cust "
				+ "where accounts.id=cust.account_id and cust.customer_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,cutomerId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int id=rs.getInt(1);
			AccountStatusEnum status = AccountStatusEnum.values()[rs.getInt(2)];
			AccountTypeEnum type = AccountTypeEnum.values()[rs.getInt(3)];
			long balance = rs.getLong(4);
			
			AbstractAccount acct;
			if (type.equals(AccountTypeEnum.SINGLE)) {
				
				acct=(new SingleAccount(id,balance,cust,status));	
				acctSet.add(acct);
			} else {
				acct=(new JointAccount(id,balance,cust,status));	
				acctSet.add(acct);
			}
			
			if(status.equals(AccountStatusEnum.IN_APPLICATION)) {
//				applicationQueue.add(acct);
			}
		}
		
		for (AbstractAccount each:acctSet) {
			each.setCustomerSet(getCustomerSetByAccount(each));
			
		}
		return acctSet;
		
	}

	private static Set<Customer> getCustomerSetByAccount(AbstractAccount account) throws SQLException {
		int actId=account.getId();
		ArrayList<Integer> relevantCustomerIds = new ArrayList<>();
		Connection conn = cf.getConnection();
		String sql = "select users.id "
				+ "from users,customer_account_relationships car "
				+ "where users.id=car.customer_id and car.account_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,actId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			relevantCustomerIds.add(rs.getInt(1));
		}
		getCustomerList();
		Set<Customer>custSet = new HashSet<>();
		for(int each: relevantCustomerIds) {
			for(Customer cust: customerList) {
				if (cust.getUserId()==each) {
					custSet.add(cust);
					break;
				}
			}
		}
		return custSet;
		
	}
//	
//	public void catchNewPookimans(Pookiemans p) throws SQLException {
//		Connection conn = cf.getConnection();
//
//        //PreparedStatement way
//        String sql= "insert into pookiemans values(?,?)";
//        PreparedStatement ps= conn.prepareStatement(sql);
//        ps.setInt(1, p.getpId());
//        ps.setString(2, p.getpName());
//        ps.executeUpdate();
//	}
//	
	
	//pulled info on returning keys from here:
//www.baeldung.com/jdbc-returning-generated-keys
	public static void insertUserIntoDatabase(User user) throws SQLException {
		Connection conn = cf.getConnection();
		String sql="insert into users (first_name,last_name) "
				+ "values(?,?) RETURNING id";
		PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1,user.getFirstName());
		ps.setString(2,user.getLastName());
		int affectedRows=ps.executeUpdate();
		ResultSet keys=ps.getGeneratedKeys();
		int userId=-1;
		while(keys.next()) {
			userId=keys.getInt(1);
		}
		//in future database downloads this will be automatic, but
		//for work done before the next update, I'll set the new id here.
		user.setUserId(userId);
		//Do the password protection measures, I can't access it anywhere outside
		//of the inital class for saving purposes
		user.getLoginInfo().saveUserInfoToDatabase();
		

		
		if (user.checkIfCustomer()) {
			runCustomerTableUpdates((Customer)user);
		} else if(user.checkIfEmployee()) {
			runEmployeeTableUpdates((Employee)user);
		}
		
		
	}
	
	
	private static void runCustomerTableUpdates (Customer user) throws SQLException {
		Connection conn = cf.getConnection();
		String sql ="insert into customers values (?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,user.getUserId());
		ps.executeUpdate();
		
	}



	private static void runEmployeeTableUpdates(Employee user) throws SQLException {
		Connection conn = cf.getConnection();

		String sql ="insert into employee_level_relationships values (?,?);";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,user.getLevelAsInt());
		ps.setInt(2,user.getUserId());
		ps.executeUpdate();
		
	}
	
	//pulled info from here:
	//stackoverflow.com/questions/2944297/postgresql-function-for-last-inserted-id
//	private static int getIdOfLastUserInsert() {
//		String sql="lastval()";
//	}

}
