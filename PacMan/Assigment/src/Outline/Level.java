package Outline;
/**
 *  @author Deng pengcheng 18206167
 * this controls the level of the game,
 * the level determines the place of the maze,
 * and the  initial position of ghosts and pacman
 */
import java.io.File;
import java.util.Scanner;




public class Level {
	private int ghostSpeed;
	private String filename;
	/**
	 * set the level of game
	 * @param s this is the speed f ghosts
	 * @param file this is the file determines the place
	 */
	public Level(int s,String file) {
		ghostSpeed = s;
		filename = file;
	}
	/**
	 * get the speed of ghost 
	 * @return return the speed f ghosts 
	 */
	int getSpeed() {
		return ghostSpeed;
	}
	/**
	 * this method read the file,
	 * store the information in an int[],
	*int[] is used to pass the place expediently
	 * @return return an int[]
	 */
	int[] getstring() {
		int[] ii = new int[480];
		try {
	    	File map=new File(filename);
	    	System.out.println(map.canRead());
	    	Scanner in = new Scanner(map);
			String line;
			char c;
			int i=0;
			while(in.hasNextLine()){
			    line=in.nextLine();
			    for(int x=0;x<line.length();x++) {
			        c=line.charAt(x);
			        if (Integer.valueOf(c)==37){//%
		        	    ii[i]=37;
		        	}
			        else if (Integer.valueOf(c)==46){//.
		        	    ii[i]=46;
	       	        }
			        else if (Integer.valueOf(c)==42){//*
		        	    ii[i]=42;
	       	        }
			        else if (Integer.valueOf(c)==70){//F
		        		ii[i]=70;
	       	        }
			        else if (Integer.valueOf(c)==71){//G
		        	    ii[i]=71;
	       	        }
			        else if (Integer.valueOf(c)==80){//P
		        	    ii[i]=80;
	       	        }
			        i++;
			    }
			}
			in.close();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    		System.out.println();
	    	}
		return ii;
	}

}
