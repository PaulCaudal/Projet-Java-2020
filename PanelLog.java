
package controler;


import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static model.DBRecherche.*;
/**
 * Cette classe permet aux utilisateur de ce connecter et ainsi toutes les actions en dépende 
 */
public class PanelLog 
{
    private Container contenu;
   
   public PanelLog(Container c)
   {
       contenu=c;
       contenu.setLayout(null);
   }
    public Container getcontenu()
    {
         return contenu;//retourne le container
    }
    public void paintlog()
    {
        Color a=new Color(117,20,65); //declarer les couleurs que on va utiliser 
        Color b=new Color(255,255,255); 
        JLabel log=new JLabel("Email:");//texte
         JLabel pass=new JLabel("Mot de Passe:");//texte 
        JButton logb=new JButton("Se connecter");//boutton 
        final JPasswordField textpass=new JPasswordField();//zone de texte MdP
        final JTextField textlog=new JTextField();//zone de texte email 
        textpass.setEchoChar('*');
        JLabel img = new JLabel(new ImageIcon("logo.jpg"));
        Font font1=new Font(Font.SERIF,Font.BOLD,25);
        textlog.setFont(font1);//modifier la police
        textpass.setFont(font1);
        Font font=new Font(Font.SERIF,Font.BOLD,45);
        log.setFont(font);//modifier la police
        pass.setFont(font);
        log.setForeground(b);//modifier la couleur 
        pass.setForeground(b);
        //ajouter au container
        contenu.add(log);
        contenu.add(pass);
        contenu.add(logb);
        contenu.add(textlog);
        contenu.add(textpass);
        contenu.add(img);
        //ajouet les coordonées 
        textlog.setBounds(950, 360, 300, 50);
        textpass.setBounds(950, 460, 300, 50);
        logb.setBounds(825, 570, 250, 60);
        log.setBounds(670, 360, 200, 60);
        pass.setBounds(670, 460, 300, 60);
        img.setBounds(875, 0, 150, 150);
        contenu.setBackground(a);
        //on ajoute une action sur le boutton logb
        logb.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //on recupere les données saisie par l'utilisateur
                String login=textlog.getText();
                String password=textpass.getText();
                int id=Researchlog(login,password);//on verifie que l'utilisateur existe et on recupére son id
                if(id!=0)
                {
                   int droit=getdroit(id);//on recupere ces droit 
                   if(droit==0)//si c'est un etudiant 
                   {
                       contenu.removeAll();//on remet le container a 0
                       Etudiant E=new Etudiant(contenu,id);//on appelle la classe Etudiant
                       E.paint(droit);//on appelle la methode paint
                       contenu=E.getcontenu();//on recupere le container 
                       contenu.repaint();//on refresh le container
                   }
                   if(droit==1)//si c'est un enseignant
                   {
                       contenu.removeAll();//on remet le container a 0
                       Enseignant E=new Enseignant(contenu,id);//on appelle la classe Enseignant
                       E.paint(droit);//on appelle la methode paint
                       contenu=E.getcontenu();//on recupere le container 
                       contenu.repaint();//on refresh le container
                   }
                   if(droit==2)//si c'est un admine
                   {
                       contenu.removeAll();//on remet le container a 0
                       Admin E=new Admin(contenu);//on appelle la classe Admin
                       E.paint(droit);//on appelle la methode paint
                       contenu=E.getcontenu();//on recupere le container 
                       contenu.repaint();//on refresh le container
                   }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "mot de passe ou email incorrect ");//message d'erreur
                }
            }});
  }
}
