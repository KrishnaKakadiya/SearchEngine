package websearchengine;

import java.io.*;
import java.util.*;

import org.jsoup.Jsoup;

public class DictionaryCustom {

	public Hashtable<String, Value_Custom> dictionary_find() throws IOException {

		
		ArrayList<String> al = new ArrayList<>();
		String line;
		ArrayList<Value_Custom> alv = new ArrayList<>();

		Hashtable<String, Value_Custom> ht = new Hashtable<String, Value_Custom>();

		File folder = new File("E:\\Advance Computing Concepts\\Project\\Project\\W3C Web Pages\\ConvertedTextFiles");

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

	public static void convert() throws IOException,FileNotFoundException,NullPointerException {
		int F_num = 1;
		try {
			File dir = new File("E:\\Advance Computing Concepts\\Assignments\\SearchEngine\\W3C Web Pages");
			File[] array1 = dir.listFiles();
			File[] array2 = new File[101];
			int nFiles=0;
			for(int _i=0;_i<array1.length;_i++)
			{
				if(array1[_i].isFile())
				{
					array2[nFiles] = array1[_i];
					nFiles++;
				}
			}
			
			for(int i=0;i<100;i++)
			{
				convertHtmlToText(array2[i],F_num);
				F_num = F_num + 1;
			}
		} catch (Exception e) {
			System.out.println("Exception:"+e);
		}
		finally
		{
			
		}
	
	}
	
	public static void convertHtmlToText(File _resourceFile,int F_num) throws IOException,FileNotFoundException,NullPointerException
	{
		try {
			org.jsoup.nodes.Document _doc = Jsoup.parse(_resourceFile, "UTF-8");
			BufferedWriter out = new BufferedWriter(new FileWriter("E:\\Advance Computing Concepts\\Assignments\\SearchEngine\\W3C Web Pages\\ConvertedTextFiles\\"+_resourceFile.getName()+".txt"));
			out.write(_doc.text());
			out.close();
			System.out.println("File "+_resourceFile.getName()+"     --->     "+_resourceFile.getName()+".txt successfully");
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
