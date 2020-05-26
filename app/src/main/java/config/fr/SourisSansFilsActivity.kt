package config.fr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SourisSansFilsActivity : AppCompatActivity() {
    // on créer les variables:

    /// la BDD


    private var database = Firebase.database

    // La souris 1
    private lateinit var souris1btn: ConstraintLayout
    private lateinit var souris1: TextView
    private lateinit var prix_souris1: TextView
    private lateinit var lien_souris1: String

    //la souris 2
    private lateinit var souris2btn: ConstraintLayout
    private lateinit var souris2: TextView
    private lateinit var prix_souris2: TextView
    private lateinit var lien_souris2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_souris_sans_fils)

        // on défini les variables
        souris1btn = findViewById(R.id.souris1btn)
        souris1 = findViewById(R.id.souris1)
        prix_souris1 = findViewById(R.id.prix_souris1)

        souris2btn = findViewById(R.id.souris2btn)
        souris2 = findViewById(R.id.souris2)
        prix_souris2 = findViewById(R.id.prix_souris2)

        // on lit dans la bdd les infos sur les variables
        read("souris1", souris1)
        read("prix_souris1", prix_souris1)
        readLienSouris1()

        read("souris2", souris2)
        read("prix_souris2", prix_souris2)
        readLienSouris2()

        // si les boutons sont clickés ; ils redirigent vers l'url des produits
        souris1btn.setOnClickListener{
            var url = lien_souris1
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }

        souris2btn.setOnClickListener{
            var url = lien_souris2
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }
    }

    // on lit les infos dans la BDD
    private fun read(child:String, id:TextView){
        database.getReference("sourisSansFils").child(child)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    id.text = value
                }

            })
    }

    // On lit les liens dans la BDD
    private fun readLienSouris1(){
        database.getReference("sourisSansFils").child("lien_souris1")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_souris1 = value
                }

            })
    }

    private fun readLienSouris2(){
        database.getReference("sourisSansFils").child("lien_souris2")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_souris2 = value
                }

            })
    }
}
