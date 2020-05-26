package config.fr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton


class ConfigMain : AppCompatActivity() {

    //on créer les variables bouton

    private lateinit var config800btn:ImageButton
    private lateinit var config1200btn:ImageButton
    private lateinit var config1600btn:ImageButton
    private lateinit var config2000btn:ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_main)

        //on défini les variables


        config800btn  = findViewById(R.id.config800btn)
        config1200btn  = findViewById(R.id.config1200btn)
        config1600btn  = findViewById(R.id.config1600btn)
        config2000btn  = findViewById(R.id.config2000btn)

        // on  créer les intent menant aux autres activité
        val configa800 = Intent(this, config800::class.java)
        val configa1200 = Intent(this, config1200::class.java)
        val configa1600 = Intent(this, config1600::class.java)
        val configa2000 = Intent(this, config2000::class.java)



        // si les boutons sont cliqué , nous somme redirigé vers les activités
        config800btn.setOnClickListener{
            startActivity(configa800)
        }
        config1200btn.setOnClickListener{
            startActivity(configa1200)
        }
        config1600btn.setOnClickListener{
            startActivity(configa1600)
        }
        config2000btn.setOnClickListener{
            startActivity(configa2000)

        }

    }
}
