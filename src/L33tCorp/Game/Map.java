package L33tCorp.Game;

import processing.core.PApplet;

public class Map {

    public Map(int width, int height)
    {
        Height = height;
        Width = width;
        Teams = new int[Width][Height];
        Terrain = new int[Width][Height];
        Population = new int[Width][Height];
        Troops = new float[Width][Height];
        Damage = new float[Width][Height];
        for(int i = 0; i < Width; i++)
        {
            for(int j = 0; j < Height; j++)
            {
                Troops[i][j] = (float) Math.random() * 10;
                Teams[i][j] = (int) (Math.random() * 60);
            }
        }
    }

    int Height;
    int Width;
    int[][] Teams;
    int[][] Terrain;
    int[][] Population;
    float[][] Troops;
    float[][] Damage;
    public void TakeTile(int i, int j, int h, int k, float min)
    {
        Teams[h][k] = Teams[i][j];
        Damage[h][k] += min*10;
        Troops[i][j] -= min*5;
        Troops[h][k] = Troops[i][j]*.1f;
        Troops[i][j] *= .9f;
    }

    private void Transfer(int i, int j, int h, int k, float s, float f)
    {
        if(h>=0&&k>=0&&h<Width&&k< Height) {
            DammageSpread(i, j, h, k, f);
            float s2 = Troops[h][k];
            float min = Math.min(s,s2);
            if (Teams[i][j] != Teams[h][k]) { // check if same team
                if (s2 > min*15) {
                    TakeTile(h,k,i,j,min);
                } else if (s > min*15) {
                    TakeTile(i,j,h,k,min);
                } else if (s > 0 && s2 > 0) {
                    Troops[i][j] -= min/1;
                    Troops[h][k] -= min/1;
                    Damage[i][j] += min;
                    Damage[h][k] += min;
                }
            }
            else if (s != s2) {
                float m = 1f;
                if (s < s2 - .11f * f * m /*&& s < 100*/ && s2 > 0) {
                    Troops[h][k] -=.1f * f * m;
                    Troops[i][j] +=.1f * f * m;
                    return;
                } else if (s2 < s - .11f * f * m /*&& s2 < 100*/ && s > 0) {
                    Troops[h][k] +=.1f * f * m;
                    Troops[i][j] -=.1f * f * m;
                    return;
                }
                m /= 10;
                if (s < s2 - .11f * f * m /*&& s < 100*/ && s2 > 0) {
                    Troops[h][k] -=.1f * f * m;
                    Troops[i][j] +=.1f * f * m;
                    return;
                } else if (s2 < s - .11f * f * m /*&& s2 < 100*/ && s > 0) {
                    Troops[h][k] +=.1f * f * m;
                    Troops[i][j] -=.1f * f * m;
                    return;
                }
                m /= 10;
                if (s < s2 - .11f * f * m /*&& s < 100*/ && s2 > 0) {
                    Troops[h][k] -=.1f * f * m;
                    Troops[i][j] +=.1f * f * m;
                    return;
                } else if (s2 < s - .11f * f * m /*&& s2 < 100*/ && s > 0) {
                    Troops[h][k] +=.1f * f * m;
                    Troops[i][j] -=.1f * f * m;
                    return;
                }
            }
        }
    }

    private void DammageSpread(int i, int j, int h, int k, float f) {
        float loss = 1;//.99999f;
        float d = Damage[i][j];
        float d2 = Damage[h][k];
        float m = Math.abs(d-d2)/10;
        if(d>0)
            Damage[i][j] -= .01f;
        if (d < d2 - .11f * f * m /*&& s < 100*/ && d2 > 0) {
            Damage[h][k] -=.1f * f * m * loss;
            Damage[i][j] +=.1f * f * m;
            return;
        } else if (d2 < d - .11f * f * m /*&& s2 < 100*/ && d > 0) {
            Damage[h][k] +=.1f * f * m;
            Damage[i][j] -=.1f * f * m * loss;
            return;
        }
        m /= 10;
        if (d < d2 - .11f * f * m /*&& s < 100*/ && d2 > 0) {
            Damage[h][k] -=.1f * f * m * loss;
            Damage[i][j] +=.1f * f * m;
            return;
        } else if (d2 < d - .11f * f * m /*&& d2 < 100*/ && d > 0) {
            Damage[h][k] +=.1f * f * m;
            Damage[i][j] -=.1f * f * m * loss;
            return;
        }
    }

    boolean flip;
    float f = 1;
    void Draw()
    {
        Op1();
    }

    private void Op2()
    {
        for(int i = 0; i < Width; i+=2)
        {
            for(int j = 0; j < Height; j+=2)
            {
                float s = Troops[i][j];
                //Transfer(i,j,i,j+1,s,f);
                //Transfer(i,j,i+1,j,s,f);
                Transfer(i,j,i,j-1,s,f);
                Transfer(i,j,i-1,j,s,f);
                //Transfer(i,j,i+1,j+1,s,f);
                Troops[i][j] += (.1f/PApplet.max(Damage[i][j],1))/PApplet.max(Troops[i][j],1);// .1f+((1f/Moral[i][j])/100f);igiygiy
            }
        }
        for(int i = 1; i < Width; i+=2)
        {
            for(int j = 1; j < Height; j+=2)
            {
                float s = Troops[i][j];
                //Transfer(i,j,i,j+1,s,f);
                //Transfer(i,j,i+1,j,s,f);
                Transfer(i,j,i,j-1,s,f);
                Transfer(i,j,i-1,j,s,f);
                //Transfer(i,j,i+1,j+1,s,f);
                Troops[i][j] += .1f;// .1f+((1f/Moral[i][j])/100f);igiygiy
            }
        }
        for(int i = 1; i < Width; i+=2)
        {
            for(int j = 0; j < Height; j+=2)
            {
                float s = Troops[i][j];
                //Transfer(i,j,i,j+1,s,f);
                //Transfer(i,j,i+1,j,s,f);
                Transfer(i,j,i,j-1,s,f);
                Transfer(i,j,i-1,j,s,f);
                //Transfer(i,j,i+1,j+1,s,f);
                Troops[i][j] += .1f;// .1f+((1f/Moral[i][j])/100f);igiygiy
            }
        }
        for(int i = 0; i < Width; i+=2)
        {
            for(int j = 1; j < Height; j+=2)
            {
                float s = Troops[i][j];
                //Transfer(i,j,i,j+1,s,f);
                //Transfer(i,j,i+1,j,s,f);
                Transfer(i,j,i,j-1,s,f);
                Transfer(i,j,i-1,j,s,f);
                //Transfer(i,j,i+1,j+1,s,f);
                Troops[i][j] += .1f;// .1f+((1f/Moral[i][j])/100f);igiygiy
            }
        }
    }

    private void Op1() {
        //flip = !flip;
        //if(flip)
            for(int i = 0; i < Width; i++)
            {
                for(int j = 0; j < Height; j++)
                {
                    float s = Troops[i][j];
                    //Transfer(i,j,i,j+1,s,f);
                    //Transfer(i,j,i+1,j,s,f);
                    Transfer(i,j,i,j+1,s,f);
                    Transfer(i,j,i+1,j,s,f);
                    //Transfer(i,j,i+1,j+1,s,f);
                    Troops[i][j] += (.1f/PApplet.max(Damage[i][j],1))/PApplet.max(Troops[i][j],1);// .1f+((1f/Moral[i][j])/100f);igiygiy
                }
            }
        //else
        //    for(int i = Width-1; i > 0; --i)
        //    {
        //        for(int j = Height-1; j > 0; --j)
        //        {
        //            float s = Moral[i][j];
        //            //Transfer(i,j,i,j+1,s,f);
        //            //Transfer(i,j,i+1,j,s,f);
        //            Transfer(i,j,i,j-1,s,f);
        //            Transfer(i,j,i-1,j,s,f);
        //            //Transfer(i,j,i+1,j+1,s,f);
        //            Moral[i][j] += .1f;// .1f+((1f/Moral[i][j])/100f);igiygiy
        //        }
        //    }
    }


}
