package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import static model.Connexion.connecterDB;
/**
 * cette classe fait partie du model
  *elle regroupe toutes les methodes de recherche sur la BDD pour les utilisateur
    */
public class DBRecherche 
{
    private static Connection cnx;
    private static Statement st;
    private static ResultSet rst;
    /**
     * Cette methode sert a rechercher un utilisateur en fonction de son email et de son MdP
     */
    public static int Researchlog(String login,String password)
    {
        int n=0;//on initialise n à 0
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `utilisateur` WHERE `Email`='"+login+"' AND `MdP`='"+password+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            n=rst.getInt("IDUtilisateur");//recupérer l'id de l'utilisateur
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn n
    }
    /**
     * cette methode permet de recuperer les droit d'un utilisateur
     */
    public static int getdroit(int id)
    {
         int n=0;//on initialise n à 0
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `utilisateur` WHERE `IDUtilisateur`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
             n=rst.getInt("Droit");//recupérer les droit
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn n
    }
    /**
     * cette methode permet de recupérer n'importe quelle string de la table utilisateur en fonction de l'id
     */
    public static String getstring(int id,String info)
    {
         String n="";
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `utilisateur` WHERE `IDUtilisateur`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
             n=rst.getString(info);//recupérer l'info souhaiter
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//retourner l'info
    }
    /**
     * Cette methode permet de recupérer toute les seance pour un utilisateur donné sur une semaine donné 
     */
    public static int[] getsceance(int id,int ids)
    {
        int [] n = {-1};
        try
        {
            cnx=connecterDB();//on identifie le grouoe de l'utilisateur 
            String query="SELECT * FROM `etudiant` WHERE `IDUtilisateur`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            if(rst.last())
            {int g =rst.getInt("IDGroupe");//on recupere l'id du groupe
            //on compte le nombre de seance
            query="SELECT * FROM `seanceg` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            int nb=0;
            rst.beforeFirst();
            int temp=0;
            ResultSet rst2;
            int j=0;
            while(rst.next())//on parcours les resultat
            {
                temp=rst.getInt("IDSeance");
                query="SELECT * FROM `seance` WHERE `Semaine`='"+ids+"'AND `IDSeance`='"+temp+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    nb=nb+1;//on compte les seance 
                }
                j+=1;
            }
            if(nb!=0)
            {n=new int[nb];//on initialise le tableau 
            temp=0;
            rst.first();
            int h=0;
            for(int i=0;i<j;i++)
            {
                temp=rst.getInt("IDSeance");
                query="SELECT * FROM `seance` WHERE `Semaine`='"+ids+"'AND `IDSeance`='"+temp+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    n[h]=temp;//stock les id des seance 
                    h=h+1;
                }
                rst.next();
            }}}
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourne les resultat
    }
    /**
     * Cette methode permet de recupérer toute les seance pour un proffesseur donné sur une semaine donné
     */
    public static int[] getsceanceprof(int id,int ids)
        {
            int [] n = {-1};
            try
            {
                cnx=connecterDB();
                String query="SELECT * FROM `seancee` WHERE `IDUtilisateur`='"+id+"'";//requete
                st=cnx.createStatement();
                rst=st.executeQuery(query);//executer la requete 
                rst.beforeFirst();
                int nb=0,j=0;
                int temp=0;
                ResultSet rst2;
                while(rst.next())//on parcours les resultat
                {
                    temp=rst.getInt("IDSeance");//on recupere l'id de la seance
                    query="SELECT * FROM `seance` WHERE `Semaine`='"+ids+"'AND `IDSeance`='"+temp+"'";//requete
                    st=cnx.createStatement();
                    rst2=st.executeQuery(query);//executer la requete 
                    if(rst2.last())
                    {
                        nb=nb+1;//on compte les seance 
                    }
                    j+=1;
                }
                if(nb!=0)
                {n=new int[nb];//on initialise le tableau 
                temp=0;
                rst.first();
                int g=0;
                for(int i=0;i<j;i++)
                {
                    temp=rst.getInt("IDSeance");//on recupere l'id de la seance
                    query="SELECT * FROM `seance` WHERE `Semaine`='"+ids+"'AND `IDSeance`='"+temp+"'";//requete
                    st=cnx.createStatement();
                    rst2=st.executeQuery(query);//executer la requete 
                    if(rst2.last())
                    {
                        n[g]=temp;//stock les id des seance 
                        g+=1;
                    }
                    rst.next();
                }}
            }
            catch(SQLException e)
            {
                System.out.print(e.getMessage());
                return n;
            }
            return n;//on retourne les resultat
        }
    /**
     * Cette methode permet de recupérer toute les seance pour une salle donné sur une semaine donné
     */
    public static int[] getsceancesalle(int id,int ids)
        {
             int [] n = {-1};
            try
            {
                cnx=connecterDB();
                String query="SELECT * FROM `seances` WHERE `IDSalle`='"+id+"'";//requete
                st=cnx.createStatement();
                rst=st.executeQuery(query);//executer la requete 
                rst.beforeFirst();
                int nb=0,j=0;
                int temp=0;
                ResultSet rst2;
                while(rst.next())//on parcours les resultat
                {
                    temp=rst.getInt("IDSeance");//on recupere l'id de la seance
                    query="SELECT * FROM `seance` WHERE `Semaine`='"+ids+"'AND `IDSeance`='"+temp+"'";//requete
                    st=cnx.createStatement();
                    rst2=st.executeQuery(query);//executer la requete 
                    if(rst2.last())
                    {
                        nb=nb+1;//on compte les seance 
                    }
                    j+=1;
                }
                if(nb!=0)
                {n=new int[nb];//on initialise le tableau 
                temp=0;
                rst.first();
                int g=0;
                for(int i=0;i<j;i++)
                {
                    temp=rst.getInt("IDSeance");//on recupere l'id de la seance
                    query="SELECT * FROM `seance` WHERE `Semaine`='"+ids+"'AND `IDSeance`='"+temp+"'";//requete
                    st=cnx.createStatement();
                    rst2=st.executeQuery(query);//executer la requete 
                    if(rst2.last())
                    {
                        n[g]=temp;//stock les id des seance 
                        g+=1;
                    }
                    rst.next();
                }}
            }
            catch(SQLException e)
            {
                System.out.print(e.getMessage());
            }
            return n;//on retourne les resultat
        }
    /**
     * cette methode retourne le jour d'une seance donnée
     */
    public static String getjour(int id)
    {
         String n="";
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `seance` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
             n=rst.getString("jour");//on recupere le jour 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn l'information
    }
    /**
     * cette methode retourne le type de cours d'une seance donnée
     */
    public static int gettypecour(int id)
    {
         int n=0;//oninitialise n a 0
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `seance` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
             n=rst.getInt("IDType");//on recupere le type
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn l'information
    }
    /**
     * cette methode permet d'obtenir l'heure d(='une seance donné
     */
    public static String getheur(int id,String H)
    {
         String n="";
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `seance` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            Time h=rst.getTime(H);//on recupere l'heure
            n=h.toString();//on transforme l'info en string
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn l'information
    }
    /**
     * cette methode permet d'obtenir le nom du cours 
     */
    public static String getnomcour(int id)
    {
         String n="";
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `seance` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int c=rst.getInt("IDCours");//on recupere le cours
            query="SELECT * FROM `cours` WHERE `IDCours`='"+c+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            n=rst.getString("Nom");//on recupere le noms du cours
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn l'information
    }
    /**
     * cette methode retourn tout les nom de groupe sur une seance
     */
    public static ArrayList<String> getnomgroupe(int id)
    {
         ArrayList<String> n=new ArrayList<>();
        try
        {
            cnx=connecterDB();//on commence par trouver tout les groupes sur une seance donné
            String query="SELECT * FROM `seanceg` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            ResultSet rst2;
            while(rst.next())//on parcourt les resultat
            {
                int c=rst.getInt("IDGroupe");//on recupere l'id du groupe
                query="SELECT * FROM `groupe` WHERE `IDGroupe`='"+c+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {n.add(rst2.getString("Nom"));}//on ajoute le resultat a la liste
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn la liste
    }
    /**
     * cette methode retourn tout les nom de proffesseur sur une seance
     */
    public static ArrayList<String> getnomprof(int id)
    {
         ArrayList<String> n=new ArrayList<>();
        try
        {
            cnx=connecterDB();//on commence par trouver tout les proffesseures sur une seance donné
            String query="SELECT * FROM `seancee` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            ResultSet rst2;
            while(rst.next())//on parcourt les resultat
            {
                int c=rst.getInt("IDUtilisateur");//on recupere l'id de l'utilisateur
                query="SELECT * FROM `utilisateur` WHERE `IDUtilisateur`='"+c+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    n.add(rst2.getString("Nom"));//on ajoute le resultat a la liste
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn la liste
    }
    /**
     * cette methode retourn tout les nom de salle et de site sur une seance
     */
    public static ArrayList<String> getnomsalle(int id)
    {
         ArrayList<String> n=new ArrayList<>();
        try
        {
            cnx=connecterDB();//on commence par trouver toutes les salles sur une seance donné
            String query="SELECT * FROM `seances` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            ResultSet rst2;String a;
            while(rst.next())//on parcourt les resultat
            {
                int c=rst.getInt("IDSalle");//on recupere l'id de la salle
                query="SELECT * FROM `salle` WHERE `IDSalle`='"+c+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    //on recupere le nom de la salle et le nom du site
                    a=rst2.getString("Nom")+" - ";//on recupere le nom de la salle
                    int s=rst2.getInt("IDSite");//on recupere l'id du site
                    query="SELECT * FROM `site` WHERE `IDSite`='"+s+"'";//requete
                    st=cnx.createStatement();
                    rst2=st.executeQuery(query);//executer la requete 
                    rst2.last();
                    a+=rst2.getString("Nom");//on recupere le nom du site
                    n.add(a); //on stock le resultat
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn la liste
    }
    /**
     * cette methode retourn tout les nom de salle sur une seance
     */
    public static ArrayList<String> getnomsalle2(int id)
    {
         ArrayList<String> n=new ArrayList<>();
        try
        {
            cnx=connecterDB();//on commence par trouver toutes les salles sur une seance donné
            String query="SELECT * FROM `seances` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            ResultSet rst2;String a;
            while(rst.next())//on parcourt les resultat
            {
                int c=rst.getInt("IDSalle");//on recupere l'id de la salle
                query="SELECT * FROM `salle` WHERE `IDSalle`='"+c+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    a=rst2.getString("Nom");//on recupere le nom de la salle
                    n.add(a); //on stock le resultat
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn la liste
    }
    /**
     * cette methode trie un tablea pour ne retourner que les seance qui ont lieux le meme jour de la semaine 
     */
    public static int[] getnbcour(int [] id,String jour)
    {
        int []n={0};
        int j=0;
        try
        {
            cnx=connecterDB();//on commence par compter le nombre de seance pour initialiser le tableau
            for(int i=0;i<id.length;i++)
            {
                String query="SELECT * FROM `seance` WHERE `IDSeance`='"+id[i]+"'AND `jour`='"+jour+"'";//requete
                st=cnx.createStatement();
                rst=st.executeQuery(query);//executer la requete 
                rst.beforeFirst();
                while(rst.next())//on parcourt les resultat
                {j=j+1;}//on compte le nombre de seance sur le meme jour 
            }
            n=new int[j];//on initialise le tableau 
            int g=0;
            for(int i=0;i<id.length;i++)
            {
                String query="SELECT * FROM `seance` WHERE `IDSeance`='"+id[i]+"'AND `jour`='"+jour+"'";//requete
                st=cnx.createStatement();
                rst=st.executeQuery(query);//executer la requete 
                if(rst.last())
                {
                    n[g]=id[i];//on stock les reultat 
                    g+=1;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn les resultat
    }
    /**
     * cette methode retourn la promo d'un groupe
     */
    public static int getpromo(int id)
    {
         int n=0;
        try
        {
            cnx=connecterDB();//on commence par identifié le groupe
            String query="SELECT * FROM `seanceg` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
             int  g=rst.getInt("IDGroupe");//obtenir l'id du groupe
            query="SELECT * FROM `groupe` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
             n=rst.getInt("IDPromo");//obteenir l'id de la promo
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//retourner le resultat
    }
    /**
     * obtenir toutes les seances d'un groupe 
     */
    public static ArrayList<String> getttseance(int id)
    {
         ArrayList<String>n=new ArrayList<>();
        try
        {
            cnx=connecterDB();//on commence par identifié le groupe
            String query="SELECT * FROM `etudiant` WHERE `IDUtilisateur`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int g =rst.getInt("IDGroupe");//obtenir l'id du groupe
            query="SELECT * FROM `seanceg` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            int nb=0;
            rst.beforeFirst();
            int temp=0;
            ResultSet rst2,rst3;
            int j=0;String cour,courst;
            while(rst.next())//on parcours les resultat
            {
                temp=rst.getInt("IDSeance");//obtenir l'id de la seance
                query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    j=0;
                    nb=rst2.getInt("IDCours");//obtenir l'id du cours
                    query="SELECT * FROM `cours` WHERE `IDCours`='"+nb+"'";//requete
                    st=cnx.createStatement();
                    rst3=st.executeQuery(query);//executer la requete 
                    rst3.last();
                    cour=rst3.getString("Nom");
                    for(int i=0;i<n.size();i++)//on blinde pour ne pas avoir deux fois le meme cours
                    {
                        courst=n.get(i);
                        if(cour.equals(courst))
                        {
                            j=1;//indique que le cours est deja présent dans la liste
                        }
                    }
                    if(j==0)
                    {
                        n.add(cour);//on ajoute les resultat unique 
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//retourner la liste
    }
    /**
     * obtenir toutes les seances d'un proffesseur
     */
    public static ArrayList<String> getttseanceprof(int id)
    {
         ArrayList<String>n=new ArrayList<>();
         ArrayList<String>n2=new ArrayList<>();
        try
        {
            cnx=connecterDB();//on commence par identifié toutes les seance du proffesseur
            String query="SELECT * FROM `enseignant` WHERE `IDUtilisateur`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            int temp=0;
            ResultSet rst2;
            int j=0;String cour,courst;
            while(rst.next())//on parcours les resultat
            {
                j=0;
                temp=rst.getInt("IDCours");//obtenir l'id du cours
                query="SELECT * FROM `cours` WHERE `IDCours`='"+temp+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    cour=rst2.getString("Nom");//on blinde pour ne pas avoir deux fois le meme cours
                    for(int i=0;i<n.size();i++)
                    {
                        courst=n.get(i);
                        if(cour.equals(courst))
                        {
                            j=1;//indique que le cours est deja présent dans la liste
                        }
                    }
                    if(j==0)
                    {
                        n.add(cour);//on ajoute les resultat unique 
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//retourner la liste
    }
    /**
     * cette methode permet d'itentifié tout les groupe inscrit a un cours 
     */
    public static ArrayList<String> getnbgroupe(String nom)
    {
         ArrayList<String>n=new ArrayList<>();
        try
        {
            String groupe,groupet;
            cnx=connecterDB();//on commence par identifié le cours 
            String query="SELECT * FROM `cours` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int c=rst.getInt("IDCours");//obtenir l'id du cours
            query="SELECT * FROM `seance` WHERE `IDCours`='"+c+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            int temp;
            ResultSet rst2,rst3;int j=0;
            //on identifie les seance
            while(rst.next())//on parcours les resultat
            {
                temp=rst.getInt("IDSeance");//obtenir l'id de la seance
                query="SELECT * FROM `seanceg` WHERE `IDSeance`='"+temp+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                rst2.beforeFirst();
                while(rst2.next())//on parcours les resultat
                {
                    j=0;
                    //on identifie les groupes
                    int g=rst2.getInt("IDGroupe");//obtenir l'id du groupe
                    query="SELECT * FROM `groupe` WHERE `IDGroupe`='"+g+"'";//requete
                    st=cnx.createStatement();
                    rst3=st.executeQuery(query);//executer la requete 
                    rst3.last();
                    groupe=rst3.getString("Nom");//obtenir le nom
                    for(int i=0;i<n.size();i++)//on blinde pour ne pas avoir deux fois le meme groupe
                    {
                        groupet=n.get(i);
                        if(groupe.equals(groupet))
                        {
                            j=1;//indique que le cours est deja présent dans la liste
                        }
                    }
                    if(j==0)
                    {
                        n.add(groupe);//on ajoute les resultat unique
                        
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//retourner la liste
    }
    /**
     * cette methode permet de recupérer le nom d'un proffesseur en fonction du nom d'un cours 
     */
    public static String getprof(String nom)
    {
         String n="";
        try
        {
            cnx=connecterDB();//on identifie le cours 
            String query="SELECT * FROM `cours` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int c=rst.getInt("IDCours");//obtenir l'id du cours
            //on identifie le proffesseur
            query="SELECT * FROM `enseignant` WHERE `IDCours`='"+c+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            c=rst.getInt("IDUtilisateur");//obtenir l'id du proffesseur
            query="SELECT * FROM `utilisateur` WHERE `IDUtilisateur`='"+c+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            n=rst.getString("Prenom")+"  "+rst.getString("Nom");//obtenir le nom et le prenom du proffesseurs
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn le resultat
    }
    /**
     * cette methode permet de recuperer le premier jour d'une seance pour un groupe ou un enseignant 
     */
    public static int getpremierjour(String nom,int ide,int d,String nomg)
    {
         int n=0;
        try
        {
            int g=0;
            cnx=connecterDB();//on commence par identifié le cours
            String query="SELECT * FROM `cours` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int c=rst.getInt("IDCours");//obtenir l'id du cours
            //on identifie le groupe
            if(d==0)//si c'est un etudiant
            {
                query="SELECT * FROM `etudiant` WHERE `IDUtilisateur`='"+ide+"'";//requete
                st=cnx.createStatement();
                rst=st.executeQuery(query);//executer la requete 
                rst.last();
                g=rst.getInt("IDGroupe");//obtenir l'id du groupe
            }
            if(d==1)//si c'es un proffesseur
            {
                query="SELECT * FROM `groupe` WHERE `Nom`='"+nomg+"'";//requete
                st=cnx.createStatement();
                rst=st.executeQuery(query);//executer la requete 
                rst.last();
                g=rst.getInt("IDGroupe");//obtenir l'id du groupe
            }
            //on parcours toutes les seances
            query="SELECT * FROM `seanceg` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            int nb=53;
            rst.beforeFirst();
            int temp=0;
            ResultSet rst2,rst3;
            int j=0;String jour,jourt="sam"; int j2=6;
            Time h,h2=new Time(18,0,0);
            //on commence par chercher le cours avec la plus petite semaine
            while(rst.next())//on parcours les resultat
            {
                temp=rst.getInt("IDSeance");//obtenir l'id de la seance 
                query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `IDCours`='"+c+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    j=rst2.getInt("Semaine");//on cherche la plus petite semaine
                    if(j<nb)
                    {
                        nb=j;
                    }
                }
            }
            //on cherche ensuite le jour le plus tot dans la semaine 
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                temp=rst.getInt("IDSeance");//obtenir l'id de la seance 
                query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `IDCours`='"+c+"'AND `Semaine`='"+nb+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    jour=rst2.getString("jour");
                    if("lun".equals(jour)){j=1;}if("mar".equals(jour)){j=2;}if("mer".equals(jour)){j=3;}if("jed".equals(jour)){j=4;}if("ven".equals(jour)){j=5;}
                    if(j<j2)//on cherche la plus petit jour
                    {
                        j2=j;
                        jourt=jour;
                    }
                }
            }
            //on cherche ensuite l'heure la plus petite du jour 
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                temp=rst.getInt("IDSeance");//obtenir l'id de la seance 
                query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `IDCours`='"+c+"' AND `Semaine`='"+nb+"' AND `jour`='"+jourt+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    h=rst2.getTime("HDebut");
                    if(h.getHours()<h2.getHours())//on cherche la plus petite heure
                    {
                        h2=h;
                        n=temp;
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn le resultat
    }
    /**
     * cette methode permet de recuperer le dernier jour d'une seance pour un groupe ou un enseignant 
     */
    public static int getdernierjour(String nom,int ide,int d,String nomg)
    {
         int n=0;
        try
        {
            int g=0;
            cnx=connecterDB();//on commence par identifié le cours
            String query="SELECT * FROM `cours` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int c=rst.getInt("IDCours");//obtenir l'id du cours
            //on identifie le groupe
            if(d==0)//si c'est un etudiant
            {
                query="SELECT * FROM `etudiant` WHERE `IDUtilisateur`='"+ide+"'";//requete
                st=cnx.createStatement();
                rst=st.executeQuery(query);//executer la requete 
                rst.last();
                g=rst.getInt("IDGroupe");//obtenir l'id du groupe
            }
            if(d==1)//si c'est un proffesseur
            {
                query="SELECT * FROM `groupe` WHERE `Nom`='"+nomg+"'";//requete
                st=cnx.createStatement();
                rst=st.executeQuery(query);//executer la requete 
                rst.last();
                g=rst.getInt("IDGroupe");//obtenir l'id du groupe
            }
            //on parcours toutes les seances
            query="SELECT * FROM `seanceg` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            int nb=0;
            rst.beforeFirst();
            int temp=0;
            ResultSet rst2,rst3;
            int j=0;String jour,jourt="sam"; int j2=0;
            Time h,h2=new Time(7,0,0);
            //on commence par chercher le cours avec la plus grande semaine
            while(rst.next())//on parcours les resultat
            {
                temp=rst.getInt("IDSeance");//obtenir l'id de la seance
                query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `IDCours`='"+c+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    j=rst2.getInt("Semaine");
                    if(j>nb)//on cherche la plus haute semaine
                    {
                        nb=j;
                    }
                }
            }
            //on cherche ensuite le jour le plus tot dans la semaine 
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                temp=rst.getInt("IDSeance");//obtenir l'id de la seance
                query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `IDCours`='"+c+"'AND `Semaine`='"+nb+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    jour=rst2.getString("jour");
                    if("lun".equals(jour)){j=1;}if("mar".equals(jour)){j=2;}if("mer".equals(jour)){j=3;}if("jed".equals(jour)){j=4;}if("ven".equals(jour)){j=5;}
                    if(j>j2)//on cherche la plus haut jour de la semaine
                    {
                        j2=j;
                        jourt=jour;
                    }
                }
            }
            //on cherche ensuite l'heure la plus haute du jour 
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                temp=rst.getInt("IDSeance");//obtenir l'id de la seance
                query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `IDCours`='"+c+"' AND `Semaine`='"+nb+"' AND `jour`='"+jourt+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    h=rst2.getTime("HDebut");
                    if(h.getHours()>h2.getHours())//on cherche la plus haute heure
                    {
                        h2=h;
                        n=temp;
                    }
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourne le resultat
    }
    /**
     * methode qui retourn id d'une semaine en fonction de l'id d'une seance 
     */
    public static int getids(int id)
    {
         int n=0;
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `seance` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            n=rst.getInt("Semaine");//on recupere l'info
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourne le resultat
    }
    /**
     * Cette methode compte le nombre de seance pour un groupe ou pour un proffesseur 
     */
    public static int getnbseance(int ide,String nom,int d,String nomg)
    {
         int n=0;
        try
        {
            String query;int g=0;
            cnx=connecterDB();//on commence par identifié le groupe
            if(d==0)//si c'est un etudiant 
            {
                query="SELECT * FROM `etudiant` WHERE `IDUtilisateur`='"+ide+"'";//requete
                st=cnx.createStatement();
                rst=st.executeQuery(query);//executer la requete 
                rst.last();
                g=rst.getInt("IDGroupe");//on recupere l'id du goupe
            }
            if(d==1)//si c'est un proffesseur 
            {
                query="SELECT * FROM `groupe` WHERE `Nom`='"+nomg+"'";//requete
                st=cnx.createStatement();
                rst=st.executeQuery(query);//executer la requete 
                rst.last();
                g=rst.getInt("IDGroupe");//on recupere l'id du goupe
            }
            //on identifie le cours
            query="SELECT * FROM `cours` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int c=rst.getInt("IDCours");//on recupere l'id du cours
            //on parcours les seance
            query="SELECT * FROM `seanceg` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            int temp;
            ResultSet rst2;
            while(rst.next())//on parcours les resultat
            {
                temp=rst.getInt("IDSeance");//on recupere l'id de la seance
                query="SELECT * FROM `seance` WHERE `IDSeance`='"+temp+"' AND `IDCours`='"+c+"'";//requete
                st=cnx.createStatement();
                rst2=st.executeQuery(query);//executer la requete 
                if(rst2.last())
                {
                    n+=1;//on compte les seance 
                }
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn le resultat
    }
    /**
     * cette methode permet d'obtenir tout les nom des proffesseur 
     */
     public static ArrayList<String> getttnomprof(int droit)
    {
         ArrayList<String>n=new ArrayList<>();
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `utilisateur` WHERE `droit`='"+droit+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                n.add(rst.getString("Nom"));//on ajoute les resulat a la liste 
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn la liste 
    }
     /**
      * cette methode permet d'obtenir tout les nom des groupe 
      */
     public static ArrayList<String> getttnomgroupe()
    {
         ArrayList<String>n=new ArrayList<>();
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `groupe` WHERE 1";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                n.add(rst.getString("Nom"));//on ajoute les resulat a la liste 
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn la liste 
    }
     /**
      * cette methode permet d'obtenir tout les nom de salles 
      */
     public static ArrayList<String> getttnomsalle()
    {
         ArrayList<String>n=new ArrayList<>();
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `salle` WHERE 1";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.beforeFirst();
            while(rst.next())//on parcours les resultat
            {
                n.add(rst.getString("Nom"));//on ajoute les resulat a la liste 
            }
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn la liste 
    }
    /**
     * Cette methode permet d'obtenir l'id d'un etudiant appartenant a un groupe donné 
     */
    public static int getidgroupe(String nom)
    {
         int n=0;
        try
        {
            cnx=connecterDB();//on commence par identifié le groupe 
            String query="SELECT * FROM `groupe` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            int g=rst.getInt("IDGroupe");//on recupere l'id du groupe
            query="SELECT * FROM `etudiant` WHERE `IDGroupe`='"+g+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            n=rst.getInt("IDUtilisateur");//on recupere l'id de l'etudiant 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn le resultat
    }
    /**
     * Cette methode permet d'obtenir l'id d'un proffesseur  
     */
    public static int getidprof(String nom)
    {
         int n=0;
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `utilisateur` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            n=rst.getInt("IDUtilisateur");//on recupere l'id du proffesseur
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn le resultat
    }
    /**
     * Cette methode permet d'obtenir l'id d'unz salle
     */
    public static int getidsalle(String nom)
    {
         int n=0;
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `salle` WHERE `Nom`='"+nom+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            n=rst.getInt("IDSalle");//on recupere l'id de la salle 
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn le resultat
    }
    /**
     * cette methode permet d'obtenir l'etat d'une seance donné 
     */
    public static int getetat(int id)
    {
         int n=0;
        try
        {
            cnx=connecterDB();
            String query="SELECT * FROM `seance` WHERE `IDSeance`='"+id+"'";//requete
            st=cnx.createStatement();
            rst=st.executeQuery(query);//executer la requete 
            rst.last();
            n=rst.getInt("Etat");//on recupere l'etat
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return n;//on retourn le resultat
    }
}