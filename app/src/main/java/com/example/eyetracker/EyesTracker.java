package com.example.eyetracker;

import android.content.Context;
import android.media.FaceDetector;
import android.util.Log;

import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.Detector;

//레이아웃 설정인가?
import static androidx.constraintlayout.widget.Constraints.TAG;


public class EyesTracker extends Tracker<Face> {
    //set threshold
    private final float THRESHOLD = 0.75f;
    //context
    private Context context;
    //public constructor
    public EyesTracker(Context context) {
        this.context = context;
    }
    //update tracking data
    @Override
    public void onUpdate(Detector.Detections<Face> detections, Face face) {
        if (face.getIsLeftEyeOpenProbability() > THRESHOLD || face.getIsRightEyeOpenProbability() > THRESHOLD) {
            Log.i(TAG, "onUpdate: Open Eyes Detected");
            ((MainActivity)context).updateMainView(Condition.USER_EYES_OPEN);

        }else {
            Log.i(TAG, "onUpdate: Close Eyes Detected");
            ((MainActivity)context).updateMainView(Condition.USER_EYES_CLOSED);
        }
    }
    @Override
    public void onMissing(Detector.Detections<Face> detections) {
        super.onMissing(detections);
        Log.i(TAG, "onUpdate: Face Not Detected yet!");
        ((MainActivity)context).updateMainView(Condition.FACE_NOT_FOUND);
    }

    //this method is called when no face is detected
    @Override
    public void onDone() {
        super.onDone();
    }
}
