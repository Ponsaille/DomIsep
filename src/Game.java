import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game extends StateBasedGame {
    private Partie partie = new Partie();
    private List<BasicGameState>  screens;

    public static void main(String[] args) throws SlickException {
        AppGameContainer apg = new AppGameContainer(new Game(), 800, 600, false);
        apg.setTargetFrameRate(60);
        apg.start();
    }

    public Game() {
        super("DomiNation");
        screens = new ArrayList<>();
        screens.add(new MainScreen(partie));
        screens.add(new GameScreen(partie));
        screens.add(new EndScreen(partie));
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        Iterator<BasicGameState> iterator = screens.iterator();
        while (iterator.hasNext()) {
            addState(iterator.next());
        }
    }

    public List<BasicGameState> getScreens() {
        return this.screens;
    }

}
