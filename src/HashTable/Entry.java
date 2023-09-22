package HashTable;

/**
 * @author David Zelaya
 */
public class Entry {

    String username;
    long pos;
    Entry siguiente;
    
    public Entry(String username,long posicion){
        this.username=username;
        this.pos=posicion;
        siguiente=null;
    }
    
    
}
