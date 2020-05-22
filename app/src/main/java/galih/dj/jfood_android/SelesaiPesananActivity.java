package galih.dj.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SelesaiPesananActivity extends AppCompatActivity
{

    private static int currentUserId;
    private int invoiceId;
    private String date;
    private String paymentType;
    private int totalPrice;
    private String customer;
    private String foodName;
    private String foodCategory;
    private String sellerName;
    private String location;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            currentUserId = extras.getInt("currentUserId");
        }

        final Button btnLogin = findViewById(R.id.btnLogin);
        final TextView static_invoice_Customer = findViewById(R.id.static_invoice_Customer);
        final TextView static_invoice_foodName= findViewById(R.id.static_invoice_foodName);
        final TextView static_invoice_foodCategory = findViewById(R.id.static_invoice_foodCategory);
        final TextView invoice_customer_name = findViewById(R.id.invoice_customer_name);
        final TextView invoice_food_name = findViewById(R.id.invoice_food_name);
        final TextView invoice_food_category = findViewById(R.id.invoice_food_category);

        final TextView static_invoice_seller = findViewById(R.id.static_invoice_seller);
        final TextView static_invoice_seller_city= findViewById(R.id.static_invoice_seller_city);
        final TextView static_invoice_seller_date = findViewById(R.id.static_invoice_seller_date);
        final TextView invoice_seller_name = findViewById(R.id.invoice_seller_name);
        final TextView invoice_seller_city = findViewById(R.id.invoice_seller_city);
        final TextView invoice_seller_date = findViewById(R.id.invoice_seller_date);

        final TextView static_invoice_delivery_fee = findViewById(R.id.static_invoice_delivery_fee);
        final TextView static_invoice_payment_type= findViewById(R.id.static_invoice_payment_type);
        final TextView static_invoice_total_price = findViewById(R.id.static_invoice_total_price);
        final TextView invoice_delivery_fee = findViewById(R.id.invoice_delivery_fee);
        final TextView invoice_payment_type = findViewById(R.id.invoice_payment_type);
        final TextView invoice_total_price = findViewById(R.id.invoice_total_price);

        final Button btnCancel = findViewById(R.id.btnCancel);
        final Button btnDone = findViewById(R.id.btnDone);



        final Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONArray jsonResponse = new JSONArray(response);
                    for (int i=0; i<jsonResponse.length(); i++)
                    {
                        JSONObject jsonInvoice = jsonResponse.getJSONObject(i);
                        invoiceId = jsonInvoice.getInt("id");
                        date = jsonInvoice.getString("date");
                        paymentType = jsonInvoice.getString("paymentType");
                        totalPrice = jsonInvoice.getInt ("totalPrice");
                        invoice_seller_date.setText(date.substring(0,9));
                        invoice_payment_type.setText(paymentType);
                        invoice_total_price.setText(String.valueOf(totalPrice));

                        JSONObject jsonCustomer = jsonInvoice.getJSONObject("customer");
                        customer = jsonCustomer.getString("name");
                        invoice_customer_name.setText(customer);

                        JSONArray jsonFood = new JSONArray(response);
                        for (int j=0; j<jsonFood.length(); j++)
                        {
                            JSONObject jsonFoodObj = jsonFood.getJSONObject(j);
                            foodName = jsonFoodObj.getString("foodName");
                            foodCategory = jsonFoodObj.getString("foodCategory");
                            invoice_food_name.setText(foodName);
                            invoice_food_category.setText(foodCategory);

                            JSONObject jsonFoodSeller = jsonFoodObj.getJSONObject("seller");
                            sellerName = jsonFoodSeller.getString("name");
                            invoice_seller_name.setText(sellerName);

                            JSONObject jsonSellerLoc = jsonFoodSeller.getJSONObject("location");
                            location = jsonSellerLoc.getString("city");
                            invoice_seller_city.setText(location);
                        }
                    }
                }

                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Response.Listener<String> cancelListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            Intent intent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                PesananBatalRequest batalRequest = new PesananBatalRequest(String.valueOf(invoiceId), cancelListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(batalRequest);

            }
        });

        btnDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Response.Listener<String> doneListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            Intent intent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                };

                PesananSelesaiRequest selesaiRequest = new PesananSelesaiRequest(String.valueOf(invoiceId), doneListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(selesaiRequest);
            }
        });



        PesananFetchRequest fetchRequest = new PesananFetchRequest(String.valueOf(currentUserId), responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
        queue.add(fetchRequest);
    }
}
