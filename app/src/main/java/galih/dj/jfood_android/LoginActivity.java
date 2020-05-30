package galih.dj.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email_log = findViewById(R.id.email_log);
        final EditText password_log = findViewById(R.id.password_log);
        final Button btnLogin = findViewById(R.id.btnLogin);
        final TextView tvRegister = findViewById(R.id.tvRegister);


        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String email = email_log.getText().toString();
                String password = password_log.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null)
                            {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                loginIntent.putExtra("currentUserId", jsonObject.getInt("id"));
                                //loginIntent.putExtra("currentUserName", jsonObject.getString("name"));
                                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(loginIntent);
                                finish();
                            }
                        }

                        catch (JSONException e)
                        {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("ERROR", "Error occurred", error);
                    }
                };
                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


}
