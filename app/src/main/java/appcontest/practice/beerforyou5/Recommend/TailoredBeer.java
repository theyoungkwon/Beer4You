package appcontest.practice.beerforyou5.Recommend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import appcontest.practice.beerforyou5.MainActivity;
import appcontest.practice.beerforyou5.R;

/**
 * Created by jiuser on 2015-11-28.
 */
public class TailoredBeer extends ActionBarActivity{
    private ListView list_view;

    static final int GET_STRING = 1;
    public static int INFO_SIGNAL = 0;

    ///// compute beer recommendation for user
    private int numOfBeer = 50;
    private double[] result_score = new double[numOfBeer];
    private int[] result_ranking_sequence = new int[numOfBeer];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_tab1);
        initiate();
    }

    @Override
    public void onResume() {
        super.onResume();
        if( INFO_SIGNAL == 1){
            initiate();
            INFO_SIGNAL = 0;
        }
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    public void onWordSelected(int position) {
        Intent intent = new Intent(TailoredBeer.this, appcontest.practice.beerforyou5.InfoActivity.class);
        intent.putExtra("ARG_POSITION", String.valueOf(position));
        startActivityForResult(intent, GET_STRING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case GET_STRING:
                if(resultCode == RESULT_OK && null != data){
                    String input_text = data.getStringExtra("INPUT_TEXT");
                    if( input_text != null) {
                        INFO_SIGNAL = 1;
                    }
                }
                break;
        }
    }

    public class CustomList extends ArrayAdapter<String> {
        private final TailoredBeer context;
        public CustomList(TailoredBeer context ) {
            super(context, R.layout.activity_tab_text_list_item, appcontest.practice.beerforyou5.Library.DataForTest.top5_name_eng);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View rowView= inflater.inflate(R.layout.activity_tab_text_list_item, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView name_eng = (TextView) rowView.findViewById(R.id.name_eng);
            TextView name_kor = (TextView) rowView.findViewById(R.id.name_kor);
            TextView nation = (TextView) rowView.findViewById(R.id.nation);
            TextView alcohol_degree = (TextView) rowView.findViewById(R.id.alcohol_degree);
            TextView user_rating = (TextView) rowView.findViewById(R.id.userRating);

            int new_position = result_ranking_sequence[position];
            // getting information of user rating on Cnew_reate View
            SharedPreferences userRating = getSharedPreferences(MainActivity.PREFS_USER_RATING, 0);
            String rating = userRating.getString(Integer.toString(new_position) , "");

            name_eng.setText(appcontest.practice.beerforyou5.Library.DataForTest.name_eng[new_position]);
            imageView.setImageResource(appcontest.practice.beerforyou5.Library.DataForTest.images[new_position]);
            name_kor.setText(appcontest.practice.beerforyou5.Library.DataForTest.name_kor[new_position]);
            nation.setText(appcontest.practice.beerforyou5.Library.DataForTest.nation[new_position]);
            alcohol_degree.setText("도수 : "+ appcontest.practice.beerforyou5.Library.DataForTest.alcohol_degree[new_position]);
            user_rating.setText("평점 : " + rating );

            return rowView;
        }
    }

    protected void initiate(){
        int i,j_pos, j_neg;
        float zero=0;
        ArrayList<Integer> array_data_positive = new ArrayList<Integer>();
        ArrayList<Integer> array_data_negative = new ArrayList<Integer>();
        String storedUserRating;
        SharedPreferences userRating = getSharedPreferences(MainActivity.PREFS_USER_RATING, 0);
        for(i=0, j_pos=0, j_neg=0; i<numOfBeer ; i++) {
            storedUserRating = userRating.getString(Integer.toString(i), "");
            if ((storedUserRating != String.valueOf(zero)) && !(storedUserRating.isEmpty())) {
                ///// when rating is not 1 or 2 ( positive )
                if (!storedUserRating.equals("1.0") && !storedUserRating.equals("2.0")) {
                    if( j_pos < 21) {
                        array_data_positive.add(i);
                        j_pos = j_pos + 1;
                    }
                }
                ///// when rating is 1 or 2 ( negative )
                else {
                    if( j_neg < 14 ) {
                        array_data_negative.add(i);
                        j_neg = j_neg + 1;
                    }
                }
            }
        }

        ///// Calculate Positive Associations /////
        int[] test_positions_positive = new int[array_data_positive.size()];
        for(i=0 ; i< array_data_positive.size();i++){
            test_positions_positive[i] = array_data_positive.get(i);
        }
        result_score = appcontest.practice.beerforyou5.Library.RankingTest.calPredictedScorePositive(test_positions_positive);
        for(i=0; i<result_score.length; i++){
        }

        ///// Calculate Negative Associations /////
        int[] test_positions_negative = new int[array_data_negative.size()];
        for(i=0 ; i< array_data_negative.size();i++){
            test_positions_negative[i] = array_data_negative.get(i);
        }
        double[] result_score_temp = new double[numOfBeer];
        result_score_temp = appcontest.practice.beerforyou5.Library.RankingTest.calPredictedScoreNegative(test_positions_negative);
        for(i=0; i<result_score_temp.length; i++){
        }

        ///// sum result score of positive and negative
        for(i=0; i<result_score.length; i++){
            result_score[i] = result_score[i] + result_score_temp[i];
        }

        ///// sorting result score and get ranking sequence
        result_ranking_sequence = appcontest.practice.beerforyou5.Library.RankingTest.findRankingSequence(result_score);
        for(i=0; i<result_ranking_sequence.length; i++){
        }

        //////////////////////////////////////////////////////////////////
        // Setting top 5 beer for user preference
        CustomList adapter = new CustomList(TailoredBeer.this);
        list_view=(ListView) findViewById(R.id.country_tab1);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // we need to use method to use Toast function in Fragment
                // by using we can get Activity associated with this Fragment
                int new_position = result_ranking_sequence[position];
                onWordSelected(new_position);
                list_view.setItemChecked(new_position, true);
            }
        });
    }
}