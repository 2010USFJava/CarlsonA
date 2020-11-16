package com.revature.ui;

import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.FileHandler;

import com.revature.Meta.LogThis;
import com.revature.Meta.LogThis.LevelEnum;
import com.revature.Meta.RuntimeData;
import com.revature.Users.Customer;
import com.revature.Users.Employee;
import com.revature.Users.LoginInfo;
import com.revature.Users.User;
import com.revature.accounts.AbstractAccount;
import com.revature.accounts.AbstractAccount.AccountStatusEnum;
import com.revature.accounts.JointAccount;



public class BankUI {

	//using data throughout
	private String bankName="Fancy Bank of Holding";
	private final int estYear=2020;
//	private RuntimeData runData= RuntimeData.data;
	
	
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

		int selection=StringCheckUI.numberScanner(options);
		User user=null;
		switch (selection) {
		case 0://new customer
			user=makeNewCustomerLogin(false);
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
	
	private boolean checkIfGoBack() {
		return RuntimeData.data.getGoBack();
	}
	
	
	//new customer log in
		private Customer makeNewCustomerLogin(boolean makeAnotherUser) {
			Customer tempCustomer=null;
			String lName="";
			String username="";
			String password="";
			

			
			//since the user can break out of this at any time, there are multiple if strings
			String fName=StringCheckUI.scannerStringOrGoBack("first name");
			if(!checkIfGoBack()) {
				lName=StringCheckUI.scannerStringOrGoBack("last name");
			}
			
			if (!checkIfGoBack()) {

				System.out.println("Just a moment while we varify your information...");
				tempCustomer=new Customer(fName,lName);				
				System.out.println("User information completed.\nNext please create a username and password");
				
			}
			
			if (!checkIfGoBack()) {
				username=StringCheckUI.scannerStringOrGoBack("username");
//					
			}
			if (!checkIfGoBack()) {
				password=StringCheckUI.scannerStringOrGoBack("password");
					
			}
			if (!checkIfGoBack()) {
				System.out.println("Creating log in information....");
				LoginInfo info=LoginInfo.createLoginInfoAndAddToMap(username, password,tempCustomer);
				System.out.println("Finished creating log in information.");
			}
			
			if(!checkIfGoBack()&&!makeAnotherUser) {
				AbstractAccount act=makeNewAccount(tempCustomer);

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

				System.out.println("Returning information on second account");
				makeAnotherUser=false;
			}

			
			return tempCustomer;	
			
		}
	
		
	//new account
		private AbstractAccount makeNewAccount(Customer tempCustomer) {
			 return makeNewAccount(tempCustomer,false);
		}
		
		private AbstractAccount makeNewAccount(Customer tempCustomer,boolean makeAnotherUser) {

			AbstractAccount tempAcct=null;
			if(!makeAnotherUser) {

				System.out.println("Finally, please select the type of account you would like to create");
				String[] accountOptions=new String[2];
				accountOptions[0]="Create Individual Account";
				accountOptions[1]="Create Joint Account";
				int acctType=StringCheckUI.numberScanner(accountOptions);
				System.out.println("Constructing account...");
				switch (acctType) {
				case 0:
					tempAcct=tempCustomer.createAccount();
					break;
				case 1:
					tempAcct=tempCustomer.createJointAccount();
					JointAccount jointAct=(JointAccount)tempAcct;
					System.out.println("Joint account created.\n Would you like to make the user profile for the second user at this time?");
					String []makeAnotherOptions=new String[3];
					makeAnotherOptions[0]="Yes, make another user and add him or her to this account.";
					makeAnotherOptions[1]="No, do not make another user at this time.";
					makeAnotherOptions[2]="Add an already exising user to this account";

					int makeAnotherUserOption=StringCheckUI.numberScanner(makeAnotherOptions);
					switch (makeAnotherUserOption) {
					case 0:
						makeAnotherUser=true;
						Customer custB=makeNewCustomerLogin(makeAnotherUser);
						if(custB!=null) {
							jointAct.setSecondAccountHolder(custB);
						} else {
							System.out.println("Second user not created");
						}
						break;
					case 1:
						break;
					case 2:

						boolean repeatUserBLogin;
						do {
							repeatUserBLogin=false;
							
							System.out.println("Please have the second user log in");
							String usernameB=StringCheckUI.scannerStringCheck("username");
							String passB=StringCheckUI.scannerStringCheck("password");
							
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
								
								int yesOrNoInt=StringCheckUI.numberScanner(yesOrNoChoice);
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
			return tempAcct;
		}
		
	//log in handlers
		private User login() {
			boolean continueThis=false;
			User userHolder=null;
			do {
				String userName=StringCheckUI.scannerStringCheck("username");
				String password=StringCheckUI.scannerStringCheck("password");
				userHolder= LoginInfo.logIn(userName,password);
				RuntimeData.data.setUser(userHolder);
				
				if(userHolder==null) {
					System.out.println("Login unsucessful. Try again?");
					String[]options=new String[2];
					options[0]="Yes";
					options[1]="No";
					int answer=StringCheckUI.numberScanner(options);
					switch (answer) {
					case 0:
						continueThis=true;
						break;
					case 1:
						continueThis=false;
						break;
					}
					
				} else {
					continueThis=false;
				}
			}while(continueThis==true);
			
			return userHolder;

		}
	
		
	//menus
	//customer
		private void showCustomerMenu(Customer cust) {
			System.out.println("Welcome back, customer.");
			System.out.println(cust.getFullName());
			
			String [] custOption= new String[3];
			custOption[0]="Logout";
			custOption[1]="Enter Account";
			custOption[2]="Create New Account";
			
			int answer=StringCheckUI.numberScanner(custOption);
			switch (answer) {
			case 0:
				startPage();
				break;
			case 1:
				enterAccount(cust);
				break;
			case 2:
				makeNewAccount(cust);
				showCustomerMenu(cust);
				break;

			default:
				break;
			}
		}
		
		private void enterAccount(Customer cust) {
			if (cust.getNumberOfAccounts()==0) {
				System.out.println("No accounts attached to this user");
				showCustomerMenu(cust);
			} else if (cust.getNumberOfAccounts()==1) {
				AbstractAccount act=cust.getUserAccounts().iterator().next();
				modifyAccount(cust,act);
			}else {
				System.out.println("Which account would you like to enter?");
				AbstractAccount act=getAccountFromSelection(cust);
				modifyAccount(cust,act);
			}
		}
		
		private AbstractAccount getAccountFromSelection(Customer cust) {
			String [] accountSelection=new String[cust.getNumberOfAccounts()];

			Iterator actIt = cust.getUserAccounts().iterator(); 
			for(int i=0;i<cust.getNumberOfAccounts();i++) {
				Object acctHolder=actIt.next();
				accountSelection[i]=i+"] "+(AbstractAccount)acctHolder;
			}
			int selection=StringCheckUI.numberScanner(accountSelection);
			
			actIt = cust.getUserAccounts().iterator();
			AbstractAccount act=null;
			for (int i=0;i<=selection;i++) {
				Object acctHolder=actIt.next();
				act=(AbstractAccount)acctHolder;
			}
			return act;
		}
		
		private void modifyAccount(Customer cust, AbstractAccount act) {
			//check if admin in in the account
			boolean adminIn=false;
			Employee emp;
			User loggedInUser=RuntimeData.data.getUser();
			
			if(loggedInUser.checkIfEmployee()) {
				emp=(Employee)loggedInUser;
				if(emp.checkIfAdmin()) {
					adminIn=true;
				}
			}
			
			
			System.out.println(act);
			System.out.println("What would you like to do?");
			
			
			String[]choices=new String[6];
			String[]adminChoices=new String[2];
		
			choices[0]="Make a deposit";
			choices[1]="Make a withdraw";
			choices[2]="Make a transfer";
			choices[3]="Return to customer page";
			choices[4]="Log out and Return to main page";
			choices[5]="Exit";
		
			adminChoices[0]="Return to employee menu";
			adminChoices[1]="Change account status";
			
			int intChoice=9000;
				if (adminIn) {

					intChoice=StringCheckUI.numberScanner(choices,"Admin",adminChoices,true);
							
				}	else {
					intChoice=StringCheckUI.numberScanner(choices);
					
				}	
			long money=0;
			switch (intChoice) {
			case 0:
				System.out.println("How much would you like to deposit?");
				money=StringCheckUI.moneyMiddleMan();
				act.deposit(money);
				LogThis.logIt(LevelEnum.INFO, act.getAccountInformation()+" was depositied into");
				modifyAccount(cust,act);
				
				break;
			case 1:
				System.out.println("How much would you like to withdraw?");
				money=StringCheckUI.moneyMiddleMan();
				act.withdraw(money);
				LogThis.logIt(LevelEnum.INFO, act.getAccountInformation()+" was withdrawed from");
				modifyAccount(cust,act);
				break;
			case 2:
				if (cust.getNumberOfAccounts()<=1) {
					System.out.println("Can not tranfer money unless customer has more than 1 account");
				} else {
					System.out.println("Which account would you like to transfer to?");
					AbstractAccount actB=getAccountFromSelection(cust);
					System.out.println("How much would you like to transfer?");	
					money=StringCheckUI.moneyMiddleMan();
					act.transferMoneyToAccount(actB, money);
					LogThis.logIt(LevelEnum.INFO, act.getAccountInformation()+" transfered money to "+actB.getAccountInformation());
				}
				
				modifyAccount(cust,act);
				break;
			case 3:
				showCustomerMenu(cust);
				break;
			case 4:
				startPage();
				break;
			case 5:
				break;
			case 6:
				if(!adminIn) {

					System.out.println(intChoice+"was not an applicable option");
					modifyAccount(cust,act);
				} else {
					showEmployeeMenu((Employee)RuntimeData.data.getUser());
				}
				break;
			case 7://"Change account status";
				if(!adminIn) {

					System.out.println(intChoice+"was not an applicable option");
					modifyAccount(cust,act);
				} else {
					adminChangeAccountStatus(act,cust);
					
					modifyAccount(cust,act);
				}
				break;
			default:
				System.out.println(intChoice+"was not an applicable option");
				modifyAccount(cust,act);
				break;
			}
			
			
		}
		
		//employee menus
		
		
		public void adminChangeAccountStatus(AbstractAccount act, Customer cust) {
			String[] actStatusOptions=new String[4];
			actStatusOptions[0]="Change Nothing";
			actStatusOptions[1]="Set to Open";
			actStatusOptions[2]="Set to Closed";
			actStatusOptions[3]="Set to Rejected";
			int statusOptInt=StringCheckUI.numberScanner(actStatusOptions);
			
			switch (statusOptInt) {
			case 0:
				break;
			case 1:
				act.changeStatus(AccountStatusEnum.OPEN);
				break;
			case 2:
				act.changeStatus(AccountStatusEnum.CLOSED);
				break;
			case 3:
				act.changeStatus(AccountStatusEnum.REJECTED);	
				break;
			default:
				break;
			}
			
		}
		
		private void getNextApplicationInQueue() {
			AbstractAccount act = AbstractAccount.getNextApplication();

			if (act == null) {
				System.out.println("No applications in the queue at this time.");
			} else {

				System.out.println("What would you like to do with the following application?");
				System.out.println(act);
				String[] option = new String[2];
				option[0] = "Accept";
				option[1] = "Reject";
				int answerInt = StringCheckUI.numberScanner(option);
				switch (answerInt) {
				case 0:
					act.changeStatus(AccountStatusEnum.OPEN);
					break;
				case 1:
					act.changeStatus(AccountStatusEnum.REJECTED);
					break;
				default:
					break;
				}
			}
		}
		
		private void showEmployeeMenu(Employee emp) {
			System.out.println("Welcome to the employee screen.");
			System.out.println(emp);
			System.out.println("What would you like to do?");
			String[]option=new String[3];
			option[0]="Return to main menu";
			option[1]="Approve/Reject Applications";
			option[2]="View customer/account information";
			
			
			int answerInt=StringCheckUI.numberScanner(option);
			
			switch (answerInt) {
			case 0:
				startPage();
				break;
			case 1:
				getNextApplicationInQueue();
				showEmployeeMenu(emp);
				break;
			case 2://"View customer/account information";
				System.out.println("What would you like to do");
				String[] options=new String[2];
				options[0]="Enter username to pull up customer info and account list.";
				options[1]="Return to Employee Menu";
//				options[1]="Show list of all customers";
//				options[2]="Show list of all accounts";
				int optionNum=StringCheckUI.numberScanner(options);
				switch (optionNum) {
				case 0:
					String username= StringCheckUI.scannerStringCheck("customer username");
					User user = LoginInfo.employeeCheckingUserInfo(username);
					
					employeeCheckSelectedUserInfo(user);
					

					break;
				case 1:
					showEmployeeMenu(emp);
					break;
//				case 2:
//					
//					break;

				default:
					System.out.println("No such option avaliable");
					showEmployeeMenu(emp);
					break;
				}
				
				break;
			default:
				System.out.println("This option has not yet been programmed. Returning to employee menu.");
				showEmployeeMenu(emp);
				break;
			}
			
		}
		

		public void employeeCheckSelectedUserInfo(User user){

			if(user.checkIfCustomer()) {
				Customer cust=(Customer) user;
				System.out.println(cust);
				
				System.out.println("What would you like to do?");
				String []options=new String[3];
				
				options[0]="View Account Information";
				options[1]="Go Back To Employee Menu";
				options[2]="Go To Main Menu";
				
				int optionInt=StringCheckUI.numberScanner(options);
				
				switch (optionInt) {
				case 0:
					System.out.println("Which account would you like to enter?");
					AbstractAccount act=getAccountFromSelection(cust);
					//check Employee status
					Employee emp=(Employee)RuntimeData.data.getUser();
					if(emp.checkIfAdmin()) {
						modifyAccount(cust,act);
					}else {

						System.out.println("=============================================");
						employeeCheckSelectedUserInfo(cust);
							
					}
					break;
				case 1:
					try {
						showEmployeeMenu((Employee)RuntimeData.data.getUser());
					} catch(Exception e) {
						System.out.println("An Error has occured. Returning to main menu");
						LogThis.logIt(LevelEnum.ERROR, e.toString());
						startPage();
					}
					break;

				case 2:
					startPage();
					break;

				default:
					break;
				}
				
				
				
			} else {
				System.out.println("Can not view employee accounts");
			}	
		}
		

}
