package sokoban.filer;
import java.util.List;

/**
 * Zip and unzip Code by Filer Model group-
 * import and export functions + new functions  are left abstract,
 * for different implementations based on persistent storge
 *
 */

public abstract class AbstractFiler {
    public String zip(String map) {
        String zipMap = "";
        int count = 1;
        int i = 0;
        do {
            // check if there are one or more characters in the map remaining
            if (i+1 < map.length()){
                // check if the next map character matches the current one
                if (map.charAt(i) == map.charAt(i+1)) {
                    count += 1;
                    i++;
                    continue;
                }
            }
            // add the compressed sequence to the string if multiple identical characters
            if (count > 1) {
                zipMap += Integer.toString(count);
                zipMap += map.charAt(i-1);
                // otherwise add the singular character
            } else {
                zipMap += map.charAt(i);
            }

            count = 1;
            i++;

        } while (i < map.length());

        return zipMap;
    }
    public String unZip(String zippedMap) {
        String outputMap = "";
        String tempQty = "";
        char ch;
        int qty;

        for (int i=0; i < zippedMap.length(); i++){
            ch = zippedMap.charAt(i);
            // simply add the character to the output map, if
            // it is not preceded with an integer
            if (!Character.isDigit(ch)) {
                outputMap += zippedMap.charAt(i);
                // otherwise an integer represents multiples
                // of the next tile identifier
            } else {
                // check if there are more numeric digits
                while ( Character.isDigit(ch) ) {
                    // add the value to a temporary string, to store
                    // the digits without any calculations or conversions
                    tempQty += ch;
                    // get the next value in the map
                    ch = zippedMap.charAt(i+1);

                    i++;
                }
                // quantity represents the number that was before the tile identifier
                qty = Integer.parseInt(tempQty);
                // add the provided amount of tiles to the output map
                for (int x=0; x<qty; x++){
                    outputMap += zippedMap.charAt(i);
                }
                // reset the quantity variables
                qty = 0;
                tempQty = "";
            }
        }
        return outputMap;
    }
    public abstract String importMap(String key);
    public abstract void exportMap(String map, String key);
    public abstract List<String> loadAllKeys();
    public abstract String getKeyAvailability(String key);
    public abstract boolean containsData();
    public abstract void removeData(String key);
}
