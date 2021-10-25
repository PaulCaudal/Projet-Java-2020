
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
 * cette classe affiche les champs qui permettent aux admins d'ajouter a une scéance un groupe un proffesseur ou une salle 
 */
public class Ajouter 
{
    private Container contenu=new JFrame().getContentPane();;
    JComboBox<String> type=new JComboBox<>();//on utilise des combo box pour eviter toutes erreur de saisie
    JComboBox<String> element=new JComboBox<>();
    JComboBox<String> varie=new JComboBox<>();
    JTextField semaine=new JTextField();
    JComboBox<String> jours=new JComboBox<>();
    JComboBox<String> heure=new JComboBox<>();
    ArrayList<String>n;
    ArrayList<String>n2;
    public Ajouter()
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
        final JButton valider=new JButton("Valider");
        type.removeAllItems();
        varie.removeAllItems();
        //on initialise la combobox type
        type.addItem("goupe");
        type.addItem("proffesseur");
        type.addItem("salle");
        n=getnomcours();//onrecupére tout les nom de cours 
        for(int i=0;i<n.size();i++)
        {
            varie.addItem(n.get(i));//on rempli la combobox avec ces dernier
        }
        //on ajoute nos element au container 
        contenu.add(type);
        contenu.add(varie);
        contenu.add(semaine);
        contenu.add(jours);
        contenu.add(heure);
        contenu.add(se);
        contenu.add(jour);
        contenu.add(heur);
        contenu.add(cour);
        //on ajoute les coordonnées de nos element 
        type.setBounds(10, 0, 100, 25);
        se.setBounds(120, 0, 100, 25);
        semaine.setBounds(180, 0, 100, 25);
        jour.setBounds(340, 0, 100, 25);
        jours.setBounds(380, 0, 100, 25);
        heur.setBounds(560, 0, 100, 25);
        heure.setBounds(670, 0, 100, 25);
        cour.setBounds(780, 0, 100, 25);
        varie.setBounds(850, 0, 100, 25);
        valider.setBounds(1220, 0, 100, 25);
        //on ajoute un evenement sur le boutton valider
        valider.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    //on recupére toute les informations saisie par l'utilisateur 
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
                    if(id!=0)//si la seance existe 
                    {
                        //on différencie les cas en fonstion de ce que l'utilisateur veut ajouter a la seance
                        if(0==type.getSelectedIndex())//si il s'agit d'un groupe 
                        {
                            i=element.getSelectedItem();
                            String nom=i.toString();//on recupere le nom du groupe a ajouter
                            int k=verifeexistancegroupe(id,nom);//on verifie que le groupe n'est pas deja affecter a cette senace 
                            if(k==0)
                            {
                                k=blindagenb(id,"seanceg");//on verifie le nombre de groupe deja affecter a la seance  
                                if(k<2)
                                {
                                    k=verifenewgroupe(week,j,h,nom);//on verifie que le groupe est disponible 
                                    if(k==0)
                                    {
                                        k=blindagecapacite(id,nom);//on verifie la capaciter des salles 
                                        if(k==0)
                                        {
                                             k=verifpromoajout(nom,id);//on verifie que la promo correspont 
                                            if(k==0)
                                            {
                                                insertgroupe(id,nom);//on insert la groupe a la senace 
                                                JOptionPane.showMessageDialog(null, "La Mise à Jour a bien été efféctué");
                                            }
                                            else
                                            {
                                                JOptionPane.showMessageDialog(null, "Ces groupes n'apartienent pas à la même promotion");//message d'erreur
                                            }
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(null, "La capacité de la salle actuelle n'est pas suffisante");//message d'erreur
                                        }
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(null, "Ce groupe a deja une séance a ce moment la");//message d'erreur
                                    }
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Le nombre de groupe est deja au maximum");//message d'erreur
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Le groupe est deja affecter a cette séance");//message d'erreur
                            }
                        }
                        if(1==type.getSelectedIndex())//si il s'agit d'un professeur 
                        {
                            i=element.getSelectedItem();
                            String nom=i.toString();
                            int k=verifeexistanceprof(id,nom);//on verifie que le proffeseur n'es pas deja affecter a la seance 
                            if(k==0)
                            {
                                k=blindagenb(id,"seancee");//on verifie que le nombbre de proffesseur ne depasse pas 2
                                if(k<2)
                                {
                                    k=verifenewprof(week,j,h,nom);//on verifie que le proffeseur est disponible 
                                    if(k==0)
                                    {
                                        insertprof(id,nom);//on ajoute le proffesseur a la senace 
                                        JOptionPane.showMessageDialog(null, "La Mise à Jour a bien été efféctué");
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(null, "Le proffesseur a deja une séance a ce moment la");//message d'erreur
                                    }
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Le nombre d'enseignant est deja au maximum");//message d'erreur
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Le proffesseur est deja affecter a cette séance");//message d'erreur
                            }
                        }
                        if(2==type.getSelectedIndex())//si il s'agit d'une salle 
                        {
                            i=element.getSelectedItem();
                            String nom=i.toString();
                            int k=verifeexistancesalle(id,nom);//on verifie que la salle n'est pas deja affecter a cette senace 
                            if(k==0)
                            {
                                k=blindagenb(id,"seances");//on verifie le nombre de salle affecter a cette senace
                                if(k<2)
                                {
                                    k=verifenewsalle(week,j,h,nom);//on verifie que la salle est disponible 
                                    if(k==0)
                                    {
                                        insertsalle(id,nom);///on ajoute la salle a la seance 
                                        JOptionPane.showMessageDialog(null, "La Mise à Jour a bien été efféctué");
                                    }
                                    else 
                                    {
                                        JOptionPane.showMessageDialog(null, "La salle est deja occupé a ce moment la");//message d'erreur
                                    }
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Le nombre de salle est deja au maximum");//message d'erreur
                                }
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "La salle est deja affecter a cette séance");//message d'erreur
                            }
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "la séance n'éxiste pas");//message d'erreur
                    }
                
            }});
        //on ajoute un evenement sur la combo box type
        type.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//on remet le container a 0
                contenu.add(type);//on ajoute tout les element que l'on veut conserver 
                    contenu.add(varie);
                    contenu.add(semaine);
                    contenu.add(jours);
                    contenu.add(heure);
                    contenu.add(valider);
                    contenu.add(se);
                    contenu.add(jour);
                    contenu.add(heur);
                    contenu.add(cour);
                    element.removeAllItems();//on retire tout les element si il y en a de la combobox element 
                    JLabel genre = null;
                    //en fonction du choix d'ajoux la combo box element ne sera pas rempli avec les memme element 
                    if(0==type.getSelectedIndex())//si on veut ajouter un grouoe 
                    {
                        genre=new JLabel("Groupe:");
                        n2=getttnomgroupe();//on recuppere tout les nom de groupe 
                    }
                    if(1==type.getSelectedIndex())//si on veut ajouter un proffesseur 
                    {
                        genre=new JLabel("Proffesseur:");
                        n2=getttnomprof(1);//on recuppere tout les nom de proffesseur 
                    }
                    if(2==type.getSelectedIndex())//si on veut ajoueter une salle 
                    {
                        genre=new JLabel("Salle:");
                        n2=getttnomsalle();//on recuppere tout les nom de salle 
                    }
                    for(int i=0;i<n2.size();i++)
                    {
                        element.addItem(n2.get(i));//on rempli la combo box element 
                    }
                    //on ajoute les element aux container
                    contenu.add(genre);
                    contenu.add(element);
                    //on set les coordonées 
                    genre.setBounds(1000, 0, 100, 25);
                    element.setBounds(1110, 0, 100, 25);
                    contenu.repaint();//on refresh le container
                
            }});
    }
}
