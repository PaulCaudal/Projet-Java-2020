
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
 * cette classe affiche les champs qui permettent  d'enlever un groupe (ou un proffesseur ou une salle)d'une seance par l'admin
 */
public class Enlever
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
    int id;
    public Enlever()
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
        return contenu;
    }
    public void paint()
    {
        //on declare nos texte
        final JLabel se=new JLabel("Semaine:");
        final JLabel jour=new JLabel("jour:");
        final JLabel heur=new JLabel("heure de debut:");
        final JLabel cour=new JLabel("Cours:");
        final JButton recherche=new JButton("Rechercher");
        final JButton valider=new JButton("Valider");
        type.removeAllItems();
        type.addItem("goupe");//on initialise la combobox type
        type.addItem("proffesseur");
        type.addItem("salle");
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
        contenu.add(recherche);
        contenu.add(se);
        contenu.add(jour);
        contenu.add(heur);
        contenu.add(cour);
        //on ajoute les coordonnées de nos element 
        se.setBounds(10, 0, 100, 25);
        semaine.setBounds(70, 0, 100, 25);
        jour.setBounds(180, 0, 100, 25);
        jours.setBounds(220, 0, 100, 25);
        heur.setBounds(350, 0, 100, 25);
        heure.setBounds(450, 0, 100, 25);
        cour.setBounds(560, 0, 100, 25);
        varie.setBounds(600, 0, 100, 25);
        recherche.setBounds(710, 0, 150, 25);
        //on ajoute un evenement sur le boutton recherche
        recherche.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //on recupére les informations saisie par l'utilisateur 
                String s=semaine.getText();
                int week=Integer.parseInt(s);
                Object i=jours.getSelectedItem();
                String j=i.toString();
                i=heure.getSelectedItem();
                String h=i.toString();
                i=varie.getSelectedItem();
                String v=i.toString();
                id=getexistance(week,j,h,v);//on verifie avec le numéro de semaine,le jour, l'heure et le nom du coure si la seance existe.
                 //on recupére ensuite l'id de cette seance  
                if(id!=0)
                {
                    contenu.removeAll();//On remet le container a 0
                    //on remet le element graphique qui nous interresse
                    contenu.add(varie);
                    contenu.add(semaine);
                    contenu.add(jours);
                    contenu.add(heure);
                    contenu.add(recherche);
                    contenu.add(se);
                    contenu.add(jour);
                    contenu.add(heur);
                    contenu.add(cour);  
                    contenu.add(type);
                    type.setBounds(10, 35, 100, 25);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "la séance n'éxiste pas");//message d'erreur 
                }
                
            }});
        //on ajoute une action sur type
        type.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//On remet le container a 0
                //on remet le element graphique qui nous interresse
                contenu.add(varie);
                contenu.add(semaine);
                contenu.add(jours);
                contenu.add(heure);
                contenu.add(recherche);
                contenu.add(se);
                contenu.add(jour);
                contenu.add(heur);
                contenu.add(cour);
                contenu.add(type);
                if(0==type.getSelectedIndex())//si groupe est selectionné
                {
                    n2=getnomgroupe(id);//on prend tout les nom de groupe
                }
                if(1==type.getSelectedIndex())//si proffesseur est selectionné
                {
                    n2=getnomprof(id);//on prend tout les nom de proffesseur
                }
                if(2==type.getSelectedIndex())//si salle est selectionné
                {
                    n2=getnomsalle2(id);//on prend tout les nom de salle
                }
                element.removeAllItems();//on vide la combo box 
                for(int i=0;i<n2.size();i++)
                {
                    element.addItem(n2.get(i));//on rempli la combo box
                }
                //on ajoute les element au container
                contenu.add(element);
                contenu.add(valider);
                //on set les coordonnées 
                element.setBounds(120, 35, 100, 25);
                valider.setBounds(230, 35, 100, 25);
                contenu.repaint();
            }});
        //on ajoute une action au boutton valider
        valider.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               int k;String nom;
                if(0==type.getSelectedIndex())//si groupe est selectionné
                {
                    k=blindagenb(id,"seanceg");//on verifie que la seance ne se retrouve pas sans groupe
                    if(k==2)
                    {
                        Object i=element.getSelectedItem();
                        nom=i.toString();//on recupere le nom du groupe 
                        supprimergroupe(id,nom);//on supprime le groupe selectionné de la seance
                        JOptionPane.showMessageDialog(null, "La Mise à Jour a bien été efféctué");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas retirer tous les groupes de la séance");//message d'erreure 
                    }
                }
                if(1==type.getSelectedIndex())//si proffesseur est selectionné
                {
                    k=blindagenb(id,"seancee");//on verifie que la seance ne se retrouve pas sans proffesseur
                    if(k==2)
                    {
                        Object i=element.getSelectedItem();
                        nom=i.toString();//on recupere le nom du proffesseur
                        supprimerprof(id,nom);//on supprime le proffesseur selectionné de la seance
                        JOptionPane.showMessageDialog(null, "La Mise à Jour a bien été efféctué");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas retirer tous les professeurs de la séance");//message d'erreure 
                    }
                }
                if(2==type.getSelectedIndex())//si salle est selectionné
                {
                    k=blindagenb(id,"seances");//on verifie que la seance ne se retrouve pas sans salle
                    if(k==2)
                    {
                        Object i=element.getSelectedItem();
                        nom=i.toString();//on recupere le nom du proffesseur
                        k=blindagecapacite2(id,nom);//on verifie que la capacité reste suffisante 
                        if(k==0)
                        {
                            supprimersalle(id,nom);//on supprime la salle selectionné de la seance
                            JOptionPane.showMessageDialog(null, "La Mise à Jour a bien été efféctué");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Vous ne pouvez pas retirer cette salle car tout les eleves ne peuvent pas être acceuilli dans la salle restante ");//message d'erreure 
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas retirer toutes les salles de la séance");//message d'erreure 
                    }
                }
                
            }});
    }
}
