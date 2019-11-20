package display;

import logic.KeyboardListener;
import logic.MouseClickListener;
import logic.enemy;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;

public class Game extends JFrame {
    /**
     * This class is used to 'display' the game.
     */
    private static final int ROUND_TIME = 60000; // Time for each round (1 minute)

    private JPanel grid_panel; // panel used to display grid panels
    private JPanel[][] grid_array = new JPanel[50][50]; // array used to store panels in grid

    public void initialise() {
        // if grid already drawn (reinitialise rather than initialise) remove grid from frame and delete it
        if (grid_panel != null) {
            this.remove(grid_panel);
            grid_panel = null;
        }
//        panel used to display the scores
        JPanel score_panel = new JPanel(new BorderLayout());
//        The panel used to hold the game grid
        JPanel game_panel = new JPanel(new BorderLayout());
        grid_panel = new JPanel(new GridLayout(50, 50));

//        Set properties for components
        score_panel.setBackground(Color.WHITE);
        JTextArea score_area = new JTextArea();
        score_area.setText("Score: 122");
        score_area.setEditable(false);

        // for loop to create grid
        for (int x = 0; x < grid_array.length; x++) {
            for (int y = 0; y < grid_array[x].length; y++) {
                grid_array[x][y] = new JPanel();
                grid_array[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
                // unique mouse listener per panel to determine which panel was clicked
                grid_array[x][y].addMouseListener(new MouseClickListener(this));
                grid_panel.add(grid_array[x][y]);
            }
        }
        game_panel.add(grid_panel);
        score_panel.add(score_area);
        // add panel to frame
        this.add(score_panel, BorderLayout.NORTH);
        this.add(game_panel, BorderLayout.CENTER);
    }

    Game() throws Exception {
        // set up game environment
        initialise();
        // key listener to respond to key events
        addKeyListener(new KeyboardListener(this));

        // standard configuration
        setSize(new Dimension(900, 900));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

//        Spawn in the enemies
        TimerTask enemySpawner = new enemy(grid_array);
        Timer timer = new Timer(true);
        // Spawn time for enemies
        int spawn_interval = 3000;
        timer.scheduleAtFixedRate(enemySpawner, 0, spawn_interval);

        Thread.sleep(ROUND_TIME); // Do this for 1 minute
    }
}
