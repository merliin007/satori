package utility.tss;/*
 * Created by Miguel Angel Aguilar Cuevas
 * 11/01/2019 at 11:25 AM
 */

public enum LineElement {
    HOME_RESULT(2),
    HOME_SET1(3),
    HOME_SET2(4),
    HOME_SET3(5),
    PLAYED_DATE(6),
    AWAY_SET1(7),
    AWAY_SET2(8),
    AWAY_SET3(9),
    AWAY_RESULT(10);

    private final int position;

    LineElement(int pos){
        this.position = pos;
    }
    public int getPosition(){
        return this.position;
    }

}
