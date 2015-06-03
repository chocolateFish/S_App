package sokoban.game.GameView;

/**
 * Created by User on 18/05/2015.
 */
public interface SwipeInterface {

    void bottomToTop();

    void leftToRight();

    void rightToLeft();

    void topToBottom();
    void tooShortSwipe(CharSequence text);

}
