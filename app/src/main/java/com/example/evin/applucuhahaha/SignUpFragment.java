package com.example.evin.applucuhahaha;


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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static android.R.attr.country;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    public SignUpFragment() {
        // Required empty public constructor
    }

    EditText username;
    EditText password;
    EditText repassword;
    String text="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_sign_up, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button btnSignUp = (Button) v.findViewById(R.id.btnSignUp);
        Button btnBack = (Button) v.findViewById(R.id.btnBack);
        username = (EditText) v.findViewById(R.id.edtUpUsername);
        password = (EditText) v.findViewById(R.id.edtUpPassword);
        repassword = (EditText) v.findViewById(R.id.edtUpRePassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().length() != 0 && password.getText().length() != 0 && repassword.getText().length() != 0) {
                    if (repassword.getText().toString().equals(password.getText().toString())) {
                        try {
                            canRegister();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        if ((text).equals("Yes")) {
                            FragmentManager fm = getActivity().getSupportFragmentManager();
                            FragmentTransaction trans = fm.beginTransaction();
                            fm.popBackStack();

                            LoginFragment login = new LoginFragment();
                            trans.replace(R.id.login, login);
                            trans.commit();
                            Toast.makeText(getContext(), "Register successfully!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "Take another username!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Password mismatch!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Please fill all data!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction trans = fm.beginTransaction();
                fm.popBackStack();

                LoginFragment login = new LoginFragment();
                trans.replace(R.id.login, login);
                trans.commit();
            }
        });
        return v;
    }

    public void canRegister() throws UnsupportedEncodingException
    {
        BufferedReader reader = null;
        String data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username.getText().toString(),"UTF-8")+"&"+
                      URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password.getText().toString(),"UTF-8");
        try {
            URL url = new URL("http://103.52.146.34/penir/penir17/validasi.php?validasi=register");
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
            text= sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {}
        }
    }
}
