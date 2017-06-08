package com.example.evin.applucuhahaha;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static MainActivity instance = null;
    public ViewPager vp;
    public TabLayout tabs;
    public NavigationView nv;
    public DrawerLayout dl;
    public static FragmentManager fm;
    public static ArrayList<ArrayList<Post>> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent main = getIntent();
        instance = this;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vp = (ViewPager) findViewById(R.id.viewpager);
        // setupViewPager(vp);

        tabs = (TabLayout) findViewById(R.id.tabs);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabLayout.Tab tab = tabs.getTabAt(position);
                tab.select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        dl = (DrawerLayout) findViewById(R.id.drawer);

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                dl.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.itemMyPost:
                        nv.getMenu().getItem(0).setChecked(true);
                        break;
                    case R.id.itemLikedPost:
                        nv.getMenu().getItem(1).setChecked(true);
                        break;
                }

                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "FAB ditekan!", Snackbar.LENGTH_LONG).setAction("Action",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dl.openDrawer(GravityCompat.START);
                            }
                        }).show();
            }
        });

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);

        }

        ReadData rd = new ReadData(this);
        rd.execute("http://103.52.146.34/penir/penir17/penir.php");
    }

    private  void setupViewPager() {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter = new Adapter(getSupportFragmentManager());

        FunnyContentFragment cd1 = new FunnyContentFragment();
        cd1.newInstance(posts.get(0));
        adapter.addFragment(cd1);
        GamingContentFragment cd2 = new GamingContentFragment();
        cd2.newInstance(posts.get(1));
        adapter.addFragment(cd2);
        WTFContentFragment cd3 = new WTFContentFragment();
        cd3.newInstance(posts.get(2));
        adapter.addFragment(cd3);

        vp.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            dl.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    public static void readDataFinish(Context applicationContext, String result) {
        // Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        try {
            JSONObject json = new JSONObject(result);
            JSONArray json2 = json.getJSONArray("post");
            posts = new ArrayList<ArrayList<Post>>();
            posts.add(new ArrayList<Post>());
            posts.add(new ArrayList<Post>());
            posts.add(new ArrayList<Post>());

            for (int i = 0; i < json2.length(); i++) {
                JSONObject c = json2.getJSONObject(i);
                int id = c.getInt("id");
                String caption = c.getString("caption");
                String gambar = c.getString("gambar");
                int likez = c.getInt("likez");
                int dislikez = c.getInt("dislikez");
                int id_kategori = c.getInt("id_kategori");
                String id_user = c.getString("id_user");
                if(id_kategori == 1)
                    posts.get(0).add(new Post(id, caption, gambar, likez, dislikez, id_kategori, id_user));
                else if(id_kategori == 2)
                    posts.get(1).add(new Post(id, caption, gambar, likez, dislikez, id_kategori, id_user));
                else if(id_kategori == 3)
                    posts.get(2).add(new Post(id, caption, gambar, likez, dislikez, id_kategori, id_user));
            }
            instance.setupViewPager();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
