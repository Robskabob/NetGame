package L33tCorp.Systems;

import L33tCorp.Util.Vec2;
import processing.core.PApplet;

import java.util.HashMap;

public class KeySystem {
    private char Last;
    PApplet PA;
    private HashMap<Character, Boolean> Keys = new HashMap();

    public boolean Disable;

    public KeySystem(PApplet pa) {
        PA = pa;
    }


    public void setup() {
    }


    public void update() {

    }

    public Vec2 MousePos(){return null;}


    public void draw(PApplet PA) {

    }

    public boolean GetKey(Character c) {
        if(Disable)
            return false;
        return Keys.containsKey(c) && Keys.get(c);
    }

    public void KeyDown() {
        if (Last != PA.key) {
            Keys.put(PA.key, true);
        }
    }

    public void KeyUp() {
        if (Last != PA.key) {
            Keys.put(PA.key, false);
        }
    }
}
