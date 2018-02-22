# NonResiableImageViewAndroid
Demo app on how to handle `windowSoftInputMode` when you have resizing views in your app.



### 1. When you have ImageView and the bottom EditText and you want EditText to appear above keyboard without resizing ImageView
* Specify windowSoftInputMode as `adjustNothing|stateHidden` : This will not resize our view
* Handle the EditText positioning on keyboard open to do so add `[KeyboardHeightProvider]` [1]
* On keyboard open reposition the bottom EditText to above the Keyboard.
<a href="url"><img src="https://github.com/nieldeokar/NonResizableImageViewAndroid/blob/master/demo/normal-phone-not-fixed.gif?raw=true" align="left" height="800" width="500" ></a>


Normal phone Resizable             |  Normal phone non resizable
:---------------------------------:|:--------------------------------:
![Normal phone Resizable ](https://github.com/nieldeokar/NonResizableImageViewAndroid/blob/master/demo/normal-phone-not-fixed.gif?raw=true)  |  ![Normal phone non Resizable ](https://github.com/nieldeokar/NonResizableImageViewAndroid/blob/master/demo/normal-phone-fixed.gif?raw=true)

#### Libraries used
* AppCompat
* [Glide][2]






[1]: https://github.com/siebeprojects/samples-keyboardheight
[2]: https://github.com/bumptech/glide
