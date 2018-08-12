package com.nav;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CreatMetrixFrmFile{

	public String[][] createMetrix(int cols, int rows, String filePath) throws IOException{
		
		String[][] pathMetrixArray = new String[cols][rows] ;
	    String currentSign;
	    BufferedReader reader;
		reader = new BufferedReader(new FileReader (filePath));
		
	    String line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	        }
	       // System.out.println(stringBuilder.toString());
	     
	    int x = 0;
	    
	    for (int i = 0; i < cols; ++i) {			    	
	        for(int j = 0; j < rows; ++j) {
	        	
	        	String charSign = Character.toString(stringBuilder.charAt(x));// System.out.println("x value -- : "+x);
	        	x++;			        	
	        	pathMetrixArray[i][j]= charSign;  
	 		   	//System.out.println("array -("+i+":"+j+")"+pathMetrixArray[i][j]+"-");			 		      
	        }
	     }		
		return pathMetrixArray;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	        reader.close();
	    }	
	    return null;
	}
	
	
	public int[] getPoint(String ch, String[][] metrixObj){
		int[] startP = new int[2];
		for (int x = 0; x < metrixObj.length; x++) {
	        for (int y = 0; y < metrixObj[0].length; y++) {
	        	String symbol = metrixObj[x][y]; //System.out.println(symbol +"--"+ ch);
	        	if(symbol.equalsIgnoreCase(ch)){	//System.out.println(x+"----"+y);	        		
	        			startP[0] = x;
	        			startP[1] = y;
	        			break;
	        	}		        	
	        }		        
	    }
		return startP;
	}
	

}
