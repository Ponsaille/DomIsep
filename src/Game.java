import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {
    private Partie partie = new Partie();

    public static void main(String[] args) throws SlickException {
        AppGameContainer apg = new AppGameContainer(new Game(), 800, 600, false);
        apg.setTargetFrameRate(60);
        apg.start();
    }

    public Game() {
        super("DomiNation");
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new MainScreen(partie));
    }

}
