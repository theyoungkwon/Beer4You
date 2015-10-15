package appcontest.practice.beerforyou4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends ActionBarActivity {
    private ImageView imageView;
    private TextView title;
    private TextView rating;
    private TextView genre;
    private TextView year;
    private TextView explanation;
    final static String ARG_POSITION = "position";
    private int mCurrentPosition = -1;

    private RatingBar ratingBar;
    private String ratings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        setupRatingBar();
    }

    @Override
    public void onStart() {
        super.onStart();

        //Bundle args = getIntent().getExtras();
        Intent intent = getIntent();
        String position = intent.getExtras().getString("ARG_POSITION").toString();
        if (position != null) {
            updateInfoActivityView( Integer.parseInt(position) );
        } else if (mCurrentPosition != -1) {
            updateInfoActivityView(mCurrentPosition);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return super.onCreateOptionsMenu(menu);
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
            case R.id.action_info_search:
                Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_info_settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateInfoActivityView(int position){
        imageView = (ImageView) findViewById(R.id.FragmentDialogImage);
        title = (TextView) findViewById(R.id.FragmentDialogTitle);
        rating = (TextView) findViewById(R.id.FragmentDialogRating);
        genre = (TextView) findViewById(R.id.FragmentDialogGenre);
        year = (TextView) findViewById(R.id.FragmentDialogReleaseYear);
        explanation = (TextView) findViewById(R.id.FragmentDialogExplanation);

        title.setText(DataForTest.titles[position]);
        imageView.setImageResource(DataForTest.images[position]);
        rating.setText("9.0" + position);
        genre.setText("DRAMA");
        year.setText(1930 + position + "");
        explanation.setText("explanation about " + DataForTest.titles[position] );

        mCurrentPosition = position;
    }

    public void setupRatingBar(){
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

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
        if( ratings == null){
            ratingBar = (RatingBar) findViewById(R.id.ratingBar);
            ratings = String.valueOf(ratingBar.getRating());
        }
        intent.putExtra("INPUT_TEXT", ratings );
        setResult(RESULT_OK, intent);
        finish();
    }

    // click cancel button in info activity layout
    // just getting out of this page cancelling this activity
    public void onClickCancel(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}


