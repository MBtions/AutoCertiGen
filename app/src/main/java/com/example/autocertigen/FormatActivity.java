package com.example.autocertigen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextBox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class FormatActivity extends AppCompatActivity {
TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_format );

        test = findViewById( R.id.textView );

        Intent i2 = getIntent();
        String templatename = i2.getStringExtra( "template" );

        if (templatename == "templateone")
        {
            searchSlide();
        }
    }
    String path;
    Context context;
    private void searchSlide() {
        try {
            AssetManager assManager = getAssets();
            InputStream is = assManager.open("templates.pptx");

            /*
            path = Environment.getExternalStorageDirectory()+"/AutoCertiGen/";
            File file = new File(path+ "copytemplates.pptx");
            if (!file.exists()) {
                dir.mkdirs();
                copyFile(context);
            }
*/

            XMLSlideShow ppt = new XMLSlideShow(is);
            XSLFSlide slide = ppt.getSlides().get(0);
            List<XSLFShape> shapes = slide.getShapes();
            for (XSLFShape shape : shapes) {
                if (shape instanceof XSLFTextBox) {
                    String text = ((XSLFTextBox) shape).getText();
                    String newText = text.replace("<Name>", "Saurabh");
                    //((XSLFTextBox) shape).setText(newText);
                    test.setText(newText);

                }
            }
            Log.d("TemplateActivity", "shapes " + shapes.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*private void copyFile(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream in = assetManager.open("eng.traineddata");
            OutputStream out = new FileOutputStream(path+"yourdata.extension");
            byte[] buffer = new byte[1024];
            int read = in.read(buffer);
            while (read != -1) {
                out.write(buffer, 0, read);
                read = in.read(buffer);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }*/
}

/*    Context context = getApplicationContext();
    String path = Environment.getExternalStorageDirectory()+"/yourapp/";
    File file = new File(path+ "yourdata.extension");
        if (!file.exists()) {
                dir.mkdirs();
                copyFile(context);
                }

private void copyFile(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
        InputStream in = assetManager.open("eng.traineddata");
        OutputStream out = new FileOutputStream(path+"yourdata.extension");
        byte[] buffer = new byte[1024];
        int read = in.read(buffer);
        while (read != -1) {
        out.write(buffer, 0, read);
        read = in.read(buffer);
        }
        } catch (Exception e) {
        e.getMessage();
        }
        }
*/