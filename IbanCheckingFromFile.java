package ibanCheching;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class IbanCheckingFromFile {

	static ArrayList<String> checkedIbanPlusSemicolon = new ArrayList<String>();
	private static String fileDirectory;

	public IbanCheckingFromFile(String fileDirectory) {

		IbanCheckingFromFile.fileDirectory = fileDirectory;
	}
	
	private static String SavingFilePlace() {

		Path p = Paths.get(fileDirectory);
		String directory = p.getParent().toString();
		return directory;
	}

	private static String OriginalFileName() {

		String fileWithName = fileDirectory.substring(fileDirectory.lastIndexOf("\\") + 1);
		String[] strParts = fileWithName.split("\\.");

		String OriginalFileName = strParts[0];

		return OriginalFileName;
	}

	public static boolean checkingIban() throws IOException {

		for (String units : IbanChecking.getCheckedIban()) {
			if (units == null) {
				System.out.println("File verification failed");
				return false;

			} else {
				checkedIbanPlusSemicolon.add(units + ";");
			}
		}
		if (!checkedIbanPlusSemicolon.isEmpty()) {
			System.out.println("===== Verified file ============== Verified file ============================");
			WritingToFile(checkedIbanPlusSemicolon);
			
		}
		return false;
	}
	public static ArrayList<String> ArrayListClear() {
		checkedIbanPlusSemicolon.removeAll(checkedIbanPlusSemicolon);
		return checkedIbanPlusSemicolon;
		}
	
	public static ArrayList<String> getCheckedIbanPlusSemicolon() {
		//checkedIbanPlusSemicolon.removeAll(checkedIbanPlusSemicolon);
		return checkedIbanPlusSemicolon;
	}
	
	private static void WritingToFile(ArrayList<String> checkedIbanPlusSemicolon) throws IOException {
		String twoSlash = "\\";
		String FileExtension = ".out";

		String savingPlace = SavingFilePlace() + twoSlash + OriginalFileName() + FileExtension;

		System.out.println("Saved file: " + savingPlace + "\n");
		File file = new File(savingPlace);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter WriteToFile = new FileWriter(file.getAbsoluteFile());
		BufferedWriter Writer = new BufferedWriter(WriteToFile);

		for (String iban : checkedIbanPlusSemicolon) {
			Writer.write(iban + System.getProperty("line.separator"));
		}
		Writer.close();

	}
}
