
package controler;

import vue.Edt;
import vue.Recap;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import static model.DBRecherche.*;
/**
 * classe controle l'interface pour les etudiant
 */
public class Etudiant 
{
    private Container contenu;
    private int id;
    public Etudiant(Container c,int i)
    {
        contenu=c;
        id=i;
    }
    public Container getcontenu()
    {
         return contenu;//methode pour retourner le container
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
        //texte
        String nom=getstring(id,"Nom");
        String prenom=getstring(id,"Prenom");
        final JLabel Nom=new JLabel(prenom+" "+nom);
        //modification de la couleur et de la police du texte
        Nom.setForeground(Color.white);
        Font font1=new Font(Font.SERIF,Font.BOLD,25);
        Nom.setFont(font1);
        //apelle de EDT
        Edt e=new Edt(contenu,id);
        e.draw(droit);
        //ajout des element sur le container
        contenu.add(edt);
        contenu.add(recap);
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
        perso.setBounds(10, 10, 50, 50);
        //set la couleur de fond du container
        contenu.setBackground(new Color(240,240,240));
        //ajouter une action au bouton edt
        edt.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//on retire tout du contenu
                //on apelle Edt
                Edt e1=new Edt(contenu,id);
                e1.draw(droit);//appelle de la methode draw
                //on remet les element graphique de 'interface
                contenu.add(edt);
                contenu.add(recap);
                contenu.add(perso);
                contenu.add(Nom);
                contenu.add(entete);
                contenu.add(entete2);
                contenu.repaint();
            }
        }
        );
        //ajouter une action au bouton recap
        recap.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//on retire tout du contenu
                //on apelle Recap
                Recap p=new Recap(id,droit);
                contenu.add(p);//on ajout p au container
                p.setBounds(10,140,1800,760);//on set les coordonées de p
                 //on remet les element graphique de l'interface
                contenu.add(edt);
                contenu.add(recap);
                contenu.add(perso);
                contenu.add(Nom);
                contenu.add(entete);
                contenu.add(entete2);
                contenu.repaint();
            }
            
        }
        );
    }
}
