package no.sample.demoapp

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

class ThreadView : FrameLayout {


    companion object{
        var sharedCounter = 0
    }

    lateinit  var line1 : View
    lateinit  var line2 : View
    lateinit  var line3 : View
    lateinit  var line4 : View

    lateinit  var root : View

    var line:Int = 1
    var selectedThread:Boolean = false
    override fun setSelected(selected: Boolean){
        selectedThread = selected;

        invalidateTextPaintAndMeasurements()
    }

    fun reset(){
        line = 1
        selectedThread = false
        sharedCounter = 0
        invalidateTextPaintAndMeasurements()

    }

    fun runLine(){

        when( line ) {
            1-> line = if (sharedCounter < 4) 2 else 4
            2-> {
                    sharedCounter++
                    line = 3
                }
            3-> line = 1
            else ->
            {
                //exited
            }
        }


        invalidateTextPaintAndMeasurements()

    }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.ThreadView, defStyle, 0
        )

        var name = a.getString(R.styleable.ThreadView_exampleString);
        a.recycle()

        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        root =  inflater.inflate(R.layout.thread_view, this, true)

        line1 = root.findViewById(R.id.line1)
        line2 = root.findViewById(R.id.line2)
        line3 = root.findViewById(R.id.line3)
        line4 = root.findViewById(R.id.line4)

        var threadName = root.findViewById(R.id.threadName) as TextView

        threadName.text = name

        line1.setBackgroundResource ( if(line == 1) R.drawable.selected_line else R.drawable.unselected_line )

        invalidateTextPaintAndMeasurements()
    }

    private fun invalidateTextPaintAndMeasurements() {

        if(root != null){
            root.setBackgroundResource ( if(selectedThread) R.drawable.selected_thread else R.drawable.unselected_thread )

            line1.setBackgroundResource ( if(line == 1) R.drawable.selected_line else R.drawable.unselected_line )
            line2.setBackgroundResource ( if(line == 2) R.drawable.selected_line else R.drawable.unselected_line )
            line3.setBackgroundResource ( if(line == 3) R.drawable.selected_line else R.drawable.unselected_line )
            line4.setBackgroundResource ( if(line == 4) R.drawable.selected_line else R.drawable.unselected_line )
        }
    }


}
