package HashTable;
/**
 * @author David Zelaya
 */
public class Trophy {

    public enum Trofeos {
        PLATINO(5), ORO(3), PLATA(2), BRONCE(1);

        public final int points;

        Trofeos(int points) {
            this.points = points;
        }
    }

}
