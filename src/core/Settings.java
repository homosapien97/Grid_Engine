package core;

public class Settings {
	public static String inventoryKeyBind = "I";
	public static String hudKeyBind = "H";
	
	public static void setInventoryKeyBind(String str){
		str = str.substring(0, 1);
		str = str.toUpperCase();
		
		inventoryKeyBind = str;
	}
	
	public static void setHUDKeyBind(String str){
		str = str.substring(0, 1);
		str = str.toUpperCase();
		
		hudKeyBind = str;
	}
}