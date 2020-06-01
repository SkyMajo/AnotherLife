package com.nixo.afterworklife.Utils




/**
 * Created by Nixo
 * @Date 2018/09/27
 * @Data Gilde图片加载框架拓展类，同时封装3个框架，并可以使用高斯模糊。（目前Uri的的高斯模糊会报错）
 */


//
//fun setResGosImage(context: Context, imgUrl :Int, imageView : ImageView, sacala : Int,guass : Int) {
//    //   获取需要被模糊的原图bitmap
//    var  scaledBitmap = BitmapFactory.decodeResource(context.resources, imgUrl)
//    //   scaledBitmap为目标图像，10是缩放的倍数（越大模糊效果越高）
////    var blurBitmap = FastBlurUtil.toBlur(scaledBitmap, sacala,guass)
//    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
//    imageView.setImageBitmap(blurBitmap)
//}
//
//fun setURLGosImage(context: Context, imgUrl :String, imageView : ImageView, sacala : Int,guass : Int) {
//    BitmapServce.getBitmapResponseBody(imgUrl).subscribe({
//        Log.e("Nixo0407","${it.byteStream().toString()}")
//        var bitmap = BitmapFactory.decodeStream(it.byteStream())
//        //   scaledBitmap为目标图像，10是缩放的倍数（越大模糊效果越高）
//        var blurBitmap = FastBlurUtil.toBlur(bitmap, sacala,guass)
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP)
//        imageView.setImageBitmap(blurBitmap)
//    },{
//    })

//}
