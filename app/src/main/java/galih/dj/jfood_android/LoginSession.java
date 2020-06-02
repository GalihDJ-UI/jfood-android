package galih.dj.jfood_android;

import android.content.Context;
import android.content.SharedPreferences;



public class LoginSession
{
    public static final String sp_Jfood = "spJfood";
    public static final String sp_Email = "spEmail";
    public static final String sp_Password = "spPassword";
    public static final String sp_LoggedIn = "spLoggedIn";
    public static final String sp_IdCustomer = "spIdCustomer";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public LoginSession (Context context)
    {
        sp = context.getSharedPreferences(sp_Jfood, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value)
    {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value)
    {
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value)
    {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public int getSP_IdCustomer()
    {
        return sp.getInt(sp_IdCustomer, 0);
    }

    public String getSP_Email()
    {
        return sp.getString(sp_Email, "");
    }

    public String getSp_Password()
    {
        return sp.getString(sp_Password, "");
    }

    public Boolean getSP_LoggedIn()
    {
        return sp.getBoolean(sp_LoggedIn, false);
    }

}
