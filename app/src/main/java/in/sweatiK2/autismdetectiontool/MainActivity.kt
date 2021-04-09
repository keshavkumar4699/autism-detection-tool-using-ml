package `in`.sweatiK2.autismdetectiontool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val num1 = findViewById<EditText>(R.id.tv_first_number)
        val num2 = findViewById<EditText>(R.id.tv_second_number)

        val submit = findViewById<Button>(R.id.bv_submit)
        val result = findViewById<TextView>(R.id.tv_result)

        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        val py = Python.getInstance();
        val pyobj = py.getModule("script")

        submit.setOnClickListener {
            val obj = pyobj.callAttr("main", num1.text.toString(), num2.text.toString())
            result.setText(obj.toString())
        }
    }
}