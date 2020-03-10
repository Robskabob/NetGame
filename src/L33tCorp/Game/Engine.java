package L33tCorp.Game;

import L33tCorp.Systems.KeySystem;
import processing.core.PApplet;

public class Engine
{
    public KeySystem Key;
    public Map M;
    public Input I;
    public Renderer R;

    public Engine(KeySystem K,PApplet PA)
    {
        Key = K;
        M = new Map(PA.width/8,PA.height/8);
        I = new Input(this);
        R = new Renderer(this);
    }

    public void Draw(PApplet PA)
    {
        I.Update(PA);
        M.Draw();
        //if(PA.frameCount % 60 == 0)
        R.Draw(PA);
    }

    public void Update()
    {
    }
}
