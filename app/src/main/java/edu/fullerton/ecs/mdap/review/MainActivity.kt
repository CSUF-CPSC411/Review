package edu.fullerton.ecs.mdap.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import edu.fullerton.ecs.mdap.review.databinding.ActivityMainBinding

const val KEY_ERROR = "error"

/**
 * Application for storing reivews on a CrossWalk   volunteer.
 */
class MainActivity : AppCompatActivity() {
    // Store the view binding as a property so it is accessible to any method
    lateinit var binding: ActivityMainBinding

    /**
     *  Screen is created and prepared for viewing. The method may be called again when the
     *  application restarts or when the screen is rotated.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // savedInstanceState is only null the first time the View is created.
        /*
        if (savedInstanceState != null) {
            /**
             * Restore the error message from the saved data. Bundles behave
             * like a map where we use the KEY_ERROR constant (equal to "error")
             * to retrieve the previously stored error message.
             */
            binding.errorMsg.setText(savedInstanceState.getString(KEY_ERROR))
        }*/

        binding.submit.setOnClickListener {
            var errorMessage = ""
            // Display an error if there are no inputs
            if (binding.name.text.length == 0 ||
                binding.rating.text.length == 0 ||
                binding.review.text.length == 0) {
                errorMessage = "Please provide the name of the volunteer, your rating, and review."
            } else {
                // Display an error when the rating is not between 1 and 10 inclusive.
                val rating = binding.rating.text.toString().toInt()
                if (rating < 1 || rating > 10) {
                    errorMessage = "Please provide a valid rating."
                }
            }
            // There are no errors if the errorMessage variable remains empty ("")
            if (errorMessage.length == 0) {
                val toastMessage = Toast.makeText(this,
                                              "Thank you for submitting your review.",
                                                   Toast.LENGTH_SHORT)
                toastMessage.show()
            }
            // Replace the TextView with errorMessage's value, which may or may not be empty.
            binding.errorMsg.setText(errorMessage)
        }
    }

    /**
     * Called as the fragment begins to stop. An application may stop when it crashes, it is
     * forced to close or when the screen rotates and it is recreated.
     */
    /* override fun onSaveInstanceState(outState: Bundle) {
        // Save view hierarchy
        super.onSaveInstanceState(outState)
        // Bundles act like maps that store values for a given key.
        // We store the error message String using a constant to avoid mistyping errors.
        outState.putString(KEY_ERROR, binding.errorMsg.text.toString())
    } */
}