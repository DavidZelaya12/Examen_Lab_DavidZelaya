package HashTable;

import java.io.*;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 * @author David Zelaya
 */
public class PSNUsers {

    private RandomAccessFile ht;
    private RandomAccessFile trofeos;
    private HashTable users;
    boolean charge=false;

    public PSNUsers() {

        users=new HashTable();
        
        try {

            ht = new RandomAccessFile("src/Files/archivo.psn", "rw");
            trofeos = new RandomAccessFile("src/Files/trofeos.psn", "rw");
            reloadHashTable();

        } catch (IOException e) {
            System.out.println("Error en el constructor");
        }

    }

    private void reloadHashTable() throws IOException {
        ht.seek(0);

        while (ht.getFilePointer() < ht.length()) {
            Long pos = ht.getFilePointer();
            String user = ht.readUTF();//lee nombre
            ht.readInt();//lee puntos rofeo
            ht.readInt();//lee cuantos trofeo
            ht.readBoolean();
            users.add(user, pos);
        }
        charge=true;

    }

        public void AddUser(String username) throws IOException {
            ht.seek(ht.length());

            if (users.search(username) == -1) {
                Long pos = ht.getFilePointer();//pos
                ht.writeUTF(username);//nombre
                ht.writeInt(0); //puntos de trofeos
                ht.writeInt(0);//contador de trofeos
                ht.writeBoolean(true); //activo o no
                users.add(username, pos);
                if(charge==true){
                JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente!");
                }
                } else {
                JOptionPane.showMessageDialog(null, "Error: username no disponible");
            }

        }

    
    private boolean eliminado(String username) throws IOException{
        ht.seek(users.search(username));
        ht.readUTF();//lee nombre
        ht.readInt();//lee puntos
        ht.readInt();
        
        if(ht.readBoolean()==true){
            return true;
        }else{
            return false;
        }
        
        
    }
    
    public void desactivateUser(String username) throws IOException {
        long pos = users.search(username);
        if(pos!=-1){
            if(eliminado(username)){
        ht.seek(pos);
        ht.readUTF();//lee nombre
        ht.readInt();//lee puntos
        ht.writeBoolean(false);//cambia
        users.remove(username);
        JOptionPane.showMessageDialog(null, "Usuario eliminado");
            }else{
             JOptionPane.showMessageDialog(null, "Error:Este usuario ya esta inactivo");   
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error: usuario no encontrado");
        }

    }

    public void addTrophyTo(String username, String game, String nombretrofeo,  String Description) throws IOException {
        trofeos.seek(trofeos.length());

        Calendar calendar = Calendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        String fecha = dia + "/" + mes + "/" + año;

        String info = "Juego: " + game + " Tipo: " + nombretrofeo + " Fecha:" + fecha + " Descripcion: " + Description;

        if (users.search(username) != -1) {
            trofeos.writeUTF(username);
            trofeos.writeUTF(info);
            JOptionPane.showMessageDialog(null, "Trofeo Agregado Exitosamente!");
        } else {
            JOptionPane.showMessageDialog(null, "Error: user no encontrado");
        }

    }

    public String playerInfo(String username) throws IOException {
        String hola = "";
        if (users.search(username) != -1) {
            trofeos.seek(0);
            while(trofeos.getFilePointer()<trofeos.length()){
                String user=trofeos.readUTF();
                String info=trofeos.readUTF();
                if(user.equals(username)){
                    hola+=info+"\n";
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: no se han encontrado trofeos registrados con este user");
        }
        return hola;
    }

}
