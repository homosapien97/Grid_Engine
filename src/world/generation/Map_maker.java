package world.generation;
import java.io.*;

public class Map_maker{	
	final static int WPARAM = 256;
	final static int LPARAM = 256;
//-------------------------------------------------------------------
	final static int PARAM0  = 0;
	final static int PARAM1  = 256;
	final static int PARAM2  = 15;
	final static int PARAM3  = 15;
	final static int PARAM4  = 10;
	final static int PARAM5  = 10;
	final static int PARAM6  = 0;
	final static int PARAM7  = 0;
	final static int PARAM8  = 0;
	final static int PARAM9  = 0;
	final static int PARAM10 = 0;
	final static int PARAM11 = 0;
//-------------------------------------------------------------------
	final static char[][] map = new char[WPARAM][LPARAM];
//-------------------------------------------------------------------
	static String fileName = "mapp.txt";
//-------------------------------------------------------------------
//	public Map_maker(){
//		char[][] map = new char[WPARAM][LPARAM];
//		for(int i = 0; i < PARAM1; i++)
//		{
//			for(int j = 0; j < PARAM1; j++)
//			{
//				map[i][j] = 'x';
//			}
//		}
//		for(int i = PARAM0 + 1; i<PARAM1-1; i++){
//			for(int a = PARAM2; a<PARAM1-(1+PARAM3); a+=40){
//				for(int j = 0+a; j<a+PARAM3; j++){
//					map[i][j] = 'o';
//				}
//			}
//		}
//		for(int i = PARAM0+1; i<PARAM1-1; i++){
//			while(i%16 == 0){
//				for(int j = 0+i; j<PARAM4+i; j++){
//					for(int k = 1; k<PARAM1-1; k++){
//						map[j][k] = 'o';
//					}
//				}
//				i+=1;
//			}
//		}
//	}
	//----------------------------------------------------------------------------
//	static {
//		for(int i = 0; i < PARAM1; i++)
//		{
//			for(int j = 0; j < PARAM1; j++)
//			{
//				map[i][j] = 'x';
//			}
//		}
//		for(int i = PARAM0 + 1; i<PARAM1-1; i++){
//			for(int a = PARAM2; a<PARAM1-(1+PARAM3); a+=40){
//				for(int j = 0+a; j<a+PARAM3; j++){
//					map[i][j] = 'o';
//				}
//			}
//		}
//		for(int i = PARAM0+1; i<PARAM1-1; i++){
//			while(i%16 == 0){
//				for(int j = 0+i; j<PARAM4+i; j++){
//					for(int k = 1; k<PARAM1-1; k++){
//						map[j][k] = 'o';
//					}
//				}
//				i+=1;
//			}
//		}
//	}
	static {
		int count = 0;
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			 while(((line = bufferedReader.readLine()) != null) && count!= LPARAM) {
	            	for(int i = 0; i<WPARAM; i++){
	            		map[count][i] = line.charAt(i);
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
		return map;
	}
}