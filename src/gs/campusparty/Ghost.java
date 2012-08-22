package gs.campusparty;

public class Ghost {
    int time;
    int[] chips;
    
    public Ghost() {
        time = -1;
        chips = new int[Field.C_COUNT];
    }
}
