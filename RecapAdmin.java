
package vue;

import vue.Recap;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import static model.DBRecherche.*;
/**
 * cette classe permet d'afficher le recapitulatif de tout les cours pour un admin 
 */
public class RecapAdmin 
{
    private Container contenu;
     private int droit=0;
    private int id=0;
    private int d=0;
    ArrayList<String>n;
    public RecapAdmin(Container c)
    {
        contenu=c;
    }
     public void draw()
    {
        final JComboBox<String> type=new JComboBox<>();
        final JComboBox<String> nom=new JComboBox<String>();
        final JButton affiche=new JButton("afficher");
        type.addItem("groupe");//on initialise type
        type.addItem("enseignant");
        contenu.add(type);//on ajoute au container
        type.setBounds(10,145,100,30);//on set les coordonnées 
        //on ajoute une action sur type
        type.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(0==type.getSelectedIndex())//si goupe est selectionné 
                {
                    droit=0;
                    nom.removeAllItems();//on enleve tout de nom
                    n=new ArrayList<>();
                    n=getttnomgroupe();//on recupere les nom des groupe
                    for(int i=0;i<n.size();i++)
                    {
                        nom.addItem(n.get(i));//on les ajoute dans nom
                    }
                    //on ajoute au container
                    contenu.add(nom);
                    contenu.add(affiche);
                    //on set les coordonnées 
                    affiche.setBounds(230,145,100,30);
                    nom.setBounds(120,145,100,30);
                } 
                if(1==type.getSelectedIndex())//si proffesseur est selectionné 
                {
                    droit=1;
                    nom.removeAllItems();//on enleve tout de nom
                    n=new ArrayList<>();
                    n=getttnomprof(droit);//on recupere les nom des proffesseur
                    for(int i=0;i<n.size();i++)
                    {
                        nom.addItem(n.get(i));//on les ajoute dans nom
                    }
                    //on ajoute au container
                    contenu.add(nom);
                    contenu.add(affiche);
                    //on set les coordonnées 
                    affiche.setBounds(230,145,100,30);
                    nom.setBounds(120,145,100,30);
                } 
            }});
        //on ajoute une action sur type
        affiche.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(0==type.getSelectedIndex())//si groupe est selectionné
                {
                    if(d==1)
                        contenu.remove(5);//on enleve ce qu'il y a a l'ecran
                    int i=nom.getSelectedIndex();
                    String name=n.get(i);//on recupere le nom du groupe selectionné 
                    id=getidgroupe(name);
                    Recap r = new Recap(id,droit);//on appelle recap
                    contenu.add(r,5);//on affiche le reccapitulatif 
                    d=1;
                    r.setBounds(10,180,1800,760);//on set ses coordonées 
                    contenu.repaint();
                } 
                if(1==type.getSelectedIndex())//si proffesseur est selectionné
                {
                    if(d==1)
                        contenu.remove(5);//on enleve ce qu'il y a a l'ecran
                    int i=nom.getSelectedIndex();
                    String name=n.get(i);//on recupere le nom du proffesseru selectionné 
                    id=getidprof(name);
                    Recapprof r = new Recapprof(id,droit);//on appelle recapprof
                    contenu.add(r,5);//on affiche le reccapitulatif 
                    d=1;
                    r.setBounds(10,180,1800,760);//on set ses coordonées 
                    contenu.repaint();
                } 
            }});
    }
}
