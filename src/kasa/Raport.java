package kasa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Raport {
	int liczba;
	double podatek, sprzedaz;
	String godzina;
	ArrayList<Double> stawki = new ArrayList<Double>();
	ArrayList<Double> stawki2 = new ArrayList<Double>();
	Raport()
	{
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH-mm-ss");
		Calendar calendar = new GregorianCalendar();
		Calendar.getInstance();
		godzina = dateFormat2.format(calendar.getTime());
	}
	public void wypisz()
	{
		System.out.println("Liczba wystawionych paragonów: "+liczba);
		for(int i=0; i<stawki.size();i++)
		{
			System.out.println("Stawka VAT: "+ stawki.get(i)+" Wartosc sprzedazy: "+stawki2.get(i));
		}
		System.out.println("Calkowita wartosc podatku: "+podatek +" Calkowita wartosc sprzedazy: "+sprzedaz  );
	}
}
