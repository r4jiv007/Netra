/*
 * Author Ahmed Abdelhalim - 2009
 * Email: englemo@hotmail.com
 * 
 * Modified by:- Rajiv Singh - 2012
 * r4jiv007@gmail.com
 * Please do not remove the above lines
 */
package outputserver;

/**
 * Used to represent commands which can be sent by the server
 */
public enum EnumCommands {
    PRESS_MOUSE(-1),
    RELEASE_MOUSE(-2),
    PRESS_KEY(-3),
    RELEASE_KEY(-4),
    MOVE_MOUSE(-5);

    private int abbrev;

    EnumCommands(int abbrev){
        this.abbrev = abbrev;
    }

    public int getAbbrev(){
        return abbrev;
    }
}
