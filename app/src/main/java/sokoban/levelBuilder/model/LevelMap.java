package sokoban.levelBuilder.model;
/*
Written by Nate from LevelBuilder group
 */
import java.util.Scanner;
public class LevelMap implements ILevelMap {

    private int rows, cols;


    private ILevelCell[][] cells;

    public LevelMap() {
        //default rows and cols is 10
        setSize(10, 10);
    }


    private void resizeFromString(String s) {
        // This method will look at a level string and calculate the rows and columns
        // then resize the map to that
        // Moved this out of the main fromString() method because this is probably overkill
        // but we might want to use it again sometime

        Scanner scanner = new Scanner(s);
        int y = 0;
        int x  =0;
        String thisLine;

        // First we have to calculate the size of the string

        if (scanner.hasNextLine()) {
            for (y=1;scanner.hasNextLine();y++) {
                thisLine = scanner.nextLine();
                if (thisLine.length() > x) {
                    x = thisLine.length();
                }
            }
        }

        // Adjust for last line
        if (y > 0) {
            y--;
        }

        // x and y should now give us the rows and maximum cols

        // So reset the map

        setSize(x,y);

        scanner.close();
    }

    @Override
    public void setString(String s) {

        Scanner scanner = new Scanner(s);
        int y;
        int x;
        String thisLine;
        String thisChar;

        // Resize the map
        resizeFromString(s);

        // Scan each line, and set each character

        for (y=0;(y<rows) && (scanner.hasNextLine());y++) {
            thisLine = scanner.nextLine();
            System.out.println("Line " + y + " : '" + thisLine + "'"); System.out.flush();
            for (x=0;(x < cols) && (x < thisLine.length());x++) {
                thisChar = thisLine.substring(x, x+1); // select one character, 0-indexed
                System.out.println("Setting " + x + "," + y + " to " + thisChar);
                getCell(x,y).setString(thisChar);
            }
        }
        scanner.close();
    }

    @Override
    public String getString() {
        String s = "";
        for (int y=0;y<rows;y++) {
            for (int x=0;x<cols;x++) {
                s += getCell(x,y).getString();
            }
           // if (y<rows-1) {s += "\n";} // add a \n but not after the last line
           s += "\n"; // add a \n but not after the last line
        }
        return s;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    private boolean validCoords(int x, int y) {
        // Test that the given coordinates are inside the map
        return (y >= 0 && y < rows && x >= 0 && x < cols );
    }

    @Override
    public ILevelCell getCell(int x, int y) {
        if (validCoords (x,y)) {
            return cells[y][x];
        } else {
            return null;
            // Returning a null is a bit nasty but simpler than making a custom exception.
            // At least an exception will be thrown on access.
        }
    }


    @Override
    public void setSize(int cols, int rows) {
        // Recreate a new map of the specified size
        // x and y indices are 0-based so go from 0 to rows,cols -1

        this.rows = rows;
        this.cols = cols;

        // Make a new grid of LevelCells
        cells = new LevelCell[rows][cols];

        // Initialise each element of the grid to a new LevelCell
        for (int y=0;y<rows;y++) {
            for (int x=0;x<cols;x++) {
                cells[y][x]= new LevelCell();
            }
        }
    }

}
