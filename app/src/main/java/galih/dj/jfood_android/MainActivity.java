package galih.dj.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    private ArrayList<Seller> listSeller = new ArrayList<>();
    private ArrayList<Food> foodIdList = new ArrayList<>();
    private HashMap<Seller, ArrayList<Food>> childMapping = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandableListView expListView = null;
        MainListAdapter listAdapter = new MainListAdapter(MainActivity.this, listSeller, childMapping);
        expListView.setAdapter(listAdapter);
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

                    for (int i = 0; i < jsonResponse.length(); i++)
                    {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        JSONObject food = jsonResponse.getJSONObject(i);
                        JSONObject seller = food.getJSONObject("seller");
                        JSONObject location = seller.getJSONObject("location");

                        Location newLocation = new Location(
                                location.getString("province"),
                                location.getString("description"),
                                location.getString("city"));

                        Seller newSeller = new Seller(
                                seller.getInt("id"),
                                seller.getString("name"),
                                seller.getString("email"),
                                seller.getString("phoneNumber"),
                                newLocation);

                        Log.e("SELLER", seller.getString("name"));

                        Food newFood = new Food(
                                food.getInt("id"),
                                food.getString("name"),
                                newSeller,
                                food.getInt("price"),
                                food.getString("category"));

                        foodIdList.add(newFood);

                        boolean status = true;
                        for(Seller sellerStat : listSeller)
                        {
                            if(sellerStat.getId() == newSeller.getId())
                            {
                                status = false;
                            }
                        }
                        if(status)
                        {
                            listSeller.add(newSeller);
                        }
                    }

                    for (Seller sel : listSeller)
                    {
                        ArrayList<Food> temp = new ArrayList<>();
                        for (Food food : foodIdList)
                        {
                            if(food.getSeller().getId() == sel.getId())
                            {
                                temp.add(food);
                            }
                        }
                        childMapping.put(sel, temp);
                    }
                }

                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }
}

