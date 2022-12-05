package com.example.dynamicbutton

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun Button.setBackgroundColors(color: Int) {
    setBackgroundColor(ContextCompat.getColor(this.context, color))
}

fun List<MyData>.getRandomElement(): MyData = runBlocking(Dispatchers.IO) {
    var myData = MyData()
    while (true) {
        val randomIndex = Random.nextInt(size)
        if (this@getRandomElement[randomIndex].bgColor == R.color.colorWhite) {
            myData = this@getRandomElement[randomIndex]
            break
        }
    }
    return@runBlocking myData
}

fun List<MyData>.getRandomElementWithBlue(): MyData = runBlocking(Dispatchers.IO) {
    val myData = this@getRandomElementWithBlue.getRandomElement()
    myData.bgColor = R.color.colorBlue
    return@runBlocking myData
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Fragment.toast(message: Any) {
    Toast.makeText(requireContext(), message.toString(), Toast.LENGTH_SHORT).show()
}
