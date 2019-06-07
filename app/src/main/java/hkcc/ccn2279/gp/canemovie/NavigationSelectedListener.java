package hkcc.ccn2279.gp.canemovie;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import android.widget.Toast;

import static hkcc.ccn2279.gp.canemovie.MainActivity.applicationContext;
import static hkcc.ccn2279.gp.canemovie.MainActivity.drawer;
import static hkcc.ccn2279.gp.canemovie.MainActivity.fragmentManager;
import static hkcc.ccn2279.gp.canemovie.MainActivity.navigation;
import static hkcc.ccn2279.gp.canemovie.MainActivity.navigationView;
import static hkcc.ccn2279.gp.canemovie.MainActivity.onNavigationItemSelectedId;

public class NavigationSelectedListener {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // Display the fragment as the main content.
                    fragmentManager.beginTransaction()
                            .add(R.id.container, new HomeFragment()).commit();
                    try {
                        if (onNavigationItemSelectedId != R.id.nav_camera)
                            onNavigationItemSelectedId = R.id.nav_camera;
                        navigationView.setCheckedItem(R.id.nav_camera);
                    } catch (StackOverflowError e) {
                        garbageCollection();
                    }
                    return true;

                case R.id.navigation_forum:
                    // Display the fragment as the main content.
                    fragmentManager.beginTransaction()
                            .add(R.id.container, new ForumFragment()).commit();
                    try {
                        if (onNavigationItemSelectedId != R.id.nav_manage)
                            onNavigationItemSelectedId = R.id.nav_manage;
                        navigationView.setCheckedItem(R.id.nav_manage);
                    } catch (StackOverflowError e) {
                        garbageCollection();
                    }
                    return true;

                case R.id.navigation_news:
                    // Display the fragment as the main content.
                    fragmentManager.beginTransaction()
                            .add(R.id.container, new LatestNewsFragment()).commit();
                    try {
                        if (onNavigationItemSelectedId != R.id.nav_gallery)
                            onNavigationItemSelectedId = R.id.nav_gallery;
                        navigationView.setCheckedItem(R.id.nav_gallery);
                    } catch (StackOverflowError e) {
                        garbageCollection();
                    }
                    return true;
            }
            return false;
        }
    };

    private NavigationView.OnNavigationItemSelectedListener mOnToolbarItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            switch (item.getItemId()) {
                case R.id.nav_camera:
                    // Display the fragment as the main content.
                    fragmentManager.beginTransaction()
                            .add(R.id.container, new HomeFragment()).commit();
                    try {
                        if (navigation.getSelectedItemId() != R.id.navigation_home)
                            navigation.setSelectedItemId(R.id.navigation_home);
                        if (onNavigationItemSelectedId != R.id.nav_camera)
                            onNavigationItemSelectedId = R.id.nav_camera;
                        navigationView.setCheckedItem(R.id.nav_camera);
                    } catch (StackOverflowError e) {
                        garbageCollection();
                    }
                    drawer.closeDrawer(GravityCompat.START);
                    return true;

                case R.id.nav_gallery:
                    // Display the fragment as the main content.
                    fragmentManager.beginTransaction()
                            .add(R.id.container, new LatestNewsFragment()).commit();
                    try {
                        if (navigation.getSelectedItemId() != R.id.navigation_news)
                            navigation.setSelectedItemId(R.id.navigation_news);
                        if (onNavigationItemSelectedId != R.id.nav_gallery)
                            onNavigationItemSelectedId = R.id.nav_gallery;
                        navigationView.setCheckedItem(R.id.nav_gallery);
                    } catch (StackOverflowError e) {
                        garbageCollection();
                    }
                    drawer.closeDrawer(GravityCompat.START);
                    return true;

                case R.id.nav_slideshow:
                    // Display the fragment as the main content.
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, new CinemaCircuitFragment()).commit();
                    try {
                        if (onNavigationItemSelectedId != R.id.nav_slideshow)
                            onNavigationItemSelectedId = R.id.nav_slideshow;
                        navigationView.setCheckedItem(R.id.nav_slideshow);
                    } catch (StackOverflowError e) {
                        garbageCollection();
                    }
                    drawer.closeDrawer(GravityCompat.START);
                    return true;

                case R.id.nav_manage:
                    // Display the fragment as the main content.
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, new ForumFragment()).commit();
                    try {
                        if (navigation.getSelectedItemId() != R.id.navigation_forum)
                            navigation.setSelectedItemId(R.id.navigation_forum);
                        if (onNavigationItemSelectedId != R.id.nav_manage)
                            onNavigationItemSelectedId = R.id.nav_manage;
                        navigationView.setCheckedItem(R.id.nav_manage);
                    } catch (StackOverflowError e) {
                        garbageCollection();
                    }
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
            }

            return false;
        }
    };

    private void garbageCollection() {
        System.gc();
        Toast.makeText(applicationContext, "StackOverflowError: Not enough RAM", Toast.LENGTH_LONG).show();
    }

    public BottomNavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener() {
        return mOnNavigationItemSelectedListener;
    }

    public NavigationView.OnNavigationItemSelectedListener getToolbarItemSelectedListener() {
        return mOnToolbarItemSelectedListener;
    }
}
