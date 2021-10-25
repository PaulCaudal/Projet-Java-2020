
package controler;

import vue.Creer;
import vue.Enlever;
import vue.Ajouter;
import vue.Deplacer;
import vue.AouV;
import vue.MTouN;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * Cette cllas permet de conroler toutes les actions que l'on peut faire sur une seance 
 */
public class Modifier 
{
    private Container contenu=new JFrame().getContentPane();
    ArrayList<String>n;
    public Container getcontenu()
    {
        return contenu;
    }
    public void paint()
    {
        //on initialise tout nos boutton
        final JButton creer=new JButton("Créer une séance");
        final JButton AouV=new JButton("Anulé ou Validé une séance");
        final JButton ajouter=new JButton("Ajouter à une séance");
        final JButton enlever=new JButton("Enlever d'une séance");
        final JButton deplacer=new JButton("Déplacer une séance ");
        final JButton modifier=new JButton("Modifier un cours");
        //on les ajoute sur le container
        contenu.add(creer);
        contenu.add(AouV);
        contenu.add(ajouter);
        contenu.add(enlever);
        contenu.add(deplacer);
        contenu.add(modifier);
        //on set les coordonées 
        creer.setBounds(0, 0, 200, 25);
        AouV.setBounds(210, 0, 200, 25);
        ajouter.setBounds(420, 0, 200, 25);
        enlever.setBounds(630, 0, 200, 25);
        deplacer.setBounds(840, 0, 300, 25);
        modifier.setBounds(1150, 0, 200, 25);
        //on met le fond sur le container
        contenu.setBackground(new Color(240,240,240));
        //on ajoute une action sur le boutton AouV
         AouV.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//On remet le container a 0
                //on remet le element graphique qui nous interresse
                contenu.add(creer);
                contenu.add(AouV);
                contenu.add(ajouter);
                contenu.add(enlever);
                contenu.add(deplacer);
                contenu.add(modifier);
                AouV a=new AouV(contenu);//on appelle la classe AouV
                a.paint();//on appelle la methode paint
                contenu.repaint();
            }});
         //on ajoute une action sur le boutton deplacer
         deplacer.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//On remet le container a 0
                //on remet le element graphique qui nous interresse
                contenu.add(creer);
                contenu.add(AouV);
                contenu.add(ajouter);
                contenu.add(enlever);
                contenu.add(deplacer);
                contenu.add(modifier);
                Deplacer a=new Deplacer(contenu);//on appelle la classe Deplacer
                a.paint();//on appelle la methode paint
                contenu.repaint();
            }});
         ajouter.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//On remet le container a 0
                //on remet le element graphique qui nous interresse
                contenu.add(creer);
                contenu.add(AouV);
                contenu.add(ajouter);
                contenu.add(enlever);
                contenu.add(deplacer);
                contenu.add(modifier);
                Ajouter a=new Ajouter();//on appelle la classe Ajouter
                a.paint();//on appelle la methode paint
                Container c1=a.getcontenu();//on recupere le container
                contenu.add(c1);//on l'ajoute au container principal
                c1.setBounds(10, 50, 1500, 50);//on set les coordonées
                contenu.repaint();
            }});
         enlever.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//On remet le container a 0
                //on remet le element graphique qui nous interresse
                contenu.add(creer);
                contenu.add(AouV);
                contenu.add(ajouter);
                contenu.add(enlever);
                contenu.add(deplacer);
                contenu.add(modifier);
                Enlever a=new Enlever();//on appelle la classe Enlever
                a.paint();//on appelle la methode paint
                Container c1=a.getcontenu();//on recupere le container
                contenu.add(c1);//on l'ajoute au container principal
                c1.setBounds(10, 50, 1500, 70);//on set les coordonées
                contenu.repaint();
            }});
         modifier.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//On remet le container a 0
                //on remet le element graphique qui nous interresse
                contenu.add(creer);
                contenu.add(AouV);
                contenu.add(ajouter);
                contenu.add(enlever);
                contenu.add(deplacer);
                contenu.add(modifier);
                MTouN a=new MTouN();//on appelle la classe MTouN
                a.paint();//on appelle la methode paint
                Container c1=a.getcontenu();//on recupere le container
                contenu.add(c1);//on l'ajoute au container principal
                c1.setBounds(10, 50, 1500, 70);//on set les coordonées
                contenu.repaint();
            }});
         creer.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contenu.removeAll();//On remet le container a 0
                //on remet le element graphique qui nous interresse
                contenu.add(creer);
                contenu.add(AouV);
                contenu.add(ajouter);
                contenu.add(enlever);
                contenu.add(deplacer);
                contenu.add(modifier);
                Creer a=new Creer();//on appelle la classe Creer
                a.paint();//on appelle la methode paint
                Container c1=a.getcontenu();//on recupere le container
                contenu.add(c1);//on l'ajoute au container principal
                c1.setBounds(10, 50, 1500, 70);//on set les coordonées
                contenu.repaint();
            }});
         
    }
}
