package L33tCorp;

import L33tCorp.Game.Engine;
import L33tCorp.Systems.KeySystem;
import processing.core.PApplet;

public class Main extends PApplet {

    public static Main M;
    public KeySystem Key;
    public Engine E;
    public static void main(String[] args) {
        PApplet.main("L33tCorp.Main");
    }

    public void settings() {
        M = this;
        size(1366,786);
        Key = new KeySystem(this);
        E = new Engine(Key,this);
    }

    public void setup() {
        frameRate(30);
        background(0);
    }

    public void draw() {
        E.Draw(M);
    }
    public void keyPressed() { Key.KeyDown(); }
    public void keyReleased()
    {
        Key.KeyUp();
    }
}