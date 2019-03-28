package kasa;

import java.util.ArrayList;
import java.util.Scanner;

public class Produkty implements IProdukty {
	ArrayList<Produkt> produkty = new ArrayList<Produkt>();
	Produkty()
	{
		produkty = Dane.wczytajProdukty();
	}
	@Override
	public Produkt daj(int kod) throws NullPointerException 
	{
		Produkt p = null;
		for(int i=0; i<produkty.size();i++)
		{
			if(produkty.get(i).kod == kod)
			{
				p = produkty.get(i);
			}
		}
		return p;
	}
	@Override
	public void wypisz()
	{
		for(int i=0; i<produkty.size();i++)
		{
			Produkt p = produkty.get(i);
			System.out.println(p.kod+" "+p.nazwa+" "+p.jednostka+" "+p.cena+" "+p.vat);
		}
	}
	@Override
	public void dodaj(int wersja)
	{
		Produkt p = new Produkt();
		System.out.println("Dodajesz nowy produkt ");
		p.kod = produkty.size();
		System.out.println("Podaj nazwe produktu ");
		Scanner odczytnazwa = new Scanner(System.in);
		p.nazwa = odczytnazwa.nextLine();
		System.out.println("Podaj jednostke produktu ");
		Scanner odczytjednostka = new Scanner(System.in);
		p.jednostka = odczytjednostka.nextLine();
		System.out.println("Podaj cene produktu ");
		Scanner odczytcena = new Scanner(System.in);
		p.cena = odczytcena.nextDouble();
		System.out.println("Podaj VAT produktu ");
		Scanner odczytVAT = new Scanner(System.in);
		p.vat = odczytVAT.nextDouble();
		produkty.add(p);
		Dane.kopiaCSV(wersja);
		Dane.aktualizacjaCSV(produkty);
	}
	@Override
	public void usun(int wersja)
	{
		System.out.println("Podaj kod produktu do usuniêcia ");
		Scanner odczyt = new Scanner(System.in);
		int kod = odczyt.nextInt();
		for(int i=0; i<produkty.size();i++)
		{
			if(produkty.get(i).kod==kod)
			{
				produkty.remove(i);
			}
		}
		Dane.kopiaCSV(wersja);
		Dane.aktualizacjaCSV(produkty);
	}
	@Override
	public void modyfikuj(int wersja)
	{
		System.out.println("Podaj kod produktu do modyfikacji ");
		Scanner odczyt = new Scanner(System.in);
		int kod = odczyt.nextInt();
		for(int i=0; i<produkty.size();i++)
		{
			if(produkty.get(i).kod==kod)
			{
				System.out.println("Modyfikujesz produkt "+ produkty.get(i).nazwa);
				System.out.println("Podaj nowa nazwe produktu ");
				Scanner odczytnazwa = new Scanner(System.in);
				produkty.get(i).nazwa = odczytnazwa.nextLine();
				System.out.println("Podaj nowa jednostke produktu ");
				Scanner odczytjednostka = new Scanner(System.in);
				produkty.get(i).jednostka = odczytjednostka.nextLine();
				System.out.println("Podaj nowa cene produktu ");
				Scanner odczytcena = new Scanner(System.in);
				produkty.get(i).cena = odczytcena.nextDouble();
				System.out.println("Podaj nowy VAT produktu ");
				Scanner odczytVAT = new Scanner(System.in);
				produkty.get(i).vat = odczytVAT.nextDouble();
				Dane.kopiaCSV(wersja);
				Dane.aktualizacjaCSV(produkty);
			}
			else 
			{
				System.out.println("Nie znaleziono kodu produktu do modyfikacji ");
				break;
			}
		}
	}
}
