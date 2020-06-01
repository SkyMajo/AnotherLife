package com.nixo.afterworklife.Common;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapCommon {
    public String saveBitmap(Bitmap mBitmap) {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator );   //FILE_DIR自定义
        if (!file.exists()) {
            file.mkdir();
        }
        File tmpf = new File(file, "photo" + ".jpg");
        File f = tmpf;
        try {
            f.createNewFile();
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String image_file_url=f.getAbsolutePath();
        Log.i("image_file_url", image_file_url);
        return image_file_url;
    }

}
