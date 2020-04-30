package galih.dj.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
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

                    for (int i = 0; i < jsonResponse.length(); i++) {
                        JSONObject food = jsonResponse.getJSONObject(i);
                        JSONObject seller = food.getJSONObject("seller");
                        JSONObject location = seller.getJSONObject("location");

                        listSeller.add(new Seller(seller.getInt("id"), seller.getString("name"), seller.getString("email"),
                                seller.getString("phoneNumber"), new Location(location.getString("province"),
                                location.getString("description"), location.getString("city"))));

                        foodIdList.add(new Food(food.getInt("id"), food.getString("name"),
                                listSeller.get(i), food.getInt("price"), food.getString("category")));
                    }

                    for (Seller sel : listSeller)
                    {
                        ArrayList<Food> temp = new ArrayList<>();
                        for (Food food : foodIdList)
                        {
                            if (food.getSeller().getName().equals(sel.getName()) || food.getSeller().getEmail().equals(sel.getEmail()) || food.getSeller().getPhoneNumber().equals(sel.getPhoneNumber())) {
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
    }
}

