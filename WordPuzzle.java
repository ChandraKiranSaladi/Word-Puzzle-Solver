import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordPuzzle {


	public static void main(String []args) throws Exception
	{
		int count=0;
		MyHashTable<String> table = new MyHashTable<>();
		Scanner ok= new Scanner(System.in);
		System.out.println("Enter no.of rows & columns ");
		int rows = ok.nextInt();
		if(rows<1)
			throw new Exception("Invalid array rows input");
		int columns = ok.nextInt();
		if(columns<1)
			throw new Exception("Invalid array columns input");
		System.out.println("Do you need Enhancement\n 1 for Yes and any other digit for No");
		boolean enhancement = ok.nextInt()==1;

		// Reading dictionary words into Hash Table
		try {
			FileReader f= new FileReader("src/dictionary.txt");

			BufferedReader reader = new BufferedReader(f);
			String line=null;
			while((line = reader.readLine())!=null)
			{ 
				StringBuilder pre = new StringBuilder();
				for(int i=0;i<line.length();i++)	
				{
					pre.append(line.charAt(i));
					if(i==line.length()-1)
						table.insert(pre.toString(),false);
					else
						table.insert(pre.toString(),true);
				}
			}
		}
		catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Generating random Matrix 
		Random r =new Random();

		char [][]array = new char[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				array[i][j] = (char) (r.nextInt(26)+97);
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
//		char array[][] = {
//	            "hixtxxxxsx".toCharArray(),
//	            "xsxxhxxixx".toCharArray(),
//	            "xixxsihtxx".toCharArray(),
//	            "xhsxxxstxx".toCharArray(),
//	            "xtxandxhxx".toCharArray(),
//	            "xxxthisixx".toCharArray(),
//	            "xxxxxxisxx".toCharArray(),
//	            "Praneethxx".toCharArray(),
//	            "xhxxxxxxtx".toCharArray(),
//	            "xxixxxxxxx".toCharArray()
//	    };

		System.out.println("\n\n");
		long startTime = System.currentTimeMillis( );

		// check for words 


		StringBuilder sb = new StringBuilder();

		boolean[] info; 
		for (int i=0; i<rows; i++)
		{ 

			for (int j=0; j<columns; j++)
			{
				sb.setLength(0);
				sb.append(array[i][j]);
				info = table.contains(new String(sb));
				if(info[0]!=false)
				{
					if(info[1]==false)
					{

						System.out.println(sb + " at "
								+ "(" + i + "," + j + ")");
						count++;
					}
				}else
					if(enhancement)
						continue;




				for (int k=1; j+k < columns; k++)
				{	  
					sb.append(array[i][j+k]);
					info = table.contains(new String(sb));
					if(info[0]!=false)
					{
						if(info[1]==false)
						{
							System.out.println(sb + " \"left to right\"  at "
									+ "(" + i + "," + j + ")");

							count++;
						}
					}else
						if(enhancement)
							break;


				}
				sb.setLength(1);

				for (int k=1; i+k < rows; k++)
				{
					sb.append(array[i+k][j]);
					info = table.contains(new String(sb));
					if(info[0]!=false)
					{
						if(info[1]==false)
						{	
							System.out.println( sb + " \"top to bottom\" at "
									+ "(" + i + "," + j + ")");
							count++;
						}
					}else
						if(enhancement)
							break;


				} sb.setLength(1);
				for(int k=1;i+k<rows&&j+k<columns;k++)
				{
					sb.append(array[i+k][j+k]);
					info = table.contains(new String(sb));
					if(info[0]!=false)
					{
						if(info[1]==false)
						{
							System.out.println( sb + " \"top left to bottom right\" at "
									+ "(" + i + "," + j + ")");
							count++;
						}
					}else
						if(enhancement)
							break;


				}sb.setLength(1);
				for(int k=1;(i-k)>-1&&(j-k)>-1;k++)
				{
					sb.append(array[i-k][j-k]);
					info = table.contains(new String(sb));
					if(info[0]!=false)
					{
						if(info[1]==false)
						{
							System.out.println( sb + " \"bottom right to top left\" at "
									+ "(" + i + "," + j + ")");
							count++;
						}
					}else
						if(enhancement)
							break;


				}sb.setLength(1);
				for(int k=1;(j-k)>-1;k++)
				{
					sb.append(array[i][j-k]);
					info = table.contains(new String(sb));
					if(info[0]!=false)
					{
						if(info[1]==false)
						{
							System.out.println( sb + " \"right  to left\" at "
									+ "(" + i + "," + j + ")");
							count++;
						}
					}else
						if(enhancement)
							break;


				}sb.setLength(1);
				for(int k=1;(i-k)>-1;k++)
				{
					sb.append(array[i-k][j]);
					info = table.contains(new String(sb));
					if(info[0]!=false)
					{
						if(info[1]==false)
						{
							System.out.println( sb + " \"bottom to top\" at "
									+ "(" + i + "," + j + ")");
							count++;
						}
					}else
						if(enhancement)
							break;


				}sb.setLength(1);
				for(int k=1;(i-k)>-1&&j+k<columns;k++)
				{
					sb.append(array[i-k][j+k]);
					info = table.contains(new String(sb));
					if(info[0]!=false)
					{
						if(info[1]==false)
						{
							System.out.println( sb + " \"bottom left to top right\" at "
									+ "(" + i + "," + j + ")");
							count++;
						}
					}else
						if(enhancement)
							break;


				}sb.setLength(1);
				for(int k=1;(i+k)<rows&&j-k>-1;k++)
				{
					sb.append(array[i+k][j-k]);
					info = table.contains(new String(sb));
					if(info[0]!=false)
					{
						if(info[1]==false)
						{
							System.out.println( sb + " \"top right to bottom left\" at "
									+ "(" + i + "," + j + ")");
							count++;
						}
					}else
						if(enhancement)
							break;


				}

			}
		}
		long endTime = System.currentTimeMillis( );

		System.out.println("Elapsed time: " + (endTime - startTime));
		System.out.println("No.of word matches = "+ count);
	} 
}






