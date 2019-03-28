package kasa;

import java.util.ArrayList;

public class Paragony {
	ArrayList<Paragon> paragony;
	Paragony() 
	{
		paragony = new ArrayList<Paragon>();
	}
	
	public ArrayList<Double> stawkiVat()
	{
		ArrayList<Double> stawki = new ArrayList<Double>();
		for(int i=0; i<paragony.size();i++)
		{
			for(int j=0; j<paragony.get(i).stawki.size();j++)
			{
				if(!stawki.contains(paragony.get(i).stawki.get(j)))
				{
					stawki.add(paragony.get(i).stawki.get(j));
				}
			}
		}
		return stawki;
	}
	public ArrayList<Double> wartosciVat()
	{
		ArrayList<Double> stawki = new ArrayList<Double>();
		ArrayList<Double> stawki2 = new ArrayList<Double>();
		stawki = stawkiVat();
		double wynik=0;
		for(int k=0; k<paragony.size();k++) 
		{
			for(int i=0;i<stawki.size();i++)
			{
				for(int j=0;j<paragony.get(k).linijka.size();j++)
				{
					if(stawki.get(i)==paragony.get(k).linijka.get(j).produkt.vat)
					{
						wynik += paragony.get(k).linijka.get(j).vat();
					}
				}
			}
			stawki2.add(wynik);
		}
		return stawki2;
	}
	
}
