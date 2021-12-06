package model.exceptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EscritorExceptions {
	
	public static void escribirExceptions(String direccion,Exception excepcion) {
		File archivo;
		FileWriter fw= null;
		PrintWriter pw= null;
		archivo = new File(direccion);
		if(!archivo.exists()) {
			try {
				archivo.createNewFile();
				fw = new FileWriter(direccion,true);
				pw = new PrintWriter(fw);
				pw.print("Se ha lanzado una excepcion con el mensaje: " + excepcion.getMessage() + "\n");
				fw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			try {
				fw = new FileWriter(direccion,true);
				pw = new PrintWriter(fw);
				pw.print("Se ha lanzado una excepcion con el mensaje: " + excepcion.getMessage() + "\n");
				fw.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}

