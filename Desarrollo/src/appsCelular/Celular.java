package appsCelular;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import jdk.jfr.events.FileWriteEvent;
import sun.swing.AccumulativeRunnable;

public class Celular extends EjercicioOIA {
	private int[] apps;
	private int cantApps;
	private int cantMb;

	public Celular(File e, File s) {
		super(e, s);
		Scanner sc;
		try {
			sc = new Scanner(this.entrada);
			this.cantApps = sc.nextInt();
			this.cantMb = sc.nextInt();
			this.apps = new int[this.cantApps];

			for (int i = 0; i < apps.length; i++) {
				this.apps[i] = sc.nextInt();
			}

			sc.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void resolver() {
		int i = 0;
		int j = 0;
		int pos = 0;

		int cantMenor = 0;
		int tamMayor = this.apps[0];

		int cantParcial; // cantidad de appp
		int tamParcial; // tamañno app

		while (i < this.apps.length && tamMayor != 0) {
			j = i;
			cantParcial = 0;
			tamParcial = 0;
			while (j < this.apps.length && tamParcial < this.cantMb) {
				tamParcial += this.apps[j];
				cantParcial++;
				j++;
			}

			if (tamParcial >= this.cantMb) {
				if (cantMenor >= cantParcial || cantMenor == 0) {
					if (cantMenor == cantParcial) {
						if (tamMayor < tamParcial) {
							tamMayor = tamParcial;
							pos = i;
							cantMenor = cantParcial;
						}
					} else {
						tamMayor = tamParcial;
						pos = i;
						cantMenor = cantParcial;
					}
				}
			} else {
				tamMayor = 0;
			}

			i++;
		}

		
		System.out.println(cantMenor + " " + (pos+1));
		escritura(cantMenor, pos + 1);

	}

	public void escritura(int cantMinApp, int pos) {
		try {
			FileWriter fw = new FileWriter(this.salida);
			PrintWriter pw = new PrintWriter(fw);

			if (cantMinApp != 0) {
				pw.println(cantMinApp);
				pw.print(pos);
			} else
				pw.println("MEMORIA INSUFICIENTE");

			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Celular celu = new Celular(new File("fatiga.in"), new File("fatiga.out"));
		celu.resolver();
	}

}