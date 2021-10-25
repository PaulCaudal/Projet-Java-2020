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
/**
 * Cette classe affiche les champs qui permettent a un admin de modifier le nom d'un cours ou d'un type de cours 
 */
public class MTouN 
{
    private Container contenu=new JFrame().getContentPane();
    JComboBox<String> varie=new JComboBox<>();//on utilise des combo box pour eviter toutes erreur de saisie
    JComboBox<String> type=new JComboBox<>();
    JTextField nouveau=new JTextField();
    ArrayList<String>n;
    public Container getcontenu()
    {
        return contenu;
    }
    public void paint()
    {
        
        //on declare nos texte
        final JLabel texte1=new JLabel("Modifier le");
        final JLabel texte2=new JLabel("de");
        final JLabel texte3=new JLabel("à");
        final JButton valider=new JButton("Valider");
        type.removeAllItems();
        type.addItem("nom du cours");//on initialise la combobox type
        type.addItem("nom du type de cours");
        //on ajoute nos element au container 
        contenu.add(texte1);
        contenu.add(type);
        contenu.add(texte2);
        contenu.add(texte3);
        //on ajoute les coordonnées de nos element 
        texte1.setBounds(10, 0, 80, 25);
        type.setBounds(100, 0, 120, 25);
        texte2.setBounds(230, 0, 20, 25);
        //on ajoute un evenement sur type
        type.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//On remet le container a 0
                //on remet le element graphique qui nous interresse
                contenu.add(nouveau);
                contenu.add(texte1);
                contenu.add(type);
                contenu.add(texte2);
                contenu.add(texte3);
                if(0==type.getSelectedIndex())//si on veut modifié le nom d'un cours
                {
                    n=getnomcours();//on prend tout les nom de cours
                }
                if(1==type.getSelectedIndex())//si on veut modifié le nom d'un type de cours
                {
                    n=getnomtype();//on prend tout les nom de type de cours
                }
               
                varie.removeAllItems();
                for(int i=0;i<n.size();i++)
                {
                    varie.addItem(n.get(i));//on met tout les nom dans la combobox
                }
                //on ajoute au container
                contenu.add(varie);
                contenu.add(valider);
                //on set les coordonées
                varie.setBounds(260, 0, 120, 25);
                texte3.setBounds(390, 0, 15, 25);
                nouveau.setBounds(415, 0, 100, 25);
                valider.setBounds(525, 0, 100, 25);
                contenu.repaint();
            }});
        //on ajoute une action sur valider
        valider.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //on recupére les données saisi par l'utilisateur
                String nouvnom=nouveau.getText();
                Object i=varie.getSelectedItem();
                String n=i.toString();
                if(0==type.getSelectedIndex())//si c'est sur un cours
                {
                    updatnom(nouvnom,n,"cours");//on modifie le nom dans la BDD
                }
                if(1==type.getSelectedIndex())//si c'est sur un type de cours
                {
                    updatnom(nouvnom,n,"typecours");//on modifie le nom dans la BDD
                }
                JOptionPane.showMessageDialog(null, "La Mise à Jour a bien été efféctué");
                    
            }});
    }
}
