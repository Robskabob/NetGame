package L33tCorp.Game;

import L33tCorp.Util.Vec2;
import processing.core.PApplet;

public class Renderer
{
    Engine E;
    public float Scale = 8;
    public Vec2 Offset = new Vec2(0,0);


    public Renderer(Engine e)
    {
        E = e;
    }

    public int GetColor(int x, int y)
    {
        return (int)(E.M.Troops[x][y]);
    }

    public void Draw(PApplet PA)
    {
        //PA.noStroke();
        for(int i = 0; i < E.M.Width; i++)
        {
            for(int j = 0; j < E.M.Height; j++)
            {
                float d = E.M.Damage[i][j];
                float t = E.M.Command[i][j];
                float m = E.M.Troops[i][j];
                //PA.fill(PA.color(m*25,m/100,m/10));
                //PA.fill(0,E.M.Teams[i][j]*25,0);
                PA.fill(PA.color(d*5,t/10,m*5));
                PA.rect(i*Scale,j*Scale,Scale,Scale);
                //if(d<-.1f) {
                //    PA.fill(PA.color(100, 0, 0));
                //    PA.circle(i*Scale,j*Scale,Scale);
                //}else if(t<0) {
                //    PA.fill(PA.color(0, 100, 0));
                //    PA.circle((i-.25f)*Scale,j*Scale,Scale);
                //}else if(m<0) {
                //    PA.fill(PA.color(0, 0, 100));
                //    PA.circle((i-.5f)*Scale,j*Scale,Scale);
                //}
            }
        }
    }
}
