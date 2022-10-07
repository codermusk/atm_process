package src.main;

import java.util.List;
import java.util.Scanner;

import src.atm_model.ATMBal;
import src.atm_model.ATM_User;
import file.Generator;
import file.Loader;

public class Driver {
	
	static Scanner sc = new Scanner(System.in);
	
	public static ATMBal loadMoney(int _2000,  int _500, int _100, ATMBal current) {
		if(current == null) {
			current = new ATMBal();
		}
		return new ATMBal(current.get_2000()+_2000,current.get_500()+_500,current.get_100()+_100);
	}
	
	public static void printUsers(List<ATM_User> users) {
		System.out.println("Account" + "\t" + "Name" + "\t" + "Balance");
		users.forEach(System.out::println);
	}
	
	public static void showOperation(ATMBal bal,ATM_User user,List<ATM_User> users) {
		int choice,loop = 1;
		
		while(loop == 1) {
			System.out.println("Select your option");
			System.out.println("1. Check Balance");
			System.out.println("2. Withdraw Money");
			System.out.println("3. Transfer Money");
			System.out.println("4. Back");
			System.out.print("Enter Your Choice: ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					System.out.println("\n***************************************************");
					System.out.println("Balance: "+ user.getBalance());
					System.out.println("***************************************************");
					break;
				case 2:
					System.out.println("\n***************************************************");
					System.out.println("Enter the Amount: ");
					int amt = sc.nextInt();
					if(user.getBalance() >= amt ) {
						if((amt >= 100) && (amt <= 10000)) {
							if(amt > bal.balance()) {
								System.out.println("ATM is not having that amount");
							} else {
								if(bal.withdraw(amt)) {
									user.setBalance(user.getBalance()-amt);
									System.out.println("Done");
								} else {
									System.out.println("Transaction Failed.");
								}
							}
						} else {
							System.out.println("Enter amount between 100 and 10,000.");
						}
					} else {
						System.out.println("Insufficent Balance.");
					}
					System.out.println("***************************************************");
					break;
				case 3:
					System.out.println("\n***************************************************");
					System.out.println("Enter the Account Number: ");
					int accno = sc.nextInt();
					System.out.println("Enter the Amount: ");
					int amot = sc.nextInt();
					boolean isTranfered = false;
					if(user.getBalance() >= amot ) {
						for(ATM_User usr : users) {
							if(usr.getAccNo() == accno) {
								isTranfered = true;
								usr.setBalance(usr.getBalance()+amot);
								user.setBalance(user.getBalance()-amot);
								break;
							}
						}
						if(isTranfered) {
							System.out.println("Done");
						} else {
							System.out.println("Transaction Failed.");
						}
					} else {
						System.out.println("Insufficent Balance.");
					}
					System.out.println("***************************************************");
					break;
				case 4:
					loop = 0;
					break;
				default:
					System.err.println("Invalid Input");
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ATMBal bal;
		List<ATM_User> users;
		
		bal = (ATMBal)Loader.load(".\\res\\bal.bat");
		users = (List<ATM_User>)Loader.load(".\\res\\user-info.bat");
		
		int choice,loop = 1;
		
		while(loop == 1) {
			System.out.println("Select your option");
			System.out.println("1. Load Cash to ATM");
			System.out.println("2. Show Customer Details");
			System.out.println("3. Show ATM Operations");
			System.out.println("4. Show ATM Balance");
			System.out.println("5. Upate changes");
			System.out.println("6. Exit");
			System.out.print("Enter Your Choice: ");
			choice = sc.nextInt();
			
			switch (choice) {
				case 1:
					System.out.println("\n***************************************************");
					System.out.print("Enter no.of 2000 notes: ");
					int _2000 = sc.nextInt();
					System.out.print("Enter no.of 500 notes: ");
					int _500 = sc.nextInt();
					System.out.print("Enter no.of 100 notes: ");
					int _100 = sc.nextInt();
					bal = loadMoney(_2000, _500, _100, bal);
					System.out.println("Done");
					System.out.println("***************************************************");
					break;
					
				case 2:
					System.out.println("\n***************************************************");
					printUsers(users);
					System.out.println("***************************************************");
					break;
					
				case 3:
					
					System.out.print("Enter your Account Number: ");
					int accno = sc.nextInt();
					System.out.print("Enter your ATM pin: ");
					boolean isFound = false;
					int pin = sc.nextInt();
					
					for(ATM_User user : users) {
						if((user.getAccNo() == accno) && user.checkPin(pin)) {
							isFound = true;
							showOperation(bal, user, users);
							break;
						}
					}
					
					if(!isFound) {
						System.out.println("Invaild name or pin. Try again");
					}
					break;
					
				case 4:
					System.out.println("\n***************************************************");
					System.out.println(bal);
					System.out.println("***************************************************");
					break;
					
				case 5:
					System.out.println("\n************");
					Generator.save(bal, ".\\res\\bal.bat");
					Generator.save(users, ".\\res\\user-info.bat");
					System.out.println("*   Done   *");
					System.out.println("************");
					break;
				
				case 6:
					loop = 0;
					break;
					
				default:
					System.err.println("Invalid Input");
			}
			System.out.println();
		}
		sc.close();
	}
}
