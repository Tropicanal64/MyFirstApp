package config.fr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {
    // ecran de chargement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        var runnable: Runnable = Runnable(){

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        // chargement de 2secondes
        Handler().postDelayed(runnable, 2000)

    }
}
