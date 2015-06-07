package tools;

public class Map_maker{	
	final int WPARAM = 256;
	final int LPARAM = 256;
//-------------------------------------------------------------------
	final int PARAM0  = 0;
	final int PARAM1  = 256;
	final int PARAM2  = 15;
	final int PARAM3  = 15;
	final int PARAM4  = 10;
	final int PARAM5  = 10;
	final int PARAM6  = 0;
	final int PARAM7  = 0;
	final int PARAM8  = 0;
	final int PARAM9  = 0;
	final int PARAM10 = 0;
	final int PARAM11 = 0;
	
//-------------------------------------------------------------------
	public Map_maker(){
		char[][] map = new char[WPARAM][LPARAM];
		for(int i = 0; i < PARAM1; i++)
		{
			for(int j = 0; j < PARAM1; j++)
			{
				map[i][j] = 'x';
			}
		}
		for(int i = PARAM0+1; i<PARAM1-1; i++){
			for(int a = PARAM2; a<PARAM1-(1+PARAM3); a+=40){
				for(int j = 0+a; j<a+PARAM3; j++){
					map[i][j] = 'o';
				}
			}
		}
		for(int i = PARAM0+1; i<PARAM1-1; i++){
			while(i%16 == 0){
				for(int j = 0+i; j<PARAM4+i; j++){
					for(int k = 1; k<PARAM1-1; k++){
						map[j][k] = 'o';
					}
				}
				i+=1;
			}
		}
	}
	public char[] mapReturn(char[] map){
		return map;
	}
}