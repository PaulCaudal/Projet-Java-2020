
package vue;

import controler.Semaine;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;

/**
 * cette classe permet a un enseignant et au etudient d'affichage l'emplois du temps
 */
public class Edt 
{
     private Container contenu;
     private int goff;//goff et loff permette de savoir si l'affichage est en grille ou en lsite 
     private int loff;
    private int id;//id utilisateur 
    public Edt(Container c,int i)
    {
        contenu=c;
        goff=1;
        loff=1;
        id=i;
    }
    public void draw(final int droit)
    {
        final JComboBox<String> option=new JComboBox<>();
        option.addItem("en grille");//on initialise la combo box
        option.addItem("en liste");
        //on cherche la semaine actuelle
        Date actual=new Date();
        final int day=actual.getDate();
        final int month=actual.getMonth();
        final int year=actual.getYear()+2000-100;
        final Calendar c= Calendar.getInstance();
        c.set(year,month,day);
        //on ajoute au container 
        contenu.add(option);
        //on affiche EDT engrille par defaut
        final Semaine s=new Semaine(c.get(c.WEEK_OF_YEAR),contenu);
        s.draw(0,id,droit);
        loff=1;
        goff=0;
        //on set les coordonné
        option.setBounds(10, 145, 100, 30);
        //on ajoute une action a option
        option.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(0==option.getSelectedIndex())//si grille est selectionnné
                {
                    if(loff==0)//si la liste est a l'ecran
                    {
                        contenu.remove(5);//on enleve la liste 
                        loff=1;
                        goff=0;
                    }
                    s.draw(0,id,droit);//on affiche l'edt en grille
                    contenu.repaint();
                } 
                if(1==option.getSelectedIndex())//si liste est selectionnné
                {
                    if(goff==0)//si la lgrille est a l'ecran
                    {
                        contenu.remove(5);//on enlevr la grille 
                        goff=1;
                        loff=0;
                    }
                    s.draw(1,id,droit);//on affiche l'edt en liste
                    contenu.repaint();
                } 
            }
        }
        );
    }
}
