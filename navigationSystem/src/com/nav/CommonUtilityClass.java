package com.nav;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CommonUtilityClass {
	
	 
	public String getRowCol(String path, int rows, int column) throws IOException{
			 int lineNum = 0;
		     int wordCount = 0;
		     int charCount = 0; 
		     Scanner input = new Scanner(new FileReader(path));
		     while (input.hasNextLine()) {
			 String line;
			 line = input.nextLine();
			 lineNum++;			
			 String str [] = line.split((" ")); 
			 for ( int i = 0; i <str.length ; i ++) {
			     if (str [i].length() > 0) {
			       wordCount ++; 
			     }
			   }
			   charCount += (line.length());			
    }			
    //System.out.println(lineNum);
    //System.out.println(wordCount);
    return lineNum+"x"+wordCount;
	}

}
