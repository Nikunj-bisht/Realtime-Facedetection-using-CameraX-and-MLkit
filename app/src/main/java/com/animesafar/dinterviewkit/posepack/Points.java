package com.animesafar.dinterviewkit.posepack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;

import com.google.mlkit.vision.common.PointF3D;
import com.google.mlkit.vision.pose.Pose;
import com.google.mlkit.vision.pose.PoseLandmark;

import java.util.List;

public class Points extends View {

    private static final float DOT_RADIUS = 8.0f;
    private static final float IN_FRAME_LIKELIHOOD_TEXT_SIZE = 30.0f;
    private static final float STROKE_WIDTH = 10.0f;
    private static final float POSE_CLASSIFICATION_TEXT_SIZE = 60.0f;
    Context context;
    private final Pose pose;
    private final boolean showInFrameLikelihood;
    private final boolean visualizeZ;
    private final boolean rescaleZForVisualization;
    private float zMin = Float.MAX_VALUE;
    private float zMax = Float.MIN_VALUE;

    private final List<String> poseClassification;
    private final Paint classificationTextPaint;
    private final Paint leftPaint;
    private final Paint rightPaint;
    private final Paint whitePaint;

    Points(

            Pose pose,
            boolean showInFrameLikelihood,
            boolean visualizeZ,
            boolean rescaleZForVisualization,
            List<String> poseClassification, Context context) {
        super(context);


        this.pose = pose;
        this.showInFrameLikelihood = showInFrameLikelihood;
        this.visualizeZ = visualizeZ;
        this.rescaleZForVisualization = rescaleZForVisualization;

        this.poseClassification = poseClassification;
        classificationTextPaint = new Paint();
        classificationTextPaint.setColor(Color.WHITE);
        classificationTextPaint.setTextSize(POSE_CLASSIFICATION_TEXT_SIZE);
        classificationTextPaint.setShadowLayer(5.0f, 0f, 0f, Color.BLACK);

        whitePaint = new Paint();
        whitePaint.setStrokeWidth(STROKE_WIDTH);
        whitePaint.setColor(Color.WHITE);
        whitePaint.setTextSize(IN_FRAME_LIKELIHOOD_TEXT_SIZE);
        leftPaint = new Paint();
        leftPaint.setStrokeWidth(STROKE_WIDTH);
        leftPaint.setColor(Color.GREEN);
        rightPaint = new Paint();
        rightPaint.setStrokeWidth(STROKE_WIDTH);
        rightPaint.setColor(Color.YELLOW);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        List<PoseLandmark> landmarks = pose.getAllPoseLandmarks();
        if (landmarks.isEmpty()) {
            return;
        }

        // Draw pose classification text.
        float classificationX = POSE_CLASSIFICATION_TEXT_SIZE * 0.5f;
//        for (int i = 0; i < poseClassification.size(); i++) {
//            float classificationY = (canvas.getHeight() - POSE_CLASSIFICATION_TEXT_SIZE * 1.5f
//                    * (poseClassification.size() - i));
//            canvas.drawText(
//                    poseClassification.get(i),
//                    classificationX,
//                    classificationY,
//                    classificationTextPaint);
//        }

        // Draw all the points
        for (PoseLandmark landmark : landmarks) {
            drawPoint(canvas, landmark, whitePaint);

        }

        PoseLandmark leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER);
        PoseLandmark rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER);
        PoseLandmark leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW);
        PoseLandmark rightElbow = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW);
        PoseLandmark leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST);
        PoseLandmark rightWrist = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST);
        PoseLandmark leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP);
        PoseLandmark rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP);
        PoseLandmark leftKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE);
        PoseLandmark rightKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE);
        PoseLandmark leftAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE);
        PoseLandmark rightAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE);

        PoseLandmark leftPinky = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY);
        PoseLandmark rightPinky = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY);
        PoseLandmark leftIndex = pose.getPoseLandmark(PoseLandmark.LEFT_INDEX);
        PoseLandmark rightIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_INDEX);
        PoseLandmark leftThumb = pose.getPoseLandmark(PoseLandmark.LEFT_THUMB);
        PoseLandmark rightThumb = pose.getPoseLandmark(PoseLandmark.RIGHT_THUMB);
        PoseLandmark leftHeel = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL);
        PoseLandmark rightHeel = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL);
        PoseLandmark leftFootIndex = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX);
        PoseLandmark rightFootIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX);

        Log.d("land" , leftShoulder.getPosition().x+" ll");

        drawLine(canvas, leftShoulder, rightShoulder, whitePaint);
        drawLine(canvas, leftHip, rightHip, whitePaint);

        // Left body
        drawLine(canvas, leftShoulder, leftElbow, leftPaint);
        drawLine(canvas, leftElbow, leftWrist, leftPaint);
        drawLine(canvas, leftShoulder, leftHip, leftPaint);
        drawLine(canvas, leftHip, leftKnee, leftPaint);
        drawLine(canvas, leftKnee, leftAnkle, leftPaint);
        drawLine(canvas, leftWrist, leftThumb, leftPaint);
        drawLine(canvas, leftWrist, leftPinky, leftPaint);
        drawLine(canvas, leftWrist, leftIndex, leftPaint);
        drawLine(canvas, leftIndex, leftPinky, leftPaint);
        drawLine(canvas, leftAnkle, leftHeel, leftPaint);
        drawLine(canvas, leftHeel, leftFootIndex, leftPaint);

        // Right body
        drawLine(canvas, rightShoulder, rightElbow, rightPaint);
        drawLine(canvas, rightElbow, rightWrist, rightPaint);
        drawLine(canvas, rightShoulder, rightHip, rightPaint);
        drawLine(canvas, rightHip, rightKnee, rightPaint);
        drawLine(canvas, rightKnee, rightAnkle, rightPaint);
        drawLine(canvas, rightWrist, rightThumb, rightPaint);
        drawLine(canvas, rightWrist, rightPinky, rightPaint);
        drawLine(canvas, rightWrist, rightIndex, rightPaint);
        drawLine(canvas, rightIndex, rightPinky, rightPaint);
        drawLine(canvas, rightAnkle, rightHeel, rightPaint);
        drawLine(canvas, rightHeel, rightFootIndex, rightPaint);

        // Draw inFrameLikelihood for all points
