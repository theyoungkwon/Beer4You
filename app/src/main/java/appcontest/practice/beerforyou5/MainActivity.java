package appcontest.practice.beerforyou5;

//import android.support.v7.app.ActionBar;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends FragmentActivity implements
        ActionBar.TabListener, TabRecognize.OnBtnSeeSelectedListener, TabRecommender.OnBtnSelectedListener, TabText.OnTextBtnSelectedListener {

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
    public static final int GET_STRING = 1;
    static final int CODE_SELECT_IMPORTEDBEER = 201;
    static final int CODE_SELECT_TAILOREDBEER = 202;
    static final int CODE_SELECT_COUNTRY = 401;
    static final int CODE_SELECT_DEGREE = 402;
    static final int CODE_SELECT_TASTE = 403;

    public static int INFO_SIGNAL = 0;
    public static final String PREFS_USER_RATING = "UserRatings";
    public static final String PREFS_USER_REVIEW = "UserReviews";

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
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
            boolean preselected = (i == 1);
            actionBar.addTab(actionBar.newTab()
                    .setText(mSectionsPagerAdapter.getPageTitle(i))
                    .setTabListener(this), preselected);
        }
        if( savedInstanceState != null){
            mTabSelected = savedInstanceState.getInt(TAB_SELECTED);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onPause(){
        super.onPause();
    }

    // Save the activity state when it's going to stop.
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(TAB_SELECTED, getActionBar().getSelectedNavigationIndex());
    }

    // Recover the saved state when the activity is recreated.
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
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
        switch( item.getItemId() ){
            case R.id.action_settings:
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

    ///// RECOGNIZE BUTTON /////
    public void onBtnSeeSelected() {
        Intent intent = new Intent(MainActivity.this, appcontest.practice.beerforyou5.VisionHelper.RecognizeActivity.class);
        startActivityForResult(intent, GET_STRING);
    }

    ///// RECOMMENDATION BUTTONS /////
    public void onTextBtnSelected(int position, int type){
        switch( type ) {
            case 0:
                Intent intent0 = new Intent(MainActivity.this, appcontest.practice.beerforyou5.Recommend.ImportedBeer.class);
                startActivityForResult(intent0, CODE_SELECT_IMPORTEDBEER);
                break;
            case 1:
                Intent intent1 = new Intent(MainActivity.this, appcontest.practice.beerforyou5.Recommend.TailoredBeer.class);
                startActivityForResult(intent1, CODE_SELECT_TAILOREDBEER);
                break;
            case 2:
                Intent intent2 = new Intent(MainActivity.this, appcontest.practice.beerforyou5.InfoActivity.class);
                intent2.putExtra("ARG_POSITION", String.valueOf(position));
                startActivityForResult(intent2, GET_STRING);
                break;
            default:
        }
    }

    ///// CATEGORY BUTTONS /////
    public void onBtnSelected(int position, int type) {
        switch( type ){
            case 0:
                Intent intent0 = new Intent(MainActivity.this, appcontest.practice.beerforyou5.Country.ActivityCountry.class);
                intent0.putExtra("ARG_POSITION", String.valueOf(position));
                startActivityForResult(intent0, CODE_SELECT_COUNTRY);
                break;
            case 1:
                Intent intent1 = new Intent(MainActivity.this, appcontest.practice.beerforyou5.Degree.ActivityDegree.class);
                intent1.putExtra("ARG_POSITION", String.valueOf(position));
                startActivityForResult(intent1, CODE_SELECT_DEGREE);
                break;
            case 2:
                Intent intent2 = new Intent(MainActivity.this, appcontest.practice.beerforyou5.Taste.ActivityTaste.class);
                intent2.putExtra("ARG_POSITION", String.valueOf(position));
                startActivityForResult(intent2, CODE_SELECT_TASTE);
                break;
            default:
        }
    }

    // REQ_CODE_SPEECH_INPUT : getting text input from voice recognition api
    // GET_STRING : getting some information from InfoActivity class
    // CODE_SELECT_IMAGE : select image in external storage device
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case TabRecognize.REQ_CODE_SPEECH_INPUT:
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    // implement initiating the InfoActivity part by using the textSpeechInput
                    String text = result.get(0);
                    // search the matching text with DB and
                    // initiate the InfoActivity by using Intent
                    int position = appcontest.practice.beerforyou5.Library.DataForTest.findItemPositionByText(text);
                    Intent intent = new Intent(MainActivity.this, appcontest.practice.beerforyou5.InfoActivity.class);
                    intent.putExtra("ARG_POSITION", String.valueOf(position));
                    startActivityForResult(intent, GET_STRING);
                }
                break;
            case GET_STRING:
                if(resultCode == RESULT_OK && null != data){
                    String input_text = data.getStringExtra("INPUT_TEXT");
                    if( input_text != null) {
                        INFO_SIGNAL = 1;
                    }
                }
                break;
            case CODE_SELECT_COUNTRY:
                break;
            case CODE_SELECT_DEGREE:
                break;
            case CODE_SELECT_TASTE:
                break;
            case CODE_SELECT_IMPORTEDBEER:
                break;
            case CODE_SELECT_TAILOREDBEER:
                break;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
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
                    return new TabRecognize(mContext);
                case 1:
                    return new TabText(mContext);
                case 2:
                    return new TabRecommender(mContext);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1);
                case 1:
                    return getString(R.string.title_section3);
                case 2:
                    return getString(R.string.title_section4);
            }
            return null;
        }
    }
}

