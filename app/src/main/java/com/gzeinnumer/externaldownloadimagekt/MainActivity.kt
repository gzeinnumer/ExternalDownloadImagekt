package com.gzeinnumer.externaldownloadimagekt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gzeinnumer.externaldownloadimagekt.helper.FunctionGlobalFile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity_"

    var msg = "externaldownloadimagekt\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = TAG

        tv.text = msg
        val imgUrl = "https://avatars3.githubusercontent.com/u/45892408?s=460&u=94158c6479290600dcc39bc0a52c74e4971320fc&v=4"

        //pilih 1 atau 2
        //1. jika isNew true maka file lama akan dihapus dan diganti dengan yang baru
        FunctionGlobalFile.initFileImage(imgUrl, "file name.jpg", img, false)
        //2. jika isNew false maka akan otomatis load file dan disimpan, tapi jika file tidak ada, maka akan diload baru dan disimpan
        FunctionGlobalFile.initFileImage(imgUrl, "file name.jpg", img, false)
    }
}