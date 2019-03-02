package websearchengine;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class InvertedIndexCustom {

	static LinkedHashSet<String> hash_link = new LinkedHashSet<>();
	static ArrayList<String> counter = new ArrayList<>();
	static TreeMap<String, Integer> word_Count = new TreeMap<String, Integer>();

	@SuppressWarnings("rawtypes")
	public void get(Hashtable<String, Value_Custom> ht, String inp) throws IOException {

		ArrayList<String> correct = new ArrayList<>();
		ArrayList<String> al = new ArrayList<String>();
		
		String input[] = inp.split(" ");
		for (int i = 0; i < input.length; i++)
			al.add(input[i]);

		for (String in : al) {

			if (ht.containsKey(in)) {
				correct.add(in);
				calculate_page(in, ht);
			} else {

				Set s = ht.entrySet();
			
				Iterator i = s.iterator();
				
				int ed = 101;
				int ed2 = 101;
				String Key = "";
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry) i.next();

					ed = Find_Sequences.ed(in, me.getKey().toString());
					if (ed2 > ed) {
						ed2 = ed;
						Key = me.getKey().toString();
					}

				}
				correct.add(Key);
				calculate_page(Key, ht);

			}
		}
		
		
		System.out.print("Suggestions Found :");
		System.out.print("\"");
		for (String Value_Custom : correct) {
			System.out.print(Value_Custom + " ");
		}
		System.out.print("\"");
		System.out.println();
		System.out.println();
		ArrayList<String> wordCount1 = Comparator(word_Count);
		System.out.println("Links Found: ");
		for (String Value_Custom1 : wordCount1) {
			System.out.println(Value_Custom1.replace(".txt", ".html"));
		}
		System.out.println();
		

	}

	private static void calculate_page(String in, Hashtable<String, Value_Custom> ht) {
		Value_Custom Value_Custom = ht.get(in);
		int x = 0;

		ArrayList<Page_Map> al = new ArrayList<>();

		Page_Map p = new Page_Map();
		for (String pname : Value_Custom.getPageName()) {

			if (x > 5) {

				break;
			} else {
				if (word_Count.containsKey(pname)) {
					int counter = word_Count.get(pname);
					counter += Count_String(in, pname);
					word_Count.put(pname, counter);
				} else
					word_Count.put(pname, Count_String(in, pname));
			}

			x++;
		}
		System.out.println();

	}

	private static ArrayList<String> Comparator(Map<String, Integer> myMap) {

		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(
				myMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();

		ArrayList<String> display = new ArrayList<>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it
				.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
			display.add(entry.getKey());
		}
		Collections.reverse(display);

		return display;
	}

	public static int Count_String(String stringToLookFor, String fileName) {
		int count = 0;
		try {

			FileInputStream istream = new FileInputStream("E:\\Advance Computing Concepts\\Assignments\\SearchEngine\\W3C Web Pages\\ConvertedTextFiles" + fileName);
			DataInputStream in = new DataInputStream(istream);
			BufferedReader b = new BufferedReader(new InputStreamReader(in));
			String l;
			while ((l = b.readLine()) != null) {
				int start_Index = l.toLowerCase().indexOf(stringToLookFor);

				while (start_Index != -1) {

					count++;
					start_Index = l.indexOf(stringToLookFor, start_Index
							+ stringToLookFor.length());
				}
			}

			in.close();
		} catch (Exception e) {
			System.err.println("Error is Found: " + e.getMessage());
		}
		return count;
	}

}

