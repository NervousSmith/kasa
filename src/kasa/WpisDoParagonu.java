package kasa;

public class WpisDoParagonu {
	int ilosc;
	Produkt produkt = new Produkt();
	public double vat()
	{
		double podatek = produkt.cena * produkt.vat;
		return podatek;
	}
	public double  cenaBrutto()
	{
		double cena=(vat()+produkt.cena);
		return cena;
	}
	public void wypis()
	{
		System.out.println(produkt.nazwa +" "+ilosc +" "+produkt.jednostka +" "+cenaBrutto() +" "+produkt.vat +" "+cenaBrutto()*ilosc);
	}

}
