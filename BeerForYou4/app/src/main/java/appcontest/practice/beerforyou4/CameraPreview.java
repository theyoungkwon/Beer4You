package appcontest.practice.beerforyou4;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

/**
 * Created by jiuser on 2015-10-18.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    //private Camera mCamera;
    private SurfaceView mSurfaceView;
    private Camera.Size mPreviewSize;
    private List<Camera.Size> mSupportedPreviewSizes;
    private Camera mCamera;
    private boolean mSurfaceCreated = false;


    public CameraPreview(Context context, Camera camera) {
        super(context );

        mCamera = camera;
        Log.d("CameraPreview", " Camera Preview Constructor ");
        Log.d("CameraPreview", "mCamera value : " + mCamera );

        mSurfaceView = new SurfaceView(context);
        //addView(mSurfaceView);

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void setCamera(Camera camera){
        mCamera = camera;
        Log.d("CameraPreview", " Set Camera ");
        Log.d("CameraPreview", "mCamera value : " + mCamera );
        if( mCamera != null){
            mSupportedPreviewSizes = mCamera.getParameters()
                    .getSupportedPreviewSizes();
            if (mSurfaceCreated) requestLayout();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            Log.d("CameraPreview", " Surface Created");
            Log.d("CameraPreview", "mCamera value : " + mCamera );
            if(mCamera!=null){
                mCamera.setPreviewDisplay(holder);
                mCamera.setDisplayOrientation(90);
                //mCamera.setPreviewCallback(new Camera.PreviewCallback() {
                //    @Override
                //    public void onPreviewFrame(byte[] data, Camera camera) {
                //    }
                //});
                mCamera.startPreview();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Camera", "Error setting camera preview: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("CameraPreview", " Surface Destrotyed" );

        if(mCamera != null){
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        Log.d("CameraPreview", "Surface Changed");
        if (mHolder.getSurface() == null){
            return;
        }

        try {
            mCamera.stopPreview();
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
            e.printStackTrace();
            Log.d("Camera", "Error starting camera preview: " + e.getMessage());
        }
    }
}