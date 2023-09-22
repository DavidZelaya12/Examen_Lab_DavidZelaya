package HashTable;

import javax.swing.JOptionPane;

/**
 * @author David Zelaya
 */
public class HashTable {

    private Entry inicio = null;

    public void add(String username, Long pos) {

        if (inicio == null) {
            inicio = new Entry(username, pos);
            System.out.println("Inicio null");
        } else {
            Entry actual = inicio;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            System.out.println("Lista expande");
            actual = new Entry(username, pos);
        }
    }

    public void remove(String username) {

        if(inicio!=null)
        if (inicio.username.equals(username)) {
            inicio = inicio.siguiente;
        } else {
            Entry actual = inicio;
            while (actual.siguiente != null) {
                if (actual.siguiente.username.equals(username)) {
                    actual.siguiente = actual.siguiente.siguiente;
                }
                actual = actual.siguiente;
            }
        }

    }

    public long search(String username){
        
        if(inicio==null){
            return 0;
        }
        
        if (inicio.username.equals(username)) {
            return inicio.pos;
        } else {
            Entry actual = inicio;
            while (actual!=null) {
                if(actual.username.equals(username)){
                    return actual.pos;
                }
                actual = actual.siguiente;
            }
        }
        
        return -1;
    }
    
    
    
}
