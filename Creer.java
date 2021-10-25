
package vue;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static model.DBAction.*;
import static model.DBRecherche.*;

/**
 * Cette classe affiche les champs qui permettent a un administrateur de creer une seance 
 */
public class Creer 
{
    private Container contenu=new JFrame().getContentPane();
    JComboBox<String> groupes=new JComboBox<>();//on utilise des combo box pour eviter toutes erreur de saisie
    JComboBox<String> cours=new JComboBox<>();
    JComboBox<String> salles=new JComboBox<>();
    JTextField semaine=new JTextField();
    JComboBox<String> jours=new JComboBox<>();
    JComboBox<String> heure=new JComboBox<>();
    ArrayList<String>n;
    public Creer()
    {
        
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
    }
    public Container getcontenu()
    {
        return contenu;//méthode qui retour le container
    }
    public void paint()
    {
        //on declare nos texte 
        final JLabel se=new JLabel("Semaine:");
        final JLabel jour=new JLabel("jour:");
        final JLabel heur=new JLabel("heure de debut:");
        final JLabel cour=new JLabel("Cours:");
        final JLabel groupe=new JLabel("Groupe:");
        final JLabel salle=new JLabel("Salle:");
        final JButton valider=new JButton("Valider");
        n=getttnomgroupe();//onrecupére tout les nom de groupe
        for(int i=0;i<n.size();i++)
        {
            groupes.addItem(n.get(i));//on rempli la combobox avec ces dernier
        }
        n=getnomcours();//onrecupére tout les nom de cours
        for(int i=0;i<n.size();i++)
        {
            cours.addItem(n.get(i));//on rempli la combobox avec ces dernier
        }
        n=getttnomsalle();//onrecupére tout les nom de salles
        for(int i=0;i<n.size();i++)
        {
            salles.addItem(n.get(i));//on rempli la combobox avec ces dernier
        }
        //on ajoute nos element au container 
        contenu.add(groupes);
        contenu.add(cours);
        contenu.add(salles);
        contenu.add(semaine);
        contenu.add(jours);
        contenu.add(heure);
        contenu.add(valider);
        contenu.add(se);
        contenu.add(jour);
        contenu.add(heur);
        contenu.add(cour);
        contenu.add(groupe);
        contenu.add(salle);
        //on ajoute les coordonnées de nos element 
        se.setBounds(10, 0, 100, 25);
        semaine.setBounds(70, 0, 100, 25);
        jour.setBounds(180, 0, 100, 25);
        jours.setBounds(220, 0, 100, 25);
        heur.setBounds(350, 0, 100, 25);
        heure.setBounds(450, 0, 100, 25);
        cour.setBounds(560, 0, 100, 25);
        cours.setBounds(600, 0, 100, 25);
        groupe.setBounds(710, 0, 70, 25);
        groupes.setBounds(790, 0, 100, 25);
        salle.setBounds(900, 0, 70, 25);
        salles.setBounds(980, 0, 100, 25);
        valider.setBounds(1090, 0, 100, 25);
        //on ajoute un evenement sur le boutton valider
        valider.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //on recupére toute les informations saisie par l'utilisateur 
                String s=semaine.getText();
                int week=Integer.parseInt(s);
                Object i=groupes.getSelectedItem();
                String g=i.toString();
                i=cours.getSelectedItem();
                String c=i.toString();
                i=salles.getSelectedItem();
                s=i.toString();
                i=jours.getSelectedItem();
                String j=i.toString();
                i=heure.getSelectedItem();
                String h=i.toString();
                int k=verifenewsalle(week,j,h,s);//on verifie que la salle est disponible 
                if(k==0)
                {
                    String nom=getprof2(c);
                    k=verifenewprof(week,j,h,nom);//on verifie que le prooffesseur est disponible 
                    if(k==0)
                    {
                        k=verifenewgroupe(week,j,h,g);//on verifie que le groupe est disponible a ce moment la 
                        if(k==0)
                        {
                            k=verifpromo(g,c);//in verifie que le cours correspont a bien la promo du groupe choisie 
                            if(k==0)
                            {
                                String h2=HFin(h);//on détermine l'heure de fin en fonction de l'heure de depart
                                insertseance(week,c, g,s, h, h2, j);//on crée la senace
                                JOptionPane.showMessageDialog(null, "La Mise à Jour a bien été efféctué");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Ce groupe n'est pas inscrit pour ce cours");//message d'erreur
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Ce groupe a deja une séance a ce moment la");//message d'erreur
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Le proffesseur a deja une séance a ce moment la");//message d'erreur
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "La salle est deja occupé a ce moment la");//message d'erreur
                }
                    
            }});
    }
    public String HFin(String hdebut)//on détermine l'heure de fin en fonction de l'heure de depart
    {
        String hfin="";
        //on différencie les cas en fonction de l'heure de depart
        //on en deduit l'heure de fin
        if(hdebut.equalsIgnoreCase("08:30:00"))
        {
            hfin="10:00:00";
        }
        if(hdebut.equalsIgnoreCase("10:15:00"))
        {
            hfin="11:45:00";
        }
        if(hdebut.equalsIgnoreCase("12:00:00"))
        {
            hfin="13:30:00";
        }
        if(hdebut.equalsIgnoreCase("13:45:00"))
        {
            hfin="15:15:00";
        }
        if(hdebut.equalsIgnoreCase("15:30:00"))
        {
            hfin="17:00:00";
        }
        return hfin;//on retourne l'heure de fin 
    }
}
