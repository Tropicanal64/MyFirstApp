package config.fr

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MembActivity : AppCompatActivity() {
    // on créer les variables:
    // BDD

    private var database = Firebase.database

    // le clavier1
    private lateinit var clavier1btn: LinearLayout
    private lateinit var clavier1: TextView
    private lateinit var prix_clavier1: TextView
    private lateinit var lien_clavier1:String

    // le clavier2
    private lateinit var clavier2btn : LinearLayout
    private lateinit var clavier2: TextView
    private lateinit var prix_clavier2: TextView
    private lateinit var lien_clavier2:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memb)

        // on défini les variables
        clavier1 = findViewById(R.id.clavier1)
        prix_clavier1 = findViewById(R.id.prix_clavier1)
        clavier1btn = findViewById(R.id.clavier1btn)

        clavier2 = findViewById(R.id.clavier2)
        prix_clavier2 = findViewById(R.id.prix_clavier2)
        clavier2btn = findViewById(R.id.clavier2btn)

        //Lecture base de donnée
        read("clavier1",  clavier1)
        read("prix_clavier1", prix_clavier1)
        readLienClavier1()

        read("clavier2", clavier2)
        read("prix_clavier2", prix_clavier2)
        readLienClavier2()

        // si les boutons sont clickés nous ouvrons les url des produits
        clavier1btn.setOnClickListener{
            var url = lien_clavier1
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }

        clavier2btn.setOnClickListener{
            var url = lien_clavier2
            var i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(i)
        }
    }

    // on lit les infos des produit dans la BDD
    private fun read(child:String, id:TextView){
        database.getReference("claviersMembranes").child(child)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    id.text = value
                }

            })
    }

    // On lit les liens des produits dans la BDD
    private fun readLienClavier1(){
        database.getReference("claviersMembranes").child("lien_clavier1")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_clavier1 = value
                }

            })
    }

    private fun readLienClavier2(){
        database.getReference("claviersMembranes").child("lien_clavier2")
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var value = p0.value.toString()
                    lien_clavier2 = value
                }

            })
    }
}
