package com.Revature.Meta;

import java.util.Scanner;

import com.Revature.AccountInfo.Account;
import com.Revature.AccountInfo.JointAccount;
import com.Revature.Users.Customer;
import com.Revature.Users.Employee;
import com.Revature.Users.LoginInfo;
import com.Revature.Users.User;

public class Bank {
	
	//using data throughout
	private String bankName="Fancy Bank of Holding";
	private final int estYear=2020;
	private static Scanner scan=new Scanner(System.in);
	private RuntimeData runData= new RuntimeData();
	
	
	public void startPage() {
		System.out.println("Welcome to "+bankName);
		System.out.println("Est. "+estYear);
		selectUserType();
		
	}
	
	private void selectUserType() {
		String[] options = new String[3];
		options[0] = "I am a new customer";
		options[1] = "I am a returning user";
		options[2]="Exit";
		

		System.out.println("What type of user are you?");

		int selection=StringCheck.numberScanner(options);
		User user=null;
		switch (selection) {
		case 0://new customer
			user=makeNewCustomerLogin();
			startPage();
			break;
		case 1://returning user
			user = login();
			if (user==null) {
				startPage();
			} else {
				if(user.checkIfCustomer()) {
					showCustomerMenu((Customer)user);
				} else if(user.checkIfEmployee()) {
					showEmployeeMenu((Employee)user);
				} else {
					System.err.println("No such user has been acounted for. Please contact support");
					
				}
			}
			break;
		case 2://exit
			System.out.println("Thank you for your business.");
			break;
		default: //Unprogrammed
			System.out.println("Unfortunantly that has not been added yet. Please reselect.");
			selectUserType();
			break;
		}
		
		
		
	}
	
	public static String scanner0ForMain1ForForgotPassword(String searchItem) {
		//Option 1 should be a stretchgoal now
		
		
		System.out.println("Press 0 to cancel and go back to the main menu");
//		System.out.println("Press 1 if you do not have Login Credentials, or have forgotten them");
		System.out.println("Please enter your "+searchItem);
		
		RuntimeData runData=RuntimeData.data;
		runData.setGoBack(false);
		runData.setLoginProblems(false);
		String stringAnswer = scan.nextLine().trim();
		//Opted to try parsing instead of "0" since user could enter 0000000 or the like
				boolean userEnteredNumber=true;
				int intAnswer=9000;
				try {
					intAnswer=Integer.parseInt(stringAnswer);
					
				} catch(NumberFormatException e) {
					userEnteredNumber=false;
				
					if (userEnteredNumber) {
//						Making this a switch in case of future updates that move beyound the 0 option
						switch (intAnswer) {
						case 0:
							runData.setGoBack(true);
							break;
//						case 1:
//							runData.setLoginProblems(true);
//							break;
						default:
							boolean passFobiddenNumbers=StringCheck.passForbbidenNumberCheck(intAnswer);
//							If fails to pass forbidden number check run again
							if(!passFobiddenNumbers) {
								stringAnswer=scanner0ForMain1ForForgotPassword(searchItem);
							}
							break;
						}
					}
				}
				return stringAnswer;
	}

	
	//returning user
	private User login() {
		boolean loginProblems=false;
		
		String userName=scanner0ForMain1ForForgotPassword("username");
		if(runData.getGoBack()) {
			loginProblems();
			
		} else {

			String password=scanner0ForMain1ForForgotPassword("password");
			if(runData.getGoBack()) {
				loginProblems();
			} else {

				return LoginInfo.logIn(userName,password);
				
			}
			
		}
		
		return null;
	}
	
	//user has lost or is missing log in credentials
	private void loginProblems() {
		String [] problemsOptions=new String[3];
		problemsOptions[0]="I selected this by accident. Send me back";
		problemsOptions[1]="I never made log-in information.";
		problemsOptions[2]="I forgot my username or password.";
		
		System.out.println("You've hit 0. What is your problem?");
		int selection=StringCheck.numberScanner(problemsOptions);
		
		switch (selection) {
		case 0:
			System.out.println("WIP");
			break;
		case 1:

			System.out.println("WIP");
			break;
		case 2:

			System.out.println("WIP");
			break;

		default:
			System.out.println("This selection has not been implemented at this time. Please try again");
			loginProblems();
			break;
		}
		
	}
	
