package oyun;

public class oyunTahtasi {
	 
    char[][] oyuntahtasi;
 
    public oyunTahtasi(int boy) {
        oyuntahtasi = new char[boy][boy];
    }
 
    public oyunTahtasi(char[][] tahta) {
        oyuntahtasi = tahta;
    }
 
    public char[][] oyunTahtasiniAl() {
        return oyuntahtasi;
    }
 
    public void oyunTahtasiniYazdir() {
        System.out.print(" ");
        for(int i=0;i<oyuntahtasi.length;i++){
            System.out.print("   "+(i+1));
        }
        System.out.println("\n");
        for (int i = 0; i < oyuntahtasi.length; i++) {
            System.out.print(i+1);
            for (int j = 0; j < oyuntahtasi.length; j++) {
                if (oyuntahtasi[i][j] == 'X' || oyuntahtasi[i][j] == 'O') {
                    System.out.print("   "+oyuntahtasi[i][j]);
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println("");
            System.out.println("");
        }
    }
 
    public boolean hamleyiYaz(String koordinat, char oyuncu) {
        String temp1 = koordinat.substring(0, 1);
        String temp2 = koordinat.substring(1);
        int x = Integer.parseInt(temp1);
        int y = Integer.parseInt(temp2);
        if (oyuntahtasi[x][y] == 'X' || oyuntahtasi[x][y] == 'O') {
            return false;
        } else {
            oyuntahtasi[x][y] = oyuncu;
            return true;
        }
    }
 
    public boolean kazanan(char oyuncu) {
        String winner;
        String temp1;
        String temp2;
 
        if (oyuntahtasi.length == 3) {
            winner = "" + oyuncu + oyuncu + oyuncu;
        } else {
            winner = "" + oyuncu + oyuncu + oyuncu + oyuncu;
        }
        //Satýr satýr ve sütun sütun arama yapar
        for (int i = 0; i < oyuntahtasi.length; i++) {
            temp1 = "";
            temp2 = "";
            for (int j = 0; j < oyuntahtasi.length; j++) {
                temp1 += oyuntahtasi[i][j];
                temp2 += oyuntahtasi[j][i];
            }
            if (temp1.contains(winner)) {
                return true;
            } else if (temp2.contains(winner)) {
                return true;
            }
        }
        //Eksende arama yapar
        for(int i=0;i<oyuntahtasi.length-winner.length()+1;i++)
        {
            for(int j=0;j<oyuntahtasi.length-winner.length()+1;j++)
            {
                temp1="";
                for(int k=0;k<winner.length();k++)
                {
                temp1+=oyuntahtasi[i+k][j+k];    
                }
                if (temp1.equals(winner)) {
                return true;
                }
            }
        }
        //Sanal eksende arama yapar
        for(int i=0;i<oyuntahtasi.length-winner.length()+1;i++)
        {
            for(int j=oyuntahtasi.length-1;j>winner.length()-1;j--)
            {
                temp1="";
                for(int k=0;k<winner.length();k++)
                {
                    temp1+=oyuntahtasi[i+k][j-k];
                }
                if (temp1.equals(winner)) {
                return true;
                }
            }
        }
        return false;
         
         
 
    }
     
    public boolean beraberlikKontrol()
    {
        for(int i=0;i<oyuntahtasi.length;i++)
        {
            for(int j=0;j<oyuntahtasi.length;j++)
            {
                if(oyuntahtasi[i][j]!='X' && oyuntahtasi[i][j]!='O'){
                    return false;
                }
            }
        }
        if(!kazanan('X') && !kazanan('O')){
            return true;
        }else{
            return false;
        }
    }
     
 
}