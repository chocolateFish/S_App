package sokoban.game.model;

import sokoban.Directions;

/**
 * Created by User on 18/05/2015.
 */
public class Person {
    int across;
    int down;
    int rows;
    int columns;

    public Person(int across, int down) {
        this.across = across;
        this.down = down;
        // this should actually come from the string the maze is composed of
        this.rows = 5;
        this.columns = 5;
    }

    public void move(Directions direction) {
        int across =  this.across + direction.horizontal;
        if(across >= 0 && across < this.columns ){
            this.across = across;
        }
        int down = this.down + direction.vertical;
        if(down >= 0 && down < this.rows){
            this.down = down;
        }
    }

    public int getAcross(){
        return this.across;
    }

    public int getDown(){
        return this.down;
    }

}

