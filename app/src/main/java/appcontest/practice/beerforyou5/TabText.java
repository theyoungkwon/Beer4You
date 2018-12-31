package appcontest.practice.beerforyou5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

@SuppressLint("ValidFragment")
public class TabText extends Fragment {
    Context mContext;
    OnTextBtnSelectedListener mTextBtnCallback;
    private View view;
    ///// compute beer recommendation for user
    private Button recommenderRandomBtn;

    public interface OnTextBtnSelectedListener {
        public void onTextBtnSelected(int position, int type);
    }

    public TabText(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_tab_text, null);
        Button TextBtnImportedBeer = (Button) view.findViewById(R.id.TabTextTitle1);
        Button TextBtnTailoredBeer = (Button) view.findViewById(R.id.TabTextTitle2);
        recommenderRandomBtn = (Button) view.findViewById(R.id.recommenderRandomBtn);

        ////// Implementation of Country Activity of Beer For You  //////
        TextBtnImportedBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = 0;
                int position = -1;
                mTextBtnCallback.onTextBtnSelected(position, type);
            }
        });
        ////// Implementation of Country Activity of Beer For You  //////
        TextBtnTailoredBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int type = 1;
                int position = -1;
                mTextBtnCallback.onTextBtnSelected(position, type);
            }
        });
        ////// Implementation of Random Selection of Beer For You  //////
        recommenderRandomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int numOfBeer = appcontest.practice.beerforyou5.Library.DataForTest.name_eng.length;
                int position = rand.nextInt(numOfBeer);
                int type = 2;
                mTextBtnCallback.onTextBtnSelected(position, type);
            }
        });
        return view;
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mTextBtnCallback = (OnTextBtnSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnWordSelectedListener");
        }
    }
}