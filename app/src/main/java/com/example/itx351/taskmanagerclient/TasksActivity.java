package com.example.itx351.taskmanagerclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class TasksActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Overall overall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        overall = (Overall)getApplication();

        TextView lblDomainNameContent = (TextView)findViewById(R.id.sys_lblDomainNameContent);
        TextView lblKeywordContent = (TextView)findViewById(R.id.sys_lblKeywordContent);

        lblDomainNameContent.setText(overall.DomainName);
        lblKeywordContent.setText(overall.Keyword);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.navRun) {
            Intent runActivity = new Intent();
            runActivity.setClass(TasksActivity.this, RunActivity.class);
            startActivity(runActivity);
        } else if (id == R.id.navScreenshot) {
            Intent screenshotActivity = new Intent();
            ClientGeneralThread clientGeneralThread = new ClientGeneralThread(
                     DataHead.getDataHead("screenshotCommandHead"), overall.commandOutputStream, overall);
            clientGeneralThread.start();
            screenshotActivity.setClass(TasksActivity.this, ScreenshotActivity.class);
            startActivity(screenshotActivity);
        } else if (id == R.id.navKillprocess) {
            Intent killProcessActivity = new Intent();
            //killProcessActivity.setClass(TasksActivity.this, KillProcessActivity.class);
            startActivity(killProcessActivity);

        } else if (id == R.id.navShutdown) {
//            Intent shutdownActivity = new Intent();
//            shutdownActivity.setClass(TasksActivity.this, ShutdownActivity.class);
//            startActivity(shutdownActivity);
            
        } else if (id == R.id.navDisconnect) {
            Intent disconnectActivity = new Intent();
            //disconnectActivity.setClass(TasksActivity.this, DisconnectActivity.class);
            startActivity(disconnectActivity);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
