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

import appcontest.practice.beerforyou5.MainActivity;
import appcontest.practice.beerforyou5.R;

/**
 * Created by jiuser on 2015-11-28.
 */
public class ImportedBeer extends ActionBarActivity {

    private static final String TAG = "Imported Beer : ";
    private ListView list_view;
    private int[] correct_position = {1, 3, 2, 5, 10};

    static final int GET_STRING = 1;
    public static int INFO_SIGNAL = 0;

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
        Intent intent = new Intent(ImportedBeer.this, appcontest.practice.beerforyou5.InfoActivity.class);
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
        private final ImportedBeer context;
        public CustomList(ImportedBeer context ) {
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

            int new_position = correct_position[position];
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
        // Setting 5 top selling beer
        CustomList adapter = new CustomList(ImportedBeer.this);
        list_view=(ListView) findViewById(R.id.country_tab1);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // we need to use getActivity() method to use Toast function in Fragment
                // by using getActivity() we can get Activity associated with this Fragment
                int new_position = correct_position[position];
                onWordSelected(new_position);
                list_view.setItemChecked(new_position, true);
            }
        });
    }
}