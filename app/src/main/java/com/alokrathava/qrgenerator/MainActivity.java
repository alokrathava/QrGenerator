package com.alokrathava.qrgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;

import com.alokrathava.qrgenerator.databinding.ActivityMainBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.generatebtn.setOnClickListener(v -> {
            String Text = binding.text.getText().toString();
            Log.v("Value",Text);
//          Intialize  Multi format Writer
            MultiFormatWriter writer = new MultiFormatWriter();
//            Intialize bit matrix
            try {
                BitMatrix matrix = writer.encode(Text, BarcodeFormat.QR_CODE,350,350);
//                Intialize barcode Encoder
                BarcodeEncoder encoder = new BarcodeEncoder();
//                Intialize bitmap
                Bitmap bitmap = encoder.createBitmap(matrix);
//                Set BitMap Image View
                binding.imageOutput.setImageBitmap(bitmap);
//                Initialize Input Manager
                InputMethodManager manager = (InputMethodManager)getSystemService(
                        Context.INPUT_METHOD_SERVICE
                );
//                Hide Keyboard
                manager.hideSoftInputFromWindow(binding.text.getApplicationWindowToken(),0);


            } catch (WriterException e) {
                e.printStackTrace();
            }


        });


    }
}