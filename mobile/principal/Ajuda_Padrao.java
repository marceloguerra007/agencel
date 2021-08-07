package principal;

import javax.microedition.lcdui.*;

public class Ajuda_Padrao {

    private static String sTexto = "";

    Ajuda_Padrao() {};
    
    Ajuda_Padrao(String pTexto){
    	setTexto(pTexto);
    };

    public static void setTexto(String pTexto){
		sTexto = pTexto;
	}
	
	public static void show(Display display) 
	{
		Alert alert = new Alert("Ajuda");
		alert.setTimeout(Alert.FOREVER);
	
		alert.setString(sTexto);
	
		display.setCurrent(alert);
    }

}