//        if (showInFrameLikelihood) {
//            for (PoseLandmark landmark : landmarks) {
//                canvas.drawText(
//                        String.format(Locale.US, "%.2f", landmark.getInFrameLikelihood()),
//                        translateX(landmark.getPosition().x),
//                        translateY(landmark.getPosition().y),
//                        whitePaint);
//            }
//        }
    }

    void drawPoint(Canvas canvas, PoseLandmark landmark, Paint paint) {
        PointF point = landmark.getPosition();
        Log.d("drawpo" , " ll");

        canvas.drawCircle(point.x, point.y, DOT_RADIUS, paint);
    }

    void drawLine(Canvas canvas, PoseLandmark startLandmark, PoseLandmark endLandmark, Paint paint) {
        // When visualizeZ is true, sets up the paint to draw body line in different colors based on
        // their z values.

        Log.d("drawline" , " ll");

        if (visualizeZ) {
            PointF3D start = startLandmark.getPosition3D();
            PointF3D end = endLandmark.getPosition3D();

            // Gets the range of z value.
            float zLowerBoundInScreenPixel;
            float zUpperBoundInScreenPixel;

            if (rescaleZForVisualization) {
//                zLowerBoundInScreenPixel = min(-0.001f, scale(zMin));
//                zUpperBoundInScreenPixel = max(0.001f, scale(zMax));
//
            } else {
                // By default, assume the range of z value in screen pixel is [-canvasWidth, canvasWidth].
                float defaultRangeFactor = 1f;
                zLowerBoundInScreenPixel = -defaultRangeFactor * canvas.getWidth();
                zUpperBoundInScreenPixel = defaultRangeFactor * canvas.getWidth();
            }

            // Gets average z for the current body line
            float avgZInImagePixel = (start.getZ() + end.getZ()) / 2;
            // float zInScreenPixel = getScaleX(avgZInImagePixel);

//            if (zInScreenPixel < 0) {
//                // Sets up the paint to draw the body line in red if it is in front of the z origin.
//                // Maps values within [zLowerBoundInScreenPixel, 0) to [255, 0) and use it to control the
//                // color. The larger the value is, the more red it will be.
//                int v = (int) (zInScreenPixel / zLowerBoundInScreenPixel * 255);
//                v = Ints.constrainToRange(v, 0, 255);
//                paint.setARGB(255, 255, 255 - v, 255 - v);
//            } else {
//                // Sets up the paint to draw the body line in blue if it is behind the z origin.
//                // Maps values within [0, zUpperBoundInScreenPixel] to [0, 255] and use it to control the
//                // color. The larger the value is, the more blue it will be.
//                int v = (int) (zInScreenPixel / zUpperBoundInScreenPixel * 255);
//                v = Ints.constrainToRange(v, 0, 255);
//                paint.setARGB(255, 255 - v, 255 - v, 255);
//            }
//
            canvas.drawLine(
                    start.getX(),
                    start.getY(),
                    end.getX(),
                    end.getY(),
                    paint);

        } else {
            PointF start = startLandmark.getPosition();
            PointF end = endLandmark.getPosition();
            canvas.drawLine(
                    start.x, start.y, end.x, end.y, paint);
        }
        }

}
