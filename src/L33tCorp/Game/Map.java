package L33tCorp.Game;

public class Map {

    public Map(int width, int height)
    {
        Height = height;
        Width = width;
        Teams = new int[Width][Height];
        Terrain = new int[Width][Height];
        Population = new int[Width][Height];
        Moral = new float[Width][Height];
        Damage = new float[Width][Height];
        for(int i = 0; i < Width; i++)
        {
            for(int j = 0; j < Height; j++)
            {
                Moral[i][j] = (float) Math.random() * 1000;
                Teams[i][j] = (int) (Math.random() * 75);
            }
        }
    }

    int Height;
    int Width;
    int[][] Teams;
    int[][] Terrain;
    int[][] Population;
    float[][] Moral;
    float[][] Damage;

    private void Transfer(int i, int j, int h, int k, float s, float f)
    {
        if(h>=0&&k>=0&&h<Width&&k< Height) {
            float s2 = Moral[h][k];
            float min = Math.min(s,s2);
            if (Teams[i][j] != Teams[h][k]) { // check if same team
                if (s2 > min*15) {
                    Teams[i][j] = Teams[h][k];
                    Moral[h][k] -= min*5;
                } else if (s > min*15) {
                    Teams[h][k] = Teams[i][j];
                    Moral[i][j] -= min*5;
                } else if (s > 0 && s2 > 0) {
                    Moral[i][j] -= min/1;
                    Moral[h][k] -= min/1;
                }
            }
            else if (s != s2) {
                float m = 1f;
                if (s < s2 - .11f * f * m /*&& s < 100*/ && s2 > 0) {
                    Moral[h][k] -=.1f * f * m;
                    Moral[i][j] +=.1f * f * m;
                    return;
                } else if (s2 < s - .11f * f * m /*&& s2 < 100*/ && s > 0) {
                    Moral[h][k] +=.1f * f * m;
                    Moral[i][j] -=.1f * f * m;
                    return;
                }
                m /= 10;
                if (s < s2 - .11f * f * m /*&& s < 100*/ && s2 > 0) {
                    Moral[h][k] -=.1f * f * m;
                    Moral[i][j] +=.1f * f * m;
                    return;
                } else if (s2 < s - .11f * f * m /*&& s2 < 100*/ && s > 0) {
                    Moral[h][k] +=.1f * f * m;
                    Moral[i][j] -=.1f * f * m;
                    return;
                }
                m /= 10;
                if (s < s2 - .11f * f * m /*&& s < 100*/ && s2 > 0) {
                    Moral[h][k] -=.1f * f * m;
                    Moral[i][j] +=.1f * f * m;
                    return;
                } else if (s2 < s - .11f * f * m /*&& s2 < 100*/ && s > 0) {
                    Moral[h][k] +=.1f * f * m;
                    Moral[i][j] -=.1f * f * m;
                    return;
                }
            }
        }
    }

    void Draw()
    {
        float f = 500;
        for(int i = 0; i < Width; i++)
        {
            for(int j = 0; j < Height; j++)
            {
                float s = Moral[i][j];
                Transfer(i,j,i,j+1,s,f);
                Transfer(i,j,i+1,j,s,f);
                Moral[i][j] += .5f;// .1f+((1f/Moral[i][j])/100f);igiygiy
            }
        }
    }


}
