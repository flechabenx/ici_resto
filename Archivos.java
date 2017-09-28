package ici_resto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Archivos {
	private Path archivo;

	public Path getRuta() {
		archivo = Paths.get("mesas.txt");
		return archivo;
	}

	public String leerArchivo() {
		String texto;
		try {
			texto = new String(Files.readAllBytes(getRuta()));
		} catch (IOException e) {
			System.err.println("Ha ocurrido un error al intentar leer el archivo.");
			return null;
		}
		return texto;
	}
		
	public void almacenarDatos(ArrayList<Mesa> mesas) {
		String contenido = "Mesas:/n";
		for (int i = 0; i < mesas.size(); i++) {
			contenido = contenido + "-Mesa "+mesas.get(i).getNumero()+";"+mesas.get(i).getCapacidad()+";"+mesas.get(i).getEstado()+";"+mesas.get(i).getConsumo()+"\n";
		}
		agregarTexto(contenido);
	}

	public boolean esPrimeraVez() {

		return Files.notExists(getRuta());
	}
	
	public void agregarTexto(String texto){
		try {
			Files.write(getRuta(), texto.getBytes());
			System.out.println("Archivo guardado correctamente");
		} catch (IOException e) {
			System.out.println("Error al intentar crear el archivo");
		}
	}
	
	public ArrayList<Mesa> leerMesas(){
		ArrayList<Mesa> listaMesas=new ArrayList<Mesa>();
		String texto=leerArchivo();
		String[] mesas = texto.split("-");
		for(int i=1;i<mesas.length;i++){
            String[] propiedades = mesas[i].split(";");
            String[]aux=propiedades[0].split(" ");
            propiedades[3]=propiedades[3].replaceAll("[\n\r]","");
            Mesa mesa=new Mesa(Integer.parseInt(aux[1]),Integer.parseInt(propiedades[1]),propiedades[2],Integer.parseInt(propiedades[3]));
            listaMesas.add(mesa);
		}
		return listaMesas;
	}
}
