package com.nileshdeokar.nonresizableimageview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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

//        keyboardHeightProvider = KeyboardHeightProvider(this)
//        bottomEditor.post { initialY = bottomEditor.y; initialheightOfEditText = edit_message.height }
//        parentActivityView.post { keyboardHeightProvider?.start() }
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

            if(edit_message.height != initialheightOfEditText){
                y += (initialheightOfEditText - edit_message.height)
            }
        } else {
            y = if (wasNegative && settledHeight != negativePixelHeight){
                initialY  - (settledHeight + negativePixelHeight )
            }else if (wasNegative){
                initialY
            } else{
                initialY - settledHeight
            }

            if(edit_message.height != initialheightOfEditText){
                y -= (edit_message.height - initialheightOfEditText)
            }

        }
        bottomEditor.y = y
        bottomEditor.requestLayout()
    }

    public override fun onPause() {
        super.onPause()
//        keyboardHeightProvider?.setKeyboardHeightObserver(null)
    }

    public override fun onResume() {
        super.onResume()
//        keyboardHeightProvider?.setKeyboardHeightObserver(this)
    }

    public override fun onDestroy() {
        super.onDestroy()
//        keyboardHeightProvider?.close()
    }
}
