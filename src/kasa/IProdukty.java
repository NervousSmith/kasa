package kasa;

import java.io.IOException;
import java.util.ArrayList;

public interface IProdukty {
	void wypisz();
	void dodaj(int wersja);
	void usun(int wersja);
	void modyfikuj(int wersja);
	Produkt daj(int kod) throws NullPointerException ;
	
}
