package galih.dj.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class SelesaiPesananActivity extends AppCompatActivity
{

    private static int currentUserId;
    private int invoiceId;
    private String date;
    private String paymentType;
    private int totalPrice;
    private String customer;
    private String foodName;
    private int deliveryFee;
    private String promoCode;
    private String sellerName;
    private String location;
    private String invoiceStatus;


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

        //Toast.makeText(SelesaiPesananActivity.this, String.valueOf(currentUserId), Toast.LENGTH_LONG).show();

        final TextView static_seller = findViewById(R.id.static_seller);

        final TextView static_invoice_Customer = findViewById(R.id.static_invoice_Customer);
        final TextView static_invoice_foodName= findViewById(R.id.static_invoice_foodName);
        final TextView invoice_customer_name = findViewById(R.id.invoice_customer_name);
        final TextView invoice_food_name = findViewById(R.id.invoice_food_name);

        final TextView static_invoice_seller = findViewById(R.id.static_invoice_seller);
        final TextView static_invoice_seller_city= findViewById(R.id.static_invoice_seller_city);
        final TextView static_invoice_seller_date = findViewById(R.id.static_invoice_seller_date);
        final TextView invoice_seller_name = findViewById(R.id.invoice_seller_name);
        final TextView invoice_seller_city = findViewById(R.id.invoice_seller_city);
        final TextView invoice_seller_date = findViewById(R.id.invoice_seller_date);

        final TextView static_invoice_warning = findViewById(R.id.static_invoice_warning);

        final TextView static_invoice_delivery_fee = findViewById(R.id.static_invoice_delivery_fee);
        final TextView static_invoice_promo_code = findViewById(R.id.static_invoice_promo_code);
        final TextView static_invoice_payment_type= findViewById(R.id.static_invoice_payment_type);
        final TextView static_invoice_total_price = findViewById(R.id.static_invoice_total_price);
        final TextView invoice_promo_code = findViewById(R.id.invoice_promo_code);
        final TextView invoice_delivery_fee = findViewById(R.id.invoice_delivery_fee);
        final TextView invoice_payment_type = findViewById(R.id.invoice_payment_type);
        final TextView invoice_total_price = findViewById(R.id.invoice_total_price);

        final Button btnCancel = findViewById(R.id.btnCancel);
        final Button btnDone = findViewById(R.id.btnDone);

        btnCancel.setVisibility(View.GONE);
        btnDone.setVisibility(View.GONE);
//
        static_seller.setVisibility(View.GONE);
//
        static_invoice_Customer.setVisibility(View.GONE);
        static_invoice_foodName.setVisibility(View.GONE);
        invoice_customer_name.setVisibility(View.GONE);
        invoice_food_name.setVisibility(View.GONE);

        static_invoice_warning.setVisibility(View.VISIBLE);

        static_invoice_seller.setVisibility(View.GONE);
        static_invoice_seller_city.setVisibility(View.GONE);
        static_invoice_seller_date.setVisibility(View.GONE);
        invoice_seller_name.setVisibility(View.GONE);
        invoice_seller_city.setVisibility(View.GONE);
        invoice_seller_date.setVisibility(View.GONE);
//
        static_invoice_delivery_fee.setVisibility(View.GONE);
        static_invoice_promo_code.setVisibility(View.GONE);
        static_invoice_payment_type.setVisibility(View.GONE);
        static_invoice_total_price.setVisibility(View.GONE);
        invoice_promo_code.setVisibility(View.GONE);
        invoice_delivery_fee.setVisibility(View.GONE);
        invoice_payment_type.setVisibility(View.GONE);
        invoice_total_price.setVisibility(View.GONE);

        final Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                if (response.isEmpty())
                {
                    Toast.makeText(SelesaiPesananActivity.this, "You haven't made an order yet!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                try
                {

                    JSONArray jsonResponse = new JSONArray(response);
                    for (int i=0; i<jsonResponse.length(); i++)
                    {
                        JSONObject jsonInvoice = jsonResponse.getJSONObject(i);
                        invoiceStatus = jsonInvoice.getString("invoiceStatus");
                        invoiceId = jsonInvoice.getInt("id");
                        date = jsonInvoice.getString("date");
                        paymentType = jsonInvoice.getString("paymentType");
                        totalPrice = jsonInvoice.getInt ("totalPrice");
                        invoice_seller_date.setText(date.substring(0,10));
                        invoice_payment_type.setText(paymentType);
                        invoice_total_price.setText("Rp. " + totalPrice);

                        JSONObject jsonCustomer = jsonInvoice.getJSONObject("customer");
                        customer = jsonCustomer.getString("name");
                        invoice_customer_name.setText(customer);

                        JSONArray jsonFood = jsonInvoice.getJSONArray("foods");
                        for (int j=0; j<jsonFood.length(); j++)
                        {
                            JSONObject jsonFoodObj = jsonFood.getJSONObject(j);
                            foodName = jsonFoodObj.getString("name");
                            invoice_food_name.setText(foodName);

                            JSONObject jsonFoodSeller = jsonFoodObj.getJSONObject("seller");
                            sellerName = jsonFoodSeller.getString("name");
                            invoice_seller_name.setText(sellerName);

                            JSONObject jsonSellerLoc = jsonFoodSeller.getJSONObject("location");
                            location = jsonSellerLoc.getString("city");
                            invoice_seller_city.setText(location);
                        }

                        if (invoiceStatus.equals("Ongoing"))
                        {
                            btnCancel.setVisibility(View.VISIBLE);
                            btnDone.setVisibility(View.VISIBLE);

                            static_invoice_Customer.setVisibility(View.VISIBLE);
                            static_invoice_foodName.setVisibility(View.VISIBLE);
                            invoice_customer_name.setVisibility(View.VISIBLE);
                            invoice_food_name.setVisibility(View.VISIBLE);

                            static_invoice_seller.setVisibility(View.VISIBLE);
                            static_invoice_seller_city.setVisibility(View.VISIBLE);
                            static_invoice_seller_date.setVisibility(View.VISIBLE);
                            invoice_seller_name.setVisibility(View.VISIBLE);
                            invoice_seller_city.setVisibility(View.VISIBLE);
                            invoice_seller_date.setVisibility(View.VISIBLE);

                            static_invoice_warning.setVisibility(View.GONE);

                            static_invoice_payment_type.setVisibility(View.VISIBLE);
                            static_invoice_total_price.setVisibility(View.VISIBLE);
                            invoice_payment_type.setVisibility(View.VISIBLE);
                            invoice_total_price.setVisibility(View.VISIBLE);

                            if (paymentType.equals("Cash"))
                            {
                                static_invoice_delivery_fee.setVisibility(View.VISIBLE);
                                invoice_delivery_fee.setVisibility(View.VISIBLE);
                                deliveryFee = jsonInvoice.getInt("deliveryFee");
                                invoice_delivery_fee.setText("Rp. " + deliveryFee);
                            }

                            else if (paymentType.equals("Cashless"))
                            {
                                static_invoice_promo_code.setVisibility(View.VISIBLE);
                                invoice_promo_code.setVisibility(View.VISIBLE);
                                promoCode = jsonInvoice.getString("promo");
                                if (promoCode.equals(null))
                                {
                                    invoice_promo_code.setText("No promo code applied.");
                                }

                                else
                                {
                                    JSONObject jsonPromo = jsonInvoice.getJSONObject("promo");
                                    promoCode = jsonPromo.getString("code");
                                    invoice_promo_code.setText(promoCode);
                                }
                            }
                        }

//                        else
//                        {
//                            btnCancel.setVisibility(View.GONE);
//                            btnDone.setVisibility(View.GONE);
//
//                            static_invoice_Customer.setVisibility(View.GONE);
//                            static_invoice_foodName.setVisibility(View.GONE);
//                            invoice_customer_name.setVisibility(View.GONE);
//                            invoice_food_name.setVisibility(View.GONE);
//
//                            static_invoice_seller.setVisibility(View.GONE);
//                            static_invoice_seller_city.setVisibility(View.GONE);
//                            static_invoice_seller_date.setVisibility(View.GONE);
//                            invoice_seller_name.setVisibility(View.GONE);
//                            invoice_seller_city.setVisibility(View.GONE);
//                            invoice_seller_date.setVisibility(View.GONE);
//
//
//                            static_invoice_delivery_fee.setVisibility(View.GONE);
//                            static_invoice_payment_type.setVisibility(View.GONE);
//                            static_invoice_total_price.setVisibility(View.GONE);
//                            invoice_delivery_fee.setVisibility(View.GONE);
//                            invoice_payment_type.setVisibility(View.GONE);
//                            invoice_total_price.setVisibility(View.GONE);
//                        }
                        //Log.e("Test", jsonInvoice.toString());
                    }
                }

                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        PesananFetchRequest fetchRequest = new PesananFetchRequest(String.valueOf(currentUserId), responseListener);
        RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
        queue.add(fetchRequest);


        //tombol cancel
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
                Toast.makeText(SelesaiPesananActivity.this, "Your order has been canceled!", Toast.LENGTH_LONG).show();
                PesananBatalRequest batalRequest = new PesananBatalRequest(String.valueOf(invoiceId), cancelListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(batalRequest);

            }
        });

        //tombol done
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
                Toast.makeText(SelesaiPesananActivity.this, "Your order is finished!", Toast.LENGTH_LONG).show();
                PesananSelesaiRequest selesaiRequest = new PesananSelesaiRequest(String.valueOf(invoiceId), doneListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(selesaiRequest);
            }
        });

    }
}