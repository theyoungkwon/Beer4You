package appcontest.practice.beerforyou4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class TabVoice extends Fragment {
    Context mContext;

    public TabVoice(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab_voice, null);

        Toast.makeText(getActivity().getBaseContext(), "음성인식검색 화면이 생성되었습니다.", Toast.LENGTH_SHORT).show();

        Button button1;
        button1 = (Button) view.findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Uri uri = Uri.parse("http://whdghks913.tistory.com");
                Intent it  = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(it);
            }
        });

        return view;
    }

}