package appcontest.practice.beerforyou4;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class TabText extends Fragment {
    Context mContext;
    OnWordSelectedListener mCallback;
    private ListView list;
    private View view;

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

        CustomList adapter = new CustomList(TabText.this);
        list=(ListView) view.findViewById(R.id.TabTextList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // we need to use getActivity() method to use Toast function in Fragment
                // by using getActivity() we can get Activity associated with this Fragment
                Log.i("position of Items", String.valueOf(position));
                mCallback.onWordSelected(position);
                list.setItemChecked(position, true);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Tab Text", "On Resume ");
        if( MainActivity.INFO_SIGNAL == 1){
            CustomList adapter = new CustomList(TabText.this);
            list=(ListView) view.findViewById(R.id.TabTextList);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // we need to use getActivity() method to use Toast function in Fragment
                    // by using getActivity() we can get Activity associated with this Fragment
                    Log.i("position of Items", String.valueOf(position));
                    mCallback.onWordSelected(position);
                    list.setItemChecked(position, true);
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
            super(getActivity(), R.layout.activity_tab_text_list_item, DataForTest.name_eng);
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
            SharedPreferences userRating = getActivity().getSharedPreferences(MainActivity.PREFS_USER, 0);
            String rating = userRating.getString(Integer.toString(position) , "");

            name_eng.setText(DataForTest.name_eng[position]);
            imageView.setImageResource(DataForTest.images[position]);
            name_kor.setText(DataForTest.name_kor[position]);
            nation.setText(DataForTest.nation[position]);
            alcohol_degree.setText("도수 : "+ DataForTest.alcohol_degree[position]);
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