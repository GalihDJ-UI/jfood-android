package galih.dj.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

//import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class BuatPesananActivity extends AppCompatActivity
{

    private int currentUserId;
    private int foodId;
    private String foodName;
    private String foodCategory;
    private double foodPrice;
    private String promoCode;
    private double deliveryFee = 5000;
    private String selectedPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);

        //component finalization
        final TextView textCode = findViewById(R.id.textCode);
        final TextView food_name = findViewById(R.id.food_name);
        final TextView food_category = findViewById(R.id.food_category);
        final TextView food_price = findViewById(R.id.food_price);
        final TextView total_price = findViewById(R.id.total_price);
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        final EditText promo_code = findViewById(R.id.promo_code);
        final TextView static_delivery_fee = findViewById(R.id.static_delivery_fee);
        final TextView delivery_fee = findViewById(R.id.delivery_fee);
        final Button hitung = findViewById(R.id.hitung);
        final Button pesan = findViewById(R.id.pesan);

        //bundle variable
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            currentUserId = extras.getInt("currentUserId");
            foodId = extras.getInt("item_id");
            foodName = extras.getString("item_name");
            foodCategory = extras.getString("item_category");
            foodPrice = extras.getInt("item_price");
        }

        //initial component visibility
        promo_code.setVisibility(View.GONE);
        textCode.setVisibility(View.GONE);
        static_delivery_fee.setVisibility(View.GONE);
        delivery_fee.setVisibility(View.GONE);

        pesan.setVisibility(View.GONE);

        food_name.setText(foodName);
        food_category.setText(foodCategory);
        food_price.setText("Rp. " + (int) foodPrice);
        total_price.setText("Rp. " + "0");
        delivery_fee.setText("RP. " + deliveryFee);

        //radio button check cash or cashless
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton radioButton = findViewById(checkedId);
                String selectRadio = radioButton.getText().toString().trim();
                switch (selectRadio)
                {
                    case "Via CASHLESS":
                        textCode.setVisibility(View.VISIBLE);
                        promo_code.setVisibility(View.VISIBLE);
                        static_delivery_fee.setVisibility(View.GONE);
                        delivery_fee.setVisibility(View.GONE);
                        break;

                    case "Via CASH":
                        textCode.setVisibility(View.GONE);
                        promo_code.setVisibility(View.GONE);
                        static_delivery_fee.setVisibility(View.VISIBLE);
                        delivery_fee.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        //count button function
        hitung.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int selectedRadioId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedRadioId);
                String selectRadio = radioButton.getText().toString().trim();
                switch (selectRadio)
                {
                    case "Via CASH":
                        total_price.setText("Rp. " + (foodPrice + deliveryFee));
                        break;

                    case "Via CASHLESS":
                        //Get Promo Code String
                        promoCode = promo_code.getText().toString();
                        //Listener Promo
                        final Response.Listener<String> promoResponse = new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {
                                if (promoCode.isEmpty())
                                {
                                    Toast.makeText(BuatPesananActivity.this, "No promo code applied!", Toast.LENGTH_LONG).show();
                                    total_price.setText("Rp. " + foodPrice);
                                }

                                else
                                {
                                    try
                                    {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        //Get Discount Price
                                        int promoDiscountPrice = jsonResponse.getInt("discount");
                                        int minimalDiscountPrice = jsonResponse.getInt("minPrice");
                                        boolean promoStatus = jsonResponse.getBoolean("active");

                                        if (promoStatus == false)
                                        {
                                            Toast.makeText(BuatPesananActivity.this, "This promo is unavailable!", Toast.LENGTH_LONG).show();
                                        }

                                        else if (promoStatus == true)
                                        {
                                            if (foodPrice < promoDiscountPrice || foodPrice < minimalDiscountPrice)
                                            {
                                                Toast.makeText(BuatPesananActivity.this, "Promo code is invalid!", Toast.LENGTH_LONG).show();
                                            }

                                            else
                                            {
                                                Toast.makeText(BuatPesananActivity.this, "Promo code applied!", Toast.LENGTH_LONG).show();
                                                //Set Total Price
                                                total_price.setText("Rp. " + (foodPrice - promoDiscountPrice));
                                            }
                                        }
                                    }

                                    catch (JSONException e)
                                    {
                                        Toast.makeText(BuatPesananActivity.this, "Promo code not found!", Toast.LENGTH_LONG).show();
                                        total_price.setText("Rp. " + foodPrice);
                                    }
                                }

                            }
                        };
                        Response.ErrorListener errorPromo = new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                Log.d("Error", "Error Occured", error);
                            }
                        };
                        //Volley Request for Promo Request
                        CheckPromoRequest promoRequest = new CheckPromoRequest(promoCode, promoResponse);
                        RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                        queue.add(promoRequest);
                        break;
                }

                hitung.setVisibility(View.GONE);
                pesan.setVisibility(View.VISIBLE);
            }
        });

        //order button function
        pesan.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int selectedRadioId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadio = findViewById(selectedRadioId);
                String selected = selectedRadio.getText().toString().trim();
                BuatPesananRequest request = null;

                final Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null)
                            {
                                Toast.makeText(BuatPesananActivity.this, "Your order has been saved!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("currentUserId", currentUserId);
                                startActivity(intent);
                            }

                            else
                            {
                                pesan.setVisibility(View.GONE);
                                Toast.makeText(BuatPesananActivity.this, "You still have pending order to be finished!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("currentUserId", currentUserId);
                                startActivity(intent);
                            }
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            Toast.makeText(BuatPesananActivity.this, "Order failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                if(selected.equals("Via CASH"))
                {
                    request = new BuatPesananRequest(String.valueOf(foodId), String.valueOf(currentUserId), responseListener);
                    RequestQueue queuePesan = Volley.newRequestQueue(BuatPesananActivity.this);
                    queuePesan.add(request);
                }

                else if(selected.equals("Via CASHLESS"))
                {
                    request = new BuatPesananRequest(String.valueOf(foodId), String.valueOf(currentUserId), promoCode, responseListener);
                    RequestQueue queuePesan = Volley.newRequestQueue(BuatPesananActivity.this);
                    queuePesan.add(request);
                }

            }
        });

    }
}
