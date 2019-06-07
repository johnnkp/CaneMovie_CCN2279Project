package hkcc.ccn2279.gp.canemovie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vrem.wifianalyzer.settings.Repository;
import com.vrem.wifianalyzer.settings.Settings;
import com.vrem.wifianalyzer.settings.SettingsFragment;
import com.vrem.wifianalyzer.settings.language.LocaleManager;

public class MainActivity extends AppCompatActivity {
    public static DrawerLayout drawer;
    public static BottomNavigationView navigation;
    public static NavigationView navigationView;
    public static int onNavigationItemSelectedId = R.id.nav_camera;
    public static FragmentManager fragmentManager;
    public static Context applicationContext;
    public static Settings settings;
    public static LocaleManager localeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = new Settings(new Repository(getApplicationContext()));
        settings.initializeDefaultValues();

        localeManager = new LocaleManager(this);
        if (settings.defaultLanguage()) {
            // https://www.loc.gov/standards/iso639-2/php/code_list.php
            localeManager.setNewLocale(this, "en");
        } else {
            localeManager.setNewLocale(this, "zh");
        }

        setContentView(R.layout.activity_main);
        layoutSetup();
        navigation.setSelectedItemId(R.id.navigation_home);
        navigationView.setCheckedItem(R.id.nav_camera);
        NavigationSelectedListener mNavigationSelectedListener = new NavigationSelectedListener();
        navigation.setOnNavigationItemSelectedListener(mNavigationSelectedListener.getNavigationItemSelectedListener());
        navigationView.setNavigationItemSelectedListener(mNavigationSelectedListener.getToolbarItemSelectedListener());
        fragmentManager = getSupportFragmentManager();
        applicationContext = getApplicationContext();

        // Display the fragment as the main content.
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.container, new HomeFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        drawer.openDrawer(GravityCompat.START);
        return true;
    }

    // http://blog.moagrius.com/android/android-get-system-defined-actionbar-size-height
    public int getActionBarSize() {
        TypedArray styledAttributes = getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return actionBarSize;
    }

    // https://alvinalexander.com/android/how-to-determine-android-screen-size-dimensions-orientation
    public int getScreenWidth() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    // https://alvinalexander.com/android/how-to-determine-android-screen-size-dimensions-orientation
    public void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        /* String displayName = display.getName();  // minSdkVersion=17+
        Log.i(TAG, "displayName  = " + displayName); */

        // display size in pixels
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        System.out.println("width        = " + width);
        System.out.println("height       = " + height);

        // pixels, dpi
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int heightPixels = metrics.heightPixels;
        int widthPixels = metrics.widthPixels;
        int densityDpi = metrics.densityDpi;
        float xdpi = metrics.xdpi;
        float ydpi = metrics.ydpi;
        System.out.println("widthPixels  = " + widthPixels);
        System.out.println("heightPixels = " + heightPixels);
        System.out.println("densityDpi   = " + densityDpi);
        System.out.println("xdpi         = " + xdpi);
        System.out.println("ydpi         = " + ydpi);

        // deprecated
        int screenHeight = display.getHeight();
        int screenWidth = display.getWidth();
        System.out.println("screenHeight = " + screenHeight);
        System.out.println("screenWidth  = " + screenWidth);

        // orientation (either ORIENTATION_LANDSCAPE, ORIENTATION_PORTRAIT)
        int orientation = getResources().getConfiguration().orientation;
        System.out.println("orientation  = " + orientation);
    }

    // https://stackoverflow.com/questions/32398104/how-to-set-a-custom-font-to-the-title-in-toolbar-android
    public void changeToolbarFont(Toolbar toolbar, Activity context) {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                if (tv.getText().equals(toolbar.getTitle())) {
                    // http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0608/8049.html
                    tv.setTypeface(ResourcesCompat.getFont(context, R.font.sf_pro_display_bold));
                    break;
                }
            }
        }
    }

    // https://stackoverflow.com/questions/21926644/get-height-and-width-of-a-layout-programmatically/41705445#41705445
    public static int getViewHeight(View view) {
        WindowManager wm =
                (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int deviceWidth;

        Point size = new Point();
        display.getSize(size);
        deviceWidth = size.x;

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        return view.getMeasuredHeight(); // view.getMeasuredWidth();
    }

    public void navBackOnClick(View v) {
        drawer.closeDrawer(GravityCompat.START);
    }

    public void MovieInfoActivity(View v) {
        // Display the fragment as the main content.
        fragmentManager.beginTransaction()
                .add(R.id.container, new MovieInfoFragment()).commit();
    }

    public void ChooseCinemaActivity(View v) {
        // Display the fragment as the main content.
        fragmentManager.beginTransaction()
                .add(R.id.container, new BuyTicketFragment()).commit();
    }

    public void layoutSetup() {
        Toolbar appBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(appBar);
        changeToolbarFont(appBar, this);
        if (getScreenWidth() < (480 + 72)) {
            appBar.setLogo(getResources().getDrawable(R.drawable.canemovie70));
        } else {
            appBar.getLogo().setBounds(0, 0, getActionBarSize(), getActionBarSize());
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, appBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        appBar.setNavigationIcon(R.drawable.ic_menu_black_24dp);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        LinearLayout mPrivacy = (LinearLayout) findViewById(R.id.drawer_privacy);
        mPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navBackOnClick(navigationView);

                // Display the fragment as the main content.
                fragmentManager.beginTransaction()
                        .add(R.id.container, new PrivacyFragment()).commit();
            }
        });

        LinearLayout mFaqHelp = (LinearLayout) findViewById(R.id.drawer_help);
        mFaqHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navBackOnClick(navigationView);

                // Display the fragment as the main content.
                fragmentManager.beginTransaction()
                        .add(R.id.container, new FaqHelpFragment()).commit();
            }
        });

        LinearLayout mSetting = (LinearLayout) findViewById(R.id.drawer_setting);
        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navBackOnClick(navigationView);

                // Display the fragment as the main content.
                fragmentManager.beginTransaction()
                        .add(R.id.container, new SettingsPage()).commit();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment, new SettingsFragment()).commit();

                settings.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                        // https://android--code.blogspot.com/2016/03/android-how-to-restart-activity.html
                        recreate();
                    }
                });
            }
        });
    }
}
