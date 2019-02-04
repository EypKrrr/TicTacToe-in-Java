package oyun;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author eyup
 */
public class TicTacToe {
 
    public static void main(String[] args) {
         
         
          
        Scanner oku=new Scanner(System.in);
        System.out.println("Yeni oyuna ba�lamak i�in 1'e");
        System.out.println("Kay�tl� oyuna devam etmek i�in 2'ye\nbas�n�z");
        int tercih=oku.nextInt();
         
        oyuncu oyuncu1=new oyuncu();
        oyuncu oyuncu2=new oyuncu();
        oyunTahtasi tahta=new oyunTahtasi(3);
         
        char [][] oyuntahta;
         
        String kullaniciAdi="";
         
        if(tercih==1){
        System.out.println("oyun boyutunu giriniz:");
        int boyut=oku.nextInt();
        System.out.println("Kullan�c� ad�n�z� giriniz:");
        kullaniciAdi=oku.next();
        System.out.println("O/X hangi harfi kullanaca��n�z� giriniz:(E�er girmek istemiyorsan�z E giriniz)");
        char harf=oku.next().charAt(0);
         
 
        if(harf=='E'){
             
            oyuncu1=new oyuncu(true);
            oyuncu2=new oyuncu(false);
 
         
        }else if(harf=='O' || harf=='X')
        {
    
            oyuncu1= new oyuncu(true, harf);
            if(harf=='X'){
 
            oyuncu2=new oyuncu(false,'O');
            }else if(harf=='O'){
            oyuncu2=new oyuncu(false,'X');    
            }
        }
            tahta=new oyunTahtasi(boyut);
            oyuntahta=new char[boyut][boyut];
        }else if(tercih==2){
        String line="";
            try {
                line = dosyadanOku("kayit.txt");
            } catch (IOException ex) {
                Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(line.equals("")){
                System.out.println("Kay�tl� oyun yok!!");
        }else{
            String boy=line.substring(0, 1);         //boyut
            String krktr=line.substring(1,2);        //Kullan�c�n�n harfi
            char harf=krktr.charAt(0);
          
            int boyut=Integer.parseInt(boy);
            String kayit=line.substring(2,(boyut*boyut)+2);         //oyun tahtas�
            kullaniciAdi=line.substring((boyut*boyut)+2);       //kullan�c� ad�
            oyuntahta=new char[boyut][boyut];
            for(int i=0;i<boyut;i++)
            {
                for(int j=0;j<boyut;j++)
                {
                    oyuntahta[i][j]=kayit.charAt(i*boyut+j) ; //str3.substring(i*boyut+j,(i*boyut+j)+1);
                }
            }
             
        oyuncu1=new oyuncu(true,harf);
        if(harf=='X')
            oyuncu2=new oyuncu(false, 'O');
        else
             oyuncu2=new oyuncu(false, 'X');
         
        tahta=new oyunTahtasi(oyuntahta);
         
         
            tahta.oyunTahtasiniYazdir();
         
        }
        }
         
            while(1==1){
             
            String hamle="";
             
            do{
            hamle=oyuncu1.insanOyuncuHamlesiniKontrol();
             
            }while(!tahta.hamleyiYaz(hamle, oyuncu1.karakteriAl()));
             
            oyuntahta=tahta.oyunTahtasiniAl();
             
            if(tahta.kazanan(oyuncu1.karakteriAl())){
                tahta.oyunTahtasiniYazdir();
                    System.out.println(kullaniciAdi+" kazand�!");
                try {
                    dosyayaYaz("kayit.txt", "");
                } catch (IOException ex) {
                    Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
                }
                    break;
                }
                 
                if(tahta.beraberlikKontrol()){
                try {
                    dosyayaYaz("kayit.txt", "");
                } catch (IOException ex) {
                    Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
                }
                tahta.oyunTahtasiniYazdir();
                    System.out.println("Oyun berabere!");
                    break;
                }
             
            hamle=oyuncu2.bilgisayarHamlesiUret(oyuntahta);
            tahta.hamleyiYaz(hamle, oyuncu2.karakteriAl());
             
            oyuntahta=tahta.oyunTahtasiniAl();
             
             
            if(tahta.kazanan(oyuncu2.karakteriAl())==true){
                try {
                    dosyayaYaz("kayit.txt", "");
                } catch (IOException ex) {
                    Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
                }
                tahta.oyunTahtasiniYazdir();
                    System.out.println("Bilgisayar kazand�!");
                    break;
                }else if(tahta.beraberlikKontrol()==true){
                try {
                    dosyayaYaz("kayit.txt", "");
                } catch (IOException ex) {
                    Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
                }
                tahta.oyunTahtasiniYazdir();
                    System.out.println("Oyun berabere!");
                    break;
                }
             
            tahta.oyunTahtasiniYazdir();
           
             
            String satir=""+oyuntahta.length;
            satir+=oyuncu1.karakteriAl();
            for(int i=0;i<oyuntahta.length;i++)
            {
                for(int j=0;j<oyuntahta.length;j++)
                {
                    if(oyuntahta[i][j]=='X' || oyuntahta[i][j]=='O')
                        satir+=oyuntahta[i][j];
                    else
                        satir+=" ";
                }
            }
            satir+=kullaniciAdi;
             
            try {
                dosyayaYaz("kayit.txt", satir);
            } catch (IOException ex) {
                Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
            }
            }       
         
     
    }
     
    public static void dosyayaYaz(String dosyaAdi,String metin) throws IOException{
            File file=new File(dosyaAdi);
            FileWriter fw=new FileWriter(file);
            fw.write(metin);
            fw.close();
        }
         
        public static String dosyadanOku(String dosyaAdi)throws IOException{
            File file=new File(dosyaAdi);
            Scanner sc=new Scanner(file);
            String dosyaicer="";
            while(sc.hasNextLine()){
                dosyaicer+=sc.nextLine();
            }
            return dosyaicer;
             
        }
     
}
