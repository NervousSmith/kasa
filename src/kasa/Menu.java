package kasa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.omg.CORBA.DynAnyPackage.TypeMismatch;

public class Menu {
	ArrayList<Paragon> paragony = new ArrayList<Paragon>();
	ArrayList<Produkt> produkty = new ArrayList<Produkt>();
	Paragony para = new Paragony();
	IProdukty pro = new Produkty();
	Menu()
	{
		paragony = para.paragony;
	}
	public void wypisz()
	{
		int wersja =0;
		String zakoncz="1";
		String opcja=" ";
		while(!opcja.equals(zakoncz))
		{
			opcja=" ";
		System.out.println("Podaj co chcesz zrobiæ");
			System.out.println("P - nowy paragon");
			System.out.println("L - lista paragonow");
			System.out.println("W - wyswietl paragon");
			System.out.println("R - raport dobowy");
			System.out.println("B - modyfikacja bazy produktow");
			System.out.println("K - koniec");
			Scanner odczyto = new Scanner(System.in);
			opcja = odczyto.nextLine();
			switch (opcja)
			{	
			case "P":
			case "p":
				p();
			break;
			case "w":
			case "W":
				w();
			break;
			case "l":
			case "L":
				l();
			break;
			case "r":
			case "R":
				r();
			break;
			case "k":	
			case "K":
				opcja=zakoncz;
			break;
			case "b":
			case "B":
				wersja = b(wersja);
			break;
			}

		}
	}
	public void p() 
	{
		int nr = paragony.size()+1;
		Paragon paragon = new Paragon();
		paragon.numer = nr ;
		paragony.add(paragon);
		System.out.println("Paragon numer : " +paragony.get(nr-1).numer);
		int kod = 0, ilosc = 0;
		String opcja2=" ";
		String koniec="x";
		String dodaj="+";
		String wynik="=";
		while(!opcja2.equals(koniec))
		{
			WpisDoParagonu nowy = new WpisDoParagonu();
			System.out.println("Podaj kod produktu oraz ilosc");
			Scanner odczytkod = new Scanner(System.in);
			try
			{
				kod = odczytkod.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.out.println("Podaj odpowiedni kod produktu ");
				return;
			}
			Scanner odczytil = new Scanner(System.in);
			try
			{
				ilosc = odczytil.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.out.println("Podaj odpowiedni kod produktu ");
				return;
			}
			nowy.ilosc=ilosc;
			try
			{
				nowy.produkt=pro.daj(kod);
			}
			catch(NullPointerException e)
			{
				System.out.println("Nie ma takiego kodu");
			}
			nowy.wypis();	
			System.out.println("Podaj co chcesz zrobic");
			Scanner odczyt2 = new Scanner(System.in);
			opcja2 = odczyt2.nextLine();
			if(opcja2.equals(dodaj))
			{
				paragon.linijka.add(nowy);
			}
			if(opcja2.equals(wynik))
				{
				paragon.linijka.add(nowy);
				paragon.wypisParagonu();
				paragon.stawki();
				paragon.wartoscStawek();
				paragon.wypisPodatku();
				System.out.println("Kwota do zaplaty " +paragon.koszt());
				System.out.println("Podaj forme platnosci");
				System.out.println("1-gotowka");
				System.out.println("2-karta");
				Scanner odczytplatnosc = new Scanner(System.in);
				int pl = odczytplatnosc.nextInt();
				switch (pl)
				{
					case 2 :
					 	paragon.platnosc.karta();
					break;
				    case 1 :
				    	paragon.platnosc.gotowka(paragon.koszt());
					break;
				}
				opcja2=koniec;
			}
		}
	}
	
	public void w()
	{
		Paragon paragon = new Paragon();
		System.out.println("Podaj numer paragonu");
		Scanner odczytkod = new Scanner(System.in);
		int kod = odczytkod.nextInt();
		for(int i=0;i<paragony.size();i++)
		{
			if(paragony.get(i).numer==kod)
			{
				paragon = paragony.get(i);
			}
		}
		paragon.wypisParagonu();	
	}
	public void l()
	{
		String data;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = new GregorianCalendar();
		Calendar.getInstance();
		data = dateFormat.format(calendar.getTime());
		for(int i=0; i<paragony.size();i++)
		{
			if(data.equals(paragony.get(i).data))
			{
				System.out.println("Paragon numer: "+paragony.get(i).numer +" Godzina wystawienia : "+ paragony.get(i).godzina+" Liczba produktów : "+ paragony.get(i).iloscProduktow()+" Wartosc sprzedazy : "+ paragony.get(i).koszt());
			}
		}
	}
	public void r()
	{
		Raport raport = new Raport();
		int liczba = paragony.size();
		double podatek = 0, sprzedaz = 0;
		for(int i=0; i<paragony.size();i++)
		{
			podatek += paragony.get(i).vat();
			sprzedaz += paragony.get(i).koszt();
		}
		raport.liczba=liczba;
		raport.sprzedaz=sprzedaz;
		raport.podatek=podatek;
		raport.stawki=para.stawkiVat();
		raport.stawki2=para.wartosciVat();
		raport.wypisz();
		Dane.zapiszRaport(raport);
	}
	public int b(int wersja)
	{
		wersja+=1;
		System.out.println("Podaj co chcesz zrobic");
		Scanner odczyt2 = new Scanner(System.in);
		int pl = odczyt2.nextInt();
		switch (pl)
		{
			 case 1 :
			 	pro.wypisz();
			 break;
			 case 2 :
			    pro.dodaj(wersja);
			 break;
			 case 3 :
				pro.usun(wersja);
			break;
			case 4 :
				 pro.modyfikuj(wersja);
			break;
		}
		return wersja;	
			
	}
	
}