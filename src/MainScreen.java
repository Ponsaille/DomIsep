import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.TrueTypeFont;

import java.awt.Font;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class MainScreen extends BasicGameState {
    public static final int ID = 1;
    private Partie partie;
    private StateBasedGame game;
    private Image background;
    private TrueTypeFont titleFont;
    private TrueTypeFont font;

    public MainScreen(Partie partie) {
        this.partie = partie;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        this.background = new Image(new File("data/images/home_background.png").getAbsolutePath());
        this.titleFont = new TrueTypeFont(new Font("TimesRoman", Font.PLAIN, 50),false);
        this.font = new TrueTypeFont(new Font("TimesRoman", Font.PLAIN, 30),false);;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        background.draw(0, 0, container.getWidth(), container.getHeight());
        titleFont.drawString(250, 10, "DomiNation", Color.black);
        g.setColor(Color.white);
        g.fillRect(200, 280, 400, 200);

        List<Player> players = partie.getPlayers();
        for (int i = 0; i<players.size(); i++) {
            font.drawString(250, 300 + i*35, "Player " + (i+1), Color.black);
            font.drawString(450, 300 + i*35, "P", Color.black);
            font.drawString(550, 300 + i*35, "X", Color.red);
        }

        font.drawString(200, 500, "Appuyer sur N pour ajouter un joueur");
        font.drawString(200, 550, "Appuyer sur Entrer pour débuter le jeu");
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    public void keyReleased(int key, char c) {
        if (key == 49) {
            partie.newPlayer();
        } else if (key == 28) {
            System.out.println("Le jeu démarre");
        }
    }

    @Override
    public int getID() {
        return ID;
    }
}
