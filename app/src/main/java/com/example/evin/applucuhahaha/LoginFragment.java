package com.example.evin.applucuhahaha;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    EditText username;
    EditText password;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_login, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button btnLogin = (Button) v.findViewById(R.id.btnLogin);
        Button btnSignUp = (Button) v.findViewById(R.id.btnSignUp);
        username = (EditText) v.findViewById(R.id.edtUsername);
        password = (EditText) v.findViewById(R.id.edtPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().length() != 0 && password.getText().length() != 0) {
                    try {
                        if(canLogin()){
                            Intent mainActivitiy = new Intent(v.getContext(), MainActivity.class);
                            mainActivitiy.putExtra("username", username.getText().toString());
                            startActivity(mainActivitiy);
                            Toast.makeText(getContext(), "Login successfully!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "Data invalid!", Toast.LENGTH_LONG).show();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), "Please fill all data!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();

                SignUpFragment register = new SignUpFragment();
                trans.addToBackStack(null);
                trans.replace(R.id.login, register);
                trans.commit();
            }
        });

        return v;
    }

    public Boolean canLogin() throws UnsupportedEncodingException
    {
        BufferedReader reader = null;
        String data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username.getText().toString(),"UTF-8")+"&"+
                URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password.getText().toString(),"UTF-8");
        try {
            URL url = new URL("http://103.52.146.34/penir/penir17/validasi.php?validasi=login");
            URLConnection httpURLConnection = url.openConnection();
            httpURLConnection.setDoOutput(true);
            OutputStreamWriter os = new OutputStreamWriter(httpURLConnection.getOutputStream());
            os.write(data);
            os.flush();

            reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                sb.append(line);
            if(sb.toString().equals("Yes"))
                return true;
            else
                return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                reader.close();
            } catch (Exception e) {}
        }
    }
}
