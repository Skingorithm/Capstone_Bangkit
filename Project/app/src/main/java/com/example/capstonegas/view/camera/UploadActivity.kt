package com.example.capstonegas.view.camera

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.capstonegas.databinding.ActivityUploadBinding
import com.example.capstonegas.view.loading.LoadingActivity
import com.example.capstonegas.view.main.MainActivity
import java.io.*


class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        startCameraX()
        setupAction()
    }

    private fun setupAction(){
        binding.tidakButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.iyaButton.setOnClickListener {
            uploadImage()
        }
    }

    private fun uploadImage(){
        if(getFile != null){
            val file = reduceFileImage(getFile as File)
            val encodedImage = encodeImage(file)
//            writeToFile(encodedImage, applicationContext)
            val intent = Intent(this, LoadingActivity::class.java)
            intent.putExtra("image", encodedImage)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Please take a picture", Toast.LENGTH_SHORT).show()
        }
    }

    private fun encodeImage(path: File): String {
        var fis: FileInputStream? = null
        try {
            fis = FileInputStream(path)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        val bm = BitmapFactory.decodeStream(fis)
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        //Base64.encode
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    private fun writeToFile(data: String, context: Context) {
        val path = context.filesDir
        val letDirectory = File(path, "Picture Base64")
        letDirectory.mkdirs()
        val file = File(letDirectory, "test.txt")
        file.appendText(data)
        Log.d("File", "File path: $file")
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
            val result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                isBackCamera
            )
            result.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(myFile))
            getFile = myFile
            binding.previewImageView.setImageBitmap(result)
        }
        else if(it.resultCode == GALLERY_RESULT){
            Log.d("UploadActivity", "Gallery")
            val selectedImg = it.data?.getSerializableExtra("image")
            val uri = Uri.parse(selectedImg.toString())
            val myFile = uriToFile(uri, this@UploadActivity)
            getFile = myFile
            binding.previewImageView.setImageURI(uri)
        }
    }

    private fun setupView(){
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    companion object{
        const val CAMERA_X_RESULT = 200
        const val GALLERY_RESULT = 300
    }
}