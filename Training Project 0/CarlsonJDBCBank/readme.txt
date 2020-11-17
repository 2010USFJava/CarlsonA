First of all, this program is backed by postgres. In the resources folder is 'bankOfHolding.sql'. Executing that script in DBeaver should reproduce the appropriate backend database.
Upon opening the java file: Main.java is located in the folder com.revature.driver
There is a tabbed out line of text that says:
 //bank.addTestData();
Untab that on the first run to add two employees of different levels: the usernames will be 'admin', and 'emp', and the password for both will be pass.
	Note: This method should be retabbed after the first run, but untabed if the sql database is remade.
After the database is built and the testData is added, the program should be simply run by calling the main method from them on.
