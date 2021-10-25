package vue;

import controler.Semaine;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import static model.DBRecherche.*;
/**
 * cette classe permet a un admin d'afficher l'emplois du temps qu'il veut 
 */
public class EdtAdmin 
{
     private Container contenu;
     private int goff;//goff et loff permette de savoir si l'affichage est en grille ou en lsite 
     private int loff;
    private int droit=0;
    private int id=0;
    ArrayList<String>n;
    public EdtAdmin(Container c)
    {
        contenu=c;
        goff=1;
        loff=1;
        
    }
    public void draw()
    {
        
        final JComboBox<String> option=new JComboBox<>();
        final JComboBox<String> type=new JComboBox<>();
        final JComboBox<String> nom=new JComboBox<String>();
        final JButton affiche=new JButton("afficher");
        option.addItem("en grille");//on initialise les combo box
        option.addItem("en liste");
        type.addItem("groupe");
        type.addItem("enseignant");
        type.addItem("salle");
        //on cherche la semaine actuelle
        Date actual=new Date();
        final int day=actual.getDate();
        final int month=actual.getMonth();
        final int year=actual.getYear()+2000-100;
        final Calendar c= Calendar.getInstance();
        c.set(year,month,day);
        //on ajoute au container 
        contenu.add(option);
        contenu.add(type);
        //on affiche EDT engrille par defaut
        final Semaine s=new Semaine(c.get(c.WEEK_OF_YEAR),contenu);
        s.draw(0,id,droit);
        loff=1;
        goff=0;
        //on set les coordonné
        option.setBounds(10, 145, 100, 30);
        type.setBounds(120,145,100,30);
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
                    s.draw(0,id,droit);;//on affiche l'edt en grille
                    contenu.repaint();
                } 
                if(1==option.getSelectedIndex())//si liste est selectionnné
                {
                    if(goff==0)//si la grille est a l'ecran
                    {
                        contenu.remove(5);//on enleve la grille
                        goff=1;
                        loff=0;
                    }
                    s.draw(1,id,droit);//on affiche l'edt en liste
                    contenu.repaint();
                } 
            }
        }
        );
        //on ajoute une action a type
         type.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(0==type.getSelectedIndex())//si etudiant est selectionné
                {
                    droit=0;
                    nom.removeAllItems();//on vide la combobox
                    n=new ArrayList<>();//on re-initialise l'ArrayList
                    n=getttnomgroupe();//on la rempli avec tous les noms de groupe 
                    for(int i=0;i<n.size();i++)
                    {
                        nom.addItem(n.get(i));//on rempli la combox
                    }
                    //on ajoute au container
                    contenu.add(nom);
                    contenu.add(affiche);
                    //on set les coordonnées
                    affiche.setBounds(340,145,100,30);
                    nom.setBounds(230,145,100,30);
                } 
                if(1==type.getSelectedIndex())//si proffesseur est selectionné
                {
                    droit=1;
                    nom.removeAllItems();//on vide la combobox
                    n=new ArrayList<>();//on re-initialise l'ArrayList
                    n=getttnomprof(droit);//on la rempli avec tous les noms de proffesseur
                    for(int i=0;i<n.size();i++)
                    {
                        nom.addItem(n.get(i));//on rempli la combox
                    }
                    //on ajoute au container
                    contenu.add(nom);
                    contenu.add(affiche);
                    //on set les coordonnées
                    affiche.setBounds(340,145,100,30);
                    nom.setBounds(230,145,100,30);
                } 
                if(2==type.getSelectedIndex())//si salle est selectionné
                {
                    droit=2;
                    nom.removeAllItems();//on vide la combobox
                    n=new ArrayList<>();//on re-initialise l'ArrayList
                    n=getttnomsalle();//on la rempli avec tous les noms de salle
                    for(int i=0;i<n.size();i++)
                    {
                        nom.addItem(n.get(i));//on rempli la combox
                    }
                    //on ajoute au container
                    contenu.add(nom);
                    contenu.add(affiche);
                    //on set les coordonnées
                    affiche.setBounds(340,145,100,30);
                    nom.setBounds(230,145,100,30);
                } 
            }
            
        }
        );
         //on ajoute une action sur le boutton affiche
         affiche.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(0==type.getSelectedIndex())//si etudiant est selectionné
                {
                    contenu.remove(5);//on enleve L'EDT affiché
                    contenu.add(option);
                    goff=1;
                    loff=0;
                    int i=nom.getSelectedIndex();//on recupere l'index de l'objet selectionné 
                    String name=n.get(i);//obtnir le nom du groupe 
                    id=getidgroupe(name);//et son id
                    s.draw(0,id,droit);//on affiche l'edt en grille par default
                    contenu.repaint();
                } 
                if(1==type.getSelectedIndex())//si proffesseur est selectionné
                {
                    contenu.remove(5);//on enleve L'EDT affiché
                    contenu.add(option);
                    goff=1;
                    loff=0;
                    int i=nom.getSelectedIndex();//on recupere l'index de l'objet selectionné 
                    String name=n.get(i);//obtnir le nom du proffesseur 
                    id=getidprof(name);//et son id
                    s.draw(0,id,droit);//on affiche l'edt en grille par default
                    contenu.repaint();
                } 
                if(2==type.getSelectedIndex())//si salle est selectionné
                {
                    contenu.remove(5);//on enleve L'EDT affiché
                    contenu.remove(option);
                    goff=1;loff=0;
                    int i=nom.getSelectedIndex();//on recupere l'index de l'objet selectionné 
                    String name=n.get(i);//obtnir le nom de la salle 
                    id=getidsalle(name);//et son id
                    s.draw(0,id,droit);//on affiche l'edt en grille par default
                    contenu.repaint();
                } 
            }
        }
        );
    }
}
