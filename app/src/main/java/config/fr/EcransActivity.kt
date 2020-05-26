package config.fr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class EcransActivity : AppCompatActivity() {

    // on créer les variables:

    // BDD
    private var database = Firebase.database


    // progessBar
    private lateinit var progressbar:ProgressBar
    // Ecran1
    private lateinit var ecran1btn:LinearLayout
    private lateinit var ecran1:TextView
    private lateinit var prix_ecran1:TextView
    private lateinit var lien_ecran1:String
    // Ecran2
    private lateinit var ecran2btn:LinearLayout
    private lateinit var ecran2:TextView
    private lateinit var prix_ecran2:TextView
    private lateinit var lien_ecran2:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrans)

        // on défini les variables

        progressbar = findViewById(R.id.progressbar)
        // on rend visible la pogressbar
        progressbar.visibility = VISIBLE

        ecran1btn = findViewById(R.id.ecran1btn)
        ecran1 = findViewById(R.id.ecran1)
        prix_ecran1 = findViewById(R.id.prix_ecran1)

        ecran2btn = findViewById(R.id.ecran2btn)
        ecran2 = findViewById(R.id.ecran2)
        prix_ecran2 = findViewById(R.id.prix_ecran2)

        // on lit les infos des écrans sur la BDD
        readLienEcran1()
        read("ecran1", ecran1)
        read("prix_ecran1", prix_ecran1)

        readLienEcran2()
        read("ecran2", ecran2)
        read("prix_ecran2", prix_ecran2)


        // si les boutons sont clickés alors on ouvre les url des porduits
        ecran1btn.setOnClickListener{
            var url = lien_ecran1
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }
        ecran2btn.setOnClickListener{
            var url = lien_ecran2
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)

        }

    }
    // on lit les infos dans la bdd
    private fun read(child: String, id: TextView){
        database.getReference("ecrans").child(child)
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    id.text = value

                }

            })

    }

    // on lit les liens dans la BDD
    private fun readLienEcran1(){
        database.getReference("ecrans").child("lien_ecran1")
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_ecran1 = value
                }

            })
    }



    private fun readLienEcran2(){
        database.getReference("ecrans").child("lien_ecran2")
            .addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_ecran2 = value
                    progressbar.visibility = GONE
                }

            })
    }
}
