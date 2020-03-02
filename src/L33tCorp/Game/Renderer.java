package L33tCorp.Game;

import L33tCorp.Util.Vec2;
import processing.core.PApplet;

public class Renderer
{
    Engine E;
    public float Scale = 3;
    public Vec2 Offset = new Vec2(0,0);


    public Renderer(Engine e)
    {
        E = e;
    }

    public int GetColor(int x, int y)
    {
        return (int)(E.M.Moral[x][y]);
    }

    public void Draw(PApplet PA)
    {
        PA.noStroke();
        for(int i = 0; i < E.M.Width; i++)
        {
            for(int j = 0; j < E.M.Height; j++)
            {
                float m = E.M.Moral[i][j];
                //PA.fill(PA.color(m*25,m/100,m/10));
                PA.fill(PA.color(m*25,E.M.Teams[i][j]*5,m/10));
                //PA.fill(0,E.M.Teams[i][j]*25,0);
                PA.rect(i*Scale,j*Scale,Scale,Scale);
            }
        }
    }
}
