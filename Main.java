
package ibanCheching;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

import ibanCheching.IbanChecking;
import ibanCheching.IbanCheckingFromFile;

public class Main {

	static Scanner reader = new Scanner(System.in);

	static int meniu;

	private static String ibanNumber;

	public static void main(String[] args) {

		mainInput();
		ibanNumber = reader.nextLine();
		switch (meniu) {

		case 0:
			System.out.println("The program is off!");
			System.exit(1);
			break;
		case 1:
			while (true) {
				IbanChecking.checkedIban.clear();
					 new IbanChecking(inputIban());
					 IbanChecking.checkingIban();
					 for (String ibann : IbanChecking.getCheckedIban()) {
					 System.out.println(ibann);	
				}	
			 }
		case 2:

			boolean loop1 = true;
			do {
				if(IbanCheckingFromFile.checkedIbanPlusSemicolon.size()> 0 || IbanChecking.checkedIban.size() > 0) {
				IbanCheckingFromFile.checkedIbanPlusSemicolon.clear();
				IbanChecking.checkedIban.clear();
				}
				
				System.out.println(
						"Enter the path to the file. For example.: C:\\Users\\Viktoras\\Desktop\\IBAN.txt    After entering the path to file, press ENTER. To return to the previous menu, WRITE -1");

				String fileDirectory = reader.nextLine();

				if((fileDirectory =="-1") || (fileDirectory.equals("-1"))) {
					Main.main(null);
				}
				
				ArrayList<String> IbanList = new ArrayList<String>();
						
						try {
					        IbanList = (ArrayList<String>) Files.lines(Paths.get(fileDirectory))
				        			.map(String::trim)
				                    .collect(Collectors.toList());
					        
					IbanList.forEach(System.out::println);

					System.out.println("\n The file has been scanned");
					new IbanCheckingFromFile(fileDirectory);
					//
					new IbanChecking(IbanList);
					 IbanChecking.checkingIban();
					IbanCheckingFromFile.checkingIban();
					
					for (String checkedIbans : IbanCheckingFromFile.getCheckedIbanPlusSemicolon()) {
						System.out.println(checkedIbans);
					}
					loop1 = false;
				
				} catch (Throwable e) {
					System.out.println("Bad path to file!!!");

				}
			} while (loop1);
			
			System.out.println("\n---------------------------------------------------------------");
			main(args);

		default:
			System.out.println("There is no this option!");
			mainInput();
					}					
		}
	
private static int mainInput() {
	boolean loop = true;
	do {
	System.out.println(
			"Enter the desired option:  1 - Interactive IBAN numbers verification, 2 - Checking IBAN numbers from text file.    0 - Exit program. When you press a number - press ENTER ");
			try {
				meniu = reader.nextInt();
				loop = false;

			} catch (InputMismatchException a) {
				System.out.print("No numbers entered! \n");

				reader.next();
			}
		} while (loop);
		return meniu;
	}

		private static ArrayList<String> inputIban()    {
		    ArrayList<String> iban = new ArrayList<String>();
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("Enter IBAN Account Number:");
		ibanNumber = reader.nextLine();
		if((ibanNumber =="-1") || (ibanNumber.equals("-1"))) {
			Main.main(null);
		} else if(ibanNumber.length()> 38){
			ibanNumber="";
			inputIban();
		}else {
	    iban.add(ibanNumber);
		}
	    return(iban);

	}
}