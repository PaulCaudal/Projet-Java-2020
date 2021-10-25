
package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import static model.DBRecherche.*;
/**
 * cette classe permet de dessinner et les cours en liste
 */
public class Liste extends JPanel
{
    private int ids;
    private int ide;
    private int droit;
    public Liste(int id,int i,int d)
    {
        super();
        ids=id;
        ide=i;
        droit=d;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //enlever les element precedent
        Graphics2D g2 = (Graphics2D) g;
        Color a=new Color(240,240,240);
        g2.setColor(a);
        // dessiner les rectangle
        g2.fillRect(0,0,1800,1000);
        int[]n={-1};
        if(droit==0)//si c'est un etudiant
        {
            n=getsceance(ide,ids);//on recupére toutes les seances
        }
        if(droit==1)//si c"est un enseignant
        {
            n=getsceanceprof(ide,ids);//on recupére toutes les seances
        }
        ArrayList<String> done=new ArrayList<>();//liste pour identifié les jour deja fait 
        int type;//declaration des variable 
        String heur;
        int []nb;
        int d=0;
        String jour,mois = null;
        ArrayList<String>profl,sallel,groupel;
        String prof,salle,groupe,cours;
        int x = 0,y = 0,day;
        if(n[0]!=-1)
        {
            n=trilistejour(n);//tri la liste en fanction des jours 
            for(int i=0; i<n.length;i++)
            {
                jour=getjour(n[i]);//obtenir le jour 
                d=0;
                for(int z=0;z<done.size();z++)//on verifie que le jour n'as pas deja été affiché 
                {
                    String temp=done.get(z);
                    if(jour.equals(temp))
                    {
                        d=1;
                    }
                    
                }
                if(d==0)//si le jour n'a pas été affiché 
                {
                    nb=getnbcour(n,jour);//obtenir tout les cours du meme jour 
                    nb=trilisteheure(nb);//trier la liste en fonction des heure de debut 
                    a=new Color(128,255,128);
                    g2.setColor(a);
                    g2.fillRect(x+2,y+2,800,40);
                    day=jour(0);//on cherche la date du actuelle
                    if("lun".equals(jour))//on cherche la date exacte 
                    {
                        jour="lundi";
                        day=jour(0);//on obtient le jour avec un de decalage 0 jour 
                        mois=mois(day,0);//on obtient le mois avec un de decalage 0 jour 
                        done.add("lun");//on ajoute lundi a la liste des jour deja afficher
                    }
                    if("mar".equals(jour))
                    {
                        jour="mardi";
                        day=jour(1);//on obtient le jour avec un de decalage 1 jour 
                        mois=mois(day,1);//on obtient le mois avec un de decalage 1 jour 
                        done.add("mar");//on ajoute mardi a la liste des jour deja afficher
                    }
                    if("mer".equals(jour))
                    {
                        jour="mercredi";
                        day=jour(2);//on obtient le jour avec un de decalage 2 jour 
                        mois=mois(day,2);//on obtient le mois avec un de decalage 2 jour 
                        done.add("mer");//on ajoute mercredi a la liste des jour deja afficher
                    }
                    if("jed".equals(jour))
                    {
                        jour="jeudi";
                        day=jour(3);//on obtient le jour avec un de decalage 3 jour 
                        mois=mois(day,3);//on obtient le mois avec un de decalage 3 jour 
                        done.add("jed");//on ajoute jeudi a la liste des jour deja afficher
                    }
                    if("ven".equals(jour))
                    {
                        jour="vendredi";
                        day=jour(4);//on obtient le jour avec un de decalage 4 jour 
                        mois=mois(day,4);//on obtient le mois avec un de decalage 4 jour 
                        done.add("ven");//on ajoute vendredi a la liste des jour deja afficher
                    }
                    if("sam".equals(jour))
                    {
                        jour="samedi";
                        day=jour(5);//on obtient le jour avec un de decalage 5 jour 
                        mois=mois(day,5);//on obtient le mois avec un de decalage 5 jour 
                        done.add("sam");//on ajoute samedi a la liste des jour deja afficher
                    }
                    jour+=" "+day+" "+mois+" 2020";
                    //on affiche la presentation sans les cours 
                    a=new Color(100,100,100);
                    g2.setColor(a);
                    g2.drawString(jour,x+15,y+25);
                    a=new Color(225,225,225);
                    g2.setColor(a);
                    g2.fillRect(x+2,y+42,800,50*nb.length);
                    for(int j=0; j<nb.length;j++)//on affiche les cours 
                    {
                        a=definec(nb[j],droit);//on definit la couleur
                        g2.setColor(a);
                        g2.fillRect(x+17,y+57+50*j,15,20);
                        a=new Color(100,100,100);
                        g2.setColor(a);
                        heur=getheur(nb[j],"HDebut")+"-"+getheur(nb[j],"HFin");//heure
                        cours=getnomcour(nb[j]);//nom du cours
                        profl=getnomprof(nb[j]);//liste des prof sur ce cours 
                        prof="";
                        for(int k=0;k<profl.size();k++)
                        {
                            prof+=profl.get(k)+"-";//on transforme la liste en String
                        }
                        sallel=getnomsalle(nb[j]);//liste des salles sur ce cours 
                        salle="";
                        for(int k=0;k<sallel.size();k++)
                        {
                            salle+=sallel.get(k)+"-";//on transforme la liste en String
                        }
                        groupe="";
                        groupel=getnomgroupe(nb[j]);//liste des groupes sur ce cours 
                        for(int k=0;k<groupel.size();k++)
                        {
                            groupe+=groupel.get(k)+"-";//on transforme la liste en String
                        }
                        g2.drawString(heur,x+100,y+72+50*j);//on affiche tout les string 
                        g2.drawString(cours,x+250,y+72+50*j);
                        g2.drawString(prof,x+700,y+72+50*j);
                        g2.drawString(groupe,x+380,y+72+50*j);
                        g2.drawString(salle,x+500,y+72+50*j);
                        g2.drawLine(x+2, y+92+50*j, x+800, y+92+50*j);
                    }
                    if (y+52+50*nb.length>500)//boucle pour modifié les coordonées 
                    {
                        y=0;x=850;
                    }
                    else
                    {
                        y+=52+50*nb.length;
                    }
                }
            }
        }
    }
    //cette methode permet de trouver le jour du moi en fonstion du decalage par rapport au lundi
    public  int jour(int inter)
    {
        int day=0;
        int m=0;int i=ids*7;
        if(ids<=31)//on trrouve le jour du mois
        {   
            if(i>31)
            {i=i-31;m=2;}
            if(i>29)
            {i=i-29;m=0;}
            if(i>31)
            {i=i-31;m=1;}
            if(i>30)
            {i=i-30;m=0;}
            if(i>31)
            {i=i-31;m=1;}
            if(i>30)
            {i=i-30;m=0;}
            if(i>31)
            {i=i-31;m=1;}
            if(i-8>0)
            {day=i-8;}
            if(i-8<0 && m==0)
            {day=30+i-8;}
            if(i-8<0 && m==1)
            {day=31+i-8;}
            if(i-8<0 && m==2)
            {day=29+i-8;}
        }
        if(ids>31)
        {
            i=i-212;
            if(i>31)
            {i=i-31;m=1;}
            if(i>30)
            {i=i-30;m=0;}
            if(i>31)
            {i=i-31;m=1;}
            if(i>30)
            {i=i-30;m=0;}
            if(i>31)
            {i=i-31;}
            if(i-8>0)
            {day=i-7;}
            if(i-8<0 && m==0)
            {day=30+i-7;}
            if(i-8<0 && m==1)
            {day=31+i-7;}
        }
        for(int j=0;j<inter;j++)//on cherche le decallage per rapport au lundi 
        {
            if(day+1<29)
            {
                day=day+1;
            }
            else
            {
                if(day+1>=29 && ids!=9)
                {
                    if(day+1>30 && (ids==18 || ids==27 || ids==40 || ids==48))
                    {
                        day=1;
                    }
                    else
                    {
                        day=day+1;
                    }
                }
                else 
                {
                    day=1;
                }
            }
            if(day==32)
            {day=1;}
        }
        return day;
    
    }
    //cette methode permet de trouver le moi de l'année en fonction du decalage par rapport au lundi
    public String mois(int day,int inter)
    {
        String mois="janvier";
        int k=0;
        String[]tab={"janvier","février","mars","avril","mai","juin","juillet","aout","septembre","octobre","novembre","decembre"};
        int m=0;int i=ids*7-8;
        if(ids<=31)//on cherche le mois de l'année
        {   
            if(i>31)
            {i=i-31;mois="février";k+=1;m=1;}
            if(i>29)
            {i=i-29;mois="mars";k+=1;m=2;}
            if(i>31)
            {i=i-31;mois="avril";k+=1;m=1;}
            if(i>30)
            {i=i-30;mois="mai";k+=1;m=0;}
            if(i>31)
            {i=i-31;mois="juin";k+=1;m=1;}
            if(i>30)
            {i=i-30;mois="juillet";k+=1;m=0;}
            if(i>31)
            {i=i-31;mois="aout";k+=1;m=1;}
            if(i>31)
            {i=i-31;mois="septembre";k+=1;m=1;}
            if(i>30)
            {i=i-30;mois="octobre";k+=1;m=0;}
            if(i>31)
            {i=i-31;mois="novembre";k+=1;m=1;}
            if(i>30)
            {i=i-30;mois="decembre";k+=1;m=0;}
            if(i-8>0)
            {day=i;}
            if(i-8<0 && m==0)
            {day=30+i;}
            if(i-8<0 && m==1)
            {day=31+i;}
            if(i-8<0 && m==2)
            {day=29+i;}
        }
        for(int j=0;j<inter;j++)//on cherche le mois en fonction du decallage avec le lundi 
        {
             if(day+1<29)
            {
                day=day+1;
            }
            else
             {
                if(day+1>=29 && ids!=9)
                {
                    if(day+1>30 && (ids==18 || ids==27 || ids==40 || ids==48))
                    {
                        mois=tab[k+1];
                    }
                    else
                    {
                        day=day+1;
                    }
                }
                else 
                {
                    mois=tab[k+1];
                }
             }
            if(day==32)
            {mois=tab[k+1];}
        }
        return mois;
    }
    //cette methode definit la couleur 
     public Color definec(int id, int droit)
    {
        Color a = null;
        if (droit==0)//pour les etudiant la couleur est determiné en fonction du type de cours 
        {
            int type=gettypecour(id);//obtenir le type du cours 
            if(type==2)
                a=new Color(255,255,128);
            if(type==3)
                a=new Color(128,255,128);
            if(type==4)
                a=new Color(128,128,255);
            if(type==5)
                a=new Color(255,128,255);
            if(type==1)
                a=new Color(255,128,128);
        }
        if (droit==1)//pour les enseignant et les salles c'est en fonction de la promo
        {
            int type=getpromo(id);//obtenir la promo du groupe 
            if(type==2)
                a=new Color(255,255,128);
            if(type==3)
                a=new Color(128,255,128);
            if(type==1)
                a=new Color(255,128,128);
        }
        return a;
    }
     //cette methode tri une liste du lundi vers le samedi
     public int[]trilistejour(int[] nb)
     {
         int size=nb.length;
         String jour;int z=0;
         int []tabtrier=new int[size];
         for(int i=0;i<size;i++)
         {
             jour=getjour(nb[i]);
             if("lun".equals(jour))
             {
                 tabtrier[z]=nb[i];
                 z+=1;
             }
         }
          for(int i=0;i<size;i++)
         {
             jour=getjour(nb[i]);
             if("mar".equals(jour))
             {
                 tabtrier[z]=nb[i];
                 z+=1;
             }
         }
           for(int i=0;i<size;i++)
         {
             jour=getjour(nb[i]);
             if("mer".equals(jour))
             {
                 tabtrier[z]=nb[i];
                 z+=1;
             }
         }
            for(int i=0;i<size;i++)
         {
             jour=getjour(nb[i]);
             if("jed".equals(jour))
             {
                 tabtrier[z]=nb[i];
                 z+=1;
             }
         } for(int i=0;i<size;i++)
         {
             jour=getjour(nb[i]);
             if("ven".equals(jour))
             {
                 tabtrier[z]=nb[i];
                 z+=1;
             }
         }
         return tabtrier;
     }
     //cette methode tri une liste en fonction des heures
     public int[]trilisteheure(int[] nb)
     {
         int size=nb.length;
         String heure;int z=0;
         int []tabtrier=new int[size];
         for(int i=0;i<size;i++)
         {
             heure=getheur(nb[i],"HDebut");
             if("08:30:00".equals(heure))
             {
                 tabtrier[z]=nb[i];
                 z+=1;
             }
         }
          for(int i=0;i<size;i++)
         {
             heure=getheur(nb[i],"HDebut");
             if("10:15:00".equals(heure))
             {
                 tabtrier[z]=nb[i];
                 z+=1;
             }
         }
           for(int i=0;i<size;i++)
         {
             heure=getheur(nb[i],"HDebut");
             if("12:00:00".equals(heure))
             {
                 tabtrier[z]=nb[i];
                 z+=1;
             }
         }
            for(int i=0;i<size;i++)
         {
              heure=getheur(nb[i],"HDebut");
             if("13:45:00".equals(heure))
             {
                 tabtrier[z]=nb[i];
                 z+=1;
             }
         } 
         for(int i=0;i<size;i++)
         {
              heure=getheur(nb[i],"HDebut");
             if("15:30:00".equals(heure))
             {
                 tabtrier[z]=nb[i];
                 z+=1;
             }
         }
          for(int i=0;i<size;i++)
         {
              heure=getheur(nb[i],"HDebut");
             if("17:15:00".equals(heure))
             {
                 tabtrier[z]=nb[i];
                 z+=1;
             }
         }
         return tabtrier;
     }
}
