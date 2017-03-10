import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadAndWrite {

	Person[] person = new Person[18750];
	Person[] sorted_cIDs = new Person[18750];
	Person[] sorted_firstName = new Person[18750];
	Person[] sorted_lastName = new Person[18750];
	Person[] sorted_city = new Person[18750];
	Person[] sorted_addressLine1 = new Person[18750];
	Person[] sorted_sSN = new Person[18750];

	public void createPerson() {
		for (int i = 0; i < 18750; i++) {
			person[i] = new Person();
			sorted_cIDs[i] = new Person();
			sorted_firstName[i] = new Person();
			sorted_lastName[i] = new Person();
			sorted_city[i] = new Person();
			sorted_addressLine1[i] = new Person();
			sorted_sSN[i] = new Person();
		}
	}

	public void createSortedObjects() {

		for (int i = 0; i < 18750; i++) {
			sorted_cIDs[i] = new Person();
			sorted_firstName[i] = new Person();
			sorted_lastName[i] = new Person();
			sorted_city[i] = new Person();
			sorted_addressLine1[i] = new Person();
			sorted_sSN[i] = new Person();
		}

		Person[] cIDs = sortCIDs(0, 18749);

		for (int i = 0; i < 18750; i++) {
			sorted_cIDs[i].setcID(cIDs[i].getcID());
			sorted_cIDs[i].setFirstName(cIDs[i].getFirstName());
			sorted_cIDs[i].setLastName(cIDs[i].getLastName());
			sorted_cIDs[i].setCity(cIDs[i].getCity());
			sorted_cIDs[i].setAddressLine1(cIDs[i].getAddressLine1());
			sorted_cIDs[i].setSocialSecurityNumber(cIDs[i].getSocialSecurityNumber());
		}

		Person[] firstName = sortFirstName(0, 18749);

		for (int i = 0; i < 18750; i++) {
			sorted_firstName[i].setcID(firstName[i].getcID());
			sorted_firstName[i].setFirstName(firstName[i].getFirstName());
			sorted_firstName[i].setLastName(firstName[i].getLastName());
			sorted_firstName[i].setCity(firstName[i].getCity());
			sorted_firstName[i].setAddressLine1(firstName[i].getAddressLine1());
			sorted_firstName[i].setSocialSecurityNumber(firstName[i].getSocialSecurityNumber());
		}

		Person[] lastName = sortLastName(0, 18749);

		for (int i = 0; i < 18750; i++) {
			sorted_lastName[i].setcID(lastName[i].getcID());
			sorted_lastName[i].setFirstName(lastName[i].getFirstName());
			sorted_lastName[i].setLastName(lastName[i].getLastName());
			sorted_lastName[i].setCity(lastName[i].getCity());
			sorted_lastName[i].setAddressLine1(lastName[i].getAddressLine1());
			sorted_lastName[i].setSocialSecurityNumber(lastName[i].getSocialSecurityNumber());
		}

		Person[] city = sortCity(0, 18749);

		for (int i = 0; i < 18750; i++) {
			sorted_city[i].setcID(city[i].getcID());
			sorted_city[i].setFirstName(city[i].getFirstName());
			sorted_city[i].setLastName(city[i].getLastName());
			sorted_city[i].setCity(city[i].getCity());
			sorted_city[i].setAddressLine1(city[i].getAddressLine1());
			sorted_city[i].setSocialSecurityNumber(city[i].getSocialSecurityNumber());
		}

		Person[] addressLine1 = sortAddressLine1(0, 18749);

		for (int i = 0; i < 18750; i++) {
			sorted_addressLine1[i].setcID(addressLine1[i].getcID());
			sorted_addressLine1[i].setFirstName(addressLine1[i].getFirstName());
			sorted_addressLine1[i].setLastName(addressLine1[i].getLastName());
			sorted_addressLine1[i].setCity(addressLine1[i].getCity());
			sorted_addressLine1[i].setAddressLine1(addressLine1[i].getAddressLine1());
			sorted_addressLine1[i].setSocialSecurityNumber(addressLine1[i].getSocialSecurityNumber());
		}

		Person[] sSN = sortSocialSecurityNumbers(0, 18749);

		for (int i = 0; i < 18750; i++) {
			sorted_sSN[i].setcID(sSN[i].getcID());
			sorted_sSN[i].setFirstName(sSN[i].getFirstName());
			sorted_sSN[i].setLastName(sSN[i].getLastName());
			sorted_sSN[i].setCity(sSN[i].getCity());
			sorted_sSN[i].setAddressLine1(sSN[i].getAddressLine1());
			sorted_sSN[i].setSocialSecurityNumber(sSN[i].getSocialSecurityNumber());
		}
	}

	public void readFileData(String path) {
		BufferedReader br = null;
		String line = "";
		int count = 0;

		try {
			br = new BufferedReader(new FileReader(path));

			while ((line = br.readLine()) != null) {
				String[] word = line.split("\\|");

				if (word[0].equals("CID"))
					continue;

				person[count].setcID(Integer.parseInt(word[0]));
				person[count].setFirstName(word[1]);
				person[count].setLastName(word[2]);
				person[count].setCity(word[3]);
				person[count].setAddressLine1(word[4]);
				person[count].setSocialSecurityNumber(Integer.parseInt(word[5]));
				count++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void readFileCommandAndWrite(Person[] f_array, BufferedWriter write, int length, String[] word,
			String[] word2, String[] results, int counter) {

		if (("AND").equals(word[counter])) {
			return;
		}
		if (word[counter].indexOf('>') >= 0) {
			String[] word3 = word[counter].split(">");
			if (word3[0].equals("SocialSecurityNumber")) {
				int index = 0;
				int value = Integer.parseInt(word3[1]);
				index = binarySearchSSNGreater(value, sorted_sSN);
				if (f_array[0].getcID() == 0) {
					assignFinalArrayforGreaterThanSSN(sorted_sSN, index, f_array);
				} else {
					for (int i = 0; i < 18750; i++) {
						int count = 0;
						for (int j = index; j < 18750; j++) {
							if (f_array[i].getcID() == sorted_sSN[j].getcID()) {
								count++;
							}
						}
						if (count == 0) {
							f_array[i].setAddressLine1(null);
							f_array[i].setFirstName(null);
							f_array[i].setCity(null);
							f_array[i].setLastName(null);
							f_array[i].setcID(0);
							f_array[i].setSocialSecurityNumber(0);
						}
					}
				}
				swap(f_array);
				return;

			} else if (word3[0].equals("CID")) {
				int value = Integer.parseInt(word3[1]);
				int index = binarySearchCIDGreater(value, sorted_cIDs);
				if (f_array[0].getcID() == 0) {
					assignFinalArrayforGreaterThanCID(sorted_cIDs, index, f_array);
				} else {
					for (int i = 0; i < 18750; i++) {
						int count = 0;
						for (int y = index; y < 18750; y++) {
							if (f_array[i].getcID() == sorted_cIDs[y].getcID()) {
								count++;
							}
						}
						if (count == 0) {
							f_array[i].setAddressLine1(null);
							f_array[i].setFirstName(null);
							f_array[i].setCity(null);
							f_array[i].setLastName(null);
							f_array[i].setcID(0);
							f_array[i].setSocialSecurityNumber(0);
						}
					}
				}
				swap(f_array);
				return;
			}

		} else if (word[counter].indexOf('<') >= 0) {
			String[] word3 = word[counter].split("<");
			if (word3[0].equals("SocialSecurityNumber")) {
				int index = 0;
				int value = Integer.parseInt(word3[1]);
				index = binarySearchSSNSmaller(value, sorted_sSN);
				if (f_array[0].getcID() == 0) {
					assignFinalArrayforSmallerThan(sorted_sSN, f_array, index);
				} else {
					for (int i = 0; i < 18750; i++) {
						int count = 0;
						for (int j = 0; j <= index; j++) {
							if (f_array[i].getcID() == sorted_sSN[j].getcID()) {
								count++;
							}
						}
						if (count == 0) {
							f_array[i].setAddressLine1(null);
							f_array[i].setFirstName(null);
							f_array[i].setCity(null);
							f_array[i].setLastName(null);
							f_array[i].setcID(0);
							f_array[i].setSocialSecurityNumber(0);
						}
					}
				}
				swap(f_array);
				return;
			} else if (word3[0].equals("CID")) {
				int value = Integer.parseInt(word3[1]);
				int index = binarySearchCIDSmaller(value, sorted_cIDs);
				if (f_array[0].getcID() == 0) {
					assignFinalArrayforSmallerThan(sorted_cIDs, f_array, index);
				} else {
					for (int i = 0; i < 18750; i++) {
						int count = 0;
						for (int j = 0; j <= index; j++) {
							if (f_array[i].getcID() == sorted_cIDs[j].getcID()) {
								count++;
							}
						}
						if (count == 0) {
							f_array[i].setAddressLine1(null);
							f_array[i].setFirstName(null);
							f_array[i].setCity(null);
							f_array[i].setLastName(null);
							f_array[i].setcID(0);
							f_array[i].setSocialSecurityNumber(0);
						}
					}
				}
				swap(f_array);
				return;
			}
		} else if (word[counter].indexOf('~') >= 0) {
			String[] word3 = word[counter].split("~");
			if (word3[0].equals("FirstName")) {
				String value = word3[1].substring(0, 1).toUpperCase() + word3[1].substring(1);
				int index = BinarSearchFN(value, sorted_firstName);
				if (f_array[0].getcID() == 0) {
					assignFinalArrayforFN(sorted_firstName, index, f_array, value);

				} else {
					for (int i = 0; i < 18750; i++) {
						int count = 0;
						for (int j = index; sorted_firstName[j].getFirstName().startsWith(value); j++) {
							if (f_array[i].getcID() == sorted_firstName[j].getcID()) {
								count++;
							}
						}
						if (count == 0) {
							f_array[i].setAddressLine1(null);
							f_array[i].setFirstName(null);
							f_array[i].setCity(null);
							f_array[i].setLastName(null);
							f_array[i].setcID(0);
							f_array[i].setSocialSecurityNumber(0);
						}
					}
				}
				swap(f_array);
				return;
			} else if (word3[0].equals("LastName")) {
				String value = word3[1].substring(0, 1).toUpperCase() + word3[1].substring(1);
				int index = BinarSearchLN(value, sorted_lastName);
				if (f_array[0].getcID() == 0) {
					assignFinalArrayforLN(sorted_lastName, index, f_array, value);
				} else {
					for (int i = 0; i < 18750; i++) {
						int count = 0;
						for (int j = index; sorted_lastName[j].getLastName().startsWith(value); j++) {
							if (f_array[i].getcID() == sorted_lastName[j].getcID()) {
								count++;
							}
						}
						if (count == 0) {
							f_array[i].setAddressLine1(null);
							f_array[i].setFirstName(null);
							f_array[i].setCity(null);
							f_array[i].setLastName(null);
							f_array[i].setcID(0);
							f_array[i].setSocialSecurityNumber(0);
						}
					}
				}
				swap(f_array);
				return;
			} else if (word3[0].equals("City")) {
				String value = word3[1].substring(0, 1).toUpperCase() + word3[1].substring(1);
				int index = BinarSearchC(value, sorted_city);
				if (f_array[0].getcID() == 0) {
					assignFinalArrayforC(sorted_city, index, f_array, value);
				} else {
					for (int i = 0; i < 18750; i++) {
						int count = 0;
						for (int j = index; sorted_city[j].getCity().startsWith(value); j++) {
							if (f_array[i].getcID() == sorted_city[j].getcID()) {
								count++;
							}
						}
						if (count == 0) {
							f_array[i].setAddressLine1(null);
							f_array[i].setFirstName(null);
							f_array[i].setCity(null);
							f_array[i].setLastName(null);
							f_array[i].setcID(0);
							f_array[i].setSocialSecurityNumber(0);
						}
					}
				}
				swap(f_array);
				return;
			} else if (word3[0].equals("AddressLine1")) {
				String value = word3[1];
				int index = BinarSearchAL(value, sorted_addressLine1);
				if (f_array[0].getcID() == 0) {
					assignFinalArrayforAL(sorted_addressLine1, index, f_array, value);
				} else {
					for (int i = 0; i < 18750; i++) {
						int count = 0;
						for (int j = index; sorted_addressLine1[j].getAddressLine1().startsWith(value); j++) {
							if (f_array[i].getcID() == sorted_addressLine1[j].getcID()) {
								count++;
							}
						}
						if (count == 0) {
							f_array[i].setAddressLine1(null);
							f_array[i].setFirstName(null);
							f_array[i].setCity(null);
							f_array[i].setLastName(null);
							f_array[i].setcID(0);
							f_array[i].setSocialSecurityNumber(0);
						}
					}
				}
				swap(f_array);
				return;
			}
		}
	}

	public void writeColumnsAndVariables(BufferedWriter write, Person[] array, String[] array2, String[] results,
			int counter) {
		String formatStr = "%-16s";

		try {
			write.write("CommandText: " + '"' + results[counter] + '"');
			write.newLine();
			write.newLine();
			write.write("Results:");
			write.newLine();
			write.write(String.format(formatStr, array2[0]));
			write.write("	");
			for (int i = 1; i < array2.length; i++) {
				write.write(String.format(formatStr, array2[i]));
				write.write("	");
			}
			write.newLine();

			for (int i = 0; array[i].getcID() != 0; i++) {
				for (int j = 0; j < array2.length; j++) {
					if (array2[j].equals("CID")) {
						write.write(String.format(formatStr, Integer.toString(array[i].getcID())));
						write.write("	");
					} else if (array2[j].equals("FirstName")) {
						write.write(String.format(formatStr, array[i].getFirstName()));
						write.write("	");
					} else if (array2[j].equals("LastName")) {
						write.write(String.format(formatStr, array[i].getLastName()));
						write.write("	");
					} else if (array2[j].equals("City")) {
						write.write(String.format(formatStr, array[i].getCity()));
						write.write("	");
					} else if (array2[j].equals("AddressLine1")) {
						write.write(String.format(formatStr, array[i].getAddressLine1()));
						write.write("	");
					} else if (array2[j].equals("SocialSecurityNumber")) {
						write.write(String.format(formatStr, Integer.toString(array[i].getSocialSecurityNumber())));
						write.write("	");
					}
				}
				write.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeEmpty(BufferedWriter write, String[] results, int counter) {
		try {
			write.write("CommandText: " + '"' + results[counter] + '"');
			write.newLine();
			write.newLine();
			write.write("Results:");
			write.newLine();
			write.write("Empty Rowset");
			write.newLine();
		} catch (IOException e) {
			;
		}
	}

	public Person[] sortCIDs(int lo, int hi) {

		if (hi <= lo) {
			return person;
		}

		int lt = lo, gt = hi;
		Person v = person[lo];
		int i = lo;
		while (i <= gt) {
			if (person[i].getcID() < v.getcID())
				exch(person, lt++, i++);
			else if (person[i].getcID() > v.getcID())
				exch(person, i, gt--);
			else
				i++;
		}
		sortCIDs(lo, lt - 1);
		sortCIDs(gt + 1, hi);

		return person;
	}

	public Person[] sortFirstName(int lo, int hi) {

		if (hi <= lo) {
			return person;
		}

		int lt = lo, gt = hi;
		Person v = person[lo];
		int i = lo;
		while (i <= gt) {
			int cmp = (person[i].getFirstName()).compareTo(v.getFirstName());
			if (cmp < 0)
				exch(person, lt++, i++);
			else if (cmp > 0)
				exch(person, i, gt--);
			else
				i++;
		}
		sortFirstName(lo, lt - 1);
		sortFirstName(gt + 1, hi);
		return person;
	}

	public Person[] sortLastName(int lo, int hi) {

		if (hi <= lo) {
			return person;
		}

		int lt = lo, gt = hi;
		Person v = person[lo];
		int i = lo;
		while (i <= gt) {
			int cmp = (person[i].getLastName()).compareTo(v.getLastName());
			if (cmp < 0)
				exch(person, lt++, i++);
			else if (cmp > 0)
				exch(person, i, gt--);
			else
				i++;
		}
		sortLastName(lo, lt - 1);
		sortLastName(gt + 1, hi);
		return person;
	}

	public Person[] sortCity(int lo, int hi) {

		if (hi <= lo) {
			return person;
		}

		int lt = lo, gt = hi;
		Person v = person[lo];
		int i = lo;
		while (i <= gt) {
			int cmp = person[i].getCity().compareTo(v.getCity());
			if (cmp < 0)
				exch(person, lt++, i++);
			else if (cmp > 0)
				exch(person, i, gt--);
			else
				i++;
		}
		sortCity(lo, lt - 1);
		sortCity(gt + 1, hi);
		return person;
	}

	public Person[] sortAddressLine1(int lo, int hi) {

		if (hi <= lo) {
			return person;
		}

		int lt = lo, gt = hi;
		Person v = person[lo];
		int i = lo;
		while (i <= gt) {
			int cmp = person[i].getAddressLine1().compareTo(v.getAddressLine1());
			if (cmp < 0)
				exch(person, lt++, i++);
			else if (cmp > 0)
				exch(person, i, gt--);
			else
				i++;
		}
		sortAddressLine1(lo, lt - 1);
		sortAddressLine1(gt + 1, hi);
		return person;
	}

	public Person[] sortSocialSecurityNumbers(int lo, int hi) {

		if (hi <= lo) {
			return person;
		}

		int lt = lo, gt = hi;
		Person v = person[lo];
		int i = lo;
		while (i <= gt) {
			if (person[i].getSocialSecurityNumber() < v.getSocialSecurityNumber())
				exch(person, lt++, i++);
			else if (person[i].getSocialSecurityNumber() > v.getSocialSecurityNumber())
				exch(person, i, gt--);
			else
				i++;
		}
		sortSocialSecurityNumbers(lo, lt - 1);
		sortSocialSecurityNumbers(gt + 1, hi);
		return person;
	}

	public void exch(Object[] a, int i, int j) {
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public int binarySearchCIDSmaller(int value, Person[] sorted_cIDs) {
		int left = 0;
		int right = 18749;
		int mid = 0;

		while (left <= right) {
			mid = (int) ((right - left) / 2) + left;
			if (sorted_cIDs[mid].getcID() == value) {
				return mid;
			}
			if (value < sorted_cIDs[mid].getcID()) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return mid - 1;
	}

	public int binarySearchCIDGreater(int value, Person[] sorted_cIDs) {
		int left = 0;
		int right = 18749;
		int mid = 0;

		while (left <= right) {
			mid = (int) ((right - left) / 2) + left;
			if (sorted_cIDs[mid].getcID() == value) {
				return mid;
			}
			if (value < sorted_cIDs[mid].getcID()) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return mid;
	}

	public int binarySearchSSNGreater(int value, Person[] sorted_sSN) {
		int left = 0;
		int right = 18749;
		int mid = 0;

		while (left <= right) {
			mid = (int) ((right - left) / 2) + left;
			if (sorted_sSN[mid].getSocialSecurityNumber() == value) {
				return mid;
			}
			if (value < sorted_sSN[mid].getSocialSecurityNumber()) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return mid;
	}

	public int binarySearchSSNSmaller(int value, Person[] sorted_sSN) {
		int left = 0;
		int right = 18749;
		int mid = 0;

		while (left <= right) {
			mid = (int) ((right - left) / 2) + left;
			if (sorted_sSN[mid].getSocialSecurityNumber() == value) {
				return mid;
			}
			if (value < sorted_sSN[mid].getSocialSecurityNumber()) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return mid - 1;
	}

	public void assignFinalArrayforSmallerThan(Person array[], Person[] f_array, int index) {

		for (int i = 0; i <= index; i++) {
			f_array[i] = array[i];
		}
	}

	public void assignFinalArrayforGreaterThanCID(Person array[], int index, Person[] f_array) {

		for (int j = 0, i = index; i < 18750; i++, j++) {
			f_array[j] = array[i];
		}
	}

	public void assignFinalArrayforGreaterThanSSN(Person array[], int index, Person[] f_array) {

		for (int j = 0, i = index; i < 18750; i++, j++) {
			f_array[j] = array[i];
		}
	}

	public void assignFinalArrayforLN(Person array[], int index, Person[] f_array, String value) {

		for (int j = 0, i = index; array[i].getLastName().startsWith(value); i++, j++) {
			f_array[j] = array[i];
		}
	}

	public void assignFinalArrayforC(Person array[], int index, Person[] f_array, String value) {

		for (int j = 0, i = index; array[i].getCity().startsWith(value); i++, j++) {
			f_array[j] = array[i];
		}
	}

	public void assignFinalArrayforAL(Person array[], int index, Person[] f_array, String value) {

		for (int j = 0, i = index; array[i].getAddressLine1().startsWith(value); i++, j++) {
			f_array[j] = array[i];
		}
	}

	public void assignFinalArrayforFN(Person array[], int index, Person[] f_array, String value) {

		for (int j = 0, i = index; array[i].getFirstName().startsWith(value); i++, j++) {
			f_array[j] = array[i];
		}
	}

	public int BinarSearchFN(String value, Person[] sorted_firstName) {
		int left = 0;
		int right = 18749;
		int mid = 0;

		while (left <= right) {
			mid = (int) ((right - left) / 2) + left;
			int cmp = (sorted_firstName[mid].getFirstName().compareTo(value));
			if (sorted_firstName[mid].getFirstName().startsWith(value)) {
				{
					for (int y = mid; y > 0; y--) {
						if (!sorted_firstName[y].getFirstName().startsWith(value)) {
							return y + 1;
						}
					}
				}

			}
			if (cmp > 0) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return mid;
	}

	public int BinarSearchLN(String value, Person[] sorted_lastName) {
		int left = 0;
		int right = 18749;
		int mid = 0;

		while (left <= right) {
			mid = (int) ((right - left) / 2) + left;
			int cmp = (sorted_lastName[mid].getLastName().compareTo(value));
			if (sorted_lastName[mid].getLastName().startsWith(value)) {
				{
					for (int y = mid; y > 0; y--) {
						if (!sorted_lastName[y].getLastName().startsWith(value)) {
							return y + 1;
						}
					}
				}

			}
			if (cmp > 0) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return mid;
	}

	public int BinarSearchC(String value, Person[] sorted_city) {
		int left = 0;
		int right = 18749;
		int mid = 0;

		while (left <= right) {
			mid = (int) ((right - left) / 2) + left;
			int cmp = (sorted_city[mid].getCity().compareTo(value));
			if (sorted_city[mid].getCity().startsWith(value)) {
				{
					for (int y = mid; y > 0; y--) {
						if (!sorted_city[y].getCity().startsWith(value)) {
							return y + 1;
						}
					}
				}

			}
			if (cmp > 0) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return mid;
	}

	public int BinarSearchAL(String value, Person[] sorted_addressLine1) {
		int left = 0;
		int right = 18749;
		int mid = 0;

		while (left <= right) {
			mid = (int) ((right - left) / 2) + left;
			int cmp = (sorted_addressLine1[mid].getAddressLine1().compareTo(value));
			if (sorted_addressLine1[mid].getAddressLine1().startsWith(value)) {
				{
					for (int y = mid; y > 0; y--) {
						if (!sorted_addressLine1[y].getAddressLine1().startsWith(value)) {
							return y + 1;
						}
					}
				}

			}
			if (cmp > 0) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return mid;
	}

	public void swap(Person[] f_array) {
		Person temp;
		for (int p = 0; p < 18750; p++) {
			if (f_array[p].getcID() == 0) {
				for (int q = p + 1; q < 18750; q++) {
					if (f_array[q].getcID() != 0) {
						temp = f_array[p];
						f_array[p] = f_array[q];
						f_array[q] = temp;
					}
				}
			}
		}
	}
}
