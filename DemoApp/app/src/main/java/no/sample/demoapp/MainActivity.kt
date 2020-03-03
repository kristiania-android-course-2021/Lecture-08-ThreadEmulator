package no.sample.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    lateinit var threads:ArrayList<ThreadView>
    var threadIndex = 0
    var switchContext = false
    var pulseNo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        threads = arrayListOf(thread1, thread2, thread3)

        threads[threadIndex].isSelected = true

        sharedCounter.text = "sharedCounter = ${ThreadView.sharedCounter}"


        contextSwitch.setOnClickListener{

            pulseNo++
            contextSwitch.text = "Clock pulse $pulseNo"
            if(switchContext){
                contextSwitch()
            }else{
                runLine()
            }
            switchContext = !switchContext
        }

        reset.setOnClickListener{

            for ( t in threads  ){
                t.reset()
            }
            pulseNo = 0
            contextSwitch.text = "Clock pulse $pulseNo"

            threadIndex = 0
            switchContext = false
            threads[threadIndex].isSelected = true
            sharedCounter.text = "sharedCounter = ${ThreadView.sharedCounter}"


        }
    }

    private fun runLine() {
        threads[threadIndex].runLine()

        sharedCounter.text = "sharedCounter = ${ThreadView.sharedCounter}"
    }

    private fun contextSwitch() {
        threads[threadIndex].isSelected = false
        threadIndex = ++threadIndex % 3;
        threads[threadIndex].isSelected = true
    }


}
