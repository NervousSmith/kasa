package kasa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Paragon {
	int numer;
	String data;
	String godzina;
	Platnosc platnosc = new Platnosc();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH-mm-ss");
	Calendar calendar = new GregorianCalendar();
	ArrayList<WpisDoParagonu> linijka = new ArrayList<WpisDoParagonu>();
	ArrayList<Double> stawki = new ArrayList<Double>();
	ArrayList<Double> stawki2 = new ArrayList<Double>();
	Paragon()
	{
		Calendar.getInstance();
		data = dateFormat.format(calendar.getTime());
		godzina = dateFormat2.format(calendar.getTime());
	}
	public double koszt()
	{
		double koszt=0;
		for(int i=0; i<linijka.size(); i++)
		{
			koszt += linijka.get(i).cenaBrutto()*linijka.get(i).ilosc;
		}
		return koszt;
	}
	public double vat()
	{
		double caly=0;
		for(int i=0; i<linijka.size(); i++)
		{
			caly += linijka.get(i).vat();
		}
		return caly;
	}
	public void wypisParagonu()
	{
		for(int i=0; i<linijka.size(); i++)
		{
			System.out.println("Paragon numer: "+numer);
			System.out.println(linijka.get(i).produkt.nazwa +" "+ linijka.get(i).ilosc +" x "+ linijka.get(i).cenaBrutto());
		}
	}
	public void stawki()
	{
		for(int i=0; i<linijka.size();i++)
		{
			if(!stawki.contains(linijka.get(i).produkt.vat))
			{
				stawki.add(linijka.get(i).produkt.vat);
			}
		}
	}
	public void wartoscStawek()
	{
		double wynik=0;
		for(int i=0;i<stawki.size();i++)
		{
			for(int j=0;j<linijka.size();j++)
			{
				if(stawki.get(i)==linijka.get(j).produkt.vat)
				{
					wynik += linijka.get(j).vat();
				}
			}
			stawki2.add(wynik);
		}
		
	}
	public void wypisPodatku()
	{
		System.out.println("Stawki podatku wynosz¹");
		for(int i=0;i<stawki.size();i++)
		{
			System.out.println("Stawka: " +stawki.get(i)*100+"% " +"Koszt: "+ stawki2.get(i));
		}
	}
	public int iloscProduktow()
	{
		int ilosc=0;
		for(int i=0; i<linijka.size(); i++)
		{
			ilosc += linijka.get(i).ilosc;
		}
		return ilosc;
	}
}
