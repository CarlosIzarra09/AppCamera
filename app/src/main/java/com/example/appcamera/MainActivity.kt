package com.example.appcamera

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btCamera = findViewById<Button>(R.id.bt_cameraRequest)

        btCamera.setOnClickListener{
            checkCameraPermission()
        }
    }

    private fun checkCameraPermission() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_DENIED)
        {
            requestCameraPermission()
        }
        else{
            Toast.makeText(this,"Ya tienes permiso a la camara",Toast.LENGTH_LONG).show()
        }
    }

    private fun requestCameraPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA))
        {
            Toast.makeText(this,"Rechazaste la solicitud de permiso,Habilitalo manualmente",
                Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this,"Acepta el permiso a la camara",
                Toast.LENGTH_LONG).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if(grantResults.isNotEmpty()&&grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"Se autorizó permiso a la cámara",
                        Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this,"Rechazaste la solicitud de permiso,Habilitalo manualmente",
                        Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }
}