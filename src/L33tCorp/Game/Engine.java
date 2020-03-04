package L33tCorp.Game;

import L33tCorp.Systems.KeySystem;
import processing.core.PApplet;

public class Engine
{
    public KeySystem Key;
    public Map M;
    public Input I;
    public Renderer R;

    public Engine(KeySystem K)
    {
        Key = K;
        M = new Map(200,130);
        I = new Input(this);
        R = new Renderer(this);
    }

    public void Draw(PApplet PA)
    {
        R.Draw(PA);
        I.Update(PA);
        M.Draw();
    }

    public void Update()
    {
    }
}
