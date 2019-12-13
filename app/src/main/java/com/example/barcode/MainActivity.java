package com.example.barcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

       Button btn =(Button) findViewById(R.id.button_process);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    ImageView myImageView = findViewById(R.id.imgview);
    if (myImageView == null){
        System.out.println("my im null");
    }
    Bitmap myBitmap = BitmapFactory.decodeResource(
            getApplicationContext().getResources(),
            R.drawable.puppy);
    myImageView.setImageBitmap(myBitmap);
//        //R.drawable.puppy
        BarcodeDetector detector =
                new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                        .build();
        if(!detector.isOperational()){
           // txtView.setText("Could not set up the detector!");
            return;
        }
        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);
        Barcode thisCode = barcodes.valueAt(0);
        TextView txtView = (TextView) findViewById(R.id.txtContent);
        txtView.setText(thisCode.rawValue);
    }
}
