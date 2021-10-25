
package vue;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static model.DBAction.*;

/**
 * cette classe affiche les champs qui permettent de deplacer une seance par l'admin
 */
public class Deplacer 
{
     private Container contenu;
    JComboBox<String> varie=new JComboBox<>();//on utilise des combo box pour eviter toutes erreur de saisie
    JTextField semaine=new JTextField();
    JComboBox<String> jours=new JComboBox<>();
    JComboBox<String> heure=new JComboBox<>();
    JTextField semaine2=new JTextField();
    JComboBox<String> jours2=new JComboBox<>();
    JComboBox<String> heure2=new JComboBox<>();
    ArrayList<String>n;
    public Deplacer(Container c)
    {
        contenu=c;
        jours.addItem("lun");//on initialise les combo box 
        jours.addItem("mar");
        jours.addItem("mer");
        jours.addItem("jed");
        jours.addItem("ven");
        heure.addItem("08:30:00");
        heure.addItem("10:15:00");
        heure.addItem("12:00:00");
        heure.addItem("13:45:00");
        heure.addItem("15:30:00");
        jours2.addItem("lun");
        jours2.addItem("mar");
        jours2.addItem("mer");
        jours2.addItem("jed");
        jours2.addItem("ven");
        heure2.addItem("08:30:00");
        heure2.addItem("10:15:00");
        heure2.addItem("12:00:00");
        heure2.addItem("13:45:00");
        heure2.addItem("15:30:00");
    }
    public void paint()
    {
        //on declare nos texte 
        final JLabel se=new JLabel("Semaine:");
        final JLabel se2=new JLabel("Semaine:");
        final JLabel texte1=new JLabel("Deplacer:");
        final JLabel texte2=new JLabel("Vers:");
        final JLabel jour=new JLabel("jour:");
        final JLabel heur=new JLabel("heure de debut:");
        final JLabel jour2=new JLabel("jour:");
        final JLabel heur2=new JLabel("heure de debut:");
        final JLabel cour=new JLabel("Cours:");
        final JButton valider=new JButton("Valider");
        n=getnomcours();//onrecupére tout les nom de cours
        for(int i=0;i<n.size();i++)
        {
            varie.addItem(n.get(i));//on rempli la combobox avec ces dernier
        }
        //on ajoute nos element au container 
        contenu.add(varie);
        contenu.add(semaine);
        contenu.add(jours);
        contenu.add(heure);
        contenu.add(semaine2);
        contenu.add(jours2);
        contenu.add(heure2);
        contenu.add(valider);
        contenu.add(texte1);
        contenu.add(se);
        contenu.add(jour);
        contenu.add(heur);
        contenu.add(se2);
        contenu.add(jour2);
        contenu.add(heur2);
        contenu.add(cour);
        //on ajoute les coordonnées de nos element 
        texte1.setBounds(10, 50, 100, 25);
        texte2.setBounds(10, 80, 100, 25);
        se.setBounds(120, 50, 100, 25);
        se2.setBounds(120, 80, 100, 25);
        semaine.setBounds(180, 50, 100, 25);
        semaine2.setBounds(180, 80, 100, 25);
        jour.setBounds(340, 50, 100, 25);
        jours.setBounds(380, 50, 100, 25);
        jour2.setBounds(340, 80, 100, 25);
        jours2.setBounds(380, 80, 100, 25);
        heur.setBounds(560, 50, 100, 25);
        heure.setBounds(670, 50, 100, 25);
        heur2.setBounds(560, 80, 100, 25);
        heure2.setBounds(670, 80, 100, 25);
        cour.setBounds(780, 50, 100, 25);
        varie.setBounds(850, 50, 100, 25);
        valider.setBounds(1000, 80, 100, 25);
        //on ajoute un evenement sur le boutton valider
        valider.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //on recupére les informations concernant l'ancien creneau saisie par l'utilisateur 
                String s=semaine.getText();
                int week=Integer.parseInt(s);
                Object i=jours.getSelectedItem();
                String j=i.toString();
                i=heure.getSelectedItem();
                String h=i.toString();
                i=varie.getSelectedItem();
                String v=i.toString();
                int id=getexistance(week,j,h,v);//on verifie avec le numéro de semaine,le jour, l'heure et le nom du coure si la seance existe.
                 //on recupére ensuite l'id de cette seance  
                if(id!=0)
                {
                    //on recupére les informations concernant le nouveau creneau saisie par l'utilisateur
                    s=semaine2.getText();
                    week=Integer.parseInt(s);
                    i=jours2.getSelectedItem();
                    j=i.toString();
                    i=heure2.getSelectedItem();
                    h=i.toString();
                    int k=verifegroupe(id,week,j,h);//on verifie que le groupe est libre pour le nouveau creneau
                    if(k==0)
                    {
                        k=verifeprof(id,week,j,h);//on verifie que le proffesseur est libre pour le nouveau creneau
                        if(k==0)
                        {
                            k=verifesalle(id,week,j,h);//on verifie que la salle est libre pour le nouveau creneau
                            if(k==0)
                            {
                                updatedate(id,week,j,h);
                                JOptionPane.showMessageDialog(null, "La Mise à Jour a bien été efféctué");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "La salle est deja occupé a ce moment la");//message d'erreur
                            }

                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Le proffesseur a deja une séance a ce moment la");//message d'erreur
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Ce groupe a deja une séance a ce moment la");//message d'erreur
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "la séance n'éxiste pas");//message d'erreur
                }
                
            }});
    }
}
