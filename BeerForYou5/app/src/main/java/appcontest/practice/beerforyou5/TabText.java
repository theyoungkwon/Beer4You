package appcontest.practice.beerforyou5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class TabText extends Fragment {
    Context mContext;
    OnWordSelectedListener mCallback;
    private ListView list_view1;
    private ListView list_view2;
    private ScrollView scroll_view1;
    private ScrollView scroll_view2;
    private View view;
    private int[] correct_position = {0,1,3,2,5};

    ///// compute beer recommendation for user
    //private int numOfRecommendation = 5;
    private int numOfBeer = 34;
    //private int[] test_positions = new int[numOfRecommendation];
    //private int[] test_positions = {0,2,3};
    private double[] result_score = new double[numOfBeer];
    private int[] result_ranking_sequence = new int[numOfBeer];

    public interface OnWordSelectedListener {
        public void onWordSelected(int position);
    }

    public TabText(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("Tab Text", "On Create View ");
        view = inflater.inflate(R.layout.activity_tab_text, null);
        Toast.makeText(getActivity().getBaseContext(), "텍스트 검색 화면이 생성되었습니다", Toast.LENGTH_SHORT).show();

        // Setting 5 top selling beer
        CustomList adapter = new CustomList(TabText.this);
        scroll_view1 = (ScrollView) view.findViewById(R.id.TabTextScrollView1);
        list_view1=(ListView) view.findViewById(R.id.TabTextList);
        list_view1.setAdapter(adapter);
        list_view1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // we need to use getActivity() method to use Toast function in Fragment
                // by using getActivity() we can get Activity associated with this Fragment
                int new_position = correct_position[position];
                Log.i("position of Items", String.valueOf(new_position));
                mCallback.onWordSelected(new_position);
                list_view1.setItemChecked(new_position, true);
            }
        });
        list_view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll_view1.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        //////////////////////////////////////////////////////////////////
        ///// compute beer recommendation for user
        //int numOfRecommendation = 34;
        //int numOfBeer = 34;
        //int[] test_positions = new int[numOfBeer];
        //double[] result_score = new double[numOfBeer];
        //int[] result_ranking_sequence = new int[numOfBeer];
        ///// get test_positions

        int i,j;
        double zero=0.0;
        ArrayList<Integer> array_data = new ArrayList<Integer>();
        String storedUserRating;
        SharedPreferences userRating = getActivity().getSharedPreferences(MainActivity.PREFS_USER_RATING, 0);
        for(i=0, j=0; i<numOfBeer ; i++) {
            storedUserRating = userRating.getString(Integer.toString(i), "");
            System.out.println("TabText : storedUserRating(i)"+ i +" : "+ storedUserRating);
            if( (storedUserRating != String.valueOf(zero)) && !(storedUserRating.isEmpty())) {
                array_data.add(i);
                j = j+1;
                System.out.println("TabText : storedUserRating(i)"+ i +" : "+ storedUserRating + "j : "+ j);
            }
        }

        int[] test_positions = new int[array_data.size()];
        for(i=0 ; i< array_data.size();i++){
            test_positions[i] = array_data.get(i);
        }

        result_score = appcontest.practice.beerforyou5.Library.RankingTest.calPredictedScore(test_positions);
        for(i=0; i<result_score.length; i++){
            System.out.println("i : "+i+" result_score : "+result_score[i]);
        }

        result_ranking_sequence = appcontest.practice.beerforyou5.Library.RankingTest.findRankingSequence(result_score);
        for(i=0; i<result_ranking_sequence.length; i++){
            System.out.println("i : "+i+" result_ranking_sequence : "+result_ranking_sequence[i]);
        }

        //////////////////////////////////////////////////////////////////
        // Setting top 5 beer for user preference
        CustomList2 adapter2 = new CustomList2(TabText.this);
        scroll_view2 = (ScrollView) view.findViewById(R.id.TabTextScrollView2);
        list_view2 = (ListView) view.findViewById(R.id.TabTextList2);
        list_view2.setAdapter(adapter2);
        list_view2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // we need to use getActivity() method to use Toast function in Fragment
                // by using getActivity() we can get Activity associated with this Fragment
                int new_position = result_ranking_sequence[position];
                Log.i("position of Items", String.valueOf(new_position));
                mCallback.onWordSelected(new_position);
                list_view2.setItemChecked(new_position, true);
            }
        });
        list_view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll_view2.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Tab Text", "On Resume ");
        if( MainActivity.INFO_SIGNAL == 1){

            ////// Setting 5 top selling beer //////
            CustomList adapter = new CustomList(TabText.this);
            scroll_view1 = (ScrollView) view.findViewById(R.id.TabTextScrollView1);
            list_view1=(ListView) view.findViewById(R.id.TabTextList);
            list_view1.setAdapter(adapter);
            list_view1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // we need to use getActivity() method to use Toast function in Fragment
                    // by using getActivity() we can get Activity associated with this Fragment
                    int new_position = correct_position[position];
                    Log.i("position of Items", String.valueOf(new_position));
                    mCallback.onWordSelected(new_position);
                    list_view1.setItemChecked(new_position, true);
                }
            });
            list_view1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    scroll_view1.requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });

            //////////////////////////////////////////////////////////////////
            ///// compute beer recommendation for user
            //int numOfRecommendation = 34;
            //int numOfBeer = 34;
            //int[] test_positions = new int[numOfBeer];
            //double[] result_score = new double[numOfBeer];
            //int[] result_ranking_sequence = new int[numOfBeer];
            ///// get test_positions
            /*
            int i,j, zero=0;
            String storedUserRating;
            SharedPreferences userRating = getActivity().getSharedPreferences(MainActivity.PREFS_USER_RATING, 0);
            for(i=0, j=0; i<numOfBeer ; i++) {
                storedUserRating = userRating.getString(Integer.toString(i), "");
                if( storedUserRating != String.valueOf(zero)) {
                    test_positions[j] = i;
                    j = j+1;
                }
            }
            */
            int i,j;
            double zero=0.0;
            ArrayList<Integer> array_data = new ArrayList<Integer>();
            String storedUserRating;
            SharedPreferences userRating = getActivity().getSharedPreferences(MainActivity.PREFS_USER_RATING, 0);
            for(i=0, j=0; i<numOfBeer ; i++) {
                storedUserRating = userRating.getString(Integer.toString(i), "");
                System.out.println("TabText : storedUserRating(i)"+ i +" : "+ storedUserRating);
                if( (storedUserRating != String.valueOf(zero)) && !(storedUserRating.isEmpty())){
                    array_data.add(i);
                    j = j+1;
                    System.out.println("TabText : storedUserRating(i)"+ i +" : "+ storedUserRating + "j : "+ j);
                }
            }

            int[] test_positions = new int[array_data.size()];
            for(i=0 ; i< array_data.size();i++){
                test_positions[i] = array_data.get(i);
            }

            result_score = appcontest.practice.beerforyou5.Library.RankingTest.calPredictedScore(test_positions);
            for(i=0; i<result_score.length; i++){
                System.out.println("i : "+i+" result_score : "+result_score[i]);
            }

            result_ranking_sequence = appcontest.practice.beerforyou5.Library.RankingTest.findRankingSequence(result_score);
            for(i=0; i<result_ranking_sequence.length; i++){
                System.out.println("i : "+i+" result_ranking_sequence : "+result_ranking_sequence[i]);
            }
            //////////////////////////////////////////////////////////////////

            ///// Setting top 5 beer for user preference /////
            CustomList2 adapter2 = new CustomList2(TabText.this);
            scroll_view2 = (ScrollView) view.findViewById(R.id.TabTextScrollView2);
            list_view2 = (ListView) view.findViewById(R.id.TabTextList2);
            list_view2.setAdapter(adapter2);
            list_view2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // we need to use getActivity() method to use Toast function in Fragment
                    // by using getActivity() we can get Activity associated with this Fragment
                    int new_position = result_ranking_sequence[position];
                    Log.i("position of Items", String.valueOf(new_position));
                    mCallback.onWordSelected(new_position);
                    list_view2.setItemChecked(new_position, true);
                }
            });
            list_view2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    scroll_view2.requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });
            MainActivity.INFO_SIGNAL = 0;
        }
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d("Tab Text", " On Pause ");
    }

    public class CustomList extends ArrayAdapter<String> {
        private final TabText context;
        public CustomList(TabText context ) {
            super(getActivity(), R.layout.activity_tab_text_list_item, appcontest.practice.beerforyou5.Library.DataForTest.top5_name_eng);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View rowView= inflater.inflate(R.layout.activity_tab_text_list_item, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView name_eng = (TextView) rowView.findViewById(R.id.name_eng);
            TextView name_kor = (TextView) rowView.findViewById(R.id.name_kor);
            TextView nation = (TextView) rowView.findViewById(R.id.nation);
            TextView alcohol_degree = (TextView) rowView.findViewById(R.id.alcohol_degree);
            TextView user_rating = (TextView) rowView.findViewById(R.id.userRating);

            int new_position = correct_position[position];
            // getting information of user rating on Cnew_reate View
            SharedPreferences userRating = getActivity().getSharedPreferences(MainActivity.PREFS_USER_RATING, 0);
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

    public class CustomList2 extends ArrayAdapter<String> {
        private final TabText context;
        public CustomList2(TabText context ) {
            super(getActivity(), R.layout.activity_tab_text_list_item, appcontest.practice.beerforyou5.Library.DataForTest.top5_name_eng);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View rowView= inflater.inflate(R.layout.activity_tab_text_list_item, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView name_eng = (TextView) rowView.findViewById(R.id.name_eng);
            TextView name_kor = (TextView) rowView.findViewById(R.id.name_kor);
            TextView nation = (TextView) rowView.findViewById(R.id.nation);
            TextView alcohol_degree = (TextView) rowView.findViewById(R.id.alcohol_degree);
            TextView user_rating = (TextView) rowView.findViewById(R.id.userRating);

            int new_position = result_ranking_sequence[position];
            // getting information of user rating on Create View
            SharedPreferences userRating = getActivity().getSharedPreferences(MainActivity.PREFS_USER_RATING, 0);
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


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnWordSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnWordSelectedListener");
        }
    }

}