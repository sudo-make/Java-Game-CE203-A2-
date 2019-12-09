package objects;

import java.awt.*;

import display.Game;

public class Circle extends Jewels {

    /**
     *
     */
    private static final long serialVersionUID = -5292220531349882909L;
    private Shapes type = Shapes.Circle;

    Circle() {
        setJewelColor();
        addToArray();
    }

    @Override
    public Color get_jewel_color() {
        return super.jewelColor;
    }

    @Override
    public void setJewelColor() {
        super.jewelColor = Color.RED;
    }

    @Override
    public Shapes getType() {
        return type;
    }

    @Override
    public void setType(Shapes jewelToChangeInto) {
        this.type = jewelToChangeInto;
    }

    @Override
    public void addToArray() {
        // Add this object to the global array
        for (int x = 0; x < Game.jewels_coords.length; x++) {
            for (int y = 0; y < Game.jewels_coords[x].length; y++) {
                Game.jewels_coords[x][y] = getType();
            }
        }
    }

}