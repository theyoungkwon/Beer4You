package appcontest.practice.beerforyou4;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends FragmentActivity implements
        ActionBar.TabListener, TabText.OnWordSelectedListener, TabRecommender.OnBtnSelectedListener {

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
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "On Create ");
        // Set up the action bar.
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
        mViewPager
                .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
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
        Log.d("MainActivity", "On Resume ");
        // getting information of user rating when MainActivity resume

    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d("MainActivity", " On Pause ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
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
        switch( item.getItemId() ){
            //case R.id.action_voice:
            //    Toast.makeText(this, "voice", Toast.LENGTH_SHORT).show();
            //    return true;
            case R.id.action_search:
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
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

    // initiate InfoActivity class by clicking item in TabText
    public void onWordSelected(int position) {
        Log.i("Main Activity", " Method : onWordSelected ");
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        intent.putExtra("ARG_POSITION", String.valueOf(position));
        startActivityForResult(intent, GET_STRING);
    }

    public void onBtnSelected(int position) {
        Log.i("Main Activity", " Method : onBtnSelected ");
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        intent.putExtra("ARG_POSITION", String.valueOf(position));
        startActivityForResult(intent, GET_STRING);
    }

    // REQ_CODE_SPEECH_INPUT : getting text input from voice recognition api
    // GET_STRING : getting some information from InfoActivity class
    // CODE_SELECT_IMAGE : select image in external storage device
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case TabVoice.REQ_CODE_SPEECH_INPUT:
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //txtSpeechInput.setText(result.get(0));
                    Log.i("Main Activity", ": Voice data : "+ result.get(0));
                    // implement initiating the InfoActivity part by using the textSpeechInput
                    String text = result.get(0);
                    // search the matching text with DB and
                    // initiate the InfoActivity by using Intent
                    int position = DataForTest.findItemPositionByText(text);
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                    intent.putExtra("ARG_POSITION", String.valueOf(position));
                    startActivityForResult(intent, GET_STRING);
                }
                break;
            case TabImage.CODE_SELECT_IMAGE:
                Bitmap bitmap = null;
                if(resultCode == RESULT_OK && null != data){
                    Uri image = data.getData();
                    try{
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image);
                    } catch( FileNotFoundException e){
                        e.printStackTrace();
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                    //TODO
                    // sent image data to TapImage and display it.
                    //ImageView imgView = (ImageView) findViewById(R.id.imageview);
                    //imgView.setImageVitmap(bitmap);
                }
                break;
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
                    return new TabVoice(mContext);
                case 1:
                    return new TabImage(mContext);
                case 2:
                    return new TabText(mContext);
                case 3:
                    return new TabRecommender(mContext);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section4).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_dummy,
                    container, false);
            TextView dummyTextView = (TextView) rootView
                    .findViewById(R.id.section_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(
                    ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

}

