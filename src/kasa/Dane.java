package kasa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dane {
	public static ArrayList<Produkt> wczytajProdukty()
	{
		Scanner scanIn=null;
		String input="";
		ArrayList<Produkt> produkty = new ArrayList<Produkt>();
		try 
		{
			scanIn = new Scanner(new BufferedReader(new FileReader("produkty.csv")));
			while(scanIn.hasNextLine())
			{
				Produkt p = new Produkt();
				input=scanIn.nextLine();
				String[] InArray = input.split(";");
				p.kod=Integer.parseInt(InArray[0]);
				p.nazwa=InArray[1];
				p.jednostka=InArray[2];
				p.vat=Double.parseDouble(InArray[3]);
				p.cena=Double.parseDouble(InArray[4]);
				produkty.add(p);
				
			}
		} catch (FileNotFoundException e)
		{
			System.out.println("Nie znaleziono pliku");

		}
		return produkty;
	}
	
	public static ArrayList<Produkt> wczytajProduktyNaglowki()
	{
		Scanner scanIn=null;
		String input="";
		ArrayList<Produkt> produkty = new ArrayList<Produkt>();
		String[] nag = new String[5];
		Map<String, String> protukdy = new HashMap<>();
		try 
		{
			// TODO if'y do mapy protukdy
			scanIn = new Scanner(new BufferedReader(new FileReader("produkty.csv")));
			input=scanIn.nextLine();
			String[] InArray = input.split(";");
			for(int i =0; i<5; i++)
			{
				nag[i] = InArray[i];
			}
			while(scanIn.hasNextLine())
			{
				protukdy.put(nag[0], InArray[0]);
				protukdy.put(nag[1], InArray[1]);
				protukdy.put(nag[2], InArray[2]);
				protukdy.put(nag[3], InArray[3]);
				protukdy.put(nag[4], InArray[4]);
			}
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Nie znaleziono pliku");

		}
		return produkty;
	}
	public static void zapiszParagon(Paragon p) 
	{
		try 
		{
			PrintWriter newRecipt = new PrintWriter(new FileOutputStream(new File(sciezka()+"/"+sciezka()+"/"+"paragon"+p.numer+".csv"), true ));
			newRecipt.print(p.numer + "," + p.iloscProduktow() + ",");
			for(int i=0;i<p.linijka.size();i++) 
			{
				newRecipt.print("," + p.linijka.get(i).produkt.kod +","+ p.linijka.get(i).produkt.nazwa+","+ p.linijka.get(i).produkt.jednostka+","+ p.linijka.get(i).produkt.cena+","+ p.linijka.get(i).produkt.vat);
			}
			newRecipt.println("," + p.vat() +"," + p.koszt());
			newRecipt.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	//public static ArrayList<Paragon> odczytajParagon()

	public static void kopiaCSV(int v)
	{
		Path zrodlo = Paths.get("produkty.csv");
        Path cel = Paths.get("produkty"+sciezka()+"wersja"+v+".csv");
		try {
			Files.copy(zrodlo, cel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void aktualizacjaCSV(ArrayList<Produkt> produkty)
	{
		File doUsuniecia = new File("produkty.csv");
		doUsuniecia.delete();
		try 
		{
			PrintWriter newCSV = new PrintWriter(new FileOutputStream(new File("produkty.csv")));
			for(int i=0;i<produkty.size();i++) 
			{
				Produkt p = produkty.get(i);
				newCSV.println(p.kod +";"+ p.nazwa+";"+ p.jednostka+";"+ p.cena+";"+ p.vat+";");
			}
			
			newCSV.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void dzienny(String path)
	{
		new File(path).mkdirs();
	}
	
	public static void zapiszRaport(Raport raport)
	{
		PrintWriter pw;
		try {
				pw = new PrintWriter(new File(sciezka()+"_raport.txt"));
				StringBuilder sb = new StringBuilder();
			    sb.append(raport.godzina);
			    sb.append(" ");
			    sb.append(raport.liczba);
			    sb.append(" ");
			    sb.append(raport.podatek);
			    sb.append(" ");
			    sb.append(raport.sprzedaz);
			    sb.append(" ");
			    sb.append(raport.stawki);
			    sb.append(" ");
			    for(int i=0; i<raport.stawki.size();i++)
			    {
			    	sb.append(raport.stawki.get(i));
			 	    sb.append(" ");
			 	    sb.append(raport.stawki2.get(i));
			 	    sb.append(" ");
			    }
			    pw.write(sb.toString());
		        pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}   
	}
	public static String sciezka()
	{
		String data;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = new GregorianCalendar();
		Calendar.getInstance();
		data = dateFormat.format(calendar.getTime());
		String path=data;
		return path;
	}
}

