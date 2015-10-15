package appcontest.practice.beerforyou4;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class TabText extends Fragment {
    Context mContext;
    OnWordSelectedListener mCallback;
    private ListView list;

    public interface OnWordSelectedListener {
        public void onWordSelected(int position);
    }

    public TabText(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab_text, null);
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
                Toast.makeText(getActivity().getBaseContext(), DataForTest.titles[+position], Toast.LENGTH_SHORT).show();
                // TODO
                // we need to implement the Fragment Dialog to show information of beer to users
                mCallback.onWordSelected(position);
                list.setItemChecked(position, true);

            }
        });
        return view;
    }

    public class CustomList extends ArrayAdapter<String> {
        private final TabText context;
        public CustomList(TabText context ) {
            super(getActivity(), R.layout.activity_tab_text_list_item, DataForTest.titles);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View rowView= inflater.inflate(R.layout.activity_tab_text_list_item, null, true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView rating = (TextView) rowView.findViewById(R.id.rating);
            TextView genre = (TextView) rowView.findViewById(R.id.genre);
            TextView year = (TextView) rowView.findViewById(R.id.releaseYear);

            title.setText(DataForTest.titles[position]);
            imageView.setImageResource(DataForTest.images[position]);
            rating.setText("9.0"+position);
            genre.setText("DRAMA");
            year.setText(1930+position+"");
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