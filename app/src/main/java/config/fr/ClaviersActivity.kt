package config.fr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ClaviersActivity : AppCompatActivity() {

    //Menu clavier qui donne deux choix ; les claviers mécaniques et les membranes

    // creation des varibles qui serviront de bouton
    private lateinit var btnmecanique:ImageButton
    private lateinit var btnmembrane:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_claviers)

        // on defini les boutons
        btnmecanique = findViewById(R.id.btnclaviersmecaniques)

        btnmembrane = findViewById(R.id.btnclaviersmembranes)

        // si le bouton est cliqué il redirige vers la prochaine activité
        btnmecanique.setOnClickListener{
            startActivity(Intent(this, MecaActivity::class.java))
        }

        btnmembrane.setOnClickListener{
            startActivity(Intent(this, MembActivity::class.java))

        }
    }
}
