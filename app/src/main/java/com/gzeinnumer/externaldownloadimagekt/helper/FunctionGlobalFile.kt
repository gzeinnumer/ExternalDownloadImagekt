package com.gzeinnumer.externaldownloadimagekt.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.gzeinnumer.externaldownloadimagekt.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.PrintWriter
import java.util.*


object FunctionGlobalFile {

    private val TAG = "FunctionGlobalFile_"
    //create file
    fun initFile(text: String): Boolean {
        val file = File(FunctionGlobalDir.getStorageCard + FunctionGlobalDir.appFolder + "/File.txt")
        return try {
            val f = FileOutputStream(file)
            val writer = PrintWriter(f)
            writer.println(text + "1")
            writer.println(text + "2")
            writer.println(text + "3")
            writer.flush()
            writer.close()
            f.close()
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun initFileImage(imgUrl: String, filename: String, sendImageTo: ImageView, isNew: Boolean) {
        Picasso.get().load(imgUrl)
            .placeholder(R.drawable.ic_baseline_sync_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap, from: LoadedFrom) {
                    var bitmap = bitmap
                    try {
                        var myDir = File(FunctionGlobalDir.getStorageCard + FunctionGlobalDir.appFolder)
                        if (!myDir.exists()) {
                            myDir.mkdirs()
                        }
                        myDir = if (filename.isNotEmpty()) {
                            File(myDir, "$filename.jpg")
                        } else {
                            File(myDir, Date().toString() + ".jpg")
                        }
                        if (!myDir.exists() || isNew) {
                            //jika isNew true maka foto lama akan dihapus dan diganti dengan yang baru
                            //jika file tidak ditemukan maka file akan dibuat
                            FunctionGlobalDir.myLogD(TAG, "Foto baru disimpan ke penyimpanan")
                            val out = FileOutputStream(myDir)
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                            out.flush()
                            out.close()
                        } else {
                            //jika isNew false maka akan load file lama di penyimpanan
                            FunctionGlobalDir.myLogD(TAG, "Foto lama di load dari penyimpanan")
                            bitmap = BitmapFactory.decodeFile(myDir.absolutePath)
                        }
                        sendImageTo.setImageBitmap(bitmap)
                    } catch (e: Exception) {
                        FunctionGlobalDir.myLogD(TAG, e.message!!)
                    }
                }

                override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {
                    FunctionGlobalDir.myLogD(TAG, e.message!!)
                }
                override fun onPrepareLoad(placeHolderDrawable: Drawable) {}
            })
    }
}
