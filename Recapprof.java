
package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import static model.DBRecherche.*;
/**
 * cette classe permet d'afficher le recapitulatif des cours pour un proffesseur
 */
public class Recapprof extends JPanel
{
    private final int ide;
    private final int droit;
    public Recapprof(int i,int d)
    {
        super();
        ide=i;
        droit=d;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        //enlever les element precedent
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Color a=new Color(248,248,248);
        g2.setColor(a);
        //dessiner l'entete du tableau
        g2.fillRect(0,0,1800,1000); 
        a=new Color(100,100,100);
        g2.setColor(a);
        g2.drawLine(1, 1, 1799, 1);
        g2.drawLine(1, 41, 1798, 41);
        g2.drawLine(1, 1, 1, 41);
        g2.drawLine(1066, 1, 1066, 41);
        g2.drawLine(533, 1, 533, 41);
        g2.drawLine(1600, 1, 1600, 41);
        g2.drawLine(1700, 1, 1700, 41);
        g2.drawLine(1798, 1, 1798, 41);
        Font font1=new Font(Font.SERIF,Font.BOLD,25);
        g2.setFont(font1);
        a=new Color(0,255,0);
        g2.setColor(a);
        //afficher les intituler du tableau
        g2.drawString("Matière-Public", 177, 30);
        g2.drawString("Première Séance", 709, 30);
        g2.drawString("Dernière Séance", 1242, 30);
        g2.drawString("Durée", 1620, 30);
        g2.drawString("Nb", 1740, 30);
        ArrayList<String>n = null;
        n=getttseanceprof(ide);//recupérer les cours des quelles le proffesseur est responsable
        font1=new Font(Font.SERIF,Font.BOLD,15);
        g2.setFont(font1);
        String z,z2,jour,cours,heure;int id,ids,nb;
        int y=41;
        for(int i=0;i<n.size();i++)//lecteur de la liste des seance
        {
            //dessiner les ligne pour la ligne du tableau
            z=n.get(i);
            ArrayList<String>n2=getnbgroupe(z);//obtenir les nom des groupes qui ont ce cours
            for(int j=0;j<n2.size();j++)//parcourir les groupes
            {
                a=new Color(209,233,218);
                g2.setColor(a);
                g2.fillRect(1,y+1,1799,50);
                a=new Color(100,100,100);
                g2.setColor(a);
                g2.drawLine(1, y+50, 1799, y+50);
                g2.drawLine(1, y, 1, y+50);
                g2.drawLine(1066, y, 1066, y+50);
                g2.drawLine(533, y, 533, y+50);
                g2.drawLine(1600, y, 1600, y+50);
                g2.drawLine(1700, y, 1700, y+50);
                g2.drawLine(1798, y, 1798, y+50);
                z=n.get(i);//obtenir le nom du cours 
                z2=n2.get(j);//obtenir le nom du groupe
                cours=z+"   -   "+z2;//transformer en string 
                g2.drawString(cours, 20, y+30);
                id=getpremierjour(z,ide,droit,z2);//obtnire la date et l'heure de la premiere seance
                ids=getids(id);
                jour=getjour(id);
                jour=datestring(jour,ids,id);//transformer toutes les infos en string
                g2.drawString(jour, 554, y+30);//afficher les infos
                id=getdernierjour(z,ide,droit,z2);//obtnire la date et l'heure de la derniere seance
                ids=getids(id);
                jour=getjour(id);
                jour=datestring(jour,ids,id);//transformer toutes les infos en string
                g2.drawString(jour, 1087, y+30);//afficher les infos
                nb=getnbseance(ide,z,droit,z2);//obtenir le nombre de seance
                heure=nbtotime(nb);//obtenir le nombre d'heure de cours 
                g2.drawString(nb+"", 1750, y+30);//afficher les infos 
                g2.drawString(heure, 1630, y+30);
                if(j==0)
                {
                    y=y+50;
                }
                else
                {
                    y=y+j*50;
                }
                
            }
        }
    }
    //obtenir le nombre d'heure en fonction du nombre de seance 
    public String  nbtotime(int nb)
    {
        String heure = null;
        if(nb%2==0)//nombre pair de cours 
        {
            nb=(int) (nb*(1.5));
            heure=nb+"H00";
        }
        else//nombre impaire de cours 
        {
            nb=(int) (nb*(1.5));
            heure=nb+"H30";
        }
        return heure;
    }
    //transformer la date et l'heure en string 
    public String datestring(String jour,int ids,int id)
    {
        int day=0;String mois =null;
        if("lun".equals(jour))
            {
                jour="lundi";
                day=jour(0,ids);
                mois=mois(day,0,ids);
            }
            if("mar".equals(jour))
            {
                jour="mardi";
                day=jour(1,ids);
                mois=mois(day,1,ids);
            }
            if("mer".equals(jour))
            {
                jour="mercredi";
                day=jour(2,ids);
                mois=mois(day,2,ids);
            }
            if("jed".equals(jour))
            {
                jour="jeudi";
                day=jour(3,ids);
                mois=mois(day,3,ids);
            }
            if("ven".equals(jour))
            {
                jour="vendredi";
                day=jour(4,ids);
                mois=mois(day,4,ids);
            }
            if("sam".equals(jour))
            {
                jour="samedi";
                day=jour(5,ids);
                mois=mois(day,5,ids);
            }
            jour+=" "+day+" "+mois+" 2020 de "+getheur(id,"HDebut")+" à "+getheur(id,"HFin");
        return jour;
    }
    //cette methode permet de trouver le jour du moi en fonstion du decalage par rapport au lundi
    public  int jour(int inter,int ids)
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
    public String mois(int day,int inter,int ids)
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
}

