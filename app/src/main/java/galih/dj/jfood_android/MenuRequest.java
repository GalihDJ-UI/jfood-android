package galih.dj.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.*;

public class MenuRequest extends StringRequest
{
    private static final String URL = "http://10.0.2.2:8080/food";
    private Map<String, String> params;

    public MenuRequest (Response.Listener<String> listener)
    {
        super(Method.GET, URL, listener, null);
        params = new HashMap<>();
       // params.put("id", id);
       // params.put("name", name);
       // params.put("seller", seller);
       // params.put("price", price);
       // params.put("category", category);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        return params;
    }
}