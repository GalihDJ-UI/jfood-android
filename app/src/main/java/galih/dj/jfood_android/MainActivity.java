package galih.dj.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import android.app.AlertDialog;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{

    //private int deliveryFee = 5000;
    //private String promoCode;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    private ArrayList<Seller> listSeller = new ArrayList<>();
    private ArrayList<Food> foodIdList = new ArrayList<>();
    private HashMap<Seller, ArrayList<Food>> childMapping = new HashMap<>();
    private static int currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            currentUserId = extras.getInt("currentUserId");
        }

        expListView = findViewById(R.id.lvExp);
        final Button btnCheckout = findViewById(R.id.btnCheckout);
        //Toast.makeText(MainActivity.this, String.valueOf(currentUserId), Toast.LENGTH_LONG).show();
        refreshList();

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l)
            {

                Intent intent = new Intent(MainActivity.this, BuatPesananActivity.class);

                int foodId = childMapping.get(listSeller.get(i)).get(i1).getId();
                String foodName = childMapping.get(listSeller.get(i)).get(i1).getName();
                String foodCategory = childMapping.get(listSeller.get(i)).get(i1).getCategory();
                int foodPrice = childMapping.get(listSeller.get(i)).get(i1).getPrice();

                intent.putExtra("item_id",foodId);
                intent.putExtra("item_name",foodName);
                intent.putExtra("item_category",foodCategory);
                intent.putExtra("item_price",foodPrice);
                //intent.putExtra("item_deliveryfee",deliveryFee);
                //intent.putExtra("item_promocode",promoCode);
                intent.putExtra("currentUserId", currentUserId);

                startActivity(intent);
                return true;
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SelesaiPesananActivity.class);
                intent.putExtra("currentUserId", currentUserId);
                startActivity(intent);
            }
        });
    }

    protected void refreshList()
    {
        Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONArray jsonResponse = new JSONArray(response);
                    for (int i=0; i<jsonResponse.length(); i++)
                    {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);

                        JSONObject food = jsonResponse.getJSONObject(i);
                        JSONObject seller = food.getJSONObject("seller");
                        JSONObject location = seller.getJSONObject("location");

                        Location newLocation = new Location(
                                location.getString("province"),
                                location.getString("description"),
                                location.getString("city")
                        );

                        Seller newSeller = new Seller(
                                seller.getInt("id"),
                                seller.getString("name"),
                                seller.getString("email"),
                                seller.getString("phoneNumber"),
                                newLocation
                        );

                        Log.e("SELLER", seller.getString("name"));

                        Food newFood = new Food(
                                food.getInt("id"),
                                food.getString("name"),
                                newSeller,
                                food.getInt("price"),
                                food.getString("category")
                        );

                        foodIdList.add(newFood);

                        boolean status = true;
                        for(Seller sel : listSeller)
                        {
                            if(sel.getId() == newSeller.getId())
                            {
                                status = false;
                            }
                        }

                        if(status)
                        {
                            listSeller.add(newSeller);
                        }
                    }

                    for(Seller sel : listSeller)
                    {
                        ArrayList<Food> tempFoodList = new ArrayList<>();
                        for(Food foodPtr : foodIdList)
                        {
                            if(foodPtr.getSeller().getId() == sel.getId())
                            {
                                tempFoodList.add(foodPtr);
                            }
                        }
                        childMapping.put(sel, tempFoodList);
                    }

                    Log.e("SELLER", listSeller.toString());

                    listAdapter = new MainListAdapter(MainActivity.this, listSeller, childMapping);
                    expListView.setAdapter(listAdapter);
                }
                catch (JSONException e)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Load Data Failed.").create().show();
                }
            }
        };

        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }
}

