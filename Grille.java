
package vue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static model.DBRecherche.*;
/**
 * cette classe permet de dessinner et les cours en grille
 */
public class Grille extends JPanel { 
    private int ids;
    private int ide;
    private int droit;
    public Grille(int id,int i,int d)
    {
        super();
        ids=id;
        ide=i;
        droit=d;
    }
    @Override
    public void paintComponent(Graphics g){
        //enlever les element precedent
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // dessiner les rectangle
        Color a=new Color(248,248,248);
        g2.setColor(a);
        g2.fillRect(0,0,1800,1000); 
        Color b=new Color(217,219,220);
        g2.setColor(b);
        g2.fillRect(100,50,1700,570); 
        Color d=new Color(241,130,120);
        g2.setColor(d);
        for (int j=0; j<6;j++)
        {
            g2.fillRect(311+(j*247), 50, 36,570); //remplir les rectangle pour les pause a intervalle regulier                  
        }
        Color c=new Color(100,100,100);
        g2.setColor(c);
        for (int i=0; i<14;i++)
        {
            g2.drawLine(100+(i*141), 50, 100+(i*141), 620);//on dessine le ligne verticale a intervalle regulier   
        }
        for (int j=0; j<7;j++)
        {//on dessine les ligne horizontal a intervalle regulier
            if(j==0)
            {
                g2.drawLine(100, 50+(j*132), 1800, 50+(j*132));         
            }
            else
            {
                g2.drawLine(100, 50+(j*95), 1800, 50+(j*95));
            }
        }
        
        
        // ecriture des heures
        g2.drawString("8h30",90,45);
        g2.drawString("10h15 10h30",292,45);
        g2.drawString("11h45 12h00",539,45);
        g2.drawString("13h30 13h45",786,45);
        g2.drawString("15h15 15h30",1033,45);
        g2.drawString("17h00 17h15",1280,45);
        g2.drawString("18h45 19h00",1527,45);
        //on determine a quelle jour du mois nous somme en fonction du numéro de semaine 
        int day=0;
        int i=ids*7;
        int m=0;
        if(ids<=31)
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
        
        
        // écriture des jours 
        g2.drawString("Lundi "+day,20,100);
        //on determine si on doit remettre le compteur de jour a 1
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
        g2.drawString("Mardi "+day,20,195);
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
        g2.drawString("Mercredi "+day,20,290);
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
        g2.drawString("Jeudi "+day,20,385);
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
        g2.drawString("Vendredi "+day,20,480);
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
        g2.drawString("Samedi "+day,20,575);
        int[]n=null;
        //remplir le tableau avec les id des seance sur une semaine donner pour une personne donné (ou une salle) 
        if(droit==0)
        {
            n=getsceance(ide,ids);
        }
        if(droit==1)
        {
            n=getsceanceprof(ide,ids);
        }
        if(droit==2)
        {
            n=getsceancesalle(ide,ids);
        }
        if(n[0]!=-1 )
        {paintcour(g2,n);}
    } 
    //Cette methode sert a metter les cours dans al grille
    public void paintcour(Graphics g2,int []n)
    {
        //declaration de toutes les variables 
        Color a = null;
        int type;
        String heur;
        String jour;
        ArrayList<String>profl,sallel,groupel;
        String prof,salle,groupe = "",cours;
        int x = 0,y = 0,e=0;
        for(int i=0; i<n.length;i++)
        {
            Font font1=new Font(Font.SERIF,Font.BOLD,15);//decarer un font 
            g2.setFont(font1);
            a=definec(n[i],droit);//definir la couleur 
            g2.setColor(a);
            heur=getheur(n[i],"HDebut");//obtenir l'heure de debut de la seance 
            if("08:30:00".equals(heur))//trouver le X en fonction de l'heure de dbut 
                x=100;
            if("10:15:00".equals(heur))
                x=346;
            if("12:00:00".equals(heur))
                x=592;
            if("13:45:00".equals(heur))
                x=840;
            if("15:30:00".equals(heur))
                x=1086;
            if("17:30:00".equals(heur))
                x=1330;
            jour=getjour(n[i]);// obtenir le jour le la seance 
            if("lun".equals(jour))//trouver le y en fonction du jour 
                y=50;
            if("mar".equals(jour))
                y=145;
            if("mer".equals(jour))
                y=240;
            if("jed".equals(jour))
                y=335;
            if("ven".equals(jour))
                y=430;
            if("sam".equals(jour))
                y=525;
            g2.fillRect(x+2,y+2,209,93);//remplir la case du cour 
            Color c=new Color(100,100,100);
            g2.setColor(c);
            cours=getnomcour(n[i]);//obtnir l'intitué du cours
            profl=getnomprof(n[i]);//obtnir la liste des prof 
            prof="";
            for(int j=0;j<profl.size();j++)
            {
                prof+=profl.get(j)+"-";//ecrir tout les non des prof en un seul string
            }
            sallel=getnomsalle(n[i]);//obtnir la liste des salle 
            salle="";
            for(int j=0;j<sallel.size();j++)
            {
                salle+=sallel.get(j)+"-";//ecrir tout les non des salles en un seul string
            }
            groupe="";
            groupel=getnomgroupe(n[i]);//obtnir la liste des groupes 
            for(int j=0;j<groupel.size();j++)
            {
                groupe+=groupel.get(j)+"-";//ecrir tout les non des groupes en un seul string
            }
            e=getetat(n[i]);//obtenir l'etat de la seance 
            //afficher toutes les informations relatives au cour
            g2.drawString(cours,x+70,y+25);
            g2.drawString(prof,x+30,y+45);
            g2.drawString(groupe,x+35,y+65);
            g2.drawString(salle,x+30,y+85);
            if(e==1)//cours validé
            {
                a=new Color(255,255,255);//couleur blanc 
                g2.setColor(a);
                g2.fillRect(x+2,y+20,209,30);//dessinner une banderole 
                font1=new Font(Font.SERIF,Font.BOLD,25);
                g2.setFont(font1);
                a=new Color(255,0,0);//rouge
                g2.setColor(a);
                g2.drawString("Validé",x+70,y+45);//afiicher l'etat
            }
            if(e==2)//cours annulé 
            {
                a=new Color(255,255,255);//couleur blanc 
                g2.setColor(a);
                g2.fillRect(x+2,y+20,209,30);//dessinner une banderole 
                font1=new Font(Font.SERIF,Font.BOLD,25);
                g2.setFont(font1);
                a=new Color(255,0,0);//rouge
                g2.setColor(a);
                g2.drawString("Annulé",x+70,y+45);//afiicher l'etat
            }
        }
    }
    //cette methode permet de determiner une couleur
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
        if (droit==1 || droit==2)//pour les enseignant et les salles c'est en fonction de la promo
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
}