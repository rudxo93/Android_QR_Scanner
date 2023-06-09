package com.pinekim.android_qr_scannver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.pinekim.android_qr_scannver.adapter.LottoRVAdapter
import com.pinekim.android_qr_scannver.data.model.LottoModel
import com.pinekim.android_qr_scannver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding:ActivityMainBinding? = null
    private val binding get() =mBinding!!

    private var btnQrScanner: Button? = null
    val lottoModel = LottoModel()

    lateinit var rvAdapte: LottoRVAdapter

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
            /*Toast.makeText(this, "Scanned : " + result.contents, Toast.LENGTH_SHORT).show()*/
            val lottoResult = result.contents.substring(28)


            /*val times = lottoResult.substring(0, 4) // 로또 회차*/
            lottoModel.tiems = lottoResult.substring(0, 4) // 로또 회차
            lottoModel.lottoLineNum1 = lottoResult.substring(5, 17) // 첫번째
            lottoModel.lottoLineNum2 = lottoResult.substring(18, 30) // 두번째
            lottoModel.lottoLineNum3 =lottoResult.substring(31, 43) // 세번째
            lottoModel.lottoLineNum4 = lottoResult.substring(44, 56) // 네번째
            lottoModel.lottoLineNum5 = lottoResult.substring(57, 69) // 다섯번쨰
            lottoModel.lottoNumTR = lottoResult.substring(69) // TR Number

           /* for(i: Int in ) {

            }*/

            Toast.makeText(this, lottoResult, Toast.LENGTH_SHORT).show()
            Log.e("gyoungTae", lottoModel.tiems.toString())
            Log.e("gyoungTae", lottoModel.lottoLineNum1.toString())
            Log.e("gyoungTae", lottoModel.lottoLineNum2.toString())
            Log.e("gyoungTae", lottoModel.lottoLineNum3.toString())
            Log.e("gyoungTae", lottoModel.lottoLineNum4.toString())
            Log.e("gyoungTae", lottoModel.lottoLineNum5.toString())
            Log.e("gyoungTae", lottoModel.lottoNumTR.toString())
            // 1번 라인 번호 따로 저장
            Log.e("gyoungTae", lottoModel.lottoLineNum1!!.substring(0, 2))

            // 결과 값 로그 출력
            /*Log.e("gyoungTae", result.contents.toString())*/
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
        initRecyclerViewSetting()

    }

    fun initQrScanner() {
        btnQrScanner?.setOnClickListener(View.OnClickListener {
            onScanBtnClicked()
        })
    }

    fun initRecyclerViewSetting() {
        val rv: RecyclerView = binding.lottoNumRv

        rvAdapte = LottoRVAdapter(this)
        rv.adapter = rvAdapte
        rv.layoutManager =LinearLayoutManager(this)

    }
}