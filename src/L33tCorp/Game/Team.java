package L33tCorp.Game;

public class Team
{
    public int TeamID;
    public int Color;
    public String Name;

    public Team(float cd,float ct,float cc)
    {
        CD = cd;
        CT = ct;
        CC = cc;
    }

    public float CD,CT,CC;
    public float CommandFunction(float Damage, float Troops, float Command)
    {
        return Damage * CD + Troops * CT + Command * CC;
    }
}
