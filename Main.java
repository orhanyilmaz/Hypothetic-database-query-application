import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {
		Person[] f_array = new Person[18750]; 		 // final " get wanted " person array..
		ReadAndWrite rw = new ReadAndWrite();		 // ReadAndWrite object rw..
		
		for (int i = 0; i < 18750; i++) {
			f_array[i] = new Person();
		}
		
		rw.createPerson();							// allocation space for sorted arrays..					
		rw.readFileData(args[0]);					// read data.csv..
		rw.createSortedObjects();					// create sorted (sorted_cID,sorted_sSN, etc.)arrays..

		try {
			File file = new File("output.txt");							//create output.txt
			FileWriter writer = new FileWriter(file);					
			BufferedWriter write = new BufferedWriter(writer);
			int count = 0;
			int length = Files.readAllLines(Paths.get(args[1])).size();
			String[] results = new String[length];
			for (String line : Files.readAllLines(Paths.get(args[1]))) {
				results[count++] = line;
			}

			for (int i = 0; i < length; i++) {							// turn for row count..
				String[] word = results[i].split(" ");
				String[] word2 = word[1].split(",");
				rw.createSortedObjects();								// create sorted array for each line..
				long start = System.currentTimeMillis();
				for (int j = 3; j < word.length; j++) {					// turn for blank " " count..
					rw.readFileCommandAndWrite(f_array, write, length, word, word2, results, j);
				}				
				if (f_array[0].getcID() != 0) {
					rw.writeColumnsAndVariables(write, f_array, word2, results, i);
				} else {
					rw.writeEmpty(write, results, i);
				}
				for (int b = 0; b < 18750; b++) {
					f_array[b].setAddressLine1(null);
					f_array[b].setFirstName(null);
					f_array[b].setCity(null);
					f_array[b].setLastName(null);
					f_array[b].setcID(0);
					f_array[b].setSocialSecurityNumber(0);
				}
				long end = System.currentTimeMillis();
				long time = end - start;
				write.write("---------------------------------------------------------------------");
				write.newLine();
				write.write("Process time: " + time + " miliseconds");
				write.newLine();
				write.newLine();
			}

			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
