package appcontest.practice.beerforyou4;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

@SuppressLint("ValidFragment")
public class TabVoice extends Fragment {
    Context mContext;
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    final static int REQ_CODE_SPEECH_INPUT = 100;

    public TabVoice(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("Tab Voice", "On Create View ");
        View view = inflater.inflate(R.layout.activity_tab_voice, null);

        Toast.makeText(getActivity().getBaseContext(), "음성인식검색 화면이 생성되었습니다.", Toast.LENGTH_SHORT).show();


        txtSpeechInput = (TextView) view.findViewById(R.id.txtSpeechInput);
        txtSpeechInput.setText(R.string.title_section1);
        btnSpeak = (ImageButton) view.findViewById(R.id.btnSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Tab Voice", "On Resume ");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d("Tab Voice", " On Pause ");
    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            Log.i("Voice: startAct", "  startActivityForResult ");
            getActivity().startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getActivity().getBaseContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Voice: ActResult", "  getting result ");
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == Activity.RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                    Log.i("Voice: ActResult: data", result.get(0));
                }
                break;
            }
        }
    }
    */

}