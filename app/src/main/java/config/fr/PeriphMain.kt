package config.fr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class PeriphMain : AppCompatActivity() {
    // on créer les variables:
    // bouton ecran
    private lateinit var ecranbtn:ImageButton
    // bouton clavier
    private lateinit var clavierbtn:ImageButton
    // bouton souris
    private lateinit var sourisbtn:ImageButton
    //bouton casque
    private lateinit var casquebtn:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_periph_main)

        // on défini les variables
        ecranbtn = findViewById(R.id.ecranbtn)
        clavierbtn = findViewById(R.id.clavierbtn)
        sourisbtn = findViewById(R.id.sourisbtn)
        casquebtn = findViewById(R.id.casquebtn)

        // si les boutons sont clickés ils dirige vers l'activité en question
        casquebtn.setOnClickListener{
            startActivity(Intent(this, CasquesActivity::class.java))
        }

        ecranbtn.setOnClickListener{
            startActivity(Intent(this, EcransActivity::class.java))
        }

        clavierbtn.setOnClickListener{
            startActivity(Intent(this, ClaviersActivity::class.java))
        }

        sourisbtn.setOnClickListener{
            startActivity(Intent(this, SourisActivity::class.java))
        }
    }
}
