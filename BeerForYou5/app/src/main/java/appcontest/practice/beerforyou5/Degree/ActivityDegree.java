package appcontest.practice.beerforyou5.Degree;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import java.util.Locale;

import appcontest.practice.beerforyou5.R;

/**
 * Created by jiuser on 2015-11-04.
 */
public class ActivityDegree extends FragmentActivity implements
        ActionBar.TabListener, TabDegree1.OnTabDegree1WordSelectedListener, TabDegree2.OnTabDegree2WordSelectedListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
    private final String TAB_SELECTED = "tab_selected";
    private int mTabSelected;
    static final int GET_STRING = 1;
    public static int INFO_SIGNAL = 0;

    public static final String PREFS_USER = "UserRatings";
    private String pos;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.country_activity);
        Log.d("ActivityDegree", "On Create ");
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Intent intent = getIntent();
        pos = intent.getExtras().getString("ARG_POSITION").toString();


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.country_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            boolean preselected = (i == 0);
            actionBar.addTab(actionBar.newTab()
                    .setText(mSectionsPagerAdapter.getPageTitle(i))
                    .setTabListener(this), preselected);
        }
        if (savedInstanceState != null) {
            mTabSelected = savedInstanceState.getInt(TAB_SELECTED);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ActivityDegree", "On Resume ");
        // getting information of user rating when ActivityDegree resume

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ActivityDegree", " On Pause ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TAB_SELECTED, getActionBar().getSelectedNavigationIndex());
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
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                // TODO
                // Implement "My Page Menu"
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab,
                              FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {
    }

    public void onTabDegree1WordSelected(int position) {
        Log.i("Main Activity", " Method : onWordSelected ");
        Intent intent = new Intent(ActivityDegree.this, appcontest.practice.beerforyou5.InfoActivity.class);
        intent.putExtra("ARG_POSITION", String.valueOf(position));
        startActivityForResult(intent, GET_STRING);
    }
    public void onTabDegree2WordSelected(int position) {
        Log.i("Main Activity", " Method : onWordSelected ");
        Intent intent = new Intent(ActivityDegree.this, appcontest.practice.beerforyou5.InfoActivity.class);
        intent.putExtra("ARG_POSITION", String.valueOf(position));
        startActivityForResult(intent, GET_STRING);
    }
    public void onTab3WordSelected(int position) {
        Log.i("Main Activity", " Method : onWordSelected ");
        Intent intent = new Intent(ActivityDegree.this, appcontest.practice.beerforyou5.InfoActivity.class);
        intent.putExtra("ARG_POSITION", String.valueOf(position));
        startActivityForResult(intent, GET_STRING);
    }

    // REQ_CODE_SPEECH_INPUT : getting text input from voice recognition api
    // GET_STRING : getting some information from InfoActivity class
    // CODE_SELECT_IMAGE : select image in external storage device
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {

            case GET_STRING:
                if(resultCode == RESULT_OK && null != data){
                    String input_text = data.getStringExtra("INPUT_TEXT");
                    if( input_text != null) {
                        Log.i("RATING SCORE", input_text);
                        INFO_SIGNAL = 1;
                        Toast.makeText(getBaseContext(), input_text, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        Context mContext;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a DummySectionFragment (defined as a static inner class
            // below) with the page number as its lone argument.
            switch(position) {
                case 0:
                    return new TabDegree1(mContext);
                case 1:
                    return new TabDegree2(mContext);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.degree_title_section1);
                case 1:
                    return getString(R.string.degree_title_section2);
            }
            return null;
        }
    }
}