import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.animation.CycleInterpolator
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.textfield.TextInputLayout


import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


fun Date.toMMddYYYY(): String {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
    return dateFormat.format(this)
}

fun Date.ddMMMYYY(): String {
    val dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH)
    return dateFormat.format(this)
}

fun String.fromMMddYYY(): Date {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)

    try {

        return dateFormat.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return Date()
}

/*
return true for not blank
 */
fun EditText.blankValidation(message: String? = null, et: TextInputLayout): Boolean {
    return if (text.toString().isBlank()) {
        et.isErrorEnabled = true
        et.error = message
        true
    } else {
        et.isErrorEnabled = false
        et.error = null
        false
    }
}

fun TextView.blankValidation(message: String? = null, et: TextView): Boolean {
    return if (text.toString().isBlank()) {
        et.error = message
        false
    } else {
        et.error = null
        true
    }
}


/*
return true for not blank
 */
fun EditText.minValidation(
    minChars: Int,
    minError: String? = null,
    message: String? = null
): Boolean {
    return if (text.toString().isBlank()) {
        error = if (message.isNullOrBlank())
            "Error"
        else
            message
        false
    } else if (text.toString().length < minChars) {
        error = if (minError == null) {
            "Error"
        } else {
            minError
        }
        return false
    } else {
        error = null
        true
    }
}

/*
return true for not blank
 */
fun EditText.emailValidation(): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS

    return if (text.toString().isBlank()) {

        false
    } else if (!pattern.matcher(text.toString()).matches()) {
        error = null
        false
    } else {
        error = null
        true
    }
}

