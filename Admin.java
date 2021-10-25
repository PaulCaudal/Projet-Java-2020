
package controler;

import vue.EdtAdmin;
import vue.RecapAdmin;
import vue.Reporting;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * cette classe est appellé lorsque un admin ce log 
 */
public class Admin
{
     private Container contenu;//container pour stocker tout nos element graphique 
   
    public Admin(Container c)
    {
        contenu=c;
        
    }
    public Container getcontenu()
    {
         return contenu;//methode qui retourne le container 
    }
    public void paint(final int droit)
    {
        ///declaration de tout les element graphique 
        //image
        final JLabel entete = new JLabel(new ImageIcon("Entete.png"));
        final JLabel entete2 = new JLabel(new ImageIcon("Entete2.png"));
        final JLabel perso = new JLabel(new ImageIcon("perso.png"));
        //boutton
        final JButton edt=new JButton("Emploi du temps");
        final JButton recap=new JButton("Recapitulatif des cours");
        final JButton modifier=new JButton("Modifier les cours");
        final JButton report=new JButton("Reporting");
        //texte
        final JLabel Nom=new JLabel("Administration");
        //modification de la couleur et de la police du texte
        Nom.setForeground(Color.white);
        Font font1=new Font(Font.SERIF,Font.BOLD,25);
        Nom.setFont(font1);
        //ajout des element sur le container
        contenu.add(edt);
        contenu.add(report);
        contenu.add(recap);
        contenu.add(modifier);
        contenu.add(perso);
        contenu.add(Nom);
        contenu.add(entete);
        contenu.add(entete2);
        //declaration des coordonné de chaque element 
        Nom.setBounds(70,10,200,50);
        entete.setBounds(0, 0, 1900, 80);
        entete2.setBounds(0, 80, 1900, 55);
        edt.setBounds(10, 90, 200, 25);
        recap.setBounds(220, 90, 200, 25);
        modifier.setBounds(430, 90, 200, 25);
        report.setBounds(640, 90, 200, 25);
        perso.setBounds(10, 10, 50, 50);
        contenu.setBackground(new Color(240,240,240));
        //evenement sur le boutton edt
        edt.addActionListener(new ActionListener ()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//on remet le container a 0
                EdtAdmin e1=new EdtAdmin(contenu);//on appel la classe Edt
                e1.draw();//on appel la methode painr
                contenu.add(edt);//on remet sur le container les element qui doivent rester
                contenu.add(report);
                contenu.add(recap);
                contenu.add(perso);
                contenu.add(Nom);
                contenu.add(modifier);
                contenu.add(entete);
                contenu.add(entete2);
                contenu.repaint();//on refresh le container
            }
            
        }
        );
        //evenement sur le boutton recap
        recap.addActionListener(new ActionListener ()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//on remet le container a 0
                RecapAdmin r=new RecapAdmin(contenu);//on appelle la classe recapAdmin
                r.draw();//on appelle la methode draw()
                contenu.add(edt);//on remet sur le container les element qui doivent rester
                contenu.add(report);
                contenu.add(recap);
                contenu.add(perso);
                contenu.add(modifier);
                contenu.add(Nom);
                contenu.add(entete);
                contenu.add(entete2);
                contenu.repaint();//on refresh le container
            }
            
        }
        );
        //evenement sur le boutton modifier
        modifier.addActionListener(new ActionListener ()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//on remet le container a 0
                Modifier r=new Modifier();//on appelle la classe Modifier
                r.paint();//on appelle la methode painr 
                Container c1=r.getcontenu();//on recupere la container 
                contenu.add(c1);//on l'ajoute au container principal
                c1.setBounds(10, 150, 1350, 150);//on lui attribut des coordonées
                contenu.add(edt);//on remet sur le container les element qui doivent rester
                contenu.add(recap);
                contenu.add(report);
                contenu.add(modifier);
                contenu.add(perso);
                contenu.add(Nom);
                contenu.add(entete);
                contenu.add(entete2);
                contenu.repaint();//on refresh le container
            }
            
        }
        );
        //evenement sur le boutton report
        report.addActionListener(new ActionListener ()
        {
            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//on remet le container a 0
                Reporting r=new Reporting();//on appelle la classe Reporting
                r.paint();//on appelle la methode paint
                Container c1=r.getcontenu();//on recupere le container 
                contenu.add(c1);//on l'ajoute au container principal
                c1.setBounds(700,240,1800,760);//on lui attribut des coordonées
                contenu.add(edt);//on remet sur le container les element qui doivent rester
                contenu.add(report);
                contenu.add(recap);
                contenu.add(perso);
                contenu.add(modifier);
                contenu.add(Nom);
                contenu.add(entete);
                contenu.add(entete2);
                contenu.repaint();//on refresh le container
            }
            
        }
        );
    }
}
