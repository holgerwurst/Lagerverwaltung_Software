/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sqllite;

/**
 *
 * @author Matthias Sommer
 */
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class sample
{
     private Connection conn = null;
    private ResultSet rs;
     private static JTable tabelle = new JTable();
     private static DefaultTableModel myModel;

  public static void db() throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");

    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.
      //statement.executeUpdate("drop table if exists Lagerfachbestand");
      statement.executeUpdate("drop table if exists Lagerfachstamm");
      statement.executeUpdate("drop table if exists Lagerbestandskonto");
      statement.executeUpdate("drop table if exists Teilestammdaten");
      statement.executeUpdate("drop table if exists FreieIDs");
      statement.executeUpdate("drop table if exists Buchungshistorie");
      statement.executeUpdate("create table Lagerfachstamm "
              + "(fachnummer string, lagerort string, "
              + "hochregalnummer string, zeile string,"
              + "spalte string, groesse string, belegt)");
      statement.executeUpdate("create table Lagerbestandskonto "
              + "(fachnummer string, teile_ID integer, "
              + "menge integer, anschaffungsgrund string,"
              + "haltbarkeitsdatum)");
      statement.executeUpdate("create table Teilestammdaten "
              + "(id integer, typ String, zeichnungsnummer string, "
              + "preis double, bezeichnung string, baugruppe string,"
              + "bemerkung string, max_anz_klein integer, max_anz_mittel integer integer,"
              + "max_anz_gross integer)");
      statement.executeUpdate("create table FreieIDs "
              + "(IDs integer)");
      statement.executeUpdate("create table Buchungshistorie "
              + "(datum, uhrzeit, aktion string, "
              + "id integer, fachnummer string,"
              + "alter_bestand integer, neuer_bestand integer, "
              + "altes_fach string, neues_fach string, historieid string)");
      
      
      
      
      
      /*statement.executeUpdate("insert into person values(1, 'leo')");
      statement.executeUpdate("insert into person values(2, 'yui')");
      ResultSet rs = statement.executeQuery("select * from person");
      while(rs.next())
      {
        // read the result set
        System.out.println("name = " + rs.getString("name"));
        System.out.println("id = " + rs.getInt("id"));
      }
       */
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
   createdb();
   System.out.println("fertig");
  }

  public static void dbzwo(String fnr,String lo,String hnr,String zei,String spa,String gr,boolean b) throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");

    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      //statement.executeUpdate("drop table if exists person");
      //statement.executeUpdate("create table person (id integer, name string)");
      statement.executeUpdate("insert into Lagerfachstamm values('"+fnr+"', '"+lo+"', '"+hnr+"', '"+zei+"', '"+spa+"', '"+gr+"', '"+b+"')");
      //statement.executeUpdate("insert into Lagerfachbestand values(fnr)");
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
  }

  public static void createdb()
    {
        for (int a=1;a<=2;a++)
        {
            if(a<=1)
            {
                for (int b=1;b<=4;b++)
                {
                    for(int i =1; i<=25;i++)
                    {

                        numbers("F","H",""+b,"Z",""+i,"S",i);
                    }
                }
            }
            else
            {
            for (int b=1;b<=4;b++)
                {
                    for(int i =1; i<=25;i++)
                    {
                        numbers("L","H",""+b,"Z",""+i,"S",i);
                    }
                }
            }
        }
    }

    public static void numbers(String f, String h, String h2, String z, String z2, String s, int index){
         //String fnr="";
         
                 for(int i=1;i<=25;i++)
                 {
                     if(index<=10)
                     {
                     String hg="";
                     String zg="";
                     String sp="";
                     String fnr="";
                     boolean b=false;
                     hg= h+h2;
                     zg= z+z2;
                     sp= s+i;
                     fnr=f+h+h2+z+z2+s+i+"K";
                     System.out.println(fnr+f+hg+zg+sp+"K"+b);
                     try {dbzwo(fnr,f,hg,zg,sp,"K",b);}
                     catch (ClassNotFoundException ex) {}
                     }
                     else if(index<=20)
                     {
                     String hg="";
                     String zg="";
                     String sp="";
                     String fnr="";
                     boolean b=false;
                     hg= h+h2;
                     zg= z+z2;
                     sp= s+i;
                     fnr=f+h+h2+z+z2+s+i+"M";
                     System.out.println(fnr+f+hg+zg+sp+"M"+b);
                     try {dbzwo(fnr,f,hg,zg,sp,"M",b);}
                     catch (ClassNotFoundException ex) {}
                     }
                     else if(index<=25)
                     {
                     String hg="";
                     String zg="";
                     String sp="";
                     String fnr="";
                     boolean b=false;
                     hg= h+h2;
                     zg= z+z2;
                     sp= s+i;
                     fnr=f+h+h2+z+z2+s+i+"G";
                     System.out.println(fnr+f+hg+zg+sp+"G"+b);
                     try {dbzwo(fnr,f,hg,zg,sp,"G",b);}
                     catch (ClassNotFoundException ex) {}
                     }
                 }
                 
     }

         

      
    

    public static void table(String fnr){
        tabelle = SQLLiteView.jTable1;
        myModel = new javax.swing.table.DefaultTableModel(5000, 2);

         tabelle.setModel(myModel);
         DefaultTableModel deft = (DefaultTableModel) tabelle.getModel();

         //createdb();
            for (int i=1; i<=5000;i++){

            deft.setValueAt(fnr,i-1,0);
        }
}

    public static void show() throws ClassNotFoundException
    {
        // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");

    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      ResultSet rs = statement.executeQuery("select * from Lagerfachstamm");
      while(rs.next())
      {
        // read the result set
        System.out.println("name = " + rs.getString("fachnummer"));
      }

    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
    }

  public static void count() throws ClassNotFoundException
    {
        // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");

    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      ResultSet rs = statement.executeQuery("select * from Lagerfachstamm");

ResultSet rs3 = statement.executeQuery("SELECT COUNT(*) AS fachnummer FROM Lagerfachstamm ");
    while(rs3.next()){
    int count = rs3.getInt("fachnummer");
    System.out.println(count);
    }

            System.out.println();

       }


    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
    }


  public static void main(String[] args) {
        try {
            db();
            show();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
