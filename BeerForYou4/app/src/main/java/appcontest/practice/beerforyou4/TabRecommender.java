package appcontest.practice.beerforyou4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class TabRecommender extends Fragment {
    Context mContext;

    public TabRecommender(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab_recommender, null);

        Toast.makeText(getActivity().getBaseContext(), "추천검색 화면이 생성되었습니다.", Toast.LENGTH_SHORT).show();

        return view;
    }

}