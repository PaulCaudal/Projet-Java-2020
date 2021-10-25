
package controler;

import javax.swing.JFrame;


public class Projetjava {

    
    public static void main(String[] args) 
    {
        JFrame fenetre=new JFrame();//declartion de la fenetre
        fenetre.setTitle("HyperPlaning");//set le titre le fenetre
        fenetre.setSize(1900,1000);//set la taille de la fenetre
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//activer la croix pour fermer la fenetre
        fenetre.setVisible(true);//rendre la fenetre visible
        PanelLog pan1=new PanelLog(fenetre.getContentPane());//appeller la classe PanelLog
        pan1.paintlog();//appeller la methode paintlog
    }
    
}