fun AppCompatActivity.replaceFragmentAndClearBackstack(
    fragment: Fragment,
    frameId: Int,
    addToStack: Boolean,
    clearBackStack: Boolean
) {
    Log.d("CurrentFrag", "replaceFragmentAndClearBackstack")

    supportFragmentManager.inTransaction {

        if (clearBackStack && supportFragmentManager.backStackEntryCount > 0) {
            val first = supportFragmentManager.getBackStackEntryAt(0)
            supportFragmentManager.popBackStack(
                first.id,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        if (addToStack) replace(frameId, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
        else
            replace(frameId, fragment, fragment.javaClass.simpleName)
    }
}
/*
return true for not blank
 */
fun EditText.phoneValidation(): Boolean {

    return if (text.toString().isBlank()) {
        error = "Error"
        false
    } else if (text.toString().length != 8) {
        error = "Error"
        false
    } else {
        error = null
        true
    }
}

fun EditText.passwordValidation(): Boolean {
    val pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[!@#\$&*])(?=.*[0-9])(?=.*[a-z]).{8,}$")

    return if (text.toString().isBlank()) {
        error = "Error"
        return false
    } else if (!pattern.matcher(text.toString()).matches()) {
        error = "Error"
        false
    } else {
        error = null
        true
    }


}


fun EditText.emailValidation(et: TextInputLayout): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS

    return if (text.toString().isBlank()) {
        et.error = "This cannot be left blank"
        false
    } else if (!pattern.matcher(text.toString()).matches()) {
        et.isErrorEnabled = true
        et.error = "Email address is invalid."
        false
    } else if (text.toString().length > 31) {
        et.isErrorEnabled = true
        et.error = "Email address is invalid."
        false
    } else {
        et.isErrorEnabled = false
        et.error = null
        true
    }
}

fun EditText.passwordValidation(et: TextInputLayout): Boolean {
    val pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[!@#\$&*])(?=.*[0-9])(?=.*[a-z]).{8,20}$")

    return if (text.toString().isBlank()) {
//        error = "Error"
        return false
    } else if (!pattern.matcher(text.toString()).matches()) {
        et.isErrorEnabled = true
        et.error = "Password does not comply with rules."
        false
    }
//    else if (text.toString().length>20)
//    {
//        et.isErrorEnabled = true
//        et.error = "Password does not comply with rules."
//        false
//    }
    else {
        et.isErrorEnabled = false
        et.error = null
        true
    }


}

fun EditText.specialchaectervalidation(et: TextInputLayout): Boolean {
    val pattern = Pattern.compile("^[a-zA-Z ]*\$")

    return if (text.toString().isBlank()) {
//        error = "Error"
        return false
    } else if (!pattern.matcher(text.toString()).matches()) {
        et.isErrorEnabled = true
        et.error = "Name must only contain alphanumeric characters."
        false
    } else if (text.toString().length > 50) {
        et.isErrorEnabled = true
        et.error = "Name must only contain alphanumeric characters."
        false
    } else {
        et.isErrorEnabled = false
        et.error = null
        true
    }


}

fun EditText.uaephonenumbervalidation(): Boolean {
    val pattern1 = Pattern.compile("^9715[0-9]{8}\$") // uae
//    val pattern2 = Pattern.compile("^[3567]\\d{7}\$") // qat
//    val pattern3 = Pattern.compile("^[3]\\d{7}\$") // beh

    return if (pattern1.matcher(text.toString()).matches()
    ) {

        return true
    } else {

        false
    }


}

fun EditText.qatarphonenumbervalidation(): Boolean {

    val pattern2 = Pattern.compile("^974[3567][0-9]{7}\$")


    return if (pattern2.matcher(text.toString()).matches()
    ) {

        return true
    } else {

        false
    }


}

fun EditText.behrainphonenumbervalidation(): Boolean {

    val pattern3 = Pattern.compile("^973[3][0-9]{7}\$")

    return if (pattern3.matcher(text.toString()).matches()
    ) {

        return true
    } else {

        false
    }


}



fun Activity.uncheckAllItems(menu: Menu) {

    menu.setGroupCheckable(0, true, false)
    for (i in 0 until menu.size()) {
        menu.getItem(i).isChecked = false
    }
    menu.setGroupCheckable(0, true, true)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}







// sample usage
//            showCustomDialog(Consts.Dialouge_Error,showCancellBtn = false, messgae = "Invalid Email", okcallback = {
//                Toast.makeText(this, "okcallback clicked", Toast.LENGTH_LONG).show()
//            }, cancelcallback = {
//                Toast.makeText(this, "cancelcallback clicked", Toast.LENGTH_LONG).show()
//            })


fun View.gone() {
    visibility = GONE
}

fun View.inVisible() {
    visibility = INVISIBLE
}

fun View.visible() {
    visibility = VISIBLE
}



fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}
//  usage example
//  supportFragmentManager.inTransaction {
//    add(R.id.frameLayoutContent, fragment)
//  }

//Single Choice
//You can pass a literal string array too:
//
//val myItems = listOf("Hello", "World")
//
//MaterialDialog(this).show {
//    listItemsSingleChoice(items = myItems)
//}
//If you want an option to be selected when the dialog opens, you can pass an initialSelection index):
//
//MaterialDialog(this).show {
//    listItemsSingleChoice(R.array.my_items, initialSelection = 1)
//}
//To get item selection events, just append a lambda:
//
//MaterialDialog(this).show {
//    listItemsSingleChoice(R.array.my_items) { dialog, index, text ->
//        // Invoked when the user selects an item
//    }
//}
//Without action buttons, the selection callback is invoked immediately when the user taps an item. If you add a positive action button...
//
//MaterialDialog(this).show {
//    listItemsSingleChoice(R.array.my_items) { dialog, index, text ->
//        // Invoked when the user selects an item
//    }
//    positiveButton(R.string.select)
//}
//...then the callback isn't invoked until the user selects an item and taps the positive action button. You can override that behavior using the waitForPositiveButton argument.
//
//An added bonus, you can disable items from being selected/unselected:
//
//val indices = intArrayOf(0, 2)
//
//MaterialDialog(this).show {
//    listItemsSingleChoice(R.array.my_items, disabledIndices = indices)
//}
//There are methods you can use in a built dialog to modify checked states:
//
//val dialog: MaterialDialog = // ...
//
//    dialog.checkItem(index)
//
//dialog.uncheckItem(index)
//
//dialog.toggleItemChecked(index)
//
//val checked: Boolean = dialog.isItemChecked(index)

//Checkbox Prompts
//Checkbox prompts can be used together with any other dialog type, it gets shown in the same view which shows the action buttons.
//MaterialDialog(this).show {
//    checkBoxPrompt(R.string.your_label) { checked ->
//        // Check box was checked or unchecked
//    }
//}
//You can pass a literal string for the label too:
//
//MaterialDialog(this).show {
//    checkBoxPrompt(text = "Hello, World")
//}
//You can also append a lambda which gets invoked when the checkbox is checked or unchecked:
//
//MaterialDialog(this).show {
//    checkBoxPrompt(text = "Hello, World") { checked -> }
//}
//If you only care about the checkbox state when the positive action button is pressed:
//
//MaterialDialog(this).show {
//    checkBoxPrompt(R.string.your_label)
//    positiveButton(R.string.button_text) { dialog ->
//        val isChecked = dialog.isCheckPromptChecked()
//        // do something
//    }
//}
fun View.Shake() {
    animate()
        .translationX(-15f).translationX(15f)
        .setDuration(30)
        .setInterpolator(CycleInterpolator((150 / 30).toFloat()))
        .setDuration(150)
        .start()
}