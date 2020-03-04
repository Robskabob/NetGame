package L33tCorp.Game;

import L33tCorp.Util.Vec2;
import processing.core.PApplet;

public class Input {
    Engine E;

    public Input(Engine e)
    {
        E = e;
    }

    float a = 1000;
    int b = 0;
    public void Update(PApplet PA)
    {
        if(PA.mousePressed)
        {
            //a *= 1.1;
            Vec2 Pos = new Vec2(PA.mouseX/E.R.Scale,PA.mouseY/E.R.Scale);
            if(Pos.x < 0 || Pos.y < 0 || Pos.x >= E.M.Width || Pos.y >= E.M.Height)
                return;
            if(PA.mouseButton == PApplet.LEFT)
                E.M.Command[(int)Pos.x][(int)Pos.y] += a;
            else
                E.M.Command[(int)Pos.x][(int)Pos.y] -= a*2;
        }
        if(E.Key.GetKey('w'))
        {
            b++;
        }
        if(E.Key.GetKey('s'))
        {
            b--;
        }
    }
}
