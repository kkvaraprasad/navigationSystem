package com.nav;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ManhattanDistFormula {
		
	//this method will give you Manhattan distance using the formula	
	public int getDistanceToGoal(int x1, int x2, int y1, int y2){
			
			int distanceToGoal = Math.abs(x1-x2) + Math.abs(y1-y2);			
			
			return distanceToGoal;
		}
		
	//this method will read and get the cost values of the tiles from properties file
		public int getTileToTileCost(String tile, String path){
			int tileCost = 0;
			try { System.out.println(path);
				Properties prop = loadPropertiesfile(path);
				String forestSign = prop.getProperty("forest");
				String mountainSign = prop.getProperty("mountain");
				int flatlandsCost = Integer.parseInt(prop.getProperty("flatlandsCost"));
				int forestCost = Integer.parseInt(prop.getProperty("forestCost"));
				int mountainCost = Integer.parseInt(prop.getProperty("mountainCost"));					
				String flatlands1 = prop.getProperty("flatlands1");
				String flatlands2 = prop.getProperty("flatlands2");
				String flatlands3 = prop.getProperty("flatlands3");
				
				if(tile.equals(flatlands1) || tile.equals(flatlands2) || tile.equals(flatlands3)){
					tileCost = flatlandsCost;
				}else if(tile.equals(forestSign)){
					tileCost = forestCost;
				}else if(tile.equals(mountainSign)){
					tileCost = mountainCost;
				}			
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}			
			return tileCost;
		}
		
		//this method will read the properties file
		public Properties loadPropertiesfile(String path) throws IOException{
			Properties props = new Properties(); 			
			try{
				FileInputStream in = new FileInputStream(path); System.out.println(in.read());
				props.load(in);
				in.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			return props;
		}	
		
		//this method will get you the final output array and will print the final output to a string
		public String[][] calShortestPathAlgorithm(String[][] matrixArray, int[] startP, int[] endP) throws IOException{
			
			ManhattanDistFormula manhattanDis = new ManhattanDistFormula();
			String path = "C:\\eclipse\\workspace\\navigationSystem\\WebContent\\WEB-INF\\appData.properties";
			Properties prop = manhattanDis.loadPropertiesfile(path);
		    String pathSign = prop.getProperty("pathsign");
		    String[][] finalMatrix = matrixArray;
			int totalCost = 0;
			//start point should change to pathSign
			finalMatrix[0][0] =  pathSign;			
			
			//First move to right
			String currentSign = finalMatrix[0][1];
			int tileCost = getFirstMoveCost(startP, endP, currentSign);
			int disToGoal = getDistanceToGoal(startP[0], startP[1], endP[0], endP[1]);
			totalCost = tileCost + disToGoal;
			finalMatrix[0][1] =  pathSign;
			int checkTile1 = 0;
			int checkTile2 = 1;
			
			//there are 50 rows so 49 moves.
			for(int i=0; i<finalMatrix.length; i++){
					
					int horizontalCost = getHorizontalTileTotalCost(checkTile1, checkTile2, endP, finalMatrix, path);
					int verticalCost =  getVerticalTileCost(checkTile1, checkTile2, endP, finalMatrix, path);
					int diagnalCost = getDiagnalTileCost(checkTile1, checkTile2, endP, finalMatrix, path);
					
					if ( horizontalCost > verticalCost && horizontalCost > diagnalCost )
						finalMatrix[checkTile1+1][checkTile2] = pathSign;
				      else if ( verticalCost > horizontalCost && verticalCost > diagnalCost )
				    	  finalMatrix[checkTile1][checkTile2+1] = pathSign;
				      else if ( diagnalCost > horizontalCost && diagnalCost > verticalCost )
				    	  finalMatrix[checkTile1+1][checkTile2+1] = pathSign;
				     
			}		
			return finalMatrix;
			
		}
		//this method will give horizontal sign total cost per move
		public static int getHorizontalTileTotalCost(int tile1, int tile2, int[] endP, String[][] finalMatrix, String path){
			
			int x1 = tile1;
			int y1 = tile2+1;
			int x2 = endP[0];
			int y2 = endP[1];
			ManhattanDistFormula manhattanDis = new ManhattanDistFormula();
			String sign = finalMatrix[x1][y1];
			ManhattanDistFormula manhattanDis1 = new ManhattanDistFormula();
			int cost = manhattanDis1.getTileToTileCost(sign, path);	
			int disToGoal = manhattanDis1.getDistanceToGoal(x1, y1, x2, y2);
			return disToGoal;
		}
		
		//this method will give vertical sign total cost per move
		public static int getVerticalTileCost(int tile1, int tile2, int[] endP, String[][] finalMatrix, String path){
			
			int x1 = tile1+1;
			int y1 = tile2;
			int x2 = endP[0];
			int y2 = endP[1];
			ManhattanDistFormula manhattanDis = new ManhattanDistFormula();
			String sign = finalMatrix[x1][y1];
			ManhattanDistFormula manhattanDis1 = new ManhattanDistFormula();
			int cost = manhattanDis1.getTileToTileCost(sign, path);	
			int disToGoal = manhattanDis1.getDistanceToGoal(x1, y1, x2, y2);
			return disToGoal;
		}
		
		////this method will give vertical sign total cost per move
		public static int getDiagnalTileCost(int tile1, int tile2, int[] endP, String[][] finalMatrix, String path){

			int x1 = tile1+1;
			int y1 = tile2+1;
			int x2 = endP[0];
			int y2 = endP[1];
			ManhattanDistFormula manhattanDis = new ManhattanDistFormula();
			String sign = finalMatrix[x1][y1];
			ManhattanDistFormula manhattanDis1 = new ManhattanDistFormula();
			int cost = manhattanDis1.getTileToTileCost(sign, path);	
			int disToGoal = manhattanDis1.getDistanceToGoal(x1, y1, x2, y2);
			return disToGoal;
		}
		
		public static int getFirstMoveCost(int[] startP, int[] endP, String currSign){			
			ManhattanDistFormula manhattanDis = new ManhattanDistFormula();
			String path = "C:\\eclipse\\workspace\\navigationSystem\\WebContent\\WEB-INF\\appData.properties";
			int cost = manhattanDis.getTileToTileCost(currSign, path);			
			return cost;
		}
}
