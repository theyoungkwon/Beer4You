package appcontest.practice.beerforyou5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class InfoActivity extends ActionBarActivity {
    private ImageView imageView;
    private TextView name_eng;
    private TextView nation;
    private TextView user_rating;
    private TextView alcohol_degree;
    private TextView explanation;
    private TextView user_review_title;
    private EditText user_review_content;
    private String storedUserReview;
    private String storedUserRating;

    final static String ARG_POSITION = "position";
    private int mCurrentPosition = -1;

    private String position;
    private RatingBar ratingBar;
    private String ratings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getIntent();
        position = intent.getExtras().getString("ARG_POSITION").toString();
        SharedPreferences userReview = getSharedPreferences(MainActivity.PREFS_USER_REVIEW, 0);
        storedUserReview = userReview.getString(position, "");
        SharedPreferences userRating = getSharedPreferences(MainActivity.PREFS_USER_RATING, 0);
        storedUserRating = userRating.getString(position, "");
        setupRatingBar(storedUserRating);
        if (position != null) {
            updateInfoActivityView( Integer.parseInt(position) );
        } else if (mCurrentPosition != -1) {
            updateInfoActivityView(mCurrentPosition);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        switch( item.getItemId() ){
            case R.id.action_search:
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateInfoActivityView(int position){
        imageView = (ImageView) findViewById(R.id.FragmentDialogImage);
        name_eng = (TextView) findViewById(R.id.FragmentDialogNameEng);
        nation = (TextView) findViewById(R.id.FragmentDialogNation);
        user_rating = (TextView) findViewById(R.id.FragmentDialogUserRating);
        alcohol_degree = (TextView) findViewById(R.id.FragmentDialogAlcoholDegree);
        explanation = (TextView) findViewById(R.id.FragmentDialogExplanation);
        user_review_title = (TextView) findViewById(R.id.FragmentDialogUserReviewTitle);
        user_review_content = (EditText) findViewById(R.id.FragmentDialogUserReviewContent);

        imageView.setImageResource(appcontest.practice.beerforyou5.Library.DataForTest.images[position]);
        name_eng.setText(appcontest.practice.beerforyou5.Library.DataForTest.name_eng[position]+" / "+appcontest.practice.beerforyou5.Library.DataForTest.name_kor[position]);
        nation.setText(appcontest.practice.beerforyou5.Library.DataForTest.nation[position]);
        user_rating.setText("평점 : "+storedUserRating);
        alcohol_degree.setText("도수 : "+ appcontest.practice.beerforyou5.Library.DataForTest.alcohol_degree[position]);
        explanation.setText("특징 : "+ appcontest.practice.beerforyou5.Library.DataForTest.taste[position] );
        user_review_title.setText("사용자 리뷰 : " );

        if( !storedUserReview.equals("") ){ user_review_content.setText(storedUserReview); }
        mCurrentPosition = position;
    }

    public void setupRatingBar(String storedUserRating){
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        if( !storedUserRating.equals("") ){
            ratingBar.setRating(Float.valueOf(storedUserRating));
        }
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratings = String.valueOf(rating);
            }
        });
    }

    // click OK button in info activity class layout
    // store the rating bar score for further development such as recommender system
    public void onClickOk(View view){
        Intent intent = new Intent();
        if( ratings != null){
            intent.putExtra("INPUT_TEXT", ratings );
            setResult(RESULT_OK, intent);
            // store the user rating on pause
            SharedPreferences userRating = getSharedPreferences(MainActivity.PREFS_USER_RATING, 0);
            SharedPreferences.Editor userRatingEditor = userRating.edit();
            userRatingEditor.putString(position, ratings);
            userRatingEditor.commit();
        }

        // store the user review
        if( user_review_content.getText().toString() != null) {
            SharedPreferences userReview = getSharedPreferences(MainActivity.PREFS_USER_REVIEW, 0);
            SharedPreferences.Editor userReviewEditor = userReview.edit();
            userReviewEditor.putString(position, user_review_content.getText().toString());
            userReviewEditor.commit();
        }
        finish();
    }

    // click cancel button in info activity layout
    // just getting out of this page cancelling this activity
    public void onClickCancel(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}


