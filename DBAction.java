
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static model.Connexion.connecterDB;

/**
 * cette classe fait partie du model
 *elle regroupe toutes les methodes de blindage et mise a jour de la BDD pour les administrateur
 */
public class DBAction 
{
    private static Connection cnx;
    private static Statement st;
    private static ResultSet rst;
    /**
     * cette methode retourne une ArrayListe de tout les nom de cours  
     */
    public static ArrayList<String> getnomcours()
    {
         ArrayList<String>n=new ArrayList<>();
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `cours` WHERE 1";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            //on parcour les resulat
            while(rst.next())
            {
                n.add(rst.getString("Nom"));//on stock les relutat
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return les resultats
    }
    /**
     * cette methode retourne une ArrayListe de tout les nom de type de cours
     */
    public static ArrayList<String> getnomtype()
    {
         ArrayList<String>n=new ArrayList<>();
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `typecours` WHERE 1";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
           //on parcour les resulat
            while(rst.next())
            {
                n.add(rst.getString("Nom"));//on stock les relutat
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return les resultats
    }
    /**
     * cette methode retourne l'id d'une senace si elle existe 0 sinon
     */
    public static int getexistance(int s,String j,String h,String c)
    {
         int n=0;//on initialise n à 0
        try
        {
            cnx=connecterDB();//on cherche d'abors id du cours en fonction de son nom
            String query="SELECT * FROM `cours` WHERE `Nom`='"+c+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int temp=rst.getInt("IDCours");//recupérer l'id du cours 
            //on cherche ensuite une senace repondant a tout les critere 
            query="SELECT * FROM `seance` WHERE `Semaine`='"+s+"' AND `IDCours`='"+temp+"' AND `HDebut`='"+h+"' AND `jour`='"+j+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            if(rst.last())//on regarde si il y a des resultat
            {
                n=rst.getInt("IDSeance");//recupérer l'id de la seance
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourne la reponse 
    }
    /**
     * cette methode met a jour l'etat d'une seance
     */
    public static void updateetat(int id,int etat)
    {
        try
        {
            cnx=connecterDB();
            String query="UPDATE `seance` SET `Etat`='"+etat+"'WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
    /**
     * cette methode verifie si les groupe affecter a une senace que l'on veut deplacer sont bien tous disponible pour le nouveau créneau
     */
    public static int verifegroupe(int id,int s,String j,String h)
    {
         int n=0;//n prend la valeur 0 par defaut
        try
        {
            ResultSet rst2,rst3;
            cnx=connecterDB();//on commence par identifié tout les groupe affecter a la seance 
            String query="SELECT * FROM `seanceg` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            while(rst.next())//on parcour les resulat
            {
                int g=rst.getInt("IDGroupe");//on recupére l'id du groupe 
                //on recupére toutes les seance du groupe
                query="SELECT * FROM `seanceg` WHERE `IDGroupe`='"+g+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                rst2.beforeFirst();
                int temp=0;
                while(rst2.next())//on parcour les resulat
                {
                    temp=rst2.getInt("IDSeance");
                    //on verifie si l'une des seance du groupe est sur le meme créneau que le créneau de deplacement
                    query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `Semaine`='"+s+"' AND `jour`='"+j+"' AND `HDebut`='"+h+"'";//requete
                    st=cnx.createStatement();
                    rst3=st.executeQuery(query);//executer la requete 
                    if(rst3.last())//si le resultat existe 
                    {
                        n=1;//n prend la valeur 1
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return n
    }
    /**
     * cette methode verifie si un groupe est disponible pour un creneau donné 
     */
    public static int verifenewgroupe(int s,String j,String h,String nom)
    {
         int n=0;//n prend la valeur 0 par defaut
        try
        {
            cnx=connecterDB();//on commence par identifié le groupe 
            String query="SELECT * FROM `groupe` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int g=rst.getInt("IDGroupe");//on recupére l'id du groupe 
            query="SELECT * FROM `seanceg` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            ResultSet rst2;int temp=0;
            while(rst.next())//on parcour les resulat
            {
                temp=rst.getInt("IDSeance");//on recupére l'id de la seance 
                //on verifie toutes les seance du groupe 
                query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `Semaine`='"+s+"' AND `jour`='"+j+"' AND `HDebut`='"+h+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())//si le resultat existe 
                {
                    n=1;//n prend la valeur 1
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return n
    }
    /**
     * cette methode verifie si les proffesseur affecter a une seance que l'on veut deplacer sont bien tous disponible pour le nouveau créneau
     */
    public static int verifeprof(int id,int s,String j,String h)
    {
         int n=0;//n prend la valeur 0 par defaut
        try
        {
            ResultSet rst2,rst3;
            cnx=connecterDB();//on commence par identifié tout les proffesseur affecter a la seance 
            String query="SELECT * FROM `seancee` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            while(rst.next())//on parcour les resulat
            {
                int g=rst.getInt("IDUtilisateur");//on recupére l'id du proffesseur
                //on recupére toutes les seance du proffesseur
                query="SELECT * FROM `seancee` WHERE `IDUtilisateur`='"+g+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                rst2.beforeFirst();
                int temp=0;
                while(rst2.next())//on parcour les resulat
                {
                    temp=rst2.getInt("IDSeance");//on recupére l'id de la seance 
                     //on verifie si l'une des seance du proffesseur est sur le meme créneau que le créneau de deplacement
                    query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `Semaine`='"+s+"' AND `jour`='"+j+"' AND `HDebut`='"+h+"'";//requete
                    st=cnx.createStatement();
                    rst3=st.executeQuery(query);//executer la requete 
                    if(rst3.last())//si le resultat existe 
                    {
                        n=1;//n prend la valeur 1
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return n
    }
    /**
     * cette methode verifie si un proffeseur est disponible pour un creneau donné
     */
    public static int verifenewprof(int s,String j,String h,String nom)
    {
         int n=0;//n prend la valeur 0 par defaut
        try
        {
            cnx=connecterDB();//on commence par identifié le proffesseur 
            String query="SELECT * FROM `utilisateur` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int g=rst.getInt("IDUtilisateur");//on recupére l'id du proffesseur 
            query="SELECT * FROM `seancee` WHERE `IDUtilisateur`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            ResultSet rst2;int temp=0;
            while(rst.next())//on parcour les resulat
            {
                temp=rst.getInt("IDSeance");//on recupére l'id de la seance 
                //on verifie toutes les seance du proffesseur
                query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `Semaine`='"+s+"' AND `jour`='"+j+"' AND `HDebut`='"+h+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())//si le resultat existe 
                {
                    n=1;//n prend la valeur 1
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return n
    }
    /**
     * cette methode verifie si les salles affecter a une seance que l'on veut deplacer sont bien tous disponible pour le nouveau créneau
     */
    public static int verifesalle(int id,int s,String j,String h)
    {
         int n=0;//n prend la valeur 0 par defaut
        try
        {
            ResultSet rst2,rst3;
            cnx=connecterDB();//on commence par identifié toutes les salles affecter a la seance 
            String query="SELECT * FROM `seances` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();int g=0;
            while(rst.next())//on parcour les resulat
            {
                g=rst.getInt("IDSalle");//on recupére l'id de la salle 
                //on recupére toutes les seance de la salle
                query="SELECT * FROM `seances` WHERE `IDSalle`='"+g+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                rst2.beforeFirst();
                int temp=0;
                while(rst2.next())//on parcour les resulat
                {
                    temp=rst2.getInt("IDSeance");//on recupére l'id de la seance 
                    //on verifie si l'une des seance de la salle est sur le meme créneau que le créneau de deplacement
                    query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `Semaine`='"+s+"' AND `jour`='"+j+"' AND `HDebut`='"+h+"'";//requete
                    st=cnx.createStatement();
                    rst3=st.executeQuery(query);//executer la requete 
                    if(rst3.last())//si le resultat existe 
                    {
                        n=1;//n prend la valeur 1
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return n
    }
    public static int verifenewsalle(int s,String j,String h,String nom)
    {
         int n=0;//n prend la valeur 0 par defaut
        try
        {
            cnx=connecterDB();//on commence par identifié la salle 
            String query="SELECT * FROM `salle` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int g=rst.getInt("IDSalle");//on recupére l'id de la salle 
            query="SELECT * FROM `seances` WHERE `IDSalle`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            ResultSet rst2;int temp=0;
            while(rst.next())//on parcour les resulat
            {
                temp=rst.getInt("IDSeance");//on recupére l'id de la seance 
                //on verifie toutes les seance de la salle
                query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `Semaine`='"+s+"' AND `jour`='"+j+"' AND `HDebut`='"+h+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())//si le resultat existe 
                {
                    n=1;//n prend la valeur 1
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return n
    }
    /**
     * cette mehode modifie la date d'une seance 
     */
    public static void updatedate(int id,int s,String j,String h)
    {
        try
        {
            cnx=connecterDB();
            String query="UPDATE `seance` SET `Semaine`='"+s+"',`jour`='"+j+"',`HDebut`='"+h+"'WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
    //cette methode verifie si un groupe est deja affecter a une seance
    public static int verifeexistancegroupe(int id,String nom)
    {
         int n=0;//n prend la valeur 0 par defaut
        try
        {
            cnx=connecterDB();//on commence par identifié le groupe 
            String query="SELECT * FROM `groupe` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int temp=rst.getInt("IDGroupe");//on recupére l'id du groupe
            //on verifie si le groupe est affecter a la seance
             query="SELECT * FROM `seanceg` WHERE `IDSeance`='"+id+"' AND `IDGroupe`='"+temp+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            if(rst.last())//si le resultat existe 
            {
                n=1;//n prend la valeur 1
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return n
    }
    /**
     * cette methode verifie si un proffesseur est deja affecter a une seance
     */
    public static int verifeexistanceprof(int id,String nom)
    {
         int n=0;//n prend la valeur 0 par defaut
        try
        {
            cnx=connecterDB();//on commence par identifié le proffesseur
            String query="SELECT * FROM `utilisateur` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int temp=rst.getInt("IDUtilisateur");//on recupére l'id du proffesseur
            //on verifie si le proffesseur est affecter a la seance
            query="SELECT * FROM `seancee` WHERE `IDSeance`='"+id+"' AND `IDUtilisateur`='"+temp+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            if(rst.last())//si le resultat existe 
            {
                n=1;//n prend la valeur 1
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return n
    }
    /**
     * cette methode verifie si une salle est deja affecter a une seance
     */
    public static int verifeexistancesalle(int id,String nom)
    {
         int n=0;//n prend la valeur 0 par defaut
        try
        {
            cnx=connecterDB();//on commence par identifié la salle
            String query="SELECT * FROM `salle` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int temp=rst.getInt("IDSalle");//on recupére l'id de la salle
            //on verifie si la salle est affecter a la seance
            query="SELECT * FROM `seances` WHERE `IDSeance`='"+id+"' AND `IDSalle`='"+temp+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            if(rst.last())//si le resultat existe 
            {
                n=1;//n prend la valeur 1
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return n
    }
    /**
     * cette methode compte le nombre de roupe (ou de proffesseur ou de salle) deja affecter a une seance 
     */
    public static int blindagenb(int id,String type)
    {
         int n=0;//n prend la valeur 0 par defaut
        try
        {
            cnx=connecterDB();//on ecrit une requete qui va retourner tout les element assigner a une seance donner 
             String query="SELECT * FROM `"+type+"` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            while(rst.next())//on parcourt les resultat
            {
                n+=1;//n prend la valeur n+1
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on return n
    }
    /**
     * cette methode assigne un groupe a une seance 
     */
    public static void insertgroupe(int id,String nom)
    {
        try
        {
            cnx=connecterDB();//on commence par identifié le groupe
            String query="SELECT * FROM `groupe` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int temp=rst.getInt("IDGroupe");//on recupere l'id du groupe 
            //on insert un groupe dans la seance 
            query="INSERT INTO `seanceg`(`IDSeance`, `IDGroupe`) VALUES ('"+id+"','"+temp+"')";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
    /**
     * cette methode assigne un proffesseur a une seance 
     */
    public static void insertprof(int id,String nom)
    {
        try
        {
            cnx=connecterDB();//on commence par identifié le proffesseur
            String query="SELECT * FROM `utilisateur` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int temp=rst.getInt("IDUtilisateur");//on recupere l'id du proffesseur 
            //on insert un proffesseur dans la seance 
            query="INSERT INTO `seancee`(`IDSeance`, `IDUtilisateur`) VALUES ('"+id+"','"+temp+"')";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
    /**
     * cette methode assigne une salle a une seance 
     */
    public static void insertsalle(int id,String nom)
    {
        try
        {
            cnx=connecterDB();//on commence par identifié la salles
            String query="SELECT * FROM `salle` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int temp=rst.getInt("IDSalle");//on recupere l'id de la salle 
            //on insert la salle dans la seance 
            query="INSERT INTO `seances`(`IDSeance`, `IDSalle`) VALUES ('"+id+"','"+temp+"')";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
    /**
     * cette methode supprime une salle d'une seance 
     */
    public static void supprimersalle(int id,String nom)
    {
        try
        {
            cnx=connecterDB();//on commence par identifié la salles
            String query="SELECT * FROM `salle` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int s=rst.getInt("IDSalle");//on recupere l'id de la salle 
            //on supprime la salle dans la seance 
            query="DELETE FROM `seances` WHERE `IDSeance`='"+id+"' AND `IDSalle`='"+s+"'";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
    /**
     * cette methode supprime un proffesseur d'une seance 
     */
    public static void supprimerprof(int id,String nom)
    {
        try
        {
            cnx=connecterDB();//on commence par identifié le proffesseur
             String query="SELECT * FROM `utilisateur` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int s=rst.getInt("IDUtilisateur");//on recupere l'id du proffesseur 
            //on supprime le proffesseur dans la seance 
            query="DELETE FROM `seancee` WHERE `IDSeance`='"+id+"' AND `IDUtilisateur`='"+s+"'";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
    /**
     * cette methode supprime un groupe d'une seance 
     */
    public static void supprimergroupe(int id,String nom)
    {
        try
        {
            cnx=connecterDB();//on commence par identifié le groupe
             String query="SELECT * FROM `groupe` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int s=rst.getInt("IDGroupe");//on recupere l'id du groupe
            //on supprime le groupe dans la seance 
            query="DELETE FROM `seanceg` WHERE `IDSeance`='"+id+"' AND `IDGroupe`='"+s+"'";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
    /**
     * Cette methode permet de modifier le nom d'un cours ou le nom d'un type de cours 
     */
     public static void updatnom(String nouveau,String ancien,String type)
    {
        try
        {
            cnx=connecterDB();
            String query="UPDATE `"+type+"` SET `Nom`='"+nouveau+"'WHERE `Nom`='"+ancien+"'";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
     /**
      * Cette methode permet a partir du nom d'un cours d'avoir le nom du proffesseur qui donne ce cours 
      */
     public static String getprof2(String nom)
    {
         String n="";//on initialise n
        try
        {
            cnx=connecterDB();//on commence par identifié le cours 
            String query="SELECT * FROM `cours` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int c=rst.getInt("IDCours");//on recpere l'id du cours 
            //on identifie le proffesseur 
            query="SELECT * FROM `enseignant` WHERE `IDCours`='"+c+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);
            rst.last();
            c=rst.getInt("IDUtilisateur");//on recpere l'id du proffesseur
            query="SELECT * FROM `utilisateur` WHERE `IDUtilisateur`='"+c+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            n=rst.getString("Nom");//on recpere le nom du proffesseur
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on rturn n
    }
     /**
      * cette methode permet de verifié que un cours et un groupe sont assigné a la meme promotion
      */
     public static int verifpromo(String nomg,String nomc)
    {
        int n=0;//on initialise n 0 par defaut
        try
        {
            cnx=connecterDB();//on commence par identifié la promo a laquelle appartient le cours 
            String query="SELECT * FROM `cours` WHERE `Nom`='"+nomc+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int p1=rst.getInt("IDPromo");//on recupere l'id de la promo
            //on identifie la promo a laquelle appartient le groupe 
            query="SELECT * FROM `groupe` WHERE `Nom`='"+nomg+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int p2=rst.getInt("IDPromo");//on recupere l'id de la promo
            if(p1!=p2)//si les promo sont différente 
            {
                n=1;//n prend la valeur 1
            }
            if(p1==4)//uniquement pour l'anglais 
            {
                n=0;//n prend la valeur 0
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourne n
    }
     /**
      * cette methode sert a inserer une nouvelle seance
      */
     public static void insertseance(int se,String c,String g,String s,String h,String h2,String j)
    {
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `salle` WHERE `Nom`='"+s+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int ids=rst.getInt("IDSalle");//on recupere l'id de la salle
            query="SELECT * FROM `groupe` WHERE `Nom`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int idg=rst.getInt("IDGroupe");//on recupere l'id du groupe
            query="SELECT * FROM `cours` WHERE `Nom`='"+c+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int idc=rst.getInt("IDCours");//on recupere l'id du cours 
            int idt=rst.getInt("IDType");//on recupere l'id du type de cours 
            query="SELECT * FROM `enseignant` WHERE `IDCours`='"+idc+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int idu=rst.getInt("IDUtilisateur");//on recupere l'id du proffesseur
            query="INSERT INTO `seance`(`IDSeance`, `Semaine`, `jour`, `HDebut`, `HFin`, `Etat`, `IDCours`, `IDType`) VALUES (NULL,'"+se+"','"+j+"','"+h+"','"+h2+"','0','"+idc+"','"+idt+"')";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);
             query="SELECT * FROM `seance` WHERE 1";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int id=rst.getRow();//on recupere l'id de la seance que l'on vient d'insérer
            query="INSERT INTO `seancee` (`IDSeance`, `IDUtilisateur`) VALUES ('"+id+"', '"+idu+"')";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
            query="INSERT INTO `seanceg` (`IDSeance`, `IDGroupe`) VALUES ('"+id+"', '"+idg+"')";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
            query="INSERT INTO `seances` (`IDSeance`, `IDSalle`) VALUES ('"+id+"', '"+ids+"')";//requete
            st=cnx.createStatement();
            st.executeUpdate(query);//executer la requete 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
     /**
      * cette methode permet de verifié si la capacité d'aceuil d'une ou plusieur salle est necessaire pour acceuillir les groupe 
      */
     public static int blindagecapacite(int id,String nom)
    {
         int n=0,c=0,v=0;//on inisialise toutes les varibles a 0
        try
        {
            cnx=connecterDB();//oncommence par compté le nombre d'etudiant dans le groupe deja present dans la seance
            String query="SELECT * FROM `groupe` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int g=rst.getInt("IDGroupe");//on recupere l'id du groupe
            query="SELECT * FROM `etudiant` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                n+=1;//n est le nombre total d'etudiant a placer dans une salles 
            }
            //on compte ensuite le nombre d'etudiant dans le groupe que l'on veut ajouter dans la seance
            query="SELECT * FROM `seanceg` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            g=rst.getInt("IDGroupe");//on recupere l'id du groupe 
            query="SELECT * FROM `etudiant` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                n+=1;//n est le nombre total d'etudiant a placer dans une salles 
            }
            //on compte ensuite la capacité totale des salles
            query="SELECT * FROM `seances` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            ResultSet rst2;
            while(rst.next())//on parcours les resultat
            {
                int s=rst.getInt("IDSalle");//on recupere l'id de la salle  
                query="SELECT * FROM `salle` WHERE `IDSalle`='"+s+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                rst2.last();
                c+=rst2.getInt("Capacite");//on recupere la capacité de la salle et on l'additionne a la capacité total des salles
            }
            if(n>c)//si la capacité total est insuffisante
            {
                v=1;//v prend la valeur 1
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return v;//on retourn v
    }
     /**
      * Cette methode permet de verifier que le groupe que l'on ajoute a une seance appartient a la meme promo que le groupe qui est deja assigné a cette seance
      */
     public static int verifpromoajout(String nomg,int id)
    {
        int n=0;//on intitialise n a 0 par default 
        try
        {
            cnx=connecterDB();//on commence par identifié la promo a laquelle appartient le nouveau groupe
            String query="SELECT * FROM `groupe` WHERE `Nom`='"+nomg+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete
            rst.last();
            int p1=rst.getInt("IDPromo");//on recupere l'id de la promo  
            //on cherche ensuite la prmo du groupe qui est deja assigné a la seance 
            query="SELECT * FROM `seanceg` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete
            rst.last();
            int g=rst.getInt("IDGroupe");//on recupere l'id du groupe  
            query="SELECT * FROM `groupe` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete
            rst.last();
            int p2=rst.getInt("IDPromo");//on recupere l'id de la promo  
            if(p1!=p2)//si les deux promo ne sont pas les meme
            {
                n=1;//n prend la valeur 1
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourne n
    }
     /**
      * Cette methode permet de verifie si la capacité sera toujours sufisante si on en enleve une autre 
      */
     public static int blindagecapacite2(int id,String nom)
    {
        int n=0,c=0,v=0;//on initialise toutes les variables a 0
        try
        {
            cnx=connecterDB();//on commence par compter le nommbre total d'etudiant 
            String query="SELECT * FROM `seanceg` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);;//executer la requete
            rst.beforeFirst();ResultSet rst2;
            while(rst.next())//on parcours les resultat
            {
                int g=rst.getInt("IDGroupe");//on recupere l'id du groupe  
                query="SELECT * FROM `etudiant` WHERE `IDGroupe`='"+g+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);;//executer la requete
                rst2.beforeFirst();
                while(rst2.next())//on parcours les resultat
                { 
                    n+=1;//n prend la valeur n+1
                }
            }
            String n2;//on selectionne ensuite les salle assigné a la seance
            query="SELECT * FROM `seances` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);;//executer la requete
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                int s=rst.getInt("IDSalle");//on recupere l'id de la salle  
                query="SELECT * FROM `salle` WHERE `IDSalle`='"+s+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);;//executer la requete
                rst2.last();
                n2=rst2.getString("Nom");//on recupere le nom de la salle  
                if(!nom.equals(n2))//si le nom est different de la salle a retiré 
                {
                    c+=rst2.getInt("Capacite");//on recupere la capacité de la salle et on l'additionne a la capacité total des salles
                }
            }
            if(n>c)//si la capacité est inferieur au nombre d'etudiant 
            {
                v=1;//v prend la valeur 1
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return v;//on retourn v
    }
     /**
      * cette methode permet de compté le nombre de seance assigné a un groupe
      */
     public static int comptegroupe(String nomg)
    {
         int n=0;//on initialise a 0 par defaut
        try
        {
            cnx=connecterDB();//on commence par identifié le groupe 
            String query="SELECT * FROM `groupe` WHERE `Nom`='"+nomg+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete
            rst.last();
            int g=rst.getInt("IDGroupe");//on recupere l'id du groupe  
            //on compte ensuite le nombre de seance du groupe
            query="SELECT * FROM `seanceg` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                n+=1;//n prend la valeur n+1
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn n
    }
     /**
      * cette methode permet de compté le nombre de seance assigné a un proffesseur
      */
     public static int compteprof(String nomp)
    {
         int n=0;//on initialise a 0 par defaut
        try
        {
            cnx=connecterDB();//on commence par identifié le proffesseur 
            String query="SELECT * FROM `utilisateur` WHERE `Nom`='"+nomp+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete
            rst.last();
            int g=rst.getInt("IDUtilisateur");//on recupere l'id du proffesseur
            query="SELECT * FROM `seancee` WHERE `IDUtilisateur`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                n+=1;//n prend la valeur n+1
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn n
    }
     /**
      * cette methode permet de compté le nombre de seance assigné a une salle
      */
     public static int comptesalle(String noms)
    {
         int n=0;//on initialise a 0 par defaut
        try
        {
            cnx=connecterDB();//on commence par identifié la salle 
            String query="SELECT * FROM `salle` WHERE `Nom`='"+noms+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete
            rst.last();
            int g=rst.getInt("IDSalle");//on recupere l'id de la salle
            query="SELECT * FROM `seances` WHERE `IDSalle`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                n+=1;//n prend la valeur n+1
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn n
    }
}
