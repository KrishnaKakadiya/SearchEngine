package websearchengine;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class indexer {
	
	
	static JPanel jp=new JPanel();
	  static JLabel heading=new JLabel("The Brainiacs SearchEngine");
	  static JLabel jl=new JLabel();
	  static JFrame jf=new JFrame("The Brainiacs SearchEngine");
	  static JTextField jt = new JTextField(50);
	  static JButton jbSearchWeb = new JButton("Search Website");
	  static JButton jbSearchDictionary = new JButton("Search Dictionary");
	  static JTextArea jta=new JTextArea(10,20);
	  static JTextArea jta2=new JTextArea(10,20);
	

		static Hashtable<String, String> hash = new Hashtable<>();

		@SuppressWarnings("resource")
		public indexer() throws IOException {
			BufferedReader b = new BufferedReader(new FileReader("Similar.txt"));

			String l;

			while ((l = b.readLine()) != null) {
				String a[] = l.split("=");
				hash.put(a[0].trim().toLowerCase(), a[1].trim());
			}
		}

		@SuppressWarnings("rawtypes")
		public static String editDistance(String word) {

			Set s = hash.entrySet();
			Iterator i = s.iterator();
			int ed = 101;
			int ed2 = 101;
			String key = "";
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				ed = Find_Sequences.ed(word, me.getKey().toString());
				if (ed2 > ed) {
					ed2 = ed;
					key = me.getKey().toString();
				}
			}
			return key;
		}

		public void displaySimilar(String word) {
			jta2.setText("");
			if (hash.containsKey(word)) {
				jta2.setText("Synonym  of : ");
				jta2.append(word);
				jta2.append(" is : ");
				jta2.append(hash.get(word));
			} else {
				String suggest = editDistance(word);
				jta2.setText("Do you mean? ");
				jta2.append(suggest);
				jta2.append("\n");
				jta2.append("Synonym of ");
				jta2.append(suggest);
				jta2.append(" is : ");
				jta2.append(hash.get(suggest));
			}
			System.out.println();
			System.out.println();
			
		}
	

	static LinkedHashSet<String> hash_link = new LinkedHashSet<>();
	static ArrayList<String> counter = new ArrayList<>();
	static TreeMap<String, Integer> word_Count = new TreeMap<String, Integer>();
	
	
	public static void main(String[] args) throws IOException, InterruptedException {

		
		Html_Text w2t = new Html_Text();
		DictionaryCustom dictionaryCustom = new DictionaryCustom();

		dictionaryCustom.convert();
		Hashtable<String, Value_Custom> ht = w2t.dictionary_find();

		  
		  jf.setVisible(true);
		  jf.setSize(750, 450);
		  jf.setResizable(false);
		  jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		  jp.setLayout(null);
		  heading.setBounds(150, 25, 350, 30);
		  heading.setFont(new Font("Arial", Font.BOLD, 25));
		  heading.setForeground(new Color(255,255,255));
		  jp.add(heading);
		  jt.setBounds(25,65,600,25);
		  jp.add(jt); 
		  
		  jp.setBackground(new Color(58, 96, 117));
		  jt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String input=jt.getText();
				indexer ii = null;
				try {
					ii = new indexer();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				try {
					ii.get(ht, input);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		  jbSearchWeb.setBounds(120, 100, 150, 25);
		  jbSearchWeb.setForeground(new Color(37,70,81));
		  jp.add(jbSearchWeb);
		  
		  jbSearchWeb.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  String input=jt.getText();
				  indexer ii = null;
				try {
					ii = new indexer();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
					try {
						ii.get(ht, input);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			  }
		  });
		  
		  jbSearchDictionary.setBounds(360, 100, 150, 25);
		  jbSearchDictionary.setForeground(new Color(37,70,81));
		  jp.add(jbSearchDictionary);
		  
		  jbSearchDictionary.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  String input=jt.getText();
				  indexer ii1 = null;
				try {
					ii1 = new indexer();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
					ii1.displaySimilar(input);
			  }
		  });
		  
		  jf.add(jp);
		  jp.add(jl);
		  jta2.setBounds(25,140,600,300);
		  
		  jta2.setBackground(new Color(255,255,255));

		  jta2.setFont(new Font("Arial", Font.BOLD, 15));
		  jta2.setForeground(new Color(94, 100, 102));
		  jp.add(jta2);
  }
	
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
		jta2.setText("");
		jl.setText("Suggestion for Search:   ");
		for (String Value_Custom : correct) {
			String str1=Value_Custom;
			jta2.append(" "+str1);
			
		}
		jta2.append("\n");
		jta2.append("\n\nLinks Found:\n");
		ArrayList<String> wordCount1 = Comparator(word_Count);
		for (String Value_Custom1 : wordCount1) {
			jta2.append(Value_Custom1.replace(".txt", ".html"));
			jta2.append("\n");
		}
		
		jta2.append("\n");
		jf.add(jl);
		

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

