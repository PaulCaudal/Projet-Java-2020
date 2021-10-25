
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
import static model.DBAction.getexistance;
import static model.DBAction.getnomcours;
import static model.DBAction.updateetat;

/**
 * cette classe affiche les champs qui permettent d'annulé ou de validé une seance par l'admin
 */
public class AouV 
{
    private Container contenu;
    JComboBox<String> type=new JComboBox<>();//on utilise des combo box pour eviter toutes erreur de saisie
    JComboBox<String> varie=new JComboBox<>();
    JTextField semaine=new JTextField();
    JComboBox<String> jours=new JComboBox<>();
    JComboBox<String> heure=new JComboBox<>();
    ArrayList<String>n;
    public AouV(Container c)
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
        type.addItem("validé");
        type.addItem("supprimé");
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
        contenu.add(valider);
        contenu.add(se);
        contenu.add(jour);
        contenu.add(heur);
        contenu.add(cour);
        //on ajoute les coordonnées de nos element 
        type.setBounds(10, 50, 100, 25);
        se.setBounds(120, 50, 100, 25);
        semaine.setBounds(180, 50, 100, 25);
        jour.setBounds(340, 50, 100, 25);
        jours.setBounds(380, 50, 100, 25);
        heur.setBounds(560, 50, 100, 25);
        heure.setBounds(670, 50, 100, 25);
        cour.setBounds(780, 50, 100, 25);
        varie.setBounds(850, 50, 100, 25);
        valider.setBounds(1000, 50, 100, 25);
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
                if(id!=0)//si la senace existe 
                {
                    //on différencie les cas en fonstion de ce que l'utilisateur faire
                    if(0==type.getSelectedIndex())//si il veut validé la senace 
                    {
                        updateetat(id,1);//on met a jour la BDD
                    }
                    if(1==type.getSelectedIndex())//si il veut anulé la senace
                    {
                        updateetat(id,2);//on met a jour la BDD
                    }
                    JOptionPane.showMessageDialog(null, "La Mise à Jour a bien été efféctué");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "la séance n'éxiste pas");//message d'erreur
                }

            }});
    }
}
