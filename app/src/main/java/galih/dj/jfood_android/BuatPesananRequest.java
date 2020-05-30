package galih.dj.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.*;

public class BuatPesananRequest extends StringRequest
{
    private static final String URL_Cash = "http://10.0.2.2:8080/invoice/createCashInvoice";
    private static final String URL_Cashless = "http://10.0.2.2:8080/invoice/createCashlessInvoice";
    private Map<String, String> params;

    public BuatPesananRequest(String foodList, String customerId, Response.Listener<String> listener)
    {
        super(Method.POST, URL_Cash, listener, null);
        params = new HashMap<>();
        params.put("foods", foodList);
        params.put("customer", customerId);
        params.put("deliveryFee", "5000");
    }

    public BuatPesananRequest(String foodList, String customerId, String promoCode, Response.Listener<String> listener)
    {
        super(Method.POST, URL_Cashless, listener, null);
        params = new HashMap<>();
        params.put("foods", foodList);
        params.put("customer", customerId);
        params.put("promoCode", promoCode);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError
    {
        return params;
    }
}
