# NonResiableImageViewAndroid
Demo app on how to handle `windowSoftInputMode` when you have resizing views in your app.



### When you have ImageView and the bottom EditText and you want EditText to appear above keyboard without resizing ImageView
* Specify windowSoftInputMode as `adjustNothing|stateHidden` : This will not resize our view
* Handle the EditText positioning on keyboard open to do so add [KeyboardHeightProvider] [1]
* On keyboard open reposition the bottom EditText to above the Keyboard.


-




Normal phone Resizable             |  Normal phone non resizable
:---------------------------------:|:--------------------------------:
![Normal phone Resizable ](https://github.com/nieldeokar/NonResizableImageViewAndroid/blob/master/demo/normal-phone-not-fixed.gif?raw=true)  |  ![Normal phone non Resizable ](https://github.com/nieldeokar/NonResizableImageViewAndroid/blob/master/demo/normal-phone-fixed.gif?raw=true)


### I have included solution for 18:9 ratio phones as well.
* In 18:9 phones bottom navigation creates an issue in calculating keyboard height.
* All we need to do is handle the negative value of navigation bar in these phones.

-


Samsung S8 Resizable             |  Samsung S8 non resizable
:---------------------------------:|:--------------------------------:
![Samsung S8 Resizable ](https://github.com/nieldeokar/NonResizableImageViewAndroid/blob/master/demo/samsung-s8-not-fixed.gif?raw=true)  |  ![Samsung S8 non Resizable ](https://github.com/nieldeokar/NonResizableImageViewAndroid/blob/master/demo/samsung-s8-fixed-gif.gif?raw=true)



-



#### Libraries used
* AppCompat
* [Glide][2]






[1]: https://github.com/siebeprojects/samples-keyboardheight
[2]: https://github.com/bumptech/glide
