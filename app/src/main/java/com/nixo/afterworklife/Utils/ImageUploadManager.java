package com.nixo.afterworklife.Utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import com.cjt2325.cameralibrary.util.LogUtil;
import com.google.gson.Gson;
import com.jerey.loglib.Klog;
import com.nixo.afterworklife.Utils.Data.SharedUtils;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ImageUploadManager {
    private static final String TAG = ImageUploadManager.class.getSimpleName();
    private static final String PATH = "http://app.iyaa180.com/api/files/upload";
    private static ImageUploadManager sInstance;

    private ImageUploadManager() {
    }

    public static ImageUploadManager instance() {
        if (sInstance == null) {
            synchronized (ImageUploadManager.class) {
                if (sInstance == null) {
                    sInstance = new ImageUploadManager();
                }
            }
        }
        return sInstance;
    }

    public void imageUpLoadRequest(Context context, ArrayList<String> localPath, final ImageUploadListener callBack) {
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS);
        OkHttpClient client = clientBuilder.build();

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (String path : localPath) {
            String suffix = checkPictureFormat(path);
            Bitmap bitmap;
            try {
//                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), new File(path));
                builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"file[]\"; filename=\"" + new File(suffix)),
                        RequestBody.create(MEDIA_TYPE_PNG, new File(path)));
            } catch (Exception e) {
                LogUtil.e(TAG, e.toString());
            }
        }

        if(localPath.size()!= 0||localPath != null){
            final MultipartBody requestBody = builder.build();
            //构建请求
            final Request request = new Request.Builder()
                    .header("token","eyJpdiI6IjFyakhLUk5BaFBiaGdYNEowdmNudWc9PSIsInZhbHVlIjoiN0hSZ2h0bThmWDY2NVp0ZmExZlJuQkZzSVR3ZEVJU3JiMWhTYXBOODA1ZjNYNG1kQjhNSmtlVzRXME1UTFdlSmdSWG1RalNMOTlUMUR0NkJJcm5relRlOTFCVVcwSkU0d011bXhOSVhJMU4rRVV4VjRHcnI4Wmphb1hGelJzdEgrU2FZRWRucndZYitjdnpsSGtJRk1QUmtRUHRcL2szTDVwTkh6N3p5RTdYcm81SWczbXJRK3E3SXlpcklBb0IzN0NsSm1NUHkyWE1mRXRUVzZ4QXdOd1UwSERcLzJyRUtueittQUhGYXR2cnpCZDZRemNoT1plSnZld3Rqekh5UmV5MW11RmpYZkxBcTVsNlp4Qk1VdkJaZFdqV2JWN29BaVVPOVRcLys4bFZnM0RiUWFKRXhwU2VnRVwvcVJSMzBnN3VncDVHenVTQ29xVHloKzlyOGRUZFdBWDJKRjFiQnJuelFGb0pcL3cybWlvK3ZGeXFPdzFMNTR0d09xSmY2dEc0bTY5ZEk3OW80UDlBcjhNK2JRd2dCUXdnPT0iLCJtYWMiOiI3NjQ1ZjkxNTA3Zjc3MWEyNTE4NjE0OTM5NzBhOWVmNDBjMmIzN2Y0NTNjNzBhMGI5YTJkYzFmMzg4YzZjM2E3In0")
                    .header("X-Requested-With","XMLHttpRequest")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .url("http://app.iyaa180.com/api/files/upload")//地址
                    .post(requestBody)//添加请求体
                    .build();
            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    callBack.onFailed(e.getMessage());
                    LogUtil.e(TAG, "upload failed :e.getLocalizedMessage() = " + e.getLocalizedMessage());
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                    ImageRepostion fromJson = new Gson().fromJson(response.body().string(),ImageRepostion.class);
                    callBack.onSuccess(fromJson);
                }
            });
        }


    }




    public void VideoUpLoadRequest(Context context, String localPath, final ImageUploadListener callBack) {
        MediaType MEDIA_TYPE_VIDEO = MediaType.parse("multipart/form-data");
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS);
        OkHttpClient client = clientBuilder.build();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"file[]\"; filename=\"" + new File(localPath)),
                RequestBody.create(MEDIA_TYPE_VIDEO, new File(localPath)));
        if(new File(localPath).isAbsolute()){
            final MultipartBody requestBody = builder.build();
            //构建请求
            final Request request = new Request.Builder()
                    .header("token", SharedUtils.INSTANCE.getString("token"))
                    .header("X-Requested-With","XMLHttpRequest")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .url("http://app.iyaa180.com/api/files/upload")//地址
                    .post(requestBody)//添加请求体
                    .build();
            client.newCall(request).enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    callBack.onFailed(e.getMessage());
                    LogUtil.e(TAG, "upload failed :e.getLocalizedMessage() = " + e.getLocalizedMessage());
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                    ImageRepostion fromJson = new Gson().fromJson(response.body().string(),ImageRepostion.class);
                    callBack.onSuccess(fromJson);
                }
            });
        }


    }

    /**
     * 图片类型
     *
     * @param path 图片路径
     * @return 图片类型
     */
    private String checkPictureFormat(String path) {
        Pattern r = Pattern.compile("\\.(jpg|JPG|jpeg|JPEG|png|PNG|gif|GIF|webp|WEBP)$");
        Matcher matcher = r.matcher(path);
        if (matcher.find()) {
            return path.substring(matcher.start());
        } else {
            Klog.Companion.w(TAG, "picture format not found, only support jpg|png|gif|webp");
            return null;
        }
    }

    /**
     * Bitmap转换成byte[]并且进行压缩
     *
     * @param bitmap
     * @return 压缩后的图片
     */
    private byte[] bitmapBytes(Bitmap bitmap, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        if (needRecycle) {
            bitmap.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            Klog.Companion.w("Nixo", e.getMessage());
        }
        return result;
    }

    public interface ImageUploadListener {
        void onSuccess(ImageRepostion response);

        void onFailed(String reason);
    }

}