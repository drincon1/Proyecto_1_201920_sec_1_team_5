package main;
import java.io.IOException;

import controller.Controller;

public class MVC {
	
	public static void main(String[] args) throws IOException 
	{
		Controller controler = new Controller();
		controler.run();
	}
}
