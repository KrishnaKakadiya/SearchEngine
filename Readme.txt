It is a java application. We have used Jpanel for front end development. On running the program, we have a textbox in which we can search the keyword. On pressing enter, we can see the results in the textbox below. We have two types of output for the searched keyword. By default, the website search would be rendered. The dictinary search is done based on a local file(Similar.txt) in which we have defined the snonyms of the particular keyword. If the keyword that we are trying to search is not found in the file, we will calculate the edit distance for the particular keyword and suggest the nearest word matched.

We have used the following concepts in our SearchEngine :

	- Translating HTML to Text (Used jsoup.jar file, DictionaryCustom.java)

	- Analyzing frequencies using hash tables (DictionaryCustom.java)

	- Use of inverted index to search keyword (InvertedIndexCustom.java)

	- Ranking web pages using sorting (InvertedIndexCustom.java)

	- Spellchecking keywords (Similar.java)

	- Finding keywords using string matching (Similar.java)


