package config.fr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class SourisActivity : AppCompatActivity() {
    // on créer les variables

    private lateinit var sourisfilairesbtn:ImageButton
    private lateinit var sourissansfils:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_souris)

        // on défini les variables
        sourisfilairesbtn = findViewById(R.id.btnsouris)
        sourissansfils = findViewById(R.id.btnsourissansfils)



        // si les boutons sont clickés;  ils redirigent vers l'acitivté en question
        sourisfilairesbtn.setOnClickListener{
            startActivity(Intent(this, SourisFilairesActivity::class.java))
        }

        sourissansfils.setOnClickListener{
            startActivity(Intent(this, SourisSansFilsActivity::class.java))
        }
    }
}
