package kasa;

import java.util.Scanner;

public class Platnosc {
	int rodzaj;
	double kwota;
	double reszta;
	double gotowka;
	String karta;
	int zatwierdzenie;
	Platnosc()
	{
		rodzaj = 0; // 0 - oznacza p�ato�� got�wk�; 1 - oznacza p�atno�� kart�
		kwota = 0; 
		reszta = 0;
		karta = null; // domyslnie p�atno�� got�wk� wi�c pole puste
		zatwierdzenie = 0; // 0 - oznacza brak zatwierdzenia
	}
	public void karta()
	{
		System.out.println("P�atno�� kart�");
		rodzaj = 1;
		System.out.println("Podaj ostatnie 4 cyfry numeru karty ");
		Scanner odczyt = new Scanner(System.in);
		karta = odczyt.nextLine();
		System.out.println("Czy p�atno�� jest zatwierdzona ");
		Scanner  odczytz= new Scanner(System.in);
		zatwierdzenie = odczytz.nextInt();
	}
	public void gotowka(double koszt)
	{
		kwota = koszt;
		System.out.println("Podaj gotowke");
		Scanner  odczytz= new Scanner(System.in);
		gotowka = odczytz.nextDouble();
		reszta = gotowka - kwota;
		System.out.println("Reszta: " +reszta);
		zatwierdzenie = 1;

	}
}