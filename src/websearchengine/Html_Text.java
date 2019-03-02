package websearchengine;
import java.io.*;
import java.util.*;

public class Html_Text {

	public Hashtable<String, Value_Custom> dictionary_find() throws IOException {

		
		ArrayList<String> al = new ArrayList<>();
		String line;
		ArrayList<Value_Custom> alv = new ArrayList<>();

		Hashtable<String, Value_Custom> ht = new Hashtable<String, Value_Custom>();

		File folder = new File("E:\\Advance Computing Concepts\\Assignments\\SearchEngine\\W3C Web Pages\\ConvertedTextFiles");

		for (File file : folder.listFiles()) {

			BufferedReader b = new BufferedReader(new FileReader(file));

			while ((line = b.readLine()) != null) {

				StringTokenizer str = new StringTokenizer(line,
						",.:;<>/?[)({}]+-*&%=#@^'!|_$`~");

				while (str.hasMoreTokens()) {

					String str1 = str.nextToken().toLowerCase().replaceAll("\"","");
					String str2[] = str1.split(" ");

					for (int i = 0; i < str2.length; i++) {

						if (!ht.containsKey(str2[i])) {
							Value_Custom Value_Custom = new Value_Custom();
							ArrayList<String> arrayList = new ArrayList<String>();
							int not = 1;
							arrayList.add(file.getName());
							Value_Custom.setNumberOfTimes(not);
							Value_Custom.setPageName(arrayList);
							ht.put(str2[i], Value_Custom);
						} else {

							Value_Custom Value_Custom = ht.get(str2[i]);
							ArrayList<String> arrayList = Value_Custom
									.getPageName();
							if (!arrayList.contains(file.getName()))
								arrayList.add(file.getName());

							Value_Custom.setNumberOfTimes(Value_Custom
									.getNumberOfTimes() + 1);
							Value_Custom.setPageName(arrayList);
							ht.put(str2[i], Value_Custom);
						}
					}
				}
			}
		}

		return ht;
	}

	

}
