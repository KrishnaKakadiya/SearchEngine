package websearchengine;

public class Find_Sequences {

	public static int ed(String w1, String w2) {
		int l1 = w1.length();
		int l2 = w2.length();

		int[][] a = new int[l1 + 1][l2 + 1];

		for (int i = 0; i <= l1; i++) {
			a[i][0] = i;
		}

		for (int j = 0; j <= l2; j++) {
			a[0][j] = j;
		}

		
		for (int i = 0; i < l1; i++) {
			char c1 = w1.charAt(i);
			for (int j = 0; j < l2; j++) {
				char c2 = w2.charAt(j);

				
				if (c1 == c2) {
					
					a[i + 1][j + 1] = a[i][j];
				} else {
					int rep = a[i][j] + 1;
					int ins = a[i][j + 1] + 1;
					int del = a[i + 1][j] + 1;

					int min = rep > ins ? ins : rep;
					min = del > min ? min : del;
					a[i + 1][j + 1] = min;
				}
			}
		}

		return a[l1][l2];
	}

}
