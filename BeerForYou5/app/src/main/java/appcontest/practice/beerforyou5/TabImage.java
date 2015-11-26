package appcontest.practice.beerforyou5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("ValidFragment")
public class TabImage extends Fragment {
    Context mContext;
    Camera mCamera;
    private appcontest.practice.beerforyou5.CameraPreview mPreview;
    private ImageButton captureButton;
    private FrameLayout preview;
    private TextView imageTitle;
    private View view;
    final static int CODE_SELECT_IMAGE = 200;

    private int mNumberOfCameras;
    private int mCurrentCamera;  // Camera ID currently chosen
    private int mCameraCurrentlyLocked;  // Camera ID that's actually acquired
    private int mDefaultCameraId;
    private int initialResumeFlag = 0;
    public TabImage(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("Tab Image", "On Create View ");
        view = inflater.inflate(R.layout.activity_tab_image, null);
        Toast.makeText(getActivity().getBaseContext(), "이미지검색 화면이 생성되었습니다.", Toast.LENGTH_SHORT).show();

        /*
        // Find the total number of cameras available
        mNumberOfCameras = Camera.getNumberOfCameras();

        // Find the ID of the rear-facing ("default") camera
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < mNumberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            Log.d("Camera", "Camera Info : " + cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                mCurrentCamera = mDefaultCameraId = i;
            }
        }
        //mCamera = getCameraInstance();
        Log.d("Camera", "Camera ID of rear Camera : " + mCurrentCamera);

        mCamera = Camera.open(mCurrentCamera);
        mCameraCurrentlyLocked = mCurrentCamera;
        Log.d("Camera", "Camera Value from the beginning : " + mCamera);

        //mPreview = new CameraPreview( getActivity().getBaseContext() , mCamera);
        mPreview = new CameraPreview( this.getActivity() , mCamera);
        mPreview.setCamera(mCamera);

        //imageTitle = (TextView) view.findViewById(R.id.imageTitle);
        //imageTitle.setText("CAMERA");

        preview = (FrameLayout) view.findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        captureButton = (ImageButton) view.findViewById(R.id.button_capture);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
            }
        });
        */
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Tab Image", "On onDestroyView ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Tab Image", "On onStart ");

        //if(initialResumeFlag == 0) initialResumeFlag = 1;
        // Use mCurrentCamera to select the camera desired to safely restore
        // the fragment after the camera has been changed
        // Find the total number of cameras available
        mNumberOfCameras = Camera.getNumberOfCameras();

        // Find the ID of the rear-facing ("default") camera
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < mNumberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            Log.d("Tab Image", "Camera Info : " + cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                mCurrentCamera = mDefaultCameraId = i;
            }
        }
        //mCamera = getCameraInstance();
        Log.d("Tab Image", "Camera ID of rear Camera : " + mCurrentCamera);

        mCamera = Camera.open(mCurrentCamera);
        mCameraCurrentlyLocked = mCurrentCamera;
        Log.d("Tab Image", "Camera Value from the beginning : " + mCamera);

        //mPreview = new CameraPreview( getActivity().getBaseContext() , mCamera);
        mPreview = new appcontest.practice.beerforyou5.CameraPreview( this.getActivity() , mCamera);
        mPreview.setCamera(mCamera);

        //imageTitle = (TextView) view.findViewById(R.id.imageTitle);
        //imageTitle.setText("CAMERA");

        preview = (FrameLayout) view.findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        captureButton = (ImageButton) view.findViewById(R.id.button_capture);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.takePicture(null, null, mPicture);
            }
        });

    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d("Tab Image", "On onStop ");
        /*
        if(mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        */
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Tab Image", "On Resume ");

    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d("Tab Image", " On Pause ");
        /*
        if(mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        */
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pictureFileDir = getDir();
            if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
                Log.d("Camera", "Can't create directory.");
                Toast.makeText(getActivity().getApplicationContext(),
                        "Can't create directory.", Toast.LENGTH_LONG).show();
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
            String date = dateFormat.format(new Date());
            String photoFile = "MyPic" + date + ".jpg";

            String filename = pictureFileDir.getPath() + File.separator
                    + photoFile;

            File pictureFile = new File(filename);

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
                Toast.makeText(getActivity().getApplicationContext(),
                        "New Image is saved:" + photoFile, Toast.LENGTH_LONG)
                        .show();
            } catch (Exception error) {
                Log.d("Camera", "File" + filename + "Image was not saved: "
                        + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), "Image was not saved.",
                        Toast.LENGTH_LONG).show();
            }
        }

        private File getDir() {
            File sdDir = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            return new File(sdDir, "CameraCapture");
        }
    };


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_tab_image, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement
        switch( item.getItemId() ){
            //case R.id.action_voice:
            //    Toast.makeText(this, "voice", Toast.LENGTH_SHORT).show();
            //    return true;
            //case R.id.action_search:
            //  Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
            //    return true;
            case R.id.action_select_image:
                Toast.makeText(getActivity(), "select image", Toast.LENGTH_SHORT).show();
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                getActivity().startActivityForResult(intent, CODE_SELECT_IMAGE);
                return true;
            case R.id.action_settings:
                Toast.makeText(getActivity(), "settings", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}