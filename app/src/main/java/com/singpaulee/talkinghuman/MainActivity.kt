package com.singpaulee.talkinghuman

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : AppCompatActivity() {

    var textToSpeech: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO 7 Check TTS DATA
        var intentCheckTTS = Intent()
        intentCheckTTS.action = TextToSpeech.Engine.ACTION_CHECK_TTS_DATA
        startActivityForResult(intentCheckTTS, 101)

        //TODO 9 Play Text To Speech when button clicked
        ma_btn_tts.onClick {
            if (ma_edt_teks.text.toString().isBlank()) {
                toast("Helooowww, teksnya kosong bro!!")
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    textToSpeech!!.speak(ma_edt_teks.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
                } else {
                    textToSpeech!!.speak(ma_edt_teks.text.toString(), TextToSpeech.QUEUE_FLUSH, null)
                }

                //QUEUE MODE
                //QUEUE_FLUSH : Ketika sedang memutar tts , kemudian teks diganti dan tekan tombol putar , maka tts yang lama akan otomatis tergantikan
                //QUEUE_ADD : Menjadikan sebuah antrian , menyelesaikan tts yang lama baru memulai yang baru
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            101 -> {
                if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                    Log.d("TEXTTOSPEECH", "Text To Speech data PASS")
                    initializationTTS()
                } else {
                    Log.d("TEXTTOSPEECH", "Text To Speech data is MISSING")
                    //install TTS data
                    var intentInstallTTS = Intent()
                    intentInstallTTS.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
                    startActivity(intentInstallTTS)
                }
            }
        }
    }

    //Method ini digunakan untuk menginisiasi kelas Text To Speech
    fun initializationTTS() {
        //TODO 8 Initialization TextToSpeech
        textToSpeech = TextToSpeech(this, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {
                /*  Atur Engine / bahasa yang akan digunakan
                    Disini saya menggunakan Locale.getDefault
                    Teman2 bisa menggantinya dengan bahasa yang lain */
                textToSpeech!!.language = Locale.getDefault()
            } else {
                toast("Perangkat anda tidak mendukung text to speech")
            }
        })
    }
}
