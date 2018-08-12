package com.nav;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nav.CommonUtilityClass;


public class NavFileReader extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String realPath = req.getServletContext().getRealPath("");
		PrintWriter out = res.getWriter();
	     try {
	    	 	String filePath = req.getParameter("path");
	            FileInputStream fis = new FileInputStream(filePath);
	     
	            //get the rows and columns from the file.
	            int lineNums = 0;
			    int colNums = 0;
			    
			    //getting the column and row counts from the file. 
			    CommonUtilityClass cuc = new CommonUtilityClass();
			    String cucResult = cuc.getRowCol(filePath, lineNums, colNums);
			    String[] resultArray = cucResult.split("x");
			    lineNums =  Integer.parseInt(resultArray[0]);
			    colNums = Integer.parseInt(resultArray[1]);
			    
			    //System.out.println(lineNums+"---"+colNums);
			    
			    //Creating metrix from the file
			    CreatMetrixFrmFile metrix = new CreatMetrixFrmFile();
			    String[][] charMetrix =  metrix.createMetrix(colNums, lineNums, filePath);
			    
			    ManhattanDistFormula manhattanDis = new ManhattanDistFormula();
			    Properties prop = manhattanDis.loadPropertiesfile("C:\\eclipse\\workspace\\navigationSystem\\WebContent\\");
			    String startChar = prop.getProperty("startpoint");
			    String endChar = prop.getProperty("endpoint");
			    
			    
			    //checking starting point in the file.
			    int[] startPoint =  metrix.getPoint(startChar, charMetrix);
			    //System.out.println(getStartPoint[0]+"----------"+getStartPoint[1]);
			    
			    //checking ending point in the file
			    int[] endPoint = metrix.getPoint(endChar, charMetrix);			    
			    //System.out.println(getEndPoint[0]+"----------"+getEndPoint[1]);
			    
			    //calculate score per move and finding shortest path
			    String[][] finalPathMatrix = manhattanDis.calShortestPathAlgorithm(charMetrix, startPoint, endPoint); 
			    
			    final String fileName = "C:\\eclipse\\workspace\\navigationSystem\\WebContent\\lowDistance.txt";
			    BufferedWriter bw = null;
				FileWriter fw = null;

				//writing result to a file.
				try {
					fw = new FileWriter(fileName);
					bw = new BufferedWriter(fw);
					for(int i=0; i<finalPathMatrix.length; i++){
						for(int j=0; j<finalPathMatrix.length; j++){
							bw.write(finalPathMatrix[i][j]);
							
						}
					}
					out.println(finalPathMatrix);
				} catch (IOException e) {

					e.printStackTrace();

				} finally {
					try {
						if (bw != null)
							bw.close();

						if (fw != null)
							fw.close();

					} catch (IOException ex) {

						ex.printStackTrace();
					}
				}	    
	     
	     }catch(IOException e){
	    	 e.printStackTrace();
	     }
	        
	  }	
	
}
