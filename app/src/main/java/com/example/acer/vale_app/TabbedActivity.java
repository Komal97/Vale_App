package com.example.acer.vale_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class TabbedActivity extends AppCompatActivity {

    private TabLayout tablayout;
    private ViewPager viewpager;
    private ViewPagerAdapter adapter;
   // private Button btnAccept;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        tablayout=(TabLayout)findViewById(R.id.tablayout);
        viewpager=(ViewPager)findViewById(R.id.viewpager);
       /* btnAccept= (Button) findViewById(R.id.btnaccept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(TabbedActivity.this,ReachedActivity.class);
                startActivity(i1);
            }
        });*/


        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                Fragment f=null;
                switch (position)
                {
                    case 0:
                        f=new HomeFragment();
                        break;
                    case 1:
                        f=new TripsFragment();
                        break;
                    case 2:
                        f=new MyAccountFragment();
                        break;

                }
                return f;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        final TabLayout.Tab home=tablayout.newTab();
        final TabLayout.Tab trips=tablayout.newTab();
        final TabLayout.Tab account=tablayout.newTab();



        home.setIcon(R.drawable.homeblack);
        trips.setIcon(R.drawable.cargrey);
        account.setIcon(R.drawable.accountgrey);

        home.setText("Home");
        trips.setText("Trips");
        account.setText("My Account");

        tablayout.addTab(home,0);
        tablayout.addTab(trips,1);
        tablayout.addTab(account,2);

        tablayout.setTabTextColors(ContextCompat.getColorStateList(this,R.color.tab_selector));
        tablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.indicate));

        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        home.setIcon(R.drawable.homeblack);
                        trips.setIcon(R.drawable.cargrey);
                        account.setIcon(R.drawable.accountgrey);
                        break;

                    case 1:
                        home.setIcon(R.drawable.homegrey);
                        trips.setIcon(R.drawable.carblack);
                        account.setIcon(R.drawable.accountgrey);
                        break;

                    case 2:
                        home.setIcon(R.drawable.homegrey);
                        trips.setIcon(R.drawable.cargrey);
                        account.setIcon(R.drawable.accountblack);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent i1=new Intent(TabbedActivity.this,OtpActivity.class);
        startActivity(i1);
    }

}
