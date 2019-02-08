package com.nileshdeokar.nonresizableimageview

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , KeyboardHeightProvider.KeyboardHeightObserver  {

    private lateinit var keyboardHeightProvider : KeyboardHeightProvider

    private var initialY: Float = 0.toFloat()

    private var initialheightOfEditText: Int = 0

    private var wasNegative = false

    private var negativePixelHeight = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val mActionBar = supportActionBar
        if (mActionBar != null) {
            mActionBar.setDisplayUseLogoEnabled(true)
            mActionBar.setDisplayHomeAsUpEnabled(true)
            mActionBar.setHomeButtonEnabled(true)
            mActionBar.title = getString(R.string.app_name)
        }

        keyboardHeightProvider = KeyboardHeightProvider(this)
        bottomEditor.post {
            initialY = bottomEditor.y; initialheightOfEditText = edit_url.height
        }
        parentActivityView.post {
            keyboardHeightProvider?.start()
        }

        btn_send.setOnClickListener({ loadImage() })

    }

    private fun loadImage() {
        val url = edit_url.text.toString().trim()
        if(url.isNotEmpty()) {
            Glide.with(this)
                    .load(url)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .into(imgPreview)
        }else{
            Toast.makeText(this,getString(R.string.error_empty_url),Toast.LENGTH_LONG).show()
        }
        edit_url.setText("")
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(parentActivityView.windowToken, 0)
    }

    override fun onKeyboardHeightChanged(height: Int, orientation: Int) {
        var y = initialY
        var settledHeight = height
        settledHeight = Math.abs(settledHeight)
        // In case of 18:9
        if(height < 0){
            wasNegative = true
            negativePixelHeight = settledHeight
        }
        if (settledHeight == 0) { // keyboard is closed

            if(edit_url.height != initialheightOfEditText){
                y += (initialheightOfEditText - edit_url.height)
            }
        } else {
            y = if (wasNegative && settledHeight != negativePixelHeight){
                initialY  - (settledHeight + negativePixelHeight )
            }else if (wasNegative){
                initialY
            } else{
                initialY - settledHeight
            }

            if(edit_url.height != initialheightOfEditText){
                y -= (edit_url.height - initialheightOfEditText)
            }

        }
        bottomEditor.y = y
        bottomEditor.requestLayout()
    }

    public override fun onPause() {
        super.onPause()
        keyboardHeightProvider?.setKeyboardHeightObserver(null)
    }

    public override fun onResume() {
        super.onResume()
        keyboardHeightProvider?.setKeyboardHeightObserver(this)
    }

    public override fun onDestroy() {
        super.onDestroy()
        keyboardHeightProvider?.close()
    }
}
