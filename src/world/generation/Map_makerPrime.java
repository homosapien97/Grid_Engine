package world.generation;
import java.io.*;

public class Map_makerPrime {

//--------------------------------------------------------------------	
		final static int WPARAM = 256;
		final static int LPARAM = 256;
		static String fileName = "BSmap.txt";
//--------------------------------------------------------------------	
		final static char[][] mapp = new char[WPARAM][LPARAM];
//--------------------------------------------------------------------		
		public Map_makerPrime(){
			int count = 0;
			final char[][] mapp = new char[WPARAM][LPARAM];
			
			// This will reference one line at a time
	        String line = null;
	
	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = new FileReader(fileName);
	
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	 
	            while(((line = bufferedReader.readLine()) != null) && count!= LPARAM) {
	            	for(int i = 0; i<WPARAM; i++){
	            		mapp[count][i] = line.charAt(i);
	            	}
	            	count++;
	            }
	
	            bufferedReader.close();            
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file "+fileName);                
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file " +fileName);                   
	        }
		}
		static {
			int count = 0;
			String line = null;
			
			try {
				FileReader fileReader = new FileReader(fileName);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				 while(((line = bufferedReader.readLine()) != null) && count!= LPARAM) {
		            	for(int i = 0; i<WPARAM; i++){
		            		mapp[count][i] = line.charAt(i);
		            	}
		            	count++;
		            }
				 bufferedReader.close();
			}
			catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file " +fileName);                
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file "+fileName);                   
	        }
		}
		public static char[][] mapReturn(){
			return mapp;
	}
}
