package appcontest.practice.beerforyou5.Taste;

/**
 * Created by jiuser on 2015-11-04.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import appcontest.practice.beerforyou5.R;
// 밀맥주
@SuppressLint("ValidFragment")
public class TabTaste2 extends Fragment {
    Context mContext;
    OnTabTaste2WordSelectedListener mCallback;
    private ListView list;
    private View view;
    private int[] correct_position = {48,1,25,14,35,27};
    private String[] indicateLengthOfList = new String[correct_position.length];
    private int click_flag = 0;

    public interface OnTabTaste2WordSelectedListener {
        public void onTabTaste2WordSelected(int position);
    }

    public TabTaste2(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.country_tab1, null);

        CustomList adapter = new CustomList(TabTaste2.this);
        list=(ListView) view.findViewById(R.id.country_tab1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // we need to use getActivity() method to use Toast function in Fragment
                // by using getActivity() we can get Activity associated with this Fragment
                click_flag = 1;
                int new_position = correct_position[position];
                mCallback.onTabTaste2WordSelected(new_position);
                list.setItemChecked(new_position, true);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(click_flag == 1) {
            CustomList adapter = new CustomList(TabTaste2.this);
            list = (ListView) view.findViewById(R.id.country_tab1);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // we need to use getActivity() method to use Toast function in Fragment
                    // by using getActivity() we can get Activity associated with this Fragment
                    click_flag = 1;
                    int new_position = correct_position[position];
                    mCallback.onTabTaste2WordSelected(new_position);
                    list.setItemChecked(new_position, true);
                }
            });
            click_flag = 0;
        }
    }
    @Override
    public void onPause(){
        super.onPause();
    }

    public class CustomList extends ArrayAdapter<String> {
        private final TabTaste2 context;
        public CustomList(TabTaste2 context ) {
            super(getActivity(), R.layout.activity_tab_text_list_item, indicateLengthOfList);
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

            // getting information of user rating on Create View
            int new_position = correct_position[position];
            SharedPreferences userRating = getActivity().getSharedPreferences(ActivityTaste.PREFS_USER, 0);
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
            mCallback = (OnTabTaste2WordSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTabTaste2WordSelectedListener");
        }
    }

}