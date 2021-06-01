package com.mra.drawer_menu_samplde;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mra.drawer_menu_samplde.adapters.NavDrawerRVAdapter;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    DrawerLayout drawer_menu;
    RecyclerView rv_drawer_menu;
    ImageView imv_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClick();
    }

    private void onClick() {
        imv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_menu.openDrawer(Gravity.RIGHT);
            }
        });
    }

    private void initView() {
        drawer_menu = findViewById(R.id.drawer_menu);
        rv_drawer_menu = findViewById(R.id.rv_drawer_menu);
        imv_menu = findViewById(R.id.imv_menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }

    private void setupDrawer() {

        rv_drawer_menu.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rv_drawer_menu.setAdapter(new NavDrawerRVAdapter(context).setOnItemClickListener(new NavDrawerRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, NavDrawerRVAdapter.DrawerItem item) {
                drawer_menu.closeDrawer(Gravity.RIGHT);
                if (item.getOutput().equals("")) {
                    Toast.makeText(context, "در حال توسعه", Toast.LENGTH_LONG).show();
                    return;
                }
                switch (item.getType()) {

                    case NavDrawerRVAdapter.ACTIVITY_TYPE:

                        drawer_menu.closeDrawer(Gravity.RIGHT);

                        try {
                            Class<?> classType = Class.forName(item.getOutput());
                            startActivity(new Intent(context, classType));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;

                    case NavDrawerRVAdapter.SHARE_TYPE:
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.putExtra(Intent.EXTRA_SUBJECT, "موضوع");
                        share.putExtra(Intent.EXTRA_TEXT, item.getOutput());
                        context.startActivity(Intent.createChooser(share, "عنوان"));
                        break;

                    case NavDrawerRVAdapter.CALL_TYPE:
                        Intent call = new Intent(Intent.ACTION_DIAL);
                        call.setData(Uri.parse("tel:" + item.getOutput()));
                        context.startActivity(call);
                        break;

                }
            }
        }));
    }


    @Override
    public void onBackPressed() {
        if (drawer_menu.isDrawerOpen(Gravity.RIGHT)){
            drawer_menu.closeDrawer(Gravity.RIGHT);
        }else {
            finish();
        }
    }
}