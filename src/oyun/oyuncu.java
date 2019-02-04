package oyun;

import java.util.Random;
import java.util.Scanner;

public class oyuncu {
    public boolean insanMi;
    public char karakter;
    public String hamle;
     
    public oyuncu()
    {
        insanMi=true;
        karakter='X';
    }
     
    public oyuncu(boolean insanMiKontrolu)
    {
        insanMi=insanMiKontrolu;
        if(insanMi==true)
            karakter='X';
        else
            karakter='O';
    }
     
    public oyuncu(boolean insanMiKontrolu,char kr)
    {
        insanMi=insanMiKontrolu;
        karakter=kr;
    }
     
    public char karakteriAl()
    {
        return karakter;
    }
     
    public boolean oyuncuTurunuAl()
    {
        return insanMi;
    }
     
    public String oyuncununHamlesiniAl()
    {
        return hamle;
    }
     
    public String insanOyuncuHamlesiniKontrol()
    {
        Scanner oku=new Scanner(System.in);
        System.out.println("Satýrý Giriniz:");
        int x=oku.nextInt();
        System.out.println("Sütunu Giriniz:");
        int y=oku.nextInt();
        x--;
        y--;
        hamle=""+x+y;
        return hamle;
    }
     
    String bilgisayarHamlesiUret(char [][] tahta)
    {
        Random rand=new Random();
        int x;
        int y;
         do {    
            x=rand.nextInt(tahta.length);
            y=rand.nextInt(tahta.length);
        }while(tahta[x][y]=='X' || tahta[x][y]=='O');
         hamle=""+x+y;
            return hamle;
     
    }
     
}