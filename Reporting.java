
package vue;
import org.jfree.chart.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import org.jfree.data.general.DefaultPieDataset;
import static model.DBAction.*;
import static model.DBRecherche.*;

/**
 * Cette classe permet a un admin d'obtenir des statistique sur les salles (ou sur les groupes ou les proffesseures)
 */
public class Reporting 
{
    private Container contenu=new JFrame().getContentPane();
    JComboBox<String> type=new JComboBox<>();
    ArrayList<String>n;
    public Reporting()
    {
        type.addItem("groupes");//on initialise la combo box 
        type.addItem("enseignant");
        type.addItem("salle");
    }
    public Container getcontenu()
    {
        return contenu;//on retourn le container
    }
    public void paint()
    {
        final JLabel texte1=new JLabel("Afficher l'occupatuion des");
        //on ajoute au container
        contenu.add(texte1);
        contenu.add(type);
        //on set les coordonées
        texte1.setBounds(0, 0, 250, 25);
        type.setBounds(260, 0, 100, 25);
        //on ajoute une action sur type
        type.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//On remet le container a 0
                //on remet le element graphique qui nous interresse
                contenu.add(texte1);
                contenu.add(type);
                String nom="";
                DefaultPieDataset cam=new DefaultPieDataset();//on initialise le diagramme
                if(0==type.getSelectedIndex())//si groupe est selectionné
                {
                   n=getttnomgroupe();//on recupére tout les groupes
                   cam=new DefaultPieDataset();//on re-initialise le diagramme
                   for(int i=0;i<n.size();i++)//on parcours la liste
                    {
                        nom=n.get(i);//on recupére le nom
                        int k=comptegroupe(nom);//on recupere le nombre de seance
                        cam.setValue(nom, k);//on l'ajoute au diagramme
                    }
                }
                if(1==type.getSelectedIndex())//si proffesseur est selectionné
                {
                    n=getttnomprof(1);//on recupére tout les proffesseur
                    for(int i=0;i<n.size();i++)
                    {
                        nom=n.get(i);//on recupére le nom
                        int k=compteprof(nom);//on recupere le nombre de seance
                        cam.setValue(nom, k);//on l'ajoute au diagramme
                    }
                }
                if(2==type.getSelectedIndex())//si salle est selectionné
                {
                    n=getttnomsalle();//on recupére toutes les salles
                    for(int i=0;i<n.size();i++)
                    {
                        nom=n.get(i);//on recupére le nom
                        int k=comptesalle(nom);//on recupere le nombre de seance
                        cam.setValue(nom, k);//on l'ajoute au diagramme
                    }
                }
                Object j=type.getSelectedItem();
                nom=j.toString();//nom du diagramme 
                JFreeChart pieChart = ChartFactory.createPieChart("occupation des "+nom,cam,true,true,true);
                ChartPanel cpan=new ChartPanel(pieChart);
                contenu.add(cpan);//on affiche le diagramme
                cpan.setBounds(0, 35, 500, 500);//on set les coordonnées
                contenu.repaint();
            }});
    }
}
