package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Function Output getTestDataArray(): ", getTestDataArray().toString())

        val listOfNumbers = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        Log.d("Function Output averageLessThanMedian(): ", averageLessThanMedian(listOfNumbers).toString())

        val collection = listOf(10, 20, 30, 40, 50)
        val textView = getView(1, null, collection, this)
        Log.d("Function Output getView(): ", textView.toString())


        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())

    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
    /*
    private fun getTestDataArray() : List<Int> {
        val testArray = MutableList(10){ Random.nextInt()}
        testArray.sort()
        return testArray
    }
    */
    private fun getTestDataArray (): List<Int> =
         MutableList(10) { Random.nextInt() }.apply { sort () }

    // Return true if average value in list is greater than median value, false otherwise
    /*
    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean {
        val avg = listOfNumbers.average()
        val sortedList = listOfNumbers.sorted()
        val median = if (sortedList.size % 2 == 0)
            (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
        else
            sortedList[sortedList.size / 2]

        return avg < median
    }
    */
    private fun averageLessThanMedian(listOfNumbers: List<Double>) =
        listOfNumbers.run {
            average() < sorted().let { it[it.size / 2].let {mid -> if (size % 2 == 0) (mid + it[(size - 1) /2] )
            else mid}}
        }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    /*
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View {
        val textView: TextView

        if (recycledView != null) {
            textView = recycledView as TextView
        } else {
            textView = TextView(context)
            textView.setPadding(5, 10, 10, 0)
            textView.textSize = 22f
        }

        textView.text = collection[position].toString()

        return textView
    }
    */
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View =
        (recycledView as? TextView ?: TextView(context).apply { // Safe Cast, tries to cast recycledView into Text View
            // if recycledView is already a text view, keep, if null or not TextView, return null
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }).apply {
            text = collection[position].toString()
        }
}