	//new customer
	private Customer makeNewCustomerLogin() {
		Customer tempCustomer=null;
		Customer tempCustPrimary=null;
		String lName="";
		String mName="";
		String username="";
		String password="";
		boolean makeAnotherUser=false;
		
		do {

		if(makeAnotherUser) {
			tempCustPrimary=tempCustomer;
			
		}
		//currently setting this to only run once as only two users can be added. But should be altered in case of 2+ upgrade
		makeAnotherUser=false;
		
		//since the user can break out of this at any time, there are multiple if strings
		String fName=StringCheck.scannerStringOrGoBack("first name");
		if(!checkIfGoBack()) {
			lName=StringCheck.scannerStringOrGoBack("last name");
		}
		if (!checkIfGoBack()) {
			 mName=StringCheck.scannerStringOrGoBack("middle name",true);
				
		}
		if (!checkIfGoBack()) {

			System.out.println("Just a moment while we varify your information...");
			
			if(runData.getSkipStep()) {
				
				tempCustomer=new Customer(fName,lName);
			} else {
				tempCustomer=new Customer(fName,mName,lName);
			}
			System.out.println("User information completed.\nNext please create a username and password");
			
		}
		if (!checkIfGoBack()) {
			username=StringCheck.scannerUsernameStringOrGoBack();
//				
		}
		if (!checkIfGoBack()) {
			//Check username and to see if we will continue to password.
			password=StringCheck.scannerStringOrGoBack("password");
				
		}
		if (!checkIfGoBack()) {
			System.out.println("Creating log in information....");
			LoginInfo info=new LoginInfo(username, password,tempCustomer);
			info.addToLoginMapAndSave();
			
			System.out.println("Finished creating log in information.");
		}
		
		if(!checkIfGoBack()) {
			Account act=makeANewAccount(tempCustomer);
		}
		
		if(!makeAnotherUser) {

			if(checkIfGoBack()) {
				System.out.println("Canceling User & Account information...");
				System.out.println("Returning to main menu...");
				return null;
			} 
		} else {
			if(checkIfGoBack()) {
				System.out.println("Canceling Second User Creation...");
				System.out.println("Second user can make an account at a later time.");
			}

			System.out.println("Returning information on primary account holder");
			tempCustomer=tempCustPrimary;
		}

		}while(makeAnotherUser);
		
		return tempCustomer;	
		
	}
	
	private Account makeANewAccount(Customer tempCustomer) {

		Account tempAcct=null;
		boolean makeAnotherUser=false;
		if(!makeAnotherUser) {

			System.out.println("Finally, please select the type of account you would like to create");
			String[] accountOptions=new String[2];
			accountOptions[0]="Create Individual Account";
			accountOptions[1]="Create Joint Account";
//			accountOptions[2]="Join a joint account";
			int acctType=StringCheck.numberScanner(accountOptions);
			switch (acctType) {
			case 0:
				tempAcct=tempCustomer.createAccount();
				break;
			case 1:
				tempAcct=tempCustomer.createJointAccount();
				System.out.println("Joint account created.\n Would you like to make the user profile for the second user at this time?");
				String []makeAnotherOptions=new String[3];
				makeAnotherOptions[0]="Yes, make another user and add him or her to this account.";
				makeAnotherOptions[1]="No, do not make another user at this time.";
				makeAnotherOptions[2]="Add an already exising user to this account";

				int makeAnotherUserOption=StringCheck.numberScanner(makeAnotherOptions);
				switch (makeAnotherUserOption) {
				case 0:
					makeAnotherUser=true;
					break;
				case 1:
					makeAnotherUser=false;
					break;
				case 2:

					boolean repeatUserBLogin;
					do {
						repeatUserBLogin=false;
						
						System.out.println("Please have the second user log in");
						String usernameB=StringCheck.scannerStringCheck("username");
						String passB=StringCheck.scannerStringCheck("password");
						
						if(LoginInfo.checkIfUsernameIsTaken(usernameB)){
							User userB=LoginInfo.logIn(usernameB, passB);
							if (userB!=null) {

								if (userB.checkIfCustomer()) {
								JointAccount tempJoint= (JointAccount)tempAcct;
								tempJoint.setSecondAccountHolder((Customer)userB);
									
								} else if (userB.checkIfEmployee()) {
									System.out.println("Cannot add employee to account.");
									repeatUserBLogin=true;
								}
							} else {
								repeatUserBLogin=true;
							}
							
						} else {
							repeatUserBLogin=true;
						}
						
						if(repeatUserBLogin) {
							System.out.println("Second User Login Failed.\nWould you like to try again?");
							String[] yesOrNoChoice=new String[2];
							yesOrNoChoice[0]="Yes";
							yesOrNoChoice[1]="No";
							
							int yesOrNoInt=StringCheck.numberScanner(yesOrNoChoice);
							if(yesOrNoInt==1) {
								repeatUserBLogin=false;
							}
							
						}
						
					} while(repeatUserBLogin);
					break;
					

				default:
					break;
				}
				
				
				break;

			default:
				break;
			}

		}
		FileHandler.saveAll();
		return tempAcct;
	}
	
	private boolean checkIfGoBack() {
		return RuntimeData.data.getGoBack();
	}
	
	private void showCustomerMenu(Customer cust) {
		System.out.println("Welcome back, customer.");
		System.out.println(cust);
		
		String [] custOption= new String[3];
		custOption[0]="Logout";
		custOption[1]="Enter Account";
		custOption[2]="Create New Account";
		
		int answer=StringCheck.numberScanner(custOption);
		switch (answer) {
		case 0:
			startPage();
			break;
		case 1:
			System.out.println("WIP");
			break;
		case 2:
			makeANewAccount(cust);
			showCustomerMenu(cust);
			break;

		default:
			break;
		}
	}
	
	private void showEmployeeMenu(Employee emp) {
		System.out.println("Welcome back, employee.");
		System.out.println(emp);
	}
	
	
	
	
	
	
	
	
	

}
