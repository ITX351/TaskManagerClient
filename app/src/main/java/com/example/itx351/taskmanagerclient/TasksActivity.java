package com.example.itx351.taskmanagerclient;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.itx351.taskmanagerclient.dummy.DummyContent;

public class TasksActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TasksFragment.OnListFragmentInteractionListener{

    public Overall overall;
    private FragmentManager fragmentManager;

    enum fragmentType {
        InfoPage, TaskPage, RunProcessPage, KillProcessPage
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        String strShow = "ImageName " + item.ImageName + "\nPID " + item.PID +
                "\nSessionName " + item.SessionName + "\nSession" + item.Session +
                "\nMemUsage " + item.MemUsage;
        Toast.makeText(TasksActivity.this, strShow, Toast.LENGTH_LONG).show();

//        Dialog dialog = new Dialog(TasksActivity.this);
//        dialog.setTitle("Process Details");
//        dialog.setContentView(R.layout.dialog_view);

    }

    private void changeFragment(fragmentType now)
    {
        Fragment fragment;
        switch (now) {
            case RunProcessPage:
                fragment = ProcessFragment.newInstance(0);
                break;
            case KillProcessPage:
                fragment = ProcessFragment.newInstance(1);
                break;
            case TaskPage:
                fragment = TasksFragment.newInstance(1);
                break;
            case InfoPage:
                fragment = InfoFragment.newInstance();
                break;
            default:
                fragment = new Fragment();
                break;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.tasks_content, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        overall = (Overall)getApplication();
        fragmentManager = getSupportFragmentManager();

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
        ClientGeneralThread clientGeneralThread;

        switch (id) {
            case R.id.navInformation:
                changeFragment(fragmentType.InfoPage);
                break;
            case R.id.navTasks:
                changeFragment(fragmentType.TaskPage);
                break;
            case R.id.navRunProcess:
                changeFragment(fragmentType.RunProcessPage);
                break;
            case R.id.navScreenshot:
                clientGeneralThread = new ClientGeneralThread(
                        DataHead.getDataHead("screenshotCommandHead"), overall.commandOutputStream, overall);
                clientGeneralThread.start();
                break;
            case R.id.navKillProcess:
                changeFragment(fragmentType.KillProcessPage);
                break;
            case R.id.navShutdown:
                clientGeneralThread = new ClientGeneralThread(
                        DataHead.getDataHead("shutdownCommandHead"), overall.commandOutputStream, overall);
                clientGeneralThread.start();
                break;
            case R.id.navDisconnect:
                clientGeneralThread = new ClientGeneralThread(
                        DataHead.getDataHead("disconnectCommandHead"), overall.commandOutputStream, overall);
                clientGeneralThread.start();
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
