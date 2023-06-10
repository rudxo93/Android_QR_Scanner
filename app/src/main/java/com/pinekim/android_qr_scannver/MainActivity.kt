package com.pinekim.android_qr_scannver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.pinekim.android_qr_scannver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding:ActivityMainBinding? = null
    private val binding get() =mBinding!!

    private var btnQrScanner: Button? = null

    private lateinit var qrScan: IntentIntegrator

    // 스캐너 설정
    private val barcodeLauncher = registerForActivityResult(
        ScanContract()
    ) { result: ScanIntentResult ->
        // result : 스캔된 결과

        // 내용이 없다면
        if (result.contents == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            // 내용이 있다면

            // Toast 메세지 출력
            Toast.makeText(this, "Scanned : " + result.contents, Toast.LENGTH_SHORT).show()

            // 결과 값 로그 출력
            Log.e("gyoungTae", result.contents.toString())
        }
    }
    // Scan Click
    fun onScanBtnClicked() {
        // Launch(스캔 실행)
        val option = ScanOptions()
        option.setOrientationLocked(false) // ScanOption의 Orientation Lock를 해제하기 위해서
        barcodeLauncher.launch(option) // Orientation Lock를 해제한 상태로 실행
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnQrScanner = binding.btnQrScan

        initQrScanner()

    }

    fun initQrScanner() {
        btnQrScanner?.setOnClickListener(View.OnClickListener {
            onScanBtnClicked()
        })
    }
